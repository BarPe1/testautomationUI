# testautomationUI
This is a test automation framework for handling Web UI tests

Tools:
- JetBrains IntelliJ IDE
- Java 17 or higher
- Maven
- Notepad ++
- Chromium (WebDriver for Chrome browser)
- Dependencies from mvn repos (https://mvnrepository.com/)
- JUnit
- TestNG
- F12 developer tool (click F12 button on your keyboard when Chrome is opened)
- Playwright (as dependency in pom.xml)
- Selenium  (as dependency in pom.xml)

Check version of Chrome
 - Open PowerShell and run the command: (Get-Item "C:\Program Files\Google\Chrome\Application\chrome.exe").VersionInfo.ProductVersion

Download Chromium WebDriver:
- For current browser version https://googlechromelabs.github.io/chrome-for-testing/
- Old versions: https://storage.googleapis.com/chrome-for-testing-public/xxx.x.xxxx.xx/win64/chromedriver-win64.zip , NOTE: change 'x' to IP address from your current browser by json endpoint: https://googlechromelabs.github.io/chrome-for-testing/known-good-versions-with-downloads.json (use ctrl + f to find IP address)

Create chromeDriver folder in resources (in the project tree)
Unzip download Chromium
Copy/paste to chromeDriver folder

# Test Automation Framework: Selenium WebDriver with Java & Cucumber

This repository contains a test automation framework built using **Java**, **Selenium WebDriver**, and **Cucumber (Gherkin)**. It supports both **JUnit** and **TestNG** test runners.

---

## 📌 Technical Stack & Versions

*   **Java**: 17 or higher
*   **Selenium WebDriver**: 4.21.0
*   **Cucumber (Gherkin)**: 7.18.0
*   **Build Tool**: Maven
*   **Test Runners**: JUnit 5 / TestNG

---

## 📖 What is Cucumber & Gherkin?

**Cucumber** is a test automation tool that supports **Behavior-Driven Development (BDD)**. It reads executable specifications written in plain text and validates that the software does what those specifications say.

**Gherkin** is the simple, structured language used by Cucumber to write test scenarios. It uses a specific syntax so that non-technical stakeholders can understand the test cases.

### Key Gherkin Keywords:
*   **Feature**: Describes the high-level software feature being tested.
*   **Scenario**: Represents a specific test case or user flow.
*   **Given**: Preconditions or the initial state of the system.
*   **When**: The specific action taken by the user.
*   **Then**: The expected outcome or verification step.
*   **And / But**: Used to add multiple conditions to Given, When, or Then steps.

---

## 🛠️ How to Install & First Setup

### Prerequisites
1.  Download and install **Java Development Kit (JDK 17+)**.
2.  Download and install **Apache Maven**.
3.  Set up environment variables for **JAVA_HOME** and **MAVEN_HOME**.
4.  Use an IDE like **IntelliJ IDEA** or **Eclipse**.

### IDE Plugins Installation
For full support, install the following plugins in your IDE:
*   **Cucumber for Java**
*   **Gherkin**

### First Project Setup
1.  Clone this repository to your local machine:
    ```bash
    git clone <repository-url>
    ```
2.  Open the project in your IDE as a **Maven Project**.
3.  Let Maven download all dependencies automatically (or run `mvn clean install`).

---

## 🚀 How to Run Tests

### Running via Command Line (Maven)

**Run all tests:**
```bash
mvn clean test
```

**Run specific tags (Cucumber Tags):**
```bash
mvn clean test -Dcucumber.filter.tags="@SmokeTest"
```

**Run tests using a specific TestNG XML file:**
```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

---

## 🧪 Test Runners & Annotations

This framework is flexible and can be executed using either **JUnit** or **TestNG**.

### 1. JUnit 5 Implementation

JUnit 5 uses a specific runner class to execute Cucumber features.

#### Example Runner:
```java
package runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.core.options.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@SelectClasspathResource("features")
@IncludeClassNamePatterns(".*")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, html:target/cucumber-reports/junit-report.html")
public class JUnitTestRunner {
}
```

#### Important JUnit Annotations:
*   **@Suite**: Runs multiple test classes as a test suite.
*   **@SelectClasspathResource**: Points to the location of the `.feature` files.
*   **@BeforeEach**: Runs before every individual test method (standard JUnit lifecycle).
*   **@AfterEach**: Runs after every individual test method.

---

### 2. TestNG Implementation

TestNG allows for parallel execution and advanced test configurations via an XML file.

#### Example Runner:
```java
package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepdefinitions",
        plugin = {"pretty", "html:target/cucumber-reports/testng-report.html"}
)
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
    
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
```

#### Important TestNG Annotations:
*   **@CucumberOptions**: Configures feature file locations, step definitions, and reporting plugins.
*   **@BeforeMethod**: Executes before each test method.
*   **@AfterMethod**: Executes after each test method.
*   **@DataProvider**: Used to feed data into tests (configured above to enable **parallel execution**).

---

## 📝 Cucumber Hooks (Step Definitions)

Hooks are blocks of code that run before or after each scenario. Do not confuse them with JUnit/TestNG annotations.

```java
package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void setUp() {
        // Code to initialize WebDriver before each Cucumber scenario
        System.out.println("Starting Browser...");
    }

    @After
    public void tearDown() {
        // Code to quit WebDriver after each Cucumber scenario
        System.out.println("Closing Browser...");
    }
}
```
