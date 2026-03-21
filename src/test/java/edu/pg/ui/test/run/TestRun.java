package edu.pg.ui.test.run;

import edu.pg.ui.test.BasePage;
import edu.pg.ui.test.LoginPage;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

@Slf4j
public class TestRun extends BasePage {
    public LoginPage loginPage;

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

}

