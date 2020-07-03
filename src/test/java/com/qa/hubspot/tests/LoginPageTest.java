package com.qa.hubspot.tests;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.Credentials;



public class LoginPageTest extends BasePage {
	LoginPage loginPage;
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	Credentials userCred;
	HomePage homePage;

	@BeforeTest
	public void setUp() throws Exception {
	
		basePage = new BasePage();
		prop = basePage.initialize_properties();
		String browserName = prop.getProperty("browser");
		driver = basePage.initialize_driver(browserName);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		homePage = loginPage.doLogin(userCred);
	}

	@Test(priority = 1)
	public void verifyLoginPageTitleTest() throws Exception {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String title = loginPage.getPageTitle();
		System.out.println(title);
		Assert.assertEquals(title, AppConstants.LOGIN_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void verifySignUpLinkTest() {
		Assert.assertTrue(loginPage.checkSignUpLink());
	}

	@Test(priority = 3)
	public void LoginTest() {
		HomePage homePage = loginPage.doLogin(userCred);
		String accountName = homePage.getLoggedInUserAccountName();
		Assert.assertEquals(accountName, prop.getProperty(accountName));

	}

	@DataProvider
	public Object[][] getLoginInvalidData() {
		Object data[][] = { { "Jissan", "123" }, { "Piyel", "123455" }, { "Shawon", "1234566" } };

		return data;
	}

	@Test(priority = 4, dataProvider = "getLoginInvalidData", enabled = false)
	public void login_InvalidTestCases(String username, String pwd) {
		userCred.setAppUsername(username);
		userCred.setAppPassword(pwd);
		loginPage.doLogin(userCred);
		Assert.assertTrue(loginPage.checkLoginErrorMessage());
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
