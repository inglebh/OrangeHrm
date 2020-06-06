package com.orangehrm.pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;



public class LoginPageObject {
	 WebDriver driver;
	 Logger logger;
	 
	public LoginPageObject(WebDriver driver) {
		logger = LogManager.getLogger(LoginPageObject.class);
		
		this.driver = driver;
		logger.info("Identifying and initialising WebElements from Login page ");
		PageFactory.initElements(driver, this);
		logger.info("Identifying and initialising WebElements completed from Login page ");
	}

	@FindBy(how = How.CSS, using = "div#divLogo>img[src='/webres_5ebd1457b45137.49759927/themes/default/images/login/logo.png']")
	WebElement hrmTitleLogo;

	@FindBy(how = How.CSS, using = "div#content>:nth-child(3)>span")
	 WebElement userCredsTextLine;

	@FindBy(how = How.CSS, using = "#divLoginImage>svg>switch>g>circle:nth-child(10)")
	 WebElement hrmCircularLogo;

	@FindBy(how = How.CSS, using = "div#logInPanelHeading")
	 WebElement logInPanelHeading;

	@FindBy(how = How.CSS, using = "div#divUsername>input#txtUsername")
	@CacheLookup
	 WebElement userNameInput;

	@FindBy(how = How.CSS, using = "#divUsername > span")
	 WebElement userNameInputLogo;

	@FindBy(how = How.CSS, using = "input#txtPassword")
	@CacheLookup
	 WebElement passwordInput;

	@FindBy(how = How.CSS, using = "#divPassword > span")
	 WebElement passwordInputLogo;

	@FindBy(how = How.CSS, using = "input#btnLogin")
	 WebElement loginButton;

	@FindBy(how = How.CSS, using = "div#forgotPasswordLink>a")
	 WebElement forgetPasswordLink;

	public boolean validatehrmTitleLogo() {
		boolean flag = false;
		if (hrmTitleLogo.isDisplayed()) {
			flag = true;
			
			
		}
		
		return flag;
	}

	public boolean validateuserCredsTextLine() {
		boolean flag = false;
		if (userCredsTextLine.isDisplayed()
				&& userCredsTextLine.getText().equals("( Username : Admin | Password : admin123 )")) {
			flag = true;
		}
		return flag;
	}

	public boolean validatehrmCircularLogo() {
		boolean flag = false;
		if (hrmCircularLogo.isDisplayed()) {
			flag = true;
		}
		return flag;
	}

	public boolean validatelogInPanelHeading() {
		boolean flag = false;
		if (logInPanelHeading.isDisplayed() && logInPanelHeading.getText().equals("LOGIN Panel")) {
			flag = true;
		}
		return flag;
	}

	public boolean validateuserNameInput() {
		boolean flag = false;
		if (userNameInput.isDisplayed() && userNameInput.isEnabled()) {
			flag = true;
		}
		return flag;
	}

	public boolean validateuserNameInputLogo() {
		boolean flag = false;
		if (userNameInputLogo.isDisplayed()) {
			flag = true;
		}
		return flag;
	}

	public boolean validatepasswordInput() {
		boolean flag = false;
		if (passwordInput.isDisplayed() && passwordInput.isEnabled()) {
			flag = true;
		}
		return flag;
	}

	public boolean validatepasswordInputLogo() {
		boolean flag = false;
		if (passwordInputLogo.isDisplayed()) {
			flag = true;
		}
		return flag;
	}

	public boolean validateloginButton() {
		boolean flag = false;
		if (loginButton.isDisplayed() && loginButton.isEnabled()) {
			flag = true;
		}
		return flag;
	}

	public boolean validateforgetPasswordLink() {
		boolean flag = false;
		if (forgetPasswordLink.isDisplayed() && forgetPasswordLink.isEnabled()) {
			flag = true;
		}
		return flag;
	}

	// ---------- Functional Test Cases--------------------------------------//

	public  void validateLoginFunctionality(String userName, String password) {
		
		userNameInput.clear();
		//System.out.println(userName+"--->"+password);
		userNameInput.sendKeys(userName);
		passwordInput.clear();
		passwordInput.sendKeys(password);
		loginButton.click();
		Assert.assertEquals(driver.getTitle(), "OrangeHRM");
	}
}
