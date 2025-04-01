package com.Practise;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SeleniumActions extends GlobalPayAutomation{
	static WebDriverWait  webDriverWait= new WebDriverWait(driver, Duration.ofSeconds(90));
	static Actions action = new Actions(driver);
	
	public static void SendEmployeeDetails(WebElement element,String data) {
		webDriverWait.until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(data);	
	}
	public static void sendKeysToClear(WebElement element) {
		element.sendKeys(Keys.CONTROL + "a");
		element.sendKeys(Keys.DELETE);
		element.clear();
	}
	public static void click(WebElement element) {
		webDriverWait.until(ExpectedConditions.visibilityOf(element));
		webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}	
	public static String getText(WebElement element) {
		webDriverWait.until(ExpectedConditions.visibilityOf(element));
				return element.getText();
	}	
	public static void enterText(WebElement element, String valueToEnter) {
		webDriverWait.until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(valueToEnter);
	}
	public static void waittillElement(WebElement element) {
		webDriverWait.until(ExpectedConditions.visibilityOf(element));
	}
	public static void waittillElementisPresent(WebElement element) {
		webDriverWait.until(ExpectedConditions.visibilityOf(element));
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(null));
	}
	public static void scrollToElement(WebElement element) {
		webDriverWait.until(ExpectedConditions.visibilityOf(element));
		action.scrollToElement(element);
	}
}
