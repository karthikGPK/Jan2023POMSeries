package com.test.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.qa.base.BaseTest;
import com.test.qa.dataproviders.ProductDataProvider;
import com.test.qa.opencart.pages.ProductInfoPage;

public class ProductInfoPageTest extends BaseTest {
	
	@BeforeClass
	public void productPageSetUp() {
		accPage = page.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	
//	@DataProvider
//	public Object[] testDataForProductInfoTest() {
//		return new Object[][] {
//			{"Macbook","MacBook Pro","$2,000.00"},
//			{"IMac","iMac","$100.00"},
//			{"Samsung","Samsung Galaxy Tab 10.1","$199.99"}
//		
//	};
//	}
	
	
	@Test(dataProvider = "testDataForProductInfoTest", dataProviderClass = ProductDataProvider.class)
	public void productInfoTest(String mainProduct, String subProduct, String exTax) {
		resultsPage = accPage.doSearc(mainProduct);
		productInfoPage = resultsPage.selectProduct(subProduct);
		Map<String, String> productInfo = productInfoPage.getProductInfo();
		System.out.println(productInfo);
		Assert.assertEquals(productInfo.get("Complete Product"), subProduct);
		Assert.assertEquals(productInfo.get("Ex Tax"), exTax);
		softAssert.assertAll();
		
		//softAssert.assertEquals(false, false);
		
		
	}
	 

}
