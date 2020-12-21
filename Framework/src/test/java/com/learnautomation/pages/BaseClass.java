package com.learnautomation.pages;

import java.io.File;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.learnautomation.utility.BrowserFactory;
import com.learnautomation.utility.ConfigDataProvider;
import com.learnautomation.utility.ExcelDataProvider;
import com.learnautomation.utility.Helper;

public class BaseClass 
{
	public WebDriver driver;
	public ExcelDataProvider excel;
	public ConfigDataProvider config;
	public ExtentReports report;
	public ExtentTest logger;
	
	@BeforeSuite
	public void setupSuite()
	{
		Reporter.log("Setting up reports and Test is getting ready",true);
		
		excel=new ExcelDataProvider();
		config=new ConfigDataProvider();
		ExtentHtmlReporter extent=new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/FreeCRM_"+Helper.getCurrentDateTime()+".html"));
		report=new ExtentReports();
		report.attachReporter(extent);
		
		Reporter.log("Setting done. Test can be started",true);
	}
	
	@BeforeClass
	public void setup() throws InterruptedException
	{
		Reporter.log("Trying to start browser and getting application ready",true);
		
		driver=BrowserFactory.startApplication(driver, config.getBrowser(), config.getStagingURL());//if this statement fails to execute, next line for log will not be executed		
		
		Reporter.log("Browser and Application is up and running",true);
	}
	
	@AfterClass
	public void tearDown()
	{
		BrowserFactory.quitBrowser(driver);
	}
	
	@AfterMethod
	public void tearDownMethod(ITestResult result)
	{
		Reporter.log("Test is about to end",true);
		if(result.getStatus()==ITestResult.FAILURE)
		{	
			try 
			{
				logger.fail("Test failed",MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
			} 
			catch (Exception e) 
			{
				System.out.println(e.getMessage());
			}
		}
		else if(result.getStatus()==ITestResult.SUCCESS)
		{	
			try 
			{
				logger.pass("Test passed",MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
			} 
			catch (Exception e) 
			{
				System.out.println(e.getMessage());
			}
		}//we can add same else if() for ITestResult.SKIP
			
		report.flush();
		Reporter.log("Test completed >>>>  Reports generated",true);
	}
	
	
}
