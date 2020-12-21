package com.learnautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage 
{
	WebDriver driver;
	
	public LoginPage(WebDriver ldriver)
	{
		this.driver=ldriver;
	}
	
	@FindBy(name="username") WebElement uname;
	@FindBy(name="password") WebElement pass;
	@FindBy(xpath="//input[@value='Login']") WebElement loginbtn; 
	
	public void loginToCRM(String usernameApp,String passApp)
	{
		uname.sendKeys(usernameApp);
		pass.sendKeys(passApp);
		loginbtn.click();
	}
	
}
