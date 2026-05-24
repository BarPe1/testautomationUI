package edu.pg.ui.test.stepdefinitions;

import edu.pg.ui.pages.InventoryPage;
import edu.pg.ui.pages.LoginPage;
import edu.pg.ui.test.hooks.CucumberHooks;
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

    @Given("The user opens the SauceDemo login page")
    public void the_user_opens_the_saucedemo_login_page() {
        // Wywołanie statyczne działa idealnie bez potrzeby dziedziczenia klasy Hooks!
        this.driver = CucumberHooks.getDriver();

        if (this.driver == null) {
            throw new IllegalStateException("Cucumber Webdriver instance is null! Check if CucumberHooks @Before is executing correctly.");
        }

        // Inicjalizujemy obiekty stron żywym sterownikiem
        this.loginPage = new LoginPage(this.driver);
        this.inventoryPage = new InventoryPage(this.driver); // <--- Zabezpieczenie (inicjalizacja przed użyciem w krokach Then)

        this.driver.get(SAUCE_DEMO_URL);
    }

    @When("The user enters username {string} and password {string}")
    public void the_user_enters_username_and_password(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @When("The user clicks the login button")
    public void the_user_clicks_the_login_button() {
        loginPage.clickLogin();
    }

    @Then("The user should be redirected to the inventory page")
    public void the_user_should_be_redirected_to_the_inventory_page() {
        String expectedUrl = "https://www.saucedemo.com/inventory.html"; // Upewnij się czy url ma www lub nie, zgodnie z aplikacją
        Assertions.assertEquals(expectedUrl, inventoryPage.getCurrentUrl());
    }

    @Then("The page header should display {string}")
    public void the_page_header_should_display(String expectedHeader) {
        Assertions.assertEquals(expectedHeader, inventoryPage.getHeaderTitle());
    }

    @Then("An error message containing {string} should be visible")
    public void an_error_message_containing_should_be_visible(String expectedError) {
        String actualError = loginPage.getErrorMessageText();
        Assertions.assertTrue(actualError.contains(expectedError),
                "Expected error message mismatch. Got: " + actualError);
    }
}
