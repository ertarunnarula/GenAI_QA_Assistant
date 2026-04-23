package com.personal.genaiqa.dto;

public class RequirementRequest {
    private String requirement;
    private boolean generateCode;
    private String language = "java";

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public boolean isGenerateCode() {
        return generateCode;
    }

    public void setGenerateCode(boolean generateCode) {
        this.generateCode = generateCode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}