package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountpage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003LoginDDT extends BaseClass{

	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="Data Driven") //getting data from data provider class
	public  void verify_LoginDDT(String email, String pwd,String exp)
	{
		logger.info("Starting TC_003LoginDDT");
		//Home Page
		try
		{
			HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickloin();
		//Login Page
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setpassword(pwd);
		lp.clickLogin();
		//MyAccount 
		
		MyAccountpage ac=new MyAccountpage(driver);
		boolean targetpage=ac.isMyAccountPageExists();
		
		/*Data is valid-login success-test pass-logout
		                login failed-test fail
		                
		  Data is invalid-login success-test fail-logout
		             login failed-test pass*/
		
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(targetpage==true)
			{
				ac.clickLogout();
				Assert.assertTrue(true);
				
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
	 if(exp.equalsIgnoreCase("Invalid"))
	 {
		 if(targetpage==true)
			{
				ac.clickLogout();
				Assert.assertTrue(false);
				}
		 else
		 {
			 Assert.assertTrue(true);
		 }
		}
		}catch(Exception e)
		{
			Assert.fail();
		}
	 logger.info("Finished TC_003LoginDDT");
	}
}
