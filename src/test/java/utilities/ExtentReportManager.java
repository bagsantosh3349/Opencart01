package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener{
	
	public ExtentSparkReporter sparkreporter; // UI of the report
    public ExtentReports extent;              //populate common info on the report
    public ExtentTest test;      //creating test case entries in the reports and update status of the methods

    String repName;
	private String imgPath;
	public void onStart(ITestContext testcontext) {
		
		/*SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd-MM-ss");
		Date dt=new Date();
		String currentDatetimestamp=df.format(dt);*/
	    String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //time stamp
	    repName = "Test Report-" + timestamp + ".html";
	    
	    sparkreporter = new ExtentSparkReporter(".\\reports\\" + repName); //Location of the report
	    sparkreporter.config().setDocumentTitle("Opencart Automation Report"); //title of the report 
	    sparkreporter.config().setReportName("Opencart Functional Testing"); // name of the report
	    sparkreporter.config().setTheme(Theme.DARK);

	    extent = new ExtentReports();
	    extent.attachReporter(sparkreporter);
	    extent.setSystemInfo("Application", "Opencart");
	    extent.setSystemInfo("Module", "Admin");
	    extent.setSystemInfo("Sub Module", "Customers");
	    extent.setSystemInfo("User Name", System.getProperty("user.name"));
	    extent.setSystemInfo("Environment", "QA");

	    String os = testcontext.getCurrentXmlTest().getParameter("os");
	    extent.setSystemInfo("Operating System", os);
	    
	    String browser = testcontext.getCurrentXmlTest().getParameter("browser");
	    extent.setSystemInfo("Browser", browser);

	    List<String> includegroups = testcontext.getCurrentXmlTest().getIncludedGroups();
	    if (!includegroups.isEmpty()) { // Fix this condition
	        extent.setSystemInfo("Groups", includegroups.toString());
	    }
	}



     public void onTestSuccess(ITestResult result) {
    	 test = extent.createTest(result.getTestClass().getName());
     	test.assignCategory(result.getMethod().getGroups());
     	test.log(Status.PASS,result.getName()+"Test Successfully Executed");
     }
   public void onTestFailure(ITestResult result) {
    	    test = extent.createTest(result.getTestClass().getName());
    	    test.assignCategory(result.getMethod().getGroups());
    	    test.log(Status.FAIL, result.getName() + " Test Fails");
    	    test.log(Status.INFO, result.getThrowable().getMessage());
    	    
    	    try {
    	        String imgPath = new BaseClass().captureScreen(result.getName());
    	        test.addScreenCaptureFromPath(imgPath); // Moved inside try block after imgPath is set
    	    } catch (IOException e) {
    	        e.printStackTrace();
    	    }
    	}

	
    
    public void onTestSkipped(ITestResult result ) {
    	
    	test = extent.createTest(result.getTestClass().getName());
    	test.assignCategory(result.getMethod().getGroups());
    	test.log(Status.SKIP, result.getName()+"Test Skipped");
    	test.log(Status.INFO, result.getThrowable().getMessage());
    }
     public void onFinish(ITestContext context) {
    	extent.flush(); 
    	
    	String pathofExtendReport= System.getProperty("user.dir")+"\\reports\\"+repName;
    	File extendReport = new File(pathofExtendReport);
    	
    	try {
			Desktop.getDesktop().browse(extendReport.toURI());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
       /* try {
			URL url=new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+"repName");
			
			ImageHtmlEmail email=new ImageHtmlEmail();
			email.setDataSourceResolver(new DataSourceUrlResolver(url));
			email.setHostName("smtp.googleemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("sstravels561@gmail.com","Tracy@321"));
			email.setSSLOnConnect(true);
			email.setFrom("sstravels561@gmail.com");
			email.setSubject("Test Results");
			email.setMsg("please find the attach report");
			email.addTo("bagsantosh3349@gmail.com");
			email.attach(url,"extend report","please check report");
			email.send();
		} catch (Exception e) {
			
			e.printStackTrace();
		}*/
     }
     
}

