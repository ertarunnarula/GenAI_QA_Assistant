package com.personal.genaiqa.dto;

import java.util.List;

public class GenerationResponse {
    private String requirement;
    private List<TestCaseDto> testCases;
    private String automationCode;

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public List<TestCaseDto> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TestCaseDto> testCases) {
        this.testCases = testCases;
    }

    public String getAutomationCode() {
        return automationCode;
    }

    public void setAutomationCode(String automationCode) {
        this.automationCode = automationCode;
    }
}