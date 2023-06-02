package com.test.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.test.qa.utils.AppConstants;
import com.test.qa.utils.ElementUtils;

// No Assertion - Don't use
//Private webdriver
//public constructor
//private By loc

public class LoginPage {

	private WebDriver driver;
	private ElementUtils eUtil;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eUtil = new ElementUtils(this.driver);
	}

	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPassword = By.linkText("Forgotten Password");
	private By footerLinks = By.xpath("//footer//a");
	private By registerPage = By.linkText("Register");

	public String getLoginTitle() { // Non webelement
		String title = eUtil.waitForTitleIsAndCapture(AppConstants.LOGIN_PAGE_TITLE_VALUE, 10);

		return title;
	}

	public String getCurrentURL() {
		String currentUrl = eUtil
				.waitForURLAndCapture(AppConstants.LOGIN_PAGE_URL, 10);

		return currentUrl;
	}

	public Boolean isForgotPwdLinkAvailable() {
//		WebElement forPwd = driver.findElement(forgotPassword);
//		boolean displayed = forPwd.isDisplayed();
//		return displayed;
		return eUtil.isDisplayed(forgotPassword);

	}

	public List<String> getFooterLinksList() {
		// List<WebElement> list = driver.findElements(footerLinks);
		List<WebElement> list = eUtil.visibilityOfElements(footerLinks, 10);
		List<String> completeList = new ArrayList<String>();
		for (WebElement e : list) {
			String text = e.getText();
			completeList.add(text);
		}
		return completeList;

	}
	
	// Return -- Register Page
	// Dont use starig forward metod name
	public RegisterPage registerPageClick() {
		eUtil.visibilityOfElement(registerPage, 10).click();
		return new RegisterPage(driver);
		
	}

	// Return page - Accounts
	public AccountsPage doLogin(String userName, String pwd) {
//		driver.findElement(emailId).sendKeys(userName);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(loginBtn).click();
//		return new AccountsPage(driver);

		eUtil.visibilityOfElement(emailId, 10).sendKeys(userName);
		eUtil.doSendKeys(password, pwd);
		eUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}

}
