package com.orangehrm.base;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.orangehrm.testCases.BaseClass;

public class TestListeners implements ITestListener {

	private static ExtentReports extent = ExtentManager.createReportInstance();
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	public static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	@Override
	public void onTestStart(ITestResult result) {

		ExtentTest test = extent.createTest(result.getTestClass().getName() + "-" + result.getMethod().getMethodName());
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String logText = "<b>Test Case : " + result.getMethod().getMethodName() + " Passed Successfully</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		extentTest.get().log(Status.PASS, m);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		extentTest.get().fail("<details><summary><b><font color=red>" + "Exception Occured, Click to see details : "
				+ "</font></b></summary>" + exceptionMessage.replaceAll(",", "<br>") + "</details>\n");
		WebDriver driver = ((BaseClass) result.getInstance()).driver;
		String path = takeScreenShot(driver, result.getMethod().getMethodName());
		try {
			extentTest.get().fail("<b><font color=red>" + " Screen Shot of Failure : " + "</font></b>",
					MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		} catch (Exception e) {
			extentTest.get().fail("Test Case Failed can not Attach screen shot");
		}

		String logText = "<b>Test Method" + methodName + " Failed </b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
		extentTest.get().log(Status.FAIL, m);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String logText = "<b>Test Method " + result.getMethod().getMethodName() + " Skipped</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.ORANGE);
		extentTest.get().log(Status.SKIP, m);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		if (extent != null) {
			extent.flush();
		}
	}

	public static String takeScreenShot(WebDriver driver, String screenName) {
		TakesScreenshot takess = (TakesScreenshot) driver;

		File src = takess.getScreenshotAs(OutputType.FILE);

		// String sspath = System.getProperty("user.dir") + "\\screenShots\\" +
		// System.currentTimeMillis() + ".png";
		String sspath = System.getProperty("user.dir") + "\\screenShots\\" + screenName + ".png";

		File destination = new File(sspath);

		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			System.out.println("Capture Failed " + e.getMessage());
		}

		return sspath;

	}

	public static String generateFileName(ITestResult result) {
		Date date = new Date();
		String fileName = result.getName() + "_" + dateFormat.format(date);
		return fileName;
	}

}
