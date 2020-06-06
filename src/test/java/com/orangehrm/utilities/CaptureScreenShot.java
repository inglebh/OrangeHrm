package com.orangehrm.utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

public class CaptureScreenShot {

	public static final DateFormat dateFormat = new SimpleDateFormat("DD_MM_YYYY SSS");

	public static String takeScreenShot(WebDriver driver, String screenName) {
		TakesScreenshot takess = (TakesScreenshot) driver;

		File src = takess.getScreenshotAs(OutputType.FILE);

		//String sspath = System.getProperty("user.dir") + "\\screenShots\\" + System.currentTimeMillis() + ".png";
		String sspath = System.getProperty("user.dir") + "\\screenShots\\" + screenName + ".png";

		File destination = new File(sspath);

		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			System.out.println("Capture Failed " + e.getMessage());
		}

		return sspath;

	}
	public static String generateFileName(ITestResult result)
	{
		Date date = new Date();
		String fileName= result.getName()+"_"+dateFormat.format(date);
		return fileName;
	}
}
