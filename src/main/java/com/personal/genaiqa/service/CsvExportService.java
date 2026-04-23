package com.personal.genaiqa.service;

import com.personal.genaiqa.dto.TestCaseDto;
import com.opencsv.CSVWriter;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class CsvExportService {

    public String export(List<TestCaseDto> testCases, String filePath) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            writer.writeNext(new String[]{
                "Test Case ID", "Title", "Preconditions", "Steps", "Expected Result", "Test Type"
            });

            for (TestCaseDto testCase : testCases) {
                writer.writeNext(new String[]{
                    testCase.getTestCaseId(),
                    testCase.getTitle(),
                    testCase.getPreconditions(),
                    String.join(" | ", testCase.getSteps()),
                    testCase.getExpectedResult(),
                    testCase.getTestType()
                });
            }
        }
        return filePath;
    }
}
