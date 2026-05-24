package edu.pg.ui.test.run;

import edu.pg.ui.test.assertion.AssertUnitClass;
import edu.pg.ui.pages.BasePage;
import edu.pg.ui.pages.LoginPage;
import edu.pg.ui.test.assertion.NewAssertForPage;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;

@Slf4j
public class TestRun extends BasePage {

    public LoginPage loginPage;
    public AssertUnitClass assertUnitClass;

    @BeforeMethod(alwaysRun = true)
    public void initTestNG() {
        // 1. Uruchamiamy konfigurację przeglądarki z BasePage
        super.setUp();

        // 2. SZTYWNE ZABEZPIECZENIE: Pobieramy referencję do drivera z klasy bazowej
        // Słowo kluczowe 'this.driver' musi wskazywać na ten sam obiekt co 'super.driver'
        this.driver = super.getDriverInstance();
        if (this.driver == null) {
            // Jeśli getDriverInstance() nie istnieje, przypisz po prostu bezpośrednio:
            this.driver = super.driver;
        }

        // 3. Inicjalizujemy obiekty stron żywym, działającym sterownikiem
        this.loginPage = new LoginPage(this.driver);
        this.assertUnitClass = new AssertUnitClass(this.driver);
        PageFactory.initElements(this.driver, loginPage);
    }

    @AfterMethod(alwaysRun = true)
    public void cleanTestNG() {
        // Pamiętaj o zamykaniu okna po każdym teście TestNG!
        super.tearDown();
    }

    @Test
    public void shouldOpenGoogleWebsite() {
        driver.get(GOOGLE);

        String title = driver.getTitle();
        log.info("This Google website has title: {}", title);

        Assert.assertNotNull(title);
        Assert.assertTrue(true, String.valueOf(title.contains("Google")));
    }

    @Test(groups = "regression")
    @Parameters({"user", "pass"})
    public void shouldShowErrorForLockedOutUser(@Optional("locked_out_user") String user,
                                                @Optional("secret_sauce") String pass) throws Exception {
        driver.get("https://saucedemo.com");

        // Korzysta z nowej metody login
        loginPage.loginPage(user, pass);
        takeScreenshot("login_page_loaded");

        // Korzysta z getErrorElement()
        NewAssertForPage.assertVisible(loginPage.getErrorMessage(), "Error alert should be visible");

        // Korzysta z getErrorMessageText()
        String expectedMsg = "Epic sadface: Sorry, this user has been locked out.";
        NewAssertForPage.assertTextEquals(loginPage.getErrorMessageText(), expectedMsg, "Wrong error message!");
    }

    @Test(groups = "regression")
    @Parameters({"user", "pass"})
    public void shouldShowErrorForCredentials(@Optional("user") String user,
                                              @Optional("secret_sauce") String pass) throws Exception {
        driver.get("https://saucedemo.com");
        takeScreenshot("login_page_has_loaded");
        loginPage.loginPage(user, pass);
        log.debug(" === Debug login page === ");
        takeScreenshot("credentials_has_entered");
        log.info(" === User has been logged === ");
        String expectedMsg = "Epic sadface: Username and password do not match any user in this service";
        NewAssertForPage.assertTextEquals(loginPage.getErrorMessageText(), expectedMsg, "Wrong error message!");
    }


}
