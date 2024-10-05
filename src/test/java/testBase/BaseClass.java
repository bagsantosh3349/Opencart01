package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger; //Log4j
	public Properties p;
    @BeforeClass(groups= {"sanity","Regression","Master"})
    @Parameters({"os","browser"})
	public	void setup(String os, String br) throws IOException
	{
    	//Loading config.properties file
    	FileReader file=new FileReader("./src//test//resources//config.properties");
    	p=new Properties();
    	p.load(file);
    	logger=LogManager.getLogger(this.getClass());
    	switch(br.toLowerCase())
    	{
    	case "chrome" : driver=new ChromeDriver(); break;
    	//case "edge" :   driver=new EdgeDriver(); break;
    	//default : System.out.println("Invalid browser name.."); return;
    	}
    	driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
		}
    @AfterClass(groups= {"sanity","Regression","Master"})
   	public void teardown()
   	{
   		driver.close();
   	}
    public  String randomeString()
    {
    String	GeneratedString=RandomStringUtils.randomAlphabetic(5);
    	return GeneratedString;
      }
    	
    public  String randomeNumber()
    {
    String	GeneratedNumber=RandomStringUtils.randomNumeric(10);
    	return GeneratedNumber;
      }
    
    public  String randomeAlphaNumber()
    {
    	String	GeneratedString=RandomStringUtils.randomAlphabetic(4); 
        String	GeneratedNumber=RandomStringUtils.randomNumeric(3);
    	return (GeneratedString+"@"+GeneratedNumber);
      }
	public String captureScreen(String tname) throws IOException{
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		TakesScreenshot Screenshot= (TakesScreenshot) driver;
		File sourceFile= Screenshot.getScreenshotAs(OutputType.FILE);
		String targetfilePath=System.getProperty("user.dir")+"\\screenshot\\" +tname+"_"+ timeStamp+".png";
		File targetfile=new File(targetfilePath);
		sourceFile.renameTo(targetfile);
		return targetfilePath;
	}
    
}
