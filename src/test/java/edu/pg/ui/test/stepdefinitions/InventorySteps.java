package edu.pg.ui.test.stepdefinitions;

import edu.pg.ui.pages.InventoryPage;
import edu.pg.ui.utils.DriverManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;

@Slf4j
public class InventorySteps {

    private InventoryPage inventoryPage;

    public InventorySteps() {
    }

    private InventoryPage getInventoryPage() {
        if (inventoryPage == null) {
            inventoryPage = new InventoryPage(DriverManager.getDriver());
        }
        return inventoryPage;
    }

    @Then("The page title should display {string}")
    public void thePageTitleShouldDisplay(String expectedTitle) {
        Assertions.assertEquals(expectedTitle, getInventoryPage().getPageTitle());
    }

    @When("The user adds the product {string} to the cart")
    public void theUserWantsToAddTheProdToTheCart(String text) {
        log.info(" ## WHEN The user adds the product {} to the cart", text);
        getInventoryPage().clickAddToCartButton();
    }

    @Then("The cart is active for the added item with {int}")
    public void theCartShouldBeActiveForAddedItem(int number) throws Exception {
        log.info(" ## THEN The cart is active for the added item with {}", number);
        log.info(" ### The Gherkin value from Then statement is {}", number);
        Assertions.assertEquals(number, getInventoryPage().getActiveCartBadge(), "There is an active badge displayed");
    }

}
