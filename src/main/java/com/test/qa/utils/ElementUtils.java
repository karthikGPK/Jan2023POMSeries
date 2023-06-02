package com.test.qa.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtils {
	WebDriver driver;
	private final int DEFAULT_TIME_OUT = 5;

	public ElementUtils(WebDriver driver) {
		this.driver = driver;

	}

	// FindElement -- Try , w/o wait concept
	public WebElement getFindElemnet(By locator) { // WebElement Return
		WebElement element = null;
		try {
			element = driver.findElement(locator);
			System.out.println("element is found with locator: " + locator);
		} catch (NoSuchElementException e) {
			System.out.println("Element is not found using this locator..." + locator);
			element = visibilityOfElement(locator, DEFAULT_TIME_OUT);
		}
		return element;
	}		

	

	//element.sendkeys
	public void doSendKeys(By locator, String value) { // element --send, click, getText,clear
		if(value==null) {
			System.out.println("null values are not allowed"); // Invalid Argument Exception
		}
		doClear(locator);
		getFindElemnet(locator).sendKeys(value);
	}

	//element.click
	public void doClick(By locator) { // click
		driver.findElement(locator).click();
	}
	
	// Do Click wit WebDriver Wait 
	public void doClick(By locator, int timeOut) { // click
		WebElement checkElementClickable = checkElementClickable(locator, timeOut);
		checkElementClickable.click();
	}
	
	// Element visible wit WebDriver Wait
	public WebElement getElement(By locator, int timeOut) {
		return visibilityOfElement(locator, timeOut);
	}


	//element.clear
	public void doClear(By locator) { // clear
		driver.findElement(locator).click();
	}

	//element.getText
	public String doGetText(By locator) { // getText
		String text = driver.findElement(locator).getText();
		return text;
	}

	//element.getAttribute
	public String doGetAttribute(By locator, String value) {
		return getFindElemnet(locator).getAttribute(value);
	}

	// Utility for IsDisplayed
	// Element.is Displayed
	public Boolean isDisplayed(By Locator) {
		return getFindElemnet(Locator).isDisplayed();
	}

	//FindElements -- List
	public List<WebElement> getFindElements(By locator) { // WebElement Return
		return driver.findElements(locator);
	}

	//Elements.size
	public int doGetElementsCount(By locator) {
		int size = getFindElements(locator).size();
		return size;
	}

	//Elements -- get All
	public void doGetAll(By locator) {
		List<WebElement> findElements = getFindElements(locator);
		for (int i = 0; i < findElements.size(); i++) {
			String text = findElements.get(i).getText();
			System.out.println(text); //
		}

	}

	//Elements - List -- GetAttribute
	public List<String> doGetElementsAttribute(By locator, String value) {
		List<WebElement> findElements = getFindElements(locator);
		List<String> s = new ArrayList<String>();
		for (WebElement e : findElements) {
			String list = e.getAttribute(value);
			s.add(list); // use of List

		}
		return s;
	}

	//Elements - isDsiplayed
	public  Boolean doVerifyElementsDisplayed(By locator) {
		boolean flag = false;
		if (getFindElements(locator).size() > 0) {
			flag = true;

		} else {
			flag = false;
		}
		return flag;

	}
	
	public void search(String searchKey, By searchLocator, String suggName, By suggestions)
			throws InterruptedException {

		doSendKeys(searchLocator, searchKey);
		Thread.sleep(3000);

		List<WebElement> suggList = getFindElements(suggestions);
		System.out.println("total suggestions: " + suggList.size());

		if (suggList.size() > 0) {

			for (WebElement e : suggList) {
				String text = e.getText();
				if (text.length() > 0) {
					System.out.println(text);
					if (text.contains(suggName)) {
						e.click();
						break;
					}
				} else {
					System.out.println("blank values -- no sugeestions");
					break;
				}
			}
		} else {
			System.out.println("no search suggestions found");
		}

	}

	///// **********Dropdown utils********
// Index
	public void doSelectByIndex(By loc, int indexPosition) {
		WebElement findElement = getFindElemnet(loc);
		Select select = new Select(findElement);
		select.selectByIndex(indexPosition);

	}

//Value
	public void doSelectByValue(By loc, String value) {
		WebElement findElement = getFindElemnet(loc);
		Select select = new Select(findElement);
		select.selectByValue(value);

	}

//VisibleText
	public void doSelectByVisibleText(By loc, String text) {
		WebElement findElement = getFindElemnet(loc);
		Select select = new Select(findElement);
		select.selectByVisibleText(text);

	}

	// Deselect Index - only for multiselect
	public void doDeselectByIndex(By loc, int indexPosition) {
		WebElement findElement = getFindElemnet(loc);
		Select select = new Select(findElement);
		select.deselectByIndex(indexPosition);

	}

//Deslect Value - only for multiselect
	public void doDeselectByValue(By loc, String value) {
		WebElement findElement = getFindElemnet(loc);
		Select select = new Select(findElement);
		select.deselectByValue(value);

	}

//VisibleText - only for multiselect
	public void doDeselectByVisibleText(By loc, String text) {
		WebElement findElement = getFindElemnet(loc);
		Select select = new Select(findElement);
		select.deselectByVisibleText(text);

	}

//Size - Int
	public int doGetDropdownSize(By loc) {
		WebElement findElement = getFindElemnet(loc);
		Select select = new Select(findElement);
		List<WebElement> options = select.getOptions();
		return options.size();
	}

//Fect all values - List
	public List<String> doGetAllValuesFromDropdown(By loc) {
		WebElement findElement = getFindElemnet(loc);
		Select select = new Select(findElement);
		List<WebElement> options = select.getOptions();
		
		List<String> completeList = new ArrayList<String>();
		
		for (WebElement e : options) {
			String text = e.getText();
			completeList.add(text);
		}
		return completeList;
	}

	// Select a value by not using value,index,text
	public boolean doSelectValueFromDropdown(By loc, String value) {
		boolean flag = false;
		WebElement findElement = getFindElemnet(loc);
		Select select = new Select(findElement);
		List<WebElement> options = select.getOptions();

		for (WebElement e : options) {
			String text = e.getText();

			if (text.equals(value)) {
				flag = true;
				e.click();
				break;
			}

		}
		if (flag == false) {
			System.out.println("Value you'r looking is not found :" + " " + value);
		}
		return flag;
	}

// w/o select tag
	public boolean doSelectValueFromDropdownWithoutSelect(By loc, String value) {
		boolean flag = false;
		List<WebElement> optionsList = getFindElements(loc);
		for (WebElement e : optionsList) {
			String text = e.getText();
			if (text.equals(value)) {
				flag = true;
				e.click();
				break;
			}
		}
		if (flag == false) {
			System.out.println("Value you'r looking is not found :" + " " + value);
		}
		return flag;
	}

	// is Mutiple -- Boolean
	public boolean doCheckIsMultiple(By loc) {
		WebElement findElement = getFindElemnet(loc);
		Select select = new Select(findElement);
		return select.isMultiple();
	}
	
///// **********Dropdown utils********
	

	// Actions - Context Click
	public  void rightCLick(By locator) {
		Actions action = new Actions(driver);
		action.contextClick(getFindElemnet(locator)).build().perform();
	}
	
	// Actions - Move 2 Element
	public  void moveToElement(By locator) {
		Actions action = new Actions(driver);
		action.moveToElement(getFindElemnet(locator)).build().perform();
		
	}
// Actions : Drag and Drop
	public  void dragAndDrop(By sourceLoc, By destinationLoc) {
		Actions action = new Actions(driver);
		action.dragAndDrop(getFindElemnet(sourceLoc), getFindElemnet(destinationLoc)).build().perform();
	}
	// Actions : Double Click
	public  void doubleClick(By loc) {
		Actions action = new Actions(driver);
		action.doubleClick(getFindElemnet(loc)).build().perform();
	}
	
	// Actions : MultiLevel Menu - Big Basket Application
	public void multiLevelMenu(By firstElementloc, By firstLevelloc, String secondLeve, By lastLevelloc ) throws InterruptedException {
		Actions action = new Actions(driver);
				
				WebElement firstElement = getFindElemnet(firstLevelloc);
				moveToElement(firstElementloc);
				Thread.sleep(2000);

				WebElement firstLevel = getFindElemnet(firstLevelloc);
				moveToElement(firstLevelloc);
				Thread.sleep(2000);

				WebElement secondLevel = getFindElemnet(By.linkText(secondLeve));
				action.moveToElement(secondLevel).build().perform();
				Thread.sleep(2000);
				
				WebElement lastLevel = getFindElemnet(lastLevelloc);
				moveToElement(lastLevelloc);
				Thread.sleep(2000);
				
				doClick(lastLevelloc);
			}
	
	// Actions : 2 level 
	public void handleTwoLevelMenu(By parentMenu, By childMenu) throws InterruptedException {
		moveToElement(parentMenu);
		Thread.sleep(2000);
		doClick(childMenu);
	}
	
	//***************Wait Concept : WebDriver Wait**************
	
	//Presence 
	public WebElement presenceOfElement(By locator, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public WebElement presenceOfElement(By locator, int seconds, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds),Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	//Visibility of single
	public WebElement visibilityOfElement(By locator, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public WebElement visibilityOfElement(By locator, int seconds, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds),Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	//Present : MultiElements
	public List<WebElement> presenceOfElements(By locator, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}
	
	public List<WebElement> presenceOfElements(By locator, int seconds, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds), Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}
	
	
	
	//Visibility : MultiElements
	public List<WebElement> visibilityOfElements(By locator, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	//Visibility : single element : findElemnt as argument
	public WebElement visibilityOfElement1(By locator, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		return wait.until(ExpectedConditions.visibilityOf(getFindElemnet(locator)));
	}
	
	//Visibility : MultiElement : findElements as Argument
	public List<WebElement> visibilityOfElements1(By locator, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		return wait.until(ExpectedConditions.visibilityOfAllElements(getFindElements(locator)));
	}
	
	
	// Alert
	//  No need to use driver.SitcTo().Alert()
	public Alert alertWait(int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	//AlrtGtTxt
	public String alertWaitGetText(int seconds) {
		return alertWait(seconds).getText();
	}
	
	// Alert Accept
	public void alertWaitAccept(int seconds) {
		alertWait(seconds).accept();
	}
	
	//Alert : Dismiss
	public void alertWaitDismiss(int seconds) {
		alertWait(seconds).dismiss();
	}
	//Alert : sendKeys
	public void alertWaitSendKeys(int seconds, String value) {
		alertWait(seconds).sendKeys(value);
	}
	
	//Title Contains
	public String waitForTitleIsAndCapture(String titleFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if(wait.until(ExpectedConditions.titleContains(titleFraction))) {
			String title = driver.getTitle();
			return title;
		}
		else {
			System.out.println("title is not present within the given timeout : " + timeOut);
			return null;
		}
	}
	
	// Full Title
	public String waitForFullTitleAndCapture(String titleVal, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if(wait.until(ExpectedConditions.titleIs(titleVal))) {
			String title = driver.getTitle();
			return title;
		}
		else {
			System.out.println("title is not present within the given timeout : " + timeOut);
			return null;
		}
	}
	
	
	// URL Contains
	public String waitForURLContainsAndCapture(String urlFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if(wait.until(ExpectedConditions.urlContains(urlFraction))) {
			String url = driver.getCurrentUrl();
			return url;
		}
		else {
			System.out.println("url is not present within the given timeout : " + timeOut);
			return null;
		}
	}
	
	// URL Capture
	public String waitForURLAndCapture(String urlValue, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if(wait.until(ExpectedConditions.urlToBe(urlValue))) {
			String url = driver.getCurrentUrl();
			return url;
		}
		else {
			System.out.println("url is not present within the given timeout : " + timeOut);
			return null;
		}
	}
	
	// Wait: Frame
	// no need to use switcTo()
	public void frameWaitByLocator(By locator, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}
	
	//Wait : Frame id / string
	public void frameWaitStringOrID(String idOrName, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idOrName));
	}
	
	public void frameWaitIndex(int index, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
	}
	
	//wait : Frame element
	public void frameWaitElement(WebElement frameElement, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}
	
	//wait : Windows 
	public boolean waitForTotalWindows(int totalWindowsToBe, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.numberOfWindowsToBe(totalWindowsToBe));
	}
	
	// Wait: Element isclickable
	public WebElement checkElementClickable(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	// wait : Click
	public void clickElementWhenReady(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
	
	// *************Fluent Wait**************
	
	// Alert: Fluent Wait
	public Alert waitForAlertJsPopUpWithFluentWait(int timeOut, int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
				.ignoring(NoAlertPresentException.class)
				.pollingEvery(Duration.ofSeconds(pollingTime))
				.withMessage("------time out is done...Alert is not found.....");
		
		return wait.until(ExpectedConditions.alertIsPresent());

	}
	// Visibility of Element : Fluent Wait
	public WebElement waitForElementVisibleWithFluentWait(By locator, int timeOut, int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class)
				.ignoring(ElementNotInteractableException.class)
				.pollingEvery(Duration.ofSeconds(pollingTime))
				.withMessage("------time out is done...element is not found.....");
		
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}
	
	// Present of Element: Fluent Wait
	
	public WebElement waitForElementPresenceWithFluentWait(By locator, int timeOut, int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class)
				.ignoring(ElementNotInteractableException.class)
				.pollingEvery(Duration.ofSeconds(pollingTime))
				.withMessage("------time out is done...element is not found.....");
		
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}
	

}



