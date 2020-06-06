package com.orangehrm.testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.orangehrm.pageObjects.LoginPageObject;

public class Test1 {
	WebDriver driver;
	
	@Test
	public void test1() throws InterruptedException
	{
		
		String projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectPath+"\\drivers\\chromedriver.exe");
		
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/dashboard");
		Thread.sleep(3000);
		LoginPageObject lp= new LoginPageObject(driver);
		
		lp.validateLoginFunctionality("Admin", "admin123");
		
	}

}
