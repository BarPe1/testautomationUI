package edu.pg.ui.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Slf4j
public class InventoryPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators for PageFactory design pattern
    @FindBy(xpath = "//span[@class='title']")
    private WebElement pageTitle;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addToCartButton;

    //Page Object Model approach
    private final By shoppingCartBadgeActive = By.xpath("//span[@class='shopping_cart_badge']");
    private final By appPageTitle = By.xpath("//span[@class='title']");
    private final By appHeaderTitle = By.className("app_logo");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(appPageTitle)).getText();
    }

    public String getHeaderTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(appHeaderTitle)).getText();
    }

    public void clickAddToCartButton() {
        log.info("Click on Add to Cart button");
        addToCartButton.click();
    }

    public Integer getActiveCartBadge() throws Exception {
        TimeUnit.SECONDS.sleep(1);
        WebElement element = driver.findElement(shoppingCartBadgeActive);
        return Integer.valueOf(element.getText());
    }
}
