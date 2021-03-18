import base.*;
import constants.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageobject.*;

import java.util.concurrent.TimeUnit;

public class GittiGidiyorTest extends AbstractTest {
    private Logger logger = LoggerFactory.getLogger(GittiGidiyorTest.class);
    HomePage homePage;
    LoginPage loginPage;

    @Before
    public void setup()
    {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testHome() throws InterruptedException {
        homePage.moveToSignButton();
        homePage.clickSignLink();
        loginPage.login(Constants.TEST_USER, Constants.TEST_PASSWORD);
        String userName = homePage.getUserName();
        Assert.assertEquals(userName,"aysenurkaya408353");
        homePage.findProduct(Constants.COMPUTER);
        homePage.addBasket();
        homePage.deleteBasket();

    }
}
