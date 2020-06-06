package com.orangehrm.testCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestClassUsingListeners {
	
	 public WebDriver driver;
	 
	 @BeforeClass
	 public void setUp()
	 {
		 System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/drivers/chromedriver.exe");
		 driver = new ChromeDriver();
		 driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		 driver.manage().window().maximize();
	 }
	 @AfterClass
	 public void tearDown()
	 {
		 driver.quit();
	 }
	 @Test
	 public void launchapp()
	 {
		 driver.get("https://google.com");
		 System.out.println("Executing google Test case");
		 Assert.assertEquals(driver.getTitle(), "Googl1e");;
	 }
	 @Test
	 public void failTcScenario()
	 {
		 System.out.println("Executing Fail Test case");
		 Assert.fail();
	 }
	 @Test
	 public void skipTcScenario()
	 {
		 System.out.println("Executing Skip Test case");
		 throw new SkipException("Executing Skip Test case"); 
}
	 

}
