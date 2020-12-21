package com.learnautomation.utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory 
{
	public static WebDriver startApplication(WebDriver driver, String browserName, String appURL) throws InterruptedException
	{
		if(browserName.equals("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver","./Drivers/chromedriver.exe");
			driver=new ChromeDriver();
		}
		else if(browserName.equals("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver","./Drivers/geckodriver.exe");
			driver=new FirefoxDriver();			
		}
		else
			System.out.println("\nWe donot support this browser......!");
		
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);//if any page element gets time for loading
		driver.manage().window().maximize();
		Thread.sleep(1000);
		driver.get(appURL);
		Thread.sleep(1000);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		return driver;
	}
	
	public static void quitBrowser(WebDriver driver)
	{
		driver.quit();
	}
	
}
