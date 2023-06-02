package com.test.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.test.qa.utils.ElementUtils;

public class ResultsPage {
	
	WebDriver driver;
	ElementUtils eUtil;
	
	public ResultsPage(WebDriver driver) {
		this.driver=driver;
		eUtil = new ElementUtils(this.driver);
	}

	By resultsPageProduct = By.xpath("//div[@id='content']//div[@class='product-layout product-grid col-lg-3 col-md-3 col-sm-6 col-xs-12']");
	
	// Page Actions
	
	public String getResultsPageTitle(String searc) {
		return eUtil.waitForFullTitleAndCapture(searc, 10);
	}
	
	public int getProductResultCount(){
		int resultCount = eUtil.visibilityOfElements(resultsPageProduct, 10).size();
		System.out.println("Product Searc Count:" + resultCount);
		return resultCount;
		
	}
	public ProductInfoPage selectProduct(String productName) {
		By productNameLocator = By.linkText(productName);
		eUtil.doClick(productNameLocator);
		return new ProductInfoPage(driver);
	}
}
