package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistationPage extends Basepage{

	public AccountRegistationPage(WebDriver driver) {
		super(driver);
		
	}
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstname;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastname;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtTelephone;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtpassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtconfirmpassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkPolicy;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btncontinue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	public void SetFirstName(String fname)
	{
		txtFirstname.sendKeys(fname);
	}
  public void SetLastName(String lname)
  {
	  txtLastname.sendKeys(lname);
  }
  public void SetEmail(String email)
  {
	  txtEmail.sendKeys(email);
  }
  public void SetTelephone(String tel)
  {
	  txtTelephone.sendKeys(tel);
  }
  public void Setpassword(String password)
  {
	  txtpassword.sendKeys(password);
  }
  public void Setcofirmpassword(String confirmpassword)
  {
	  txtconfirmpassword.sendKeys(confirmpassword);
  }
  public void SetPrivacyPolicy()
  {
	  chkPolicy.click();
  }
  //sol1 .click
  //sol2 .submit
  //sol3 Actions act=new Actions(driver)
  //act.moveToElement(btbcontinue).click().perform();
  //sol4 JavascriptExecuter js=new(JavascriptExecuter)driver;
  //sol5 WebDriverWait mywait=new WebDriverWait(driver,Duration.ofSeconds(10));
  public void clickContinue ()
  {
	  btncontinue.click();
  }
  public String getsmgconfirm() {
   try
	  {
		return (msgConfirmation.getText());
	  }catch(Exception e) {
    return(e.getMessage());
  }
}

}
