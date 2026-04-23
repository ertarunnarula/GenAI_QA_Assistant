package com.personal.genaiqa.service;

import org.springframework.stereotype.Service;

@Service
public class PromptService {

    public String buildTestCasePrompt(String requirement) {
        return """
            You are a senior QA automation architect.

            Generate:
            1. Functional test cases
            2. Negative test cases
            3. Edge test cases

            Return STRICT JSON only:
            {
              "testCases": [
                {
                  "testCaseId": "TC_001",
                  "title": "string",
                  "preconditions": "string",
                  "steps": ["step1", "step2"],
                  "expectedResult": "string",
                  "testType": "functional"
                }
              ]
            }

            Requirement:
            %s
            """.formatted(requirement);
    }

    public String buildAutomationCodePrompt(String requirement) {
        return """
            You are a senior QA automation engineer.

            Generate a complete Java Selenium + TestNG + Page Object Model starter framework
            for the requirement below.

            Requirement:
            %s

            Instructions:
            - Use Java
            - Use Selenium WebDriver
            - Use TestNG
            - Use Page Object Model
            - Include BaseTest, page classes, and one test class
            - Use placeholder locators
            - Return code only
            """.formatted(requirement);
    }
}