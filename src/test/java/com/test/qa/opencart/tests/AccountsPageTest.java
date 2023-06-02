package com.test.qa.opencart.tests;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.qa.base.BaseTest;
import com.test.qa.opencart.pages.AccountsPage;
import com.test.qa.utils.AppConstants;

public class AccountsPageTest extends BaseTest{
	
	// Pre- condition as Login is needed for Accounts Page Test
	//AccountsPage accPage;
	@BeforeClass
	public void accPageSetUp() {
		accPage = page.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@Test
	public void accPageTitleTest() {
		String pageTitle = accPage.getPageTitle();
		Assert.assertEquals(pageTitle, AppConstants.ACCOUNT_PAGE_TITLE_VALUE);
	}
	
	@Test
	public void accountPageTest() {
		List<String> accountPageHeadersList = accPage.getAccountPageHeadersList();
		Assert.assertEquals(accountPageHeadersList.size(), 4);
	}
	
	@Test
	public void accountPageListTest() {
		List<String> accountPageHeadersList = accPage.getAccountPageHeadersList();
		//Assert.assertTrue(accountPageHeadersList.contains("My Account"));
		List<String> expected = Arrays.asList("My Account", "My Orders", "My Affiliate Account", "Newsletter");
		Collections.sort(accountPageHeadersList);
		Collections.sort(expected);
		Assert.assertEquals(accountPageHeadersList, expected);
	}
	
		
	
	

	

}
