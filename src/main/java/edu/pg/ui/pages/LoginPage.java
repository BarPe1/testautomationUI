package edu.pg.ui.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Slf4j
public class LoginPage extends BasePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // 1. Definiowanie elementów za pomocą adnotacji PageFactory
    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = ".error-message-container")
    private WebElement errorContainer;

    // 2. Konstruktor – MUSI inicjalizować elementy przekazanym driverem
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        PageFactory.initElements(driver, this);
    }

    // 3. Bezpieczne metody akcji
    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickLogin() {
        log.info("Click login button");
        loginButton.click();
    }

    public WebElement getErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(errorContainer));
        return errorContainer;
    }

    public String getErrorMessageText() {
        wait.until(ExpectedConditions.visibilityOf(errorContainer));
        return errorContainer.getText();
    }

    public boolean isErrorMessageDisplayed() {
        try {
            wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf(errorContainer));

            return errorContainer.isDisplayed();
        } catch (NoSuchElementException | org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    public LoginPage loginPage(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        return this;
    }

}
