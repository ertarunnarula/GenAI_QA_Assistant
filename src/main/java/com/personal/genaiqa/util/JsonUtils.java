package com.personal.genaiqa.util;

public class JsonUtils {

    public static String cleanJsonBlock(String input) {
        if (input == null) {
            return "";
        }
        return input.replace("```json", "")
                    .replace("```", "")
                    .trim();
    }
}
