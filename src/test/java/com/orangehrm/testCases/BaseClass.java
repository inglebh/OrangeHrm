package com.orangehrm.testCases;

import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.ExtentTest;
import com.orangehrm.pageObjects.LoginPageObject;
import com.orangehrm.utilities.ExtentTestReport;
import com.orangehrm.utilities.PropertiesReader;

public class BaseClass {
	String projectPath;
	public LoginPageObject loginpage;
	public WebDriver driver;
	public static Logger logger= LogManager.getLogger(BaseClass.class);
	public ExtentTestReport report;
	public ExtentTest t1;
	@BeforeClass
	public void setUp() {

		PropertiesReader.getProperties();
		report= new ExtentTestReport();
		// System.out.println("**********************************");
		projectPath = System.getProperty("user.dir");
		
		switch (PropertiesReader.browserType) {
		case "chrome":
			setUpChromeBrowser();
			break;
		case "firefox":
			setUpfireFoxBrowser();
			break;
		case "ie":
			setUpIEBrowser();
			break;
		default:
			setUpChromeBrowser();
		}

	}

	public void setUpChromeBrowser() {
		logger.info("Chrome Browser Properties setting up");
		System.setProperty("webdriver.chrome.driver", projectPath + "\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		//System.out.println(projectPath + "\\drivers\\chromedriver.exe");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
		driver.manage().deleteAllCookies();
		loginpage = new LoginPageObject(this.driver);
		logger.info("Chrome Browser Properties set up completion done");
	}

	public void setUpfireFoxBrowser() {
		logger.info("FireFox Browser Properties setting up");
		System.setProperty("webdriver.chrome.driver", projectPath + "\\drivers\\chromedriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
		driver.manage().deleteAllCookies();
		loginpage = new LoginPageObject(this.driver);
		logger.info("FireFox Browser Properties set up completion done");
	}

	public void setUpIEBrowser() {

		logger.info("IE Browser Properties setting up");
		System.setProperty("webdriver.chrome.driver", projectPath + "\\drivers\\chromedriver.exe");
		driver = new InternetExplorerDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
		driver.manage().deleteAllCookies();
		loginpage = new LoginPageObject(this.driver);
		logger.info("IE Browser Properties set up completion done");
	}

	public void launchApplication() {
		logger.info("Launching Application in Browser");
		// System.out.println(PropertiesReader.appURL);
		driver.get(PropertiesReader.appURL);
		logger.info("Application opened in Browser");
		// System.out.println("Page Title : "+driver.getTitle());
		// Thread.sleep(3000);
	}
	@AfterClass
	public void tearDown()
	{
		driver.close();
		driver.quit();
	}

}
