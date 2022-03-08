package com.qa.hubspot.util;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.hubspot.base.BasePage;

public class ElementUtil {
	WebDriver driver;
	WebDriverWait wait;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		
	}

	public boolean waitForElementPresent(By locator) {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return true;
	}

	public boolean waitForElement(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return true;
	}

	public boolean waitForTitlePresent(String title) {
		wait.until(ExpectedConditions.titleIs(title));

		return true;
	}

	public boolean waitForElementVisible(By locator) {

		return true;
	}

	public String doGetPageTitle() {
		try {
			return driver.getTitle();
		} catch (Exception e) {
			System.out.println("some exceptions happended while getting the title");
		}
		return null;
	}

	public WebElement getElement(By locator) {
		WebElement element = null;
		try {
			// if (waitForElementPresent(locator));

			element = driver.findElement(locator);
		} catch (Exception e) {
			System.out.println("some exception got occured while creating the web element.....");
		}
		return element;
	}

	public void doClick(By locator) {
		try {
			getElement(locator).click();
		} catch (Exception e) {
			System.out.println("some exception got occured while creating web element....");
		}
	}

	public void doSendKeys(By locator, String value) {
		try {
			WebElement ele = getElement(locator);
			ele.clear();
			ele.sendKeys(value);
		} catch (Exception e) {
			System.out.println("some exception got occured while sending keys");
		}
	}

	public boolean doIsDisplayed(By locator) {
		try {
			return getElement(locator).isDisplayed();
		} catch (Exception e) {
			System.out.println("some error occured while displaying web element");
		}
		return false;
	}

	public String doGetText(By locator) {
		try {
			return getElement(locator).getText();
		} catch (Exception e) {
			System.out.println("some error occured while getting text from a web element");
		}
		return null;
	}

}