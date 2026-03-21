package edu.pg.ui.test.run;

import edu.pg.ui.test.BasePage;
import edu.pg.ui.test.LoginPage;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

@Slf4j
public class TestRun extends BasePage {
//
//    //@Test
//    public void shouldOpenGoogleWebsite() {
//        driver.get(GOOGLE);
//
//        String title = driver.getTitle();
//        log.info("This Google website has title: {}", title);
//
//        Assert.assertNotNull(title);
//        Assert.assertTrue(true, String.valueOf(title.contains("Google")));
//    }
//


    public LoginPage loginPage;

    @BeforeMethod(alwaysRun = true)
    @Override
    public void setUp() {
        super.setUp(); 
       
        loginPage = new LoginPage(driver);
     
        PageFactory.initElements(driver, loginPage);
    }

    @Test(groups = "smoke")
    @Parameters({"user", "pass"})
    public void userTriesToLoginToSauceDemo(String user, String pass) throws Exception {
      
        driver.get(SAUCE_DEMO_URL);

        loginPage.enterUsername(user);
        TimeUnit.SECONDS.sleep(2);
        loginPage.enterPasswordToSauceDemoWebsiteOnTestEnv(pass);
        TimeUnit.SECONDS.sleep(2);
        loginPage.clickLoginButton();
        TimeUnit.SECONDS.sleep(2);
    }

}
