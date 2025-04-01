package com.Practise;

import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class ScrollElement {
	static AppiumDriver driver;
	public static void initializeDriver() {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName", "Android");
		caps.setCapability("appium:platformVersion", "14");
		caps.setCapability("appium:deviceName", "632c58cb");
		caps.setCapability("appium:automationName", "UiAutomator2");
		caps.setCapability("appium:appPackage", "com.swaglabsmobileapp");
		caps.setCapability("appium:appActivity", "com.swaglabsmobileapp.SplashActivity");
		caps.setCapability("appium:appWaitActivity", "*");
		caps.setCapability("appium:noReset",true);
		caps.setCapability("appium:newCommandTimeout", 3600);
		caps.setCapability("appium:nativeWebScreenshot",true);
		caps.setCapability("appium:disableWindowAnimation",true);
		caps.setCapability("appium:allowTestPackages",true);
		caps.setCapability("appium:ignoreHiddenApiPolicyError",true);
		
		try {
			URL serverURL = URI.create("http://127.0.0.1:4723/").toURL();
			driver = new AndroidDriver(serverURL, caps);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
			WebElement ele= driver.findElement(By.xpath("//android.widget.TextView[@text='standard_user']"));
			SwipeHelper.scrollUntilElementVisible(driver, By.xpath("//android.widget.TextView[@text='standard_user']"));
			ele.click();
			driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='test-LOGIN']")).click();
			WebElement element = driver.findElement(By.xpath("//android.widget.ScrollView[@content-desc='test-PRODUCTS']/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.ImageView"));
	        Thread.sleep(3000);
			SwipeHelper.scrollUntilElementVisible(driver, By.xpath("//android.widget.ScrollView[@content-desc='test-PRODUCTS']/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.ImageView"));
	        driver.quit();
		} catch (Exception e) {
			System.err.println("Error initializing driver: " + e.getMessage());
		}
	}
	
	public static void scrollToElement(AppiumDriver driver, WebElement element) {
	    int maxScrolls = 10; 
	    int scrollCount = 0;

	    while (scrollCount < maxScrolls) {
	        try {
	            if (element.isDisplayed()) {
	                System.out.println("Element found!");
	                return;
	            }
	        } catch (Exception e) {
	            System.out.println("Scrolling... Attempt " + (scrollCount + 1));

	            Map<String, Object> params = new HashMap<>();
	            params.put("left", 100);  
	            params.put("top", 200);
	            params.put("width", 800);
	            params.put("height", 1600);
	            params.put("direction", "down");
	            params.put("percent", 3.0); // Scroll step size

	            ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", params);
	            scrollCount++;
	        }
	    }

	    System.out.println("Element not found after " + maxScrolls + " scrolls.");
	}
	public static void main(String[] args) {
		initializeDriver();
	}
}
