package edu.pg.ui.test.run;

import edu.pg.ui.test.AssertUnitClass;
import edu.pg.ui.test.BasePage;
import edu.pg.ui.test.LoginPage;
import edu.pg.ui.test.NewAssertForPage;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Slf4j
public class TestRun extends BasePage {
    public LoginPage loginPage;
    public AssertUnitClass assertUnitClass;

    @BeforeMethod(alwaysRun = true)
    @Override
    public void setUp() {
        super.setUp(); // <--- TO MUSI BYĆ PIERWSZE

        // Inicjalizacja Twoich obiektów stron przy użyciu drivera z BasePage
        this.loginPage = new LoginPage(driver);
        this.assertUnitClass = new AssertUnitClass(driver);
        PageFactory.initElements(driver, loginPage);
    }

    @Test
    public void shouldOpenGoogleWebsite() {
        driver.get(GOOGLE);

        String title = driver.getTitle();
        log.info("This Google website has title: {}", title);

        Assert.assertNotNull(title);
        Assert.assertTrue(true, String.valueOf(title.contains("Google")));
    }

    @Test
    public void userTriesToLoginToSauceDemo() {
        driver.get(SAUCE_DEMO_URL);
        loginPage.getLogoTextFromWebsiteHeader();

    }

    @Test(groups = "regression")
    @Parameters({"user", "pass"})
    public void shouldShowErrorForLockedOutUser(String user, String pass) throws Exception {
        driver.get("https://saucedemo.com");

        // Korzysta z nowej metody login
        loginPage.login(user, pass);

        // Korzysta z getErrorElement()
        NewAssertForPage.assertVisible(loginPage.getErrorElement(), "Error alert should be visible");

        // Korzysta z getErrorMessageText()
        String expectedMsg = "Epic sadface: Username and password do not match any user in this service";
        NewAssertForPage.assertTextEquals(loginPage.getErrorMessageText(), expectedMsg, "Wrong error message!");
    }

    @Test(groups = "regression")
    @Parameters({"user", "pass"})
    public void shouldShowErrorForCredentials(@Optional("locked_out_user") String user,
                                                @Optional("secret_sauce") String pass) {
        driver.get("https://saucedemo.com");
        loginPage.login(user, pass);
    }

}
