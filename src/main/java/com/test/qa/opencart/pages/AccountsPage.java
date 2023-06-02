package com.test.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.test.qa.utils.AppConstants;
import com.test.qa.utils.ElementUtils;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtils eUtil;

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eUtil = new ElementUtils(this.driver);
	}

	
	// Locators in Private
	private By logout = By.linkText("Logout");
	private By myAccount = By.linkText("My Account");
	private By accHeaders = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");

	public String getPageTitle() {
//		String title = driver.getTitle();
//		System.out.println(title);
//		return title;
		return eUtil.waitForFullTitleAndCapture(AppConstants.ACCOUNT_PAGE_TITLE_VALUE, 10);
		
		
	}

	public String getCurrentURL() {
//		String currentUrl = driver.getCurrentUrl();
//		System.out.println(currentUrl);
//		return currentUrl;
		return eUtil.waitForURLAndCapture(AppConstants.LOGIN_PAGE_URL, 10);
	}

	public List<String> getAccountPageHeadersList() {
		//List<WebElement> list = driver.findElements(accHeaders);
		List<WebElement> list = eUtil.getFindElements(accHeaders);
		List<String> completeList = new ArrayList<String>();

		for (WebElement e : list) {
			String text = e.getText();
			completeList.add(text);

		}
		return completeList;

	}
	
	public ResultsPage doSearc(String value) {
		WebElement findElement = driver.findElement(search);
		findElement.clear();
		findElement.sendKeys(value);
		driver.findElement(searchIcon).click();
		return new ResultsPage(driver);
	}

}
