package edu.pg.ui.test.runner;

/**
 * Main and single test runner for the automation framework.
 * It scans feature files and glues step definitions together using JUnit 4 and JUnit 5 engines.
 */
//JUnit 4 solution
//@RunWith(Cucumber.class)
//@CucumberOptions(
//        // Path to your Gherkin feature files
//        features = "src/test/resources/features",
//
//        // Packages containing step definitions and hooks
//        glue = {"stepdefinitions", "hooks"},
//
//        // Plugins for console output and HTML reporting
//        plugin = {
//                "pretty",
//                "html:target/cucumber-reports/cucumber-runner-report.html",
//                "json:target/cucumber-reports/cucumber.json"
//        },
//
//        // Fails the build if there are undefined or missing steps
//        monochrome = true
//)

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.core.options.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@SelectClasspathResource("features") // Wskaż folder w src/test/resources/
@IncludeClassNamePatterns(".*")     // Wykonaj wszystkie pasujące testy
// Odpowiednik dawnego 'glue'
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "stepdefinitions,hooks")
// Odpowiednik dawnego 'plugin' (generowanie raportu HTML)
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, html:target/cucumber-reports/cucumber-report.html")
public class CucumberRunner {
    // This class remains completely empty.
    // It serves solely as an entry point for Cucumber execution.
}