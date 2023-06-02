package com.test.qa.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.test.qa.opencart.factory.DriverFactory;
import com.test.qa.opencart.pages.AccountsPage;
import com.test.qa.opencart.pages.LoginPage;
import com.test.qa.opencart.pages.ProductInfoPage;
import com.test.qa.opencart.pages.RegisterPage;
import com.test.qa.opencart.pages.ResultsPage;


public class BaseTest {
	
	
	
	WebDriver driver;
	protected LoginPage page; // Public or Protected
	protected AccountsPage accPage;
	protected ResultsPage resultsPage;
	protected ProductInfoPage productInfoPage;
	protected RegisterPage registerPage;
	
	
	
	DriverFactory df;
	protected Properties prop;
	protected SoftAssert softAssert;
	
	@Parameters({"browser"})
	@BeforeTest
	public void setUp(String browserName) {
		
		
		df = new DriverFactory(); // Object of Driver
		prop = df.initProp(); // Call Init Prop - to Make connection
		if (browserName!=null) {
			prop.setProperty("browser", browserName);
		}
		driver = df.initDriver(prop); // Pass Property reference in Init driver
		
		page = new LoginPage(driver);
		softAssert = new SoftAssert();
		
	}
	

	@AfterTest()
	public void tearDown() {
		driver.quit();

	}
	

}
