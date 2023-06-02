package com.test.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.qa.base.BaseTest;
import com.test.qa.dataproviders.ProductDataProvider;
import com.test.qa.opencart.pages.ResultsPage;
import com.test.qa.pojo.Product;

public class ResultsPageTest extends BaseTest {

	@BeforeClass
	public void resultsPageSetUp() {
		accPage = page.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

//	@DataProvider(name = "productData")
//	public Object[][] getProductTestData() {
//		return new Object[][] {
//
//				{ new Product("Macbook", "MacBook Pro", 4) }, { new Product("IMac", "iMac", 3) },
//				{ new Product("Samsung", "Samsung Galaxy Tab 10.1", 7) },
//				{ new Product("Samsung", "Samsung SyncMaster 941BW", 1) },
//
//		};
//
//	}

	@Test(dataProvider = "productData", dataProviderClass = ProductDataProvider.class)
	public void doSearcProductTest(Product product) {
		resultsPage = accPage.doSearc(product.getSearcKey());
		int productResultCount = resultsPage.getProductResultCount();
		Assert.assertTrue(productResultCount > 0);

	}

	@Test(dataProvider = "productData", dataProviderClass = ProductDataProvider.class)
	public void searcPageTitleTest(Product product) {
		resultsPage = accPage.doSearc(product.getSearcKey());
		String actualSearcTitle = resultsPage.getResultsPageTitle("Search - " + product.getSearcKey());
		System.out.println("Searc page Actual Title:" + actualSearcTitle);
		Assert.assertEquals(actualSearcTitle, "Search - " + product.getSearcKey());
	}

	@Test(dataProvider = "productData", dataProviderClass = ProductDataProvider.class)
	public void selectProductTest(Product product) {
		resultsPage = accPage.doSearc(product.getSubProduct());
		productInfoPage = resultsPage.selectProduct(product.getSubProduct());
		String actualProductTitle = productInfoPage.getProductHeaderName();
		Assert.assertEquals(actualProductTitle, product.getSubProduct());

	}

	// Product info page - Combination of 2 pages ere
	@Test(dataProvider = "productData", dataProviderClass = ProductDataProvider.class)
	public void productImageTest(Product product) {
		resultsPage = accPage.doSearc(product.getSearcKey());
		productInfoPage = resultsPage.selectProduct(product.getSubProduct());
		int actualProductImages = productInfoPage.getProductsImagesCount();
		System.out.println("Actual Product Images count :" + actualProductImages);
		Assert.assertEquals(actualProductImages, product.getImagesCount());

	}

}
