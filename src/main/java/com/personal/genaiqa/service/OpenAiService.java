package com.personal.genaiqa.service;

import com.personal.genaiqa.dto.TestCaseDto;
import com.personal.genaiqa.util.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class OpenAiService {

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.model}")
    private String model;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final PromptService promptService;

    public OpenAiService(PromptService promptService) {
        this.promptService = promptService;
    }

    public List<TestCaseDto> generateTestCases(String requirement) throws IOException, InterruptedException {
        String prompt = promptService.buildTestCasePrompt(requirement);
        String responseContent = callOpenAi(prompt);

        String cleaned = JsonUtils.cleanJsonBlock(responseContent);
        JsonNode root = objectMapper.readTree(cleaned);
        JsonNode testCasesNode = root.get("testCases");

        return objectMapper.readerForListOf(TestCaseDto.class).readValue(testCasesNode);
    }

    public String generateAutomationCode(String requirement) throws IOException, InterruptedException {
        String prompt = promptService.buildAutomationCodePrompt(requirement);
        return callOpenAi(prompt);
    }

    private String callOpenAi(String prompt) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        String requestBody = objectMapper.writeValueAsString(
            objectMapper.createObjectNode()
                .put("model", model)
                .set("messages", objectMapper.createArrayNode()
                    .add(objectMapper.createObjectNode()
                        .put("role", "system")
                        .put("content", "You are a helpful software engineering assistant."))
                    .add(objectMapper.createObjectNode()
                        .put("role", "user")
                        .put("content", prompt)))
        );

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.openai.com/v1/chat/completions"))
            .header("Content-Type", "application/json")
            .header("Authorization", "Bearer " + apiKey)
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() >= 400) {
            throw new RuntimeException("OpenAI API error: " + response.body());
        }

        JsonNode root = objectMapper.readTree(response.body());
        return root.get("choices").get(0).get("message").get("content").asText();
    }
}