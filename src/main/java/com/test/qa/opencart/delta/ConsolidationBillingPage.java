package com.test.qa.opencart.delta;

public class ConsolidationBillingPage {
	
	int a =10;
	static String value ="Delta";
	final long l = 9797987l;
	
	
	public ConsolidationBillingPage() {
		System.out.println("Default Cons");
	}
	public ConsolidationBillingPage(int a ) {
		System.out.println("Param Constructor");
	}
	
	public void one() {
		System.out.println("First M");
	}
	public static void oneA() {
		System.out.println("Static First M");
	}
	public void two() {
		System.out.println("Second M");
	}
	
	

	public static void main(String[] args) {
		ConsolidationBillingPage cm = new ConsolidationBillingPage();
		cm.one();
		oneA();
		cm.two();

	}

}
