package com.test.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.test.qa.utils.ElementUtils;

public class ProductInfoPage {

	WebDriver driver;
	ElementUtils eUtil;
	private Map<String, String> mapList;

	public ProductInfoPage(WebDriver driver) {

		this.driver = driver;
		eUtil = new ElementUtils(this.driver);
	}

	By productHeader = By.xpath("//div[@class='col-sm-4']//h1");
	By productImages = By.xpath("//div[@class='col-sm-8']//img");
	By productSpecifications = By.xpath("(//div[@class='col-sm-4']//ul)[1]//li");
	By productPrice = By.xpath("(//div[@class='col-sm-4']//ul)[2]//li");

	public String getProductHeaderName() {
		return eUtil.doGetText(productHeader);
	}

	public int getProductsImagesCount() {
		return eUtil.visibilityOfElements(productImages, 10).size();
	}

	private void productSpecificationsList() {

		List<WebElement> specifications = eUtil.getFindElements(productSpecifications);
		//mapList = new HashMap<String, String>();
		mapList = new LinkedHashMap<String, String>();
		//mapList = new TreeMap<String, String>();
		
		for (WebElement e : specifications) {
			String values = e.getText();
			String[] listSplit = values.split(":");
			String key = listSplit[0].trim();
			String value = listSplit[1].trim();
			mapList.put(key, value);

		}
	}

//	$2,000.00
//	Ex Tax: $2,000.00

	private void productPriceList() {
		List<WebElement> productPriceList = eUtil.getFindElements(productPrice);
		mapList = new HashMap<String, String>();
		String price = productPriceList.get(0).getText(); // 2000
		String tax = productPriceList.get(1).getText();
		String[] split = tax.split(":");
		String key = split[0].trim(); // ex Tax
		String value = split[1].trim(); // 2000

		mapList.put("No Key", price);
		mapList.put(key, value);
	}

	public Map<String, String> getProductInfo() {
		productSpecificationsList();
		productPriceList();
		mapList.put("Complete Product", getProductHeaderName());
		return mapList;
		
	}

}
