package edu.pg.ui.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public static final String SAUCE_DEMO_URL = "https://saucedemo.com";
    public static final String GOOGLE = "https://google.com";

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, driver);
    }

    public BasePage() {

    }

    @BeforeMethod(alwaysRun = true, groups = {"smoke", "regression"})
    public void setUp() {
        String chromiumPath = "/home/teamsharq/IdeaProjects/test_automation_ui/testautomationUI/src/test/resources/chromedriver/chromedriver";
        String chromePath = "/usr/bin/google-chrome";

        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(chromiumPath))
                .build();

        ChromeOptions options = new ChromeOptions();
        options.setBinary(chromePath);
        options.addArguments("--start-maximize");

        driver = new ChromeDriver(service, options);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
