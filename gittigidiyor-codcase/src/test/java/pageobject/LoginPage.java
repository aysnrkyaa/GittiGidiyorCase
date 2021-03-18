package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage extends PageObject{
    private Logger logger = LoggerFactory.getLogger(LoginPage.class);

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(id = "L-UserNameField")
    private WebElement usernameinput;

    @FindBy(id = "L-PasswordField")
    private WebElement userpassword;

    @FindBy(id ="gg-login-enter")
    private WebElement loginbutton;


    public void login(String username ,String password) throws InterruptedException {
        logger.info("Login Page is opening");
        usernameinput.sendKeys(username);
        userpassword.sendKeys(password);
        Thread.sleep(3000);
        loginbutton.click();
    }



}
