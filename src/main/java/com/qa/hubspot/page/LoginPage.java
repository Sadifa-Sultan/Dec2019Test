package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.Credentials;
import com.qa.hubspot.util.ElementUtil;



public class LoginPage extends BasePage {

	WebDriver driver;
	ElementUtil elementUtil;
	By emailid = By.id("username");
	By pwd = By.id("password");
	By loginButton = By.id("loginBtn");
	By signUp = By.linkText("Sign up");
	By loginErrorText = By.xpath("//*[@id=\"hs-login\"]/div[6]/div/h5");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	// Page actions:

	public String getPageTitle() {
		//elementUtil.waitForTitlePresent(AppConstants.LOGIN_PAGE_TITLE);
		return elementUtil.doGetPageTitle();
	}

	public boolean checkSignUpLink() {
		//elementUtil.waitForElementPresent(signUp);
		return elementUtil.doIsDisplayed(signUp);
	}

	public HomePage doLogin(Credentials userCred) {
		//elementUtil.waitForElementPresent(emailid);
		elementUtil.doSendKeys(emailid, userCred.getAppUsername());
		elementUtil.doSendKeys(pwd, userCred.getAppPassword());
		elementUtil.doClick(loginButton);
		return new HomePage(driver);
	}

	public boolean checkLoginErrorMessage() {
		return elementUtil.doIsDisplayed(loginErrorText);
	}
}
