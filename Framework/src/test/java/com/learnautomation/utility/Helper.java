package com.learnautomation.utility;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Helper 
{
	//screenshot,alerts,frames,windows,sync issue,javascript executer
	
	public static String captureScreenshot(WebDriver driver)//made static becoz we can call this method without creating any object
	{
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String screenshotPath=System.getProperty("user.dir")+"/Screenshots/FreeCRM_"+getCurrentDateTime()+".png";
		try 
		{
			FileHandler.copy(src, new File(screenshotPath));
			System.out.println("Screenshot captured...!");
			
		} 
		catch (Exception e) 
		{
			System.out.println("Unable to capture screenshot: "+e.getMessage());
		}
		
		return screenshotPath;
	}

	public static String getCurrentDateTime()
	{
		DateFormat  customFormat=new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		Date currentDate=new Date();//import from java.util not java.sql
		return customFormat.format(currentDate);
		
	}
}
