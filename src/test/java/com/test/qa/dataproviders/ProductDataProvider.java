package com.test.qa.dataproviders;

import org.testng.annotations.DataProvider;

import com.test.qa.pojo.Product;

public class ProductDataProvider {
	
	
	@DataProvider(name = "productData")
	public Object[][] getProductTestData() {
		return new Object[][] {

				{ new Product("Macbook", "MacBook Pro", 4) }, { new Product("IMac", "iMac", 3) },
				{ new Product("Samsung", "Samsung Galaxy Tab 10.1", 7) },
				{ new Product("Samsung", "Samsung SyncMaster 941BW", 1) },

		};

	}
	
	@DataProvider
	public Object[] testDataForProductInfoTest() {
		return new Object[][] {
			{"Macbook","MacBook Pro","$2,000.00"},
			{"IMac","iMac","$100.00"},
			{"Samsung","Samsung Galaxy Tab 10.1","$199.99"}
		
	};
	}

}
