
package com.Practise;

import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class LongPress {

	 static AppiumDriver driver;
	
	
	public static void initializeDriver() {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName", "Android");
		caps.setCapability("appium:platformVersion", "14");
		caps.setCapability("appium:deviceName", "632c58cb");
		caps.setCapability("appium:automationName", "UiAutomator2");
		caps.setCapability("appium:appPackage", "com.android.launcher");
		caps.setCapability("appium:appActivity", "com.android.launcher.Launcher");
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
			WebElement element = driver.findElement(By.xpath("//android.widget.RelativeLayout[@resource-id='net.oneplus.widget:id/widget_layout']"));
			longPress(driver, element); 
	        Thread.sleep(3000);
	        driver.quit();
		} catch (Exception e) {
			System.err.println("Error initializing driver: " + e.getMessage());
		}
	}
	public static void longPress(AppiumDriver driver, WebElement element) {
	    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
	    Sequence longPress = new Sequence(finger, 1)
	            .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), 
	                     element.getLocation().getX(), element.getLocation().getY()))
	            .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
	            .addAction(new Pause(finger, Duration.ofSeconds(2))) // Hold for 2 seconds
	            .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

	    driver.perform(Collections.singletonList(longPress));
	}
	public static void longPressByOffset(AppiumDriver driver, int x, int y) {
	    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");

	    Sequence longPress = new Sequence(finger, 1)
	            .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y))
	            .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
	            .addAction(new Pause(finger, Duration.ofSeconds(2)))  // Hold for 2 seconds
	            .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

	    driver.perform(Collections.singletonList(longPress));
	    System.out.println("Long press performed at (" + x + ", " + y + ")");
	}
	
	public static void main(String[] args) {
		initializeDriver();
	}
}
