package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Logger;

public class HomePage extends PageObject {
    WebDriverWait wait = new WebDriverWait(driver, 20);
    Actions actions = new Actions(driver);
    @FindBy(xpath = "(//div[contains(@class,'gekhq4-4 hwMdSM')])[1]")
    private WebElement signbutton;
    @FindBy(xpath = "//a[contains(@href,'https://www.gittigidiyor.com/uye-girisi')]")
    private WebElement signbuttonlink;
    @FindBy(css = ".gekhq4-4 span")
    private WebElement loginUserName;
    @FindBy(css = "[data-cy='header-search-input']")
    private WebElement inputproduct;
    @FindBy(xpath = "//a//div[contains(text(),'Bilgisayar, Tablet / Dizüstü Bilgisayar (Laptop)')]")
    private WebElement chooseProduct;
    @FindBy(xpath = "//span[text()='BUL']")
    private WebElement findButton;
    @FindBy(xpath = "((//div[contains(@class,'cell-border')])[2]//div)[1]")
    private WebElement clickProduct;
    @FindBy(id = "add-to-basket")
    private WebElement addBasket;
    @FindBy(id = "sp-price-highPrice")
    private WebElement priceText;
    @FindBy(xpath = "//div[@class='total-price']//strong")
    private WebElement priceBasket;

    @FindBy(xpath = "(//a[@href='https://www.gittigidiyor.com/sepetim'])[1]")
    private WebElement openBasket;
    @FindBy(xpath = "//*[contains(text(),'Sepete Git')]")
    private WebElement goBasket;

    @FindBy(xpath = "(//i[@class='gg-icon gg-icon-bin-medium'])[1]")
    private WebElement deleteBasket;
    @FindBy(xpath = "(//h2['Sepetinizde ürün bulunmamaktadır.'])[1]")
    private WebElement deleteInfo;

    @FindBy(xpath = "//span[contains(text(),'Sepet')]")
    private WebElement basket;

    private Logger logger;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void moveToSignButton() {
        Actions actions = new Actions(driver);
        actions.moveToElement(signbutton);
        signbutton.click();
    }

    public void clickSignLink() {
        signbuttonlink.click();
    }

    public String getUserName() {
        return loginUserName.getText();
    }

    public void findProduct(String keyword) {
        // WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(inputproduct));
        inputproduct.sendKeys(keyword);
        chooseProduct.click();
        wait.until(ExpectedConditions.elementToBeClickable(clickProduct));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", clickProduct);
        clickProduct.click();

    }

    public String checkPrice() {
        String checkPrice = priceText.getText();
        return checkPrice;
    }

    public String basketPrice() {
        String basketPrice = priceBasket.getText();
        return basketPrice;
    }

    public void addBasket() {
        String checkPrice2 = checkPrice();
        System.out.println(checkPrice2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addBasket);
        wait.until(ExpectedConditions.elementToBeClickable(addBasket)).click();
        sleep(2000);
        if (goBasket.isDisplayed()) {
            wait.until(ExpectedConditions.elementToBeClickable(goBasket)).click();
        } else {
            //actions.moveToElement(addBasket);
            wait.until(ExpectedConditions.elementToBeClickable((basket))).click();

        }
        //goBasket.click();
        String basketPrice = basketPrice();
        System.out.println(basketPrice);
        if (basketPrice.contains(checkPrice2)) {
            System.out.println("OK eşleşiyor Bulunan = " + basketPrice + " Aranan = " + checkPrice2);
        } else {

            System.out.println("NOK eşleşiyor Bulunan = " + basketPrice + " Aranan = " + checkPrice2);
        }


    }

    public void deleteBasket() {
        int xpathCount = driver.findElements(By.xpath("//i[@class='gg-icon gg-icon-bin-medium']")).size();
        if(xpathCount>0) {
            for (int i = 0; i < xpathCount; i++) {
                try {
                    deleteBasket.click();
                }
                catch(org.openqa.selenium.StaleElementReferenceException ex)
                {
                    //ex.printStackTrace();
                }
            }
        }
        sleep(2000);
       if(deleteInfo.isDisplayed())
       {
            System.out.println("Ürünler temizlendi");
       }
       else
       {
           System.out.println("Ürünler temizlenmedi");
       }
    }

    public void sleep(int sleepMs)
    {
        try{
            Thread.sleep(sleepMs);
        }
        catch(InterruptedException ie){
        }
    }

}




