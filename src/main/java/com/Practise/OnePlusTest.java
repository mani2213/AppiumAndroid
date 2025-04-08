package com.Practise;

import java.net.URI;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class OnePlusTest {
	static AppiumDriver driver;
	
	public static void initializeDriver() {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName", "Android");
		caps.setCapability("appium:platformVersion", "14");
		caps.setCapability("appium:deviceName", "632c58cb");
		caps.setCapability("appium:automationName", "UiAutomator2");
		caps.setCapability("appium:appPackage", "Your_App_Package");
		caps.setCapability("appium:appActivity", "Your_App_Activity");
		caps.setCapability("appium:appWaitActivity", "*");
		caps.setCapability("appium:noReset", true);
		caps.setCapability("appium:newCommandTimeout", 3600);
		caps.setCapability("appium:nativeWebScreenshot", true);
		caps.setCapability("appium:disableWindowAnimation", true);
		caps.setCapability("appium:allowTestPackages", true);
		caps.setCapability("appium:ignoreHiddenApiPolicyError", true);

		try {
			URL serverURL = URI.create("http://127.0.0.1:4723/").toURL();
			driver = new AndroidDriver(serverURL, caps);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
			
		} catch (Exception e) {
			System.err.println("Error initializing driver: " + e.getMessage());
		}
	}
	public static void TestSauce() {
		System.out.println("Application Started");
		SwipeHelper.swipeAction(driver,506,2071,506,1551);
		driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"standard_user\")")).click();
		driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='test-LOGIN']")).click();
		SwipeHelper.scrollUntilElementVisible(driver, By.xpath("//android.widget.ScrollView[@content-desc='test-PRODUCTS']/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.ImageView"));
		driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='test-Menu']/android.view.ViewGroup/android.widget.ImageView")).click();
		driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"LOGOUT\")")).click();
		System.out.println("Logged Out");
	}
public static void main(String[] args) {
	initializeDriver();
	TestSauce();
}
}
