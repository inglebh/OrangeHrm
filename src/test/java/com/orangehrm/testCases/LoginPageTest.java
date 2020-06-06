package com.orangehrm.testCases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;

public class LoginPageTest extends BaseClass {

	String temp;
	
	
	@BeforeClass
	public void launchApplicationInBrowser() {
		launchApplication();
		
	}

	@Test(priority = 0)
	public void validatehrmTitleLogo() throws InterruptedException {
		logger.info("validating hrmTitleLogo");
		boolean flag = loginpage.validatehrmTitleLogo();

		try {
			if (flag) {
				logger.info("validate hrmTitleLogo passed");
				//temp = Utility.takeScreenShot(driver);

				//System.out.println("temp----> " + temp);
			//	t1.fail("Failed due to logo not present", MediaEntityBuilder.createScreenCaptureFromPath(Utility.takeScreenShot()).build());
				//t1.pass("Test Case validated",MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
				//report.addTestCaseToReport();

			} else {
				logger.info("validatehrm TitleLogo Failed");
				//t1.pass("Test Case validated", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
				//report.addTestCaseToReport();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}

	@Test(priority = 1)
	public void validateuserCredsTextLine() {
		loginpage.validateuserCredsTextLine();
	}

	@Test(priority = 2)
	public void validatehrmCircularLogo() {
		loginpage.validatehrmCircularLogo();
	}

	@Test(priority = 3)
	public void validatelogInPanelHeading() {
		loginpage.validatelogInPanelHeading();
	}

	@Test(priority = 4)
	public void validateuserNameInput() {
		loginpage.validateuserNameInput();
	}

	@Test(priority = 5)
	public void validateuserNameInputLogo() {
		loginpage.validateuserNameInputLogo();
	}

	@Test(priority = 6)
	public void validatepasswordInput() {
		loginpage.validatepasswordInput();
	}

	@Test(priority = 7)
	public void validatepasswordInputLogo() {
		loginpage.validatepasswordInputLogo();
	}

	@Test(priority = 8)
	public void validateloginButton() {
		loginpage.validateloginButton();
	}

	@Test(priority = 9)
	public void validateforgetPasswordLink() {
		loginpage.validateforgetPasswordLink();
	}

	@Parameters({ "userName", "password" })
	@Test(priority = 10)

	public void validateValidUserLoginFunctionality(String userName, String password)

	{
		// launchApplication();
		loginpage.validateLoginFunctionality(userName, password);
	}

	

}
