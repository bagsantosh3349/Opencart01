package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class Tc_001AccountRegistationTest extends BaseClass {
  
	
	
    @Test(groups={"Regression","Master"})
	public void verify_account_registration()
	{
    	
    	logger.info("Starting Tc_001AccountRegistationTest ");
    	try
    	{
    	HomePage hp=new HomePage(driver);
    	hp.clickMyAccount();
    	logger.info("Clicked on MyAccount Link");
    	hp.clickRegister();
    	logger.info("Clicked on Register Link");
    	
    	AccountRegistationPage regpage=new AccountRegistationPage(driver);
    	
    	logger.info("proving customer deatails...");
    	
    	regpage.SetFirstName(randomeString().toUpperCase());
    	regpage.SetLastName(randomeString().toUpperCase());
    	regpage.SetEmail(randomeString()+"@gmail.com");
    	regpage.SetTelephone(randomeNumber());
    	String password=randomeAlphaNumber();
    	regpage.Setpassword(password);
    	regpage.Setcofirmpassword(password);
    	regpage.SetPrivacyPolicy();
    	regpage.clickContinue();
    	logger.info("validating expected message");
    	String confmsg=regpage.getsmgconfirm();
    	if(confmsg.equals("Your Account Has Been Created!"))
    	{
    		Assert.assertTrue(true);
    	}
    	else {
    		logger.error("Test failed");
    		logger.debug("Debug logs");
    		Assert.assertTrue(false);
    		
    	}
    	
    	}
    	catch(Exception e)
    	{
    		
    		Assert.fail();
    	}
    	logger.info("Finished Tc_001AccountRegistationTest ");
	}
  
	}

