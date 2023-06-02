package com.test.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.qa.base.BaseTest;
import com.test.qa.utils.AppConstants;

public class LoginPageTest extends BaseTest {

	@Test(priority = 1)
	public void loginPageTitleTest() {
		String loginTitle = page.getLoginTitle();
		Assert.assertEquals(loginTitle, AppConstants.LOGIN_PAGE_TITLE_VALUE);
	}

	@Test(priority = 2)
	public void loginPageURLTest() {
		String currentURL = page.getCurrentURL();
		Assert.assertTrue(currentURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION));

	}

//	@Test(priority = 3)
//	public void doRegisterLinkTest() {
//		registerPage = page.registerPageClick();
//		String actualRegisterPageTitle = registerPage.registerPageTitle();
//		System.out.println("Register Page Title:" + actualRegisterPageTitle);
//		Assert.assertEquals(actualRegisterPageTitle, "Register Account");
//
//	}

	@Test(priority = 3)
	public void doLoginTest() {
		// Login me returning acc page object and wit acc page object verifying acc page
		// metods
		accPage = page.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(accPage.getPageTitle().equals(AppConstants.ACCOUNT_PAGE_TITLE_VALUE));
	}

}
