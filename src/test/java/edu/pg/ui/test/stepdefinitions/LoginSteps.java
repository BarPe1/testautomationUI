package edu.pg.ui.test.stepdefinitions;

import edu.pg.ui.pages.InventoryPage;
import edu.pg.ui.pages.LoginPage;
import edu.pg.ui.test.hooks.CucumberHooks;
import edu.pg.ui.utils.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import static edu.pg.ui.pages.BasePage.SAUCE_DEMO_URL;

public class LoginSteps {

    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CucumberHooks cucumberHooks;

    private LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage(DriverManager.getDriver());
        }
        return loginPage;
    }

    @Given("The user opens the SauceDemo login page")
    public void theUserOpensTheLoginPage() {
        this.driver = edu.pg.ui.utils.DriverManager.getDriver();

        if (this.driver == null) {
            throw new IllegalStateException("Cucumber Webdriver instance is null! Check if CucumberHooks @Before is executing correctly.");
        }

        this.loginPage = new LoginPage(this.driver);
        this.inventoryPage = new InventoryPage(this.driver);

        this.driver.get(SAUCE_DEMO_URL);
    }

    @When("The user enters username {string} and password {string}")
    public void theUserEntersUsernameAnd_password(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @When("The user clicks the login button")
    public void theUserClicksTheLoginButton() {
        loginPage.clickLogin();
    }

    @Then("The user should be redirected to the inventory page")
    public void theUserShouldBeRedirectedToTheInventoryPage() {
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        Assertions.assertEquals(expectedUrl, inventoryPage.getCurrentUrl());
    }

    @Then("The page header should display {string}")
    public void thePageHeaderShouldDisplay(String expectedHeader) {
        Assertions.assertEquals(expectedHeader, inventoryPage.getHeaderTitle());
    }

    @Then("An error message containing {string} should be visible")
    public void anErrorMessageContainingShouldBeVisible(String expectedError) {
        String actualError = loginPage.getErrorMessageText();
        Assertions.assertTrue(actualError.contains(expectedError),
                "Expected error message mismatch. Got: " + actualError);
    }
}
