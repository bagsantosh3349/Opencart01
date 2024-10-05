package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends Basepage {

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//span[normalize-space()='My Account']")
    WebElement lnkMyaccount;

    @FindBy(xpath="//a[normalize-space()='Register']")
    WebElement lnkRegister;

    @FindBy(xpath="//a[normalize-space()='Login']")
    WebElement linkLogin;
    
    public void clickMyAccount() {
        lnkMyaccount.click();
    }

    public void clickRegister() {
        lnkRegister.click();
    }
   public void clickloin()
   {
	   linkLogin.click();
   }
    
}
