package com.orangehrm.utilities;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public  class ExtentTestReport {

	private static final String projectPath = System.getProperty("user.dir");
	ExtentHtmlReporter htmlreporter;
	ExtentReports report;
	ExtentTest test;
	String temp;
	static WebDriver driver;

	// @BeforeClass
	/*
	 * public ExtentTestReport() { htmlreporter = new ExtentHtmlReporter(projectPath
	 * + "\\test-reports\\TestSummaryReport.html");
	 * 
	 * report = new ExtentReports();
	 * 
	 * report.attachReporter(htmlreporter); }
	 */
	@BeforeClass
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver",projectPath+"/drivers/chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://google.com");
		driver.manage().timeouts().implicitlyWait(2000,TimeUnit.MILLISECONDS);
	}
	@BeforeClass
	public void startTest() {
		//report = new ExtentReports();
		htmlreporter = new ExtentHtmlReporter(projectPath + "\\test-reports\\TestSummaryReport.html");
		report = new ExtentReports();
		report.setSystemInfo("Machine", "Deva-007");
		report.setSystemInfo("Env", "Test");
		report.setSystemInfo("Build", "Integration");
		report.setSystemInfo("Browser", "Chrome");
		
	}

	@AfterMethod
	public void setTestResult(ITestResult result) throws IOException {
		
		String screenShot = CaptureScreenShot.takeScreenShot(driver, CaptureScreenShot.generateFileName(result));
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, result.getName());
			test.log(Status.FAIL, result.getThrowable());
			test.fail("Screen Shot : " + test.addScreenCaptureFromPath(screenShot));
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			//test.log(Status.PASS, result.getName());
			//System.out.println(result.getName()+"---------->");
			test.pass("Screen Shot : " + test.addScreenCaptureFromPath(screenShot));
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, result.getName() + " Has been Skipped.");
			test.pass("Screen Shot : " + test.addScreenCaptureFromPath(screenShot));
		}
		report.flush();
		driver.close();
	}
	@Test
	public void googleTest()
	{
		//test=report.createTest("googleTest");
		
		Assert.assertEquals(driver.getTitle(), "Google");
	}

	
	  @AfterTest public void endTest() 
	  { report.flush(); }
	 

	/*
	 * public void prepareReport() {
	 * 
	 * reporter = new ExtentHtmlReporter(projectPath +
	 * "\\test-reports\\TestSummaryReport.html");
	 * 
	 * extent = new ExtentReports();
	 * 
	 * extent.attachReporter(reporter);
	 * 
	 * }
	 */

	/*
	 * public void addTestCaseToReport(ExtentTest t, String tcName) {
	 * 
	 * t = report.createTest(tcName);
	 * 
	 * t.log(Status.INFO, "Executing First Test Case");
	 * System.out.println("Enter User  Details"); t.log(Status.PASS,
	 * "User Logged In Successfully");
	 * 
	 * report.flush(); }
	 */

	/*
	 * @Test public void testCase1() { // ExtentTest t1 = null; t1 =
	 * extent.createTest("testCase1");
	 * 
	 * t1.log(Status.INFO, "Executing First Test Case");
	 * System.out.println("Enter User  Details"); t1.log(Status.PASS,
	 * "User Logged In Successfully"); addTestCaseToReport(); }
	 * 
	 * @Test
	 * 
	 * public void testCase2() throws IOException { t1 =
	 * extent.createTest("testCase2");
	 * 
	 * t1.log(Status.INFO, "Executing Second Test Case");
	 * System.out.println("Enter User  Details"); t1.log(Status.FAIL,
	 * "Invalid Creds"); t1.fail("Failed due to logo not present",
	 * MediaEntityBuilder.createScreenCaptureFromPath(Utility.takeScreenShot()).
	 * build()); addTestCaseToReport(); }
	 * 
	 * @Test
	 * 
	 * public void testCase3() { t1 = extent.createTest("testestCase3tCase1");
	 * 
	 * t1.log(Status.INFO, "Executing Third Test Case");
	 * System.out.println("Click on LogOut"); t1.log(Status.PASS,
	 * "User Logged Out Successfully"); addTestCaseToReport(); }
	 */

}
