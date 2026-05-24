package edu.pg.ui.test.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.time.Duration;

@Slf4j
public class CucumberHooks {

    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    @Before
    public void setUp() {
        // 1. Zdefiniowanie Twoich ścieżek lokalnych
        String chromiumPath = "/home/teamsharq/IdeaProjects/testautomationUI/src/test/resources/chromedriver/chromedriver";
        String chromePath = "/usr/bin/google-chrome";

        // 2. Konfiguracja usługi ChromeDriverService (wskazanie pliku wykonywalnego drivera)
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(chromiumPath))
                .build();

        // 3. Konfiguracja ChromeOptions (wskazanie lokalizacji samej przeglądarki Chrome)
        ChromeOptions options = new ChromeOptions();
        options.setBinary(new File(chromePath));

        // Opcjonalne flagi dla środowisk Linux (zapobiegają awariom pamięci współdzielonej)
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        // 4. Inicjalizacja sterownika z użyciem przygotowanej usługi oraz opcji
        WebDriver driver = new ChromeDriver(service, options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driverThreadLocal.set(driver);
    }

    @After
    public void tearDown() {
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
        }
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }
}
