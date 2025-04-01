package com.Practise;

import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class DragDrop {
	static AppiumDriver driver;
	
	public static void initializeDriver() {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName", "Android");
		caps.setCapability("appium:platformVersion", "14");
		caps.setCapability("appium:deviceName", "632c58cb");
		caps.setCapability("appium:automationName", "UiAutomator2");
		caps.setCapability("appium:appPackage", "com.wdiodemoapp");
		caps.setCapability("appium:appActivity", "com.wdiodemoapp.MainActivity");
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
			driver.findElement(By.xpath("//android.widget.TextView[@text='Drag']")).click();
			
	        WebElement source = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='drag-c1']/android.widget.ImageView"));
	        WebElement target = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='drop-c1']/android.view.ViewGroup"));

//	        dragAndDrop(source, target);
	        dragAndDropMobileGesture(source, target);
	        Thread.sleep(3000);
	        driver.quit();
		} catch (Exception e) {
			System.err.println("Error initializing driver: " + e.getMessage());
		}
	}
	public static void DemoApp() {
		try {
		WebElement source1 = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='drag-c1']/android.widget.ImageView"));
        WebElement source2 = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='drag-c3']/android.widget.ImageView"));
		WebElement source3 = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='drag-l3']/android.widget.ImageView"));

		// Identify different target elements
		WebElement target1 = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='drop-c1']/android.view.ViewGroup"));
		WebElement target2 = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='drop-c3']/android.view.ViewGroup"));
		WebElement target3 = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='drop-l3']/android.view.ViewGroup"));

		// Map sources to their respective targets
		Map<WebElement, WebElement> elementPairs = new HashMap<>();
		elementPairs.put(source1, target1);
		elementPairs.put(source2, target2);
		elementPairs.put(source3, target3);

		// Perform drag and drop for different elements
		dragAndDropMultipleElements(elementPairs);
		
		Thread.sleep(3000);
        driver.quit();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void dragAndDrop(WebElement source, WebElement target) {
        // Initialize Pointer Input
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");

        // Get coordinates of source and target
        int startX = source.getLocation().getX();
        int startY = source.getLocation().getY();
        int endX = target.getLocation().getX();
        int endY = target.getLocation().getY();

        // Create drag and drop sequence
        Sequence dragAndDrop = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerMove(Duration.ofSeconds(1), PointerInput.Origin.viewport(), endX, endY))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // Perform the drag and drop action
        driver.perform(Collections.singletonList(dragAndDrop));

        System.out.println("Drag and Drop Action Completed!");
    }
	
	public static void dragAndDropMobileGesture(WebElement source, WebElement target) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("elementId", ((RemoteWebElement) source).getId());
	    params.put("endX", target.getLocation().getX());
	    params.put("endY", target.getLocation().getY());
	    
	    driver.executeScript("mobile: dragGesture", params);
	    System.out.println("Drag and Drop Completed!");
	}
	
	public static void dragAndDropMultipleElements(Map<WebElement, WebElement> elementPairs) {
        for (Map.Entry<WebElement, WebElement> entry : elementPairs.entrySet()) {
            WebElement source = entry.getKey();
            WebElement target = entry.getValue();

            Map<String, Object> params = new HashMap<>();
            params.put("elementId", ((RemoteWebElement) source).getId());
            params.put("endX", target.getLocation().getX());
            params.put("endY", target.getLocation().getY());

            driver.executeScript("mobile: dragGesture", params);
            System.out.println("Dragged element " + source + " to " + target);
        }
        System.out.println("All drag and drop actions completed!");
    }
	
	public static void main(String[] args) {
		initializeDriver();
		DemoApp();
	}
}
