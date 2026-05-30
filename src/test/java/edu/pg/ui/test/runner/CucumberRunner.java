package edu.pg.ui.test.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        // 1. Ścieżka do folderu z Twoimi plikami .feature
        features = "src/test/resources/features",

        // 2. Wskazanie pakietów z krokami i hookami
        glue = {
                "edu.pg.ui.test"
        },

        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-report.html"
        },

        tags = "@smoke",

        monochrome = true
)
public class CucumberRunner extends AbstractTestNGCucumberTests {

}