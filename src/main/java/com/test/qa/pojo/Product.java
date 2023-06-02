package com.test.qa.pojo;

public class Product {
	
	private String searcKey;
	private String subProduct;
	private int imagesCount;
	
	public Product(String searcKey, String subProduct, int imagesCount) {
		
		this.searcKey = searcKey;
		this.subProduct = subProduct;
		this.imagesCount = imagesCount;
	}

	public String getSearcKey() {
		return searcKey;
	}

	public void setSearcKey(String searcKey) {
		this.searcKey = searcKey;
	}

	public String getSubProduct() {
		return subProduct;
	}

	public void setSubProduct(String subProduct) {
		this.subProduct = subProduct;
	}

	public int getImagesCount() {
		return imagesCount;
	}

	public void setImagesCount(int imagesCount) {
		this.imagesCount = imagesCount;
	}
	
	// Generate --ToString

	@Override
	public String toString() {
		return "Product [searcKey=" + searcKey + ", subProduct=" + subProduct + ", imagesCount=" + imagesCount + "]";
	}
	
	
	
	
	
	
	

}
