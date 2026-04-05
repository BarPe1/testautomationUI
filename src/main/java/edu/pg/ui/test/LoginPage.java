package edu.pg.ui.test;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Slf4j
public class LoginPage extends BasePage {

//    //Page Object Model for alphanumeric fields
//    private static final By LOGO = By.xpath("//*[@id='root']/div/div[@class='login_logo']");
//    private static final By USERNAME = By.xpath("//*[@id='user-name']");
//    private static final By PASSWORD = By.xpath("//*[@id='password']");
//
//    //Page Object Model for buttons
//    private static final By LOGIN_BUTTON = By.xpath("//*[@id='login-button']");

    @FindBy(id = "user-name") private WebElement username;
    @FindBy(className = "login_logo") private WebElement logo;
    @FindBy(xpath = "//*[@id='login-button']") private WebElement loginButton;
    @FindBy(css = "#password") private WebElement password;
    @FindBy(css = "[data-test='error']") private WebElement errorElement;

    public LoginPage(WebDriver driver) {
    }

    public LoginPage clickLoginButton() {
        log.info("Click on the Login Button");
        loginButton.click();
        return this;
    }

    public LoginPage enterPasswordToSauceDemoWebsiteOnTestEnv(String passwordText) {
        log.info("Enter password to the css selector");
        password.sendKeys(passwordText);
        return this;
    }

    public LoginPage enterUsername(String usernameText) {
        log.info("Enter username text to the id selector");
        username.sendKeys(usernameText);
        return this;
    }

    public String getLogoTextFromWebsiteHeader() {
        log.info("Get logo text from the header from the current website{}", logo.getText());
        return logo.getText();
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return errorElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public WebElement getErrorElement() {
        return errorElement;
    }

    // Pobiera tekst błędu
    public String getErrorMessageText() {
        return errorElement.getText();
    }

    public void login(String user, String pass) {
        enterUsername(user);
        enterPasswordToSauceDemoWebsiteOnTestEnv(pass);
        clickLoginButton();
    }

}
