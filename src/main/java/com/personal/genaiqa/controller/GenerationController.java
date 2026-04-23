package com.personal.genaiqa.controller;

import com.personal.genaiqa.dto.GenerationResponse;
import com.personal.genaiqa.dto.RequirementRequest;
import com.personal.genaiqa.dto.TestCaseDto;
import com.personal.genaiqa.service.CsvExportService;
import com.personal.genaiqa.service.OpenAiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ai")
public class GenerationController {

    private final OpenAiService openAiService;
    private final CsvExportService csvExportService;

    public GenerationController(OpenAiService openAiService, CsvExportService csvExportService) {
        this.openAiService = openAiService;
        this.csvExportService = csvExportService;
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Application is running");
    }

    @PostMapping("/generate")
    public ResponseEntity<GenerationResponse> generate(@RequestBody RequirementRequest request) {
        try {
            List<TestCaseDto> testCases = openAiService.generateTestCases(request.getRequirement());

            String automationCode = null;
            if (request.isGenerateCode()) {
                automationCode = openAiService.generateAutomationCode(request.getRequirement());
            }

            GenerationResponse response = new GenerationResponse();
            response.setRequirement(request.getRequirement());
            response.setTestCases(testCases);
            response.setAutomationCode(automationCode);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate AI response: " + e.getMessage(), e);
        }
    }

    @PostMapping("/generate-and-export")
    public ResponseEntity<String> generateAndExport(@RequestBody RequirementRequest request) {
        try {
            List<TestCaseDto> testCases = openAiService.generateTestCases(request.getRequirement());
            String filePath = "generated_test_cases.csv";
            csvExportService.export(testCases, filePath);
            return ResponseEntity.ok("CSV exported successfully to: " + filePath);
        } catch (Exception e) {
            throw new RuntimeException("Failed to export CSV: " + e.getMessage(), e);
        }
    }
}