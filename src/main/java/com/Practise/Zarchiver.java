package com.Practise;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class Zarchiver {
	public static void Calculator() {
		DesiredCapabilities caps = new DesiredCapabilities();
		try {
		caps.setCapability("platformName", "Android");
		caps.setCapability("appium:platformVersion", "16"); // Change this to match your device/emulator
		caps.setCapability("appium:deviceName", "emulator-5556"); // Check with `adb devices`
		caps.setCapability("appium:automationName", "UiAutomator2");
		caps.setCapability("appium:app","‪C:\\Users\\chell\\Downloads\\calculator.apk");
		caps.setCapability("appium:ensureWebviewsHavePages", true);
		caps.setCapability("appium:nativeWebScreenshot", true);
		caps.setCapability("appium:newCommandTimeout", 3600);

//		URL serverURL = new URL("http://127.0.01:4723");////wd//hub
		URL serverURL = URI.create("http://127.0.0.1:4723/").toURL();

		AppiumDriver driver = new AndroidDriver(serverURL,caps);
		Thread.sleep(2500);
		System.out.println("Calculator is installed");
		}catch (Exception e) {
			System.out.println(e);		
		}
	}
	
	public static void Demo() {
		DesiredCapabilities caps = new DesiredCapabilities();
		try {
		caps.setCapability("platformName", "Android");
		caps.setCapability("appium:platformVersion", "12"); // Change this to match your device/emulator
		caps.setCapability("appium:deviceName", "emulator-5554"); // Check with `adb devices`
		caps.setCapability("appium:automationName", "UiAutomator2");
		caps.setCapability("appium:app","‪C:\\Users\\chell\\Downloads\\calculator.apk");
		
		caps.setCapability("appium:ensureWebviewsHavePages", true);
		caps.setCapability("appium:nativeWebScreenshot", true);
		caps.setCapability("appium:newCommandTimeout", 3600);
//		caps.setCapability("appium:connectHardwareKeyboard", true);

//		URL serverURL = new URL("http://127.0.01:4723");////wd//hub
		URL serverURL = URI.create("http://127.0.0.1:4723/").toURL();

		AppiumDriver driver = new AndroidDriver(serverURL,caps);
		WebElement pop =  driver.findElement(By.id("android:id/button1"));
		if(pop.isDisplayed()) {
			pop.click();
		}
		driver.findElement(By.id("android:id/search_button")).click();
		WebElement ele1 = driver.findElement(By.id("android:id/search_src_text"));
		ele1.click();
		ele1.sendKeys("Hello");
		driver.quit();

		}
		catch (Exception e) {
			System.out.println(e);		
		}
	}

	public static void demoZar() {
		DesiredCapabilities caps = new DesiredCapabilities();

		try {
		caps.setCapability("platformName", "Android");
		caps.setCapability("appium:platformVersion", "12"); // Change this to match your device/emulator
		caps.setCapability("appium:deviceName", "emulator-5554"); // Check with `adb devices`
		caps.setCapability("appium:automationName", "UiAutomator2");
		caps.setCapability("appPackage","ru.zdevs.zarchiver");
		caps.setCapability("appActivity", "ru.zdevs.zarchiver.ZArchiver");
		caps.setCapability("appium:nativeWebScreenshot", true);
		caps.setCapability("appium:newCommandTimeout", 3600);

		URL serverURL = URI.create("http://127.0.0.1:4723/").toURL();

		AppiumDriver driver = new AndroidDriver(serverURL,caps);
		Thread.sleep(5000);
		System.out.println("Application Started");

		WebElement pop =  driver.findElement(By.id("android:id/button1"));
		if(pop.isDisplayed()) {
			pop.click();
		}
	
		WebElement el1 = driver.findElement(By.id("android:id/button1"));
		el1.click();
		WebElement el2 = driver.findElement(By.id("Search"));
		el2.click();
		WebElement el3 = driver.findElement(By.id("android:id/search_src_text"));
		el3.click();
		el3.sendKeys("downloads");
		WebElement el4 = driver.findElement(By.id("Clear query"));
		el4.click();
		WebElement el5 = driver.findElement(By.id("Navigate up"));
		el5.click();
		WebElement value = driver.findElement(By.id("ru.zdevs.zarchiver:id/nTitle"));
		System.out.println(value.getText());
		driver.quit();

	}catch (Exception e) {
		System.out.println(e);
	}
	}
	public static void main(String[] args)  {
		Calculator() ;
	}
}
