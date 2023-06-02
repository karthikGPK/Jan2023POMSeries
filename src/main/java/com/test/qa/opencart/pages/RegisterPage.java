package com.test.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.test.qa.utils.ElementUtils;

public class RegisterPage {

	private WebDriver driver;
	private ElementUtils eUtil;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eUtil = new ElementUtils(this.driver);
	}

	//

	By firstName = By.id("input-firstname");
	By lastName = By.id("input-lastname");
	By email = By.id("input-email");
	By tele = By.id("input-telephone");
	By password = By.id("input-password");
	By confirmPassword = By.id("input-confirm");
	By policyBox = By.xpath("//input[@name='agree']");
	By continueBtn = By.xpath("//input[@class='btn btn-primary']");

	public void doRegisterUser() {
		eUtil.doSendKeys(firstName, "KGP");
		eUtil.doSendKeys(lastName, "Value");
		eUtil.doSendKeys(email, "abc@info1.com");
		eUtil.doSendKeys(tele, "8902345551");
		eUtil.doSendKeys(password, "Qwerty123");
		eUtil.doSendKeys(confirmPassword, "Qwerty123");
		eUtil.clickElementWhenReady(policyBox, 10);
		eUtil.clickElementWhenReady(continueBtn, 10);

		

	}

	public String registerPageTitle() {
		return eUtil.waitForFullTitleAndCapture("Register Account", 10);

	}

}
