package com.test.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
// To initialize Driver
	
	WebDriver driver;
	OptionsManager optionsManager;
	public static final ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();
	
	                            // Call by Reference - to call and read multiple properties from config file
	                            // rigt now - 2 confi prop is used - browser and url
	
	
	
	public WebDriver initDriver(Properties prop) {
		// property - M
		String browserName = prop.getProperty("browser").trim();
		
		System.out.println("Browser name is:" + browserName);
		optionsManager = new OptionsManager(prop);
		switch (browserName.toLowerCase()) {
		case "chrome":
			//driver = new ChromeDriver(optionsManager.getCromeOptions());
			tldriver.set(new ChromeDriver(optionsManager.getCromeOptions())); // Set and Get
			break;
		case "firefox":
			//driver = new FirefoxDriver(optionsManager.getfireFoxOptions());
			tldriver.set(new FirefoxDriver(optionsManager.getfireFoxOptions()));
			break;
		case "edge":
			//driver = new EdgeDriver(optionsManager.getEdgeOptions());
			tldriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;

		default:
			System.out.println(" Please pass the right browser:" + browserName);
			break;
		}
		getDriver().get(prop.getProperty("url"));
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		return getDriver();
		
	}
	
	public synchronized static WebDriver getDriver() { // new kyword sync every metod will get a copy
		return tldriver.get(); // -- Will return tlocal Webdriver
	}
	
	public Properties initProp() {
		
		Properties prop = new Properties();
		
		FileInputStream input = null;
		
		String envName = System.getProperty("env");
		System.out.println("Env name is :" + envName);
		
		try {
		if(envName==null) {
			System.out.println("Environment name is null ....Running on default confi prop");
			input = new FileInputStream("./src/main/resources/Config/config.properties");
		}
		else {
			System.out.println("Running test cases on:" + envName);
			switch (envName.toLowerCase().trim()) {
			case "stage":
				input = new FileInputStream("./src/main/resources/Config/stage.config.properties");
				break;
			case "uat":
				input = new FileInputStream("./src/main/resources/Config/UAT.config.properties");
				break;

			default:
				System.out.println("please pass rigt env:" + envName);
				break;
			}
		}
		
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(input);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return prop;
	}
}

	
	

