#### GenAI QA Assistant (Java + Selenium + TestNG)

An AI-powered QA automation framework that **generates and executes test cases from natural language requirements** using Generative AI.

This project bridges the gap between **manual test design and automated execution** by integrating **LLMs (OpenAI), Selenium WebDriver, and TestNG** into a unified workflow.

-------------------------------------------------------------------------------

## Key Features

* AI Test Case Generation

  * Generate functional, negative, and edge test cases from plain English requirements
  
* Dynamic Test Execution**

  * Convert AI-generated test cases into **TestNG executions**
 
* Selenium Web Automation

  * Page Object Model (POM) based UI automation framework
  
* Spring Boot REST API

  * Expose endpoints for test generation and export
  
* CSV Export

  * Save generated test cases for reuse in QA workflows
  
* Data-Driven Testing**

  * Execute multiple scenarios via TestNG DataProvider
  
* Extensible Architecture

  * Easily plug in RAG, self-healing locators, or BDD support

-------------------------------------------------------------------------------

## Architecture Overview

```text
User Requirement
       ↓
Spring Boot API (GenAI Layer)
       ↓
OpenAI / LLM
       ↓
Structured Test Cases (JSON)
       ↓
TestNG DataProvider
       ↓
Selenium Execution Engine
       ↓
Test Results + Reports
```

-------------------------------------------------------------------------------

## Project Structure

```text
genaiqa/
│
├── src/main/java/com/personal/genaiqa/
│   ├── controller/        # REST APIs
│   ├── service/           # AI + business logic
│   ├── dto/               # Data models
│   └── util/              # Utilities
│
├── src/test/java/com/personal/genaiqa/ui/
│   ├── base/              # DriverFactory, BaseTest
│   ├── pages/             # Page Object Model classes
│   ├── tests/             # TestNG tests (static + dynamic)
│   ├── executor/          # AI step execution engine
│   └── client/            # API client for fetching AI test cases
│
├── src/test/resources/
│   └── testng.xml
│
├── outputs/               # Generated CSV files
└── README.md
```

-------------------------------------------------------------------------------

## Tech Stack

* **Java 17**
* **Spring Boot**
* **Selenium WebDriver 4**
* **TestNG**
* **OpenAI API**
* **Maven**
* **WebDriverManager**

-------------------------------------------------------------------------------

#### Getting Started

## 1. Prerequisites

* Java 17+
* Maven
* Google Chrome
* OpenAI API Key

-------------------------------------------------------------------------------

## 2. Clone the repository

```bash
git clone https://github.com/your-username/genaiqa.git
cd genaiqa
```

-------------------------------------------------------------------------------

## 3. Configure API Key

Edit:

```text
src/main/resources/application.properties
```

```properties
openai.api.key=YOUR_OPENAI_API_KEY
```

-------------------------------------------------------------------------------

## 4. Start the backend

```bash
mvn spring-boot:run
```

-------------------------------------------------------------------------------

## 5. Run tests

```bash
mvn test -DbaseUrl=https://your-app-url.com
```

-------------------------------------------------------------------------------

## 🧪 API Usage

### Generate Test Cases

```http
POST /api/ai/generate
```

#### Request

```json
{
  "requirement": "User should login with valid credentials",
  "generateCode": true
}
```

#### Response

```json
{
  "testCases": [...],
  "automationCode": "..."
}
```

-------------------------------------------------------------------------------

### Generate & Export CSV

```http
POST /api/ai/generate-and-export
```

## Output:

```text
outputs/generated_test_cases_<timestamp>.csv
```

-------------------------------------------------------------------------------

## Dynamic AI Test Execution

This project supports executing AI-generated test cases dynamically.

### How it works:

1. TestNG DataProvider calls AI API
2. Parses JSON response into `TestCaseDto`
3. Executes each test case via `TestStepExecutor`

```java
@Test(dataProvider = "aiGeneratedTestCases")
public void runAiGeneratedTestCase(TestCaseDto testCase) {
    executor.execute(testCase);
}
```

-------------------------------------------------------------------------------

## Example AI-Generated Test Case

```json
{
  "testCaseId": "TC_001",
  "title": "Verify login with valid credentials",
  "steps": [
    "Navigate to login page",
    "Enter valid email",
    "Enter valid password",
    "Click login button",
    "Verify dashboard is displayed"
  ]
}
```

-------------------------------------------------------------------------------

## ️Current Limitations

* AI steps are interpreted using keyword matching
* Not all natural language steps are executable
* Requires consistent test case structure

-------------------------------------------------------------------------------

## Future Enhancements

* Structured action-based execution (no keyword parsing)
* Extent Reports integration
* BDD (Cucumber) support
* RAG-based test case reuse
* Self-healing locators
* AI-based failure analysis
* CI/CD integration (GitHub Actions)

-------------------------------------------------------------------------------

## Why this project?

This project demonstrates:

* Real-world **GenAI + Automation integration**
* Strong **QA domain + AI engineering bridge**
* Practical implementation of:

  * LLM APIs
  * Test automation frameworks
  * Data-driven execution

-------------------------------------------------------------------------------

####‍Author

** Tarun Narula **

* QA Automation Lead Engineer → GenAI Engineer Transition
* Focus: AI-driven testing, intelligent automation, and applied ML systems

-------------------------------------------------------------------------------

## Contribute

Pull requests are welcome!
Feel free to fork and improve the project.

-------------------------------------------------------------------------------

## Final Note

This is not just a demo project.

It is a foundation for intelligent QA systems where:

* test cases are generated by AI
* execution is automated
* and feedback loops improve test quality over time

-------------------------------------------------------------------------------
