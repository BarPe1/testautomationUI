package edu.pg.ui.test;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

@Slf4j
public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

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

    public LoginPage clickLoginButton() {
        log.info("Click on the Login Button");
        click(loginButton);
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

    public String getLogoTextFromWebsiteHeader() throws IOException {
        log.info("Get logo text from the header from the current website{}", logo.getText());
        takeScreenshot("logo.png");
        return logo.getText();
    }

    public String getLogoTextForSmartLocator() {
        WebElement element = driver.findElement(with(By.className("login_logo")).above(username)); //Smart selector
        return String.valueOf(element.getText());
    }

    //Basic Shift-Left
    protected boolean isDisplayed(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (Exception e) {
            log.error("Get message from catch exception {}", e.getMessage());
            return false;
        }
    }

}
