package com.qa.hubspot.tests;

import java.util.Properties;import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.Credentials;



public class HomePageTest {

	LoginPage loginPage;
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	HomePage homePage;
	Credentials usercred;
	
	@BeforeTest
	public void setUp() throws Exception {
		basePage = new BasePage();
		prop = basePage.initialize_properties();
		String browserName = prop.getProperty("browser");
		driver = basePage.initialize_driver(browserName);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		Credentials userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		homePage = loginPage.doLogin(userCred);
		
	}

	@Test(priority = 1)
	public void verifyHomePageTitleTest() throws Exception {
		String homePageTitle = homePage.getHomePageTitle();
		System.out.println(homePageTitle);
		Assert.assertEquals(homePageTitle, AppConstants.HOME_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void verifyHomePageHeaderTest() {
		String header = homePage.getHomePageHeader();
		System.out.println(header);
		Assert.assertEquals(header, AppConstants.HOME_PAGE_HEADER);
	}

	@Test(priority = 3)
	public void verifyLoggedInUserAccountName() {
		String accountName = homePage.getLoggedInUserAccountName();
		System.out.println(accountName);
		Assert.assertEquals(accountName, prop.getProperty("accountName"));
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
