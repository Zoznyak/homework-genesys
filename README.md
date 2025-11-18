# homework-genesys

## Prerequisites

- **Java 11** (JDK 11 or higher)
- **Maven** (Maven Wrapper is included in the project)
- **Chrome browser** (for UI tests)

## Project Structure

### Feature Files
Feature files contain Gherkin scenarios describing test cases in a human-readable format. They are located in `src/test/resources/features/` directory and tagged for selective execution (e.g., `@ui`, `@api`, `@guru`, `@html`).

### Workflows
Workflows (`wokrflows/`) are the highest level of test organization. **Each feature file has a corresponding workflow class** that orchestrates the test execution. Workflows are responsible for:
- **Implementing Gherkin steps** with `@Given`, `@When`, `@Then` annotations
- **Orchestrating step definitions** to execute test scenarios
- **Managing test data loading** and preparation
- **Coordinating complex test flows** that span multiple step definition classes

Workflows act as the bridge between feature files and step definitions, calling the appropriate step definition methods to fulfill the test scenario requirements.

### Step Definitions
Step definitions (`steps/`) are reusable components that encapsulate **atomic page operations grouped into logical units**. Each step definition class:
- Contains methods that perform **elementary operations** on page objects
- Groups related actions into **cohesive, logical units** (e.g., login operations)
- Provides building blocks that workflows can compose into complete test scenarios
- Handles direct interaction with page objects

### Page Objects
Pages (`pages/`) are used to encapsulate page-specific elements and actions:
- **Base class**: `PageObject.java` provides common functionality for all pages
- **Page classes**: Organized by application (e.g., `sauce/`, `guru/`, `htmleditor/`)
- Each page object contains web element locators and page-specific methods

### Configuration
Configuration is managed through properties files:
- **`resources/config.properties`**: Project specific settings (URLs, timeouts)
- **`resources/texts.properties`**: Text labels from the tested applications (e.g., button texts)
- **System properties**: Can override configuration at runtime (e.g., `-Dbrowser.headless=false`)

Key configurable properties:
- `browser.headless`: Run tests in headless mode (default: `false`)
- `browser.timeout.seconds`: WebDriver timeout in seconds (default: `8`)
- `cucumber.tags`: Tags for selective test execution

### Soft Assertions
The framework uses `AssertionContext` with AssertJ's `SoftAssertions` to collect multiple assertion failures in a single test execution. This allows all validations to run even if some fail, providing comprehensive test results.

Usage:
- Assertions are collected during test execution
- All collected assertions are evaluated at the end of the scenario
- If any assertion fails, the test fails with all error details

## Running Tests

### Using Maven Wrapper

The project includes Maven Wrapper, so you don't need to install Maven separately. Use `mvnw` (Linux/Mac) or `mvnw.cmd` (Windows).

### Basic Execution

Run all tests:
```bash
mvnw clean verify -Dcucumber.tags="@ui or @api"
```

### Running Specific Test Suites

Run API tests only:
```bash
mvnw clean verify -Dtest=ApiTestRunner -Dcucumber.tags="@api"
```

Run UI tests only:
```bash
mvnw clean verify -Dtest=UiTestRunner -Dcucumber.tags="@ui"
```

### Browser Modes

Run tests in headless mode (default):
```bash
mvnw clean verify -Dtest=UiTestRunner -Dcucumber.tags="@ui" -Dbrowser.headless=true
```

### Custom Timeout

Set custom WebDriver timeout:
```bash
mvnw clean verify -Dtest=UiTestRunner -Dcucumber.tags="@ui"  -Dbrowser.timeout.seconds=10
```

## Test Reports

Test execution generates the following reports:

- **Cucumber HTML Report**: Human-readable HTML report with scenario results, located in `target/cucumber-html-reports/`
- **Surefire Reports**: JUnit-style XML reports in `target/surefire-reports/`
- **Console Output**: Real-time test execution logs displayed in the console
- **Log Files**: Detailed execution logs stored in `logs/test-run.log`
