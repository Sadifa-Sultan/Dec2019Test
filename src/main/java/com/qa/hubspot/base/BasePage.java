package com.qa.hubspot.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	WebDriver driver;
	Properties prop;

	public WebDriver initialize_driver(String browserName) throws Exception {
		
		if (browserName.equals("chrome")) {
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}

		else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else {
			System.out.println("browser name" + browserName + "is not found");
		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();

		return driver;

	}

	public Properties initialize_properties() throws IOException {
		Properties prop = new Properties();
		String path = null;
		//String env = null;
		//try {
			//env = System.getProperty("env");
			//if (env.equals("qa")) {
				path = "./src/main/java/com/qa/hubspot/config/qa.config.properties";
			//} else if (env.equals("stg")) {
				//path = "./src/main/java/com/qa/hubspot/config/stage.config.properties";
		//	}
		//} catch (Exception e) {
			//path = "./src/main/java/com/qa/hubspot/config/config.config.properties";
		//}
		try {
			FileInputStream ip = new FileInputStream(path);
			prop.load(ip);
		} catch (FileNotFoundException e) {

			System.out.println("some issue with config.........Please correct your config");
		} 

			
		
		return prop;
	}

}
