package edu.pg.ui.test.hooks;

import edu.pg.ui.utils.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CucumberHooks {

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        // 1. Odcięcie systemowego magazynu haseł (kluczowe na Linuxie)
        options.addArguments("--password-store=basic");

        // 2. Wyłączenie dodatkowych funkcji społecznościowych i onboardingów Google
        options.addArguments("--disable-features=AutofillServerCommunication");
        options.addArguments("--disable-features=PasswordManagerOnboarding");
        options.addArguments("--disable-popup-blocking");

        // 3. Eksperymentalne preferencje profilu użytkownika
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        prefs.put("profile.password_manager_leak_detection", false);
        prefs.put("password_leak_detection", false);

        options.setExperimentalOption("prefs", prefs);

        WebDriver driver = new ChromeDriver(options);
        DriverManager.setDriver(driver);
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }

}
