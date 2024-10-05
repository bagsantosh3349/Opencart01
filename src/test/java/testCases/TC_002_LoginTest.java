package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountpage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass{

	@Test(groups={"sanity","Master"})
	public void verify_Login()
	{
		logger.info("Staring TC_002_LoginTest ");
	
		HomePage hp=new HomePage(driver);
		
		try
		{
		//Home Page
		hp.clickMyAccount();
		hp.clickloin();
		//Login Page
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setpassword(p.getProperty("password"));
		lp.clickLogin();
		//MyAccount 
		
		MyAccountpage ac=new MyAccountpage(driver);
		boolean targetpage=ac.isMyAccountPageExists();
		//Assert.assertEquals(targetpage, true),"Login fail";
		
		Assert.assertTrue(targetpage);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("Finished TC_002_LoginTest ");
	}
	
	
	
}
