package edu.pg.ui.test;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;

import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.File;
import java.time.Duration;
import java.io.IOException;

import java.text.SimpleDateFormat;

import java.util.Date;

@Slf4j
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

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        String chromiumPath = "/home/teamsharq/IdeaProjects/testautomationUI/src/test/resources/chromedriver/chromedriver";
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

    public void takeScreenshot(String testName) {
        // 1. Tworzymy format daty dla unikalnej nazwy pliku
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // 2. Rzutujemy drivera na interfejs TakesScreenshot
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String path = System.getProperty("user.dir") + "/screenshots/" + testName + "_" + timestamp + ".png";

        try {
            // 4. Kopiujemy plik z pamięci tymczasowej na dysk
            FileUtils.copyFile(srcFile, new File(path));
            log.info("Screenshot saved to: {}", path);
        } catch (IOException e) {
            if (log.isErrorEnabled()) {
                log.error("""
                        Failed to save screenshot: {}""",
                        e.getMessage());
            }
        }
    }
}
