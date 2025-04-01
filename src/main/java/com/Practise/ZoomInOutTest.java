package com.Practise;

import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class ZoomInOutTest {
	static AppiumDriver driver;
	public static void initializeDriver() {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName", "Android");
		caps.setCapability("appium:platformVersion", "14");
		caps.setCapability("appium:deviceName", "632c58cb");
		caps.setCapability("appium:automationName", "UiAutomator2");
		caps.setCapability("appium:appPackage", "com.swaglabsmobileapp");
		caps.setCapability("appium:appActivity", "com.swaglabsmobileapp.MainActivity");
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
			SwipeHelper.swipeAction(driver,506,2071,506,1551);
			driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"standard_user\")")).click();
			driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='test-LOGIN']")).click();
	        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='test-Menu']/android.view.ViewGroup/android.widget.ImageView")).click();
	        driver.findElement(By.xpath("//android.widget.TextView[@text='DRAWING']")).click();
	        WebElement element = driver.findElement(By.xpath("//android.widget.Image"));	        
	        zoomOut(driver,element);
//	        dragAndDrop(source, target);
	        Thread.sleep(3000);
	        
	        driver.quit();
		} catch (Exception e) {
			System.err.println("Error initializing driver: " + e.getMessage());
		}
	}
	public static void zoom(WebElement element) {
		
        Point centerOfElement = getCenterOfElement(element.getLocation(), element.getSize());

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");

        Sequence sequence1 = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(200)))
                .addAction(finger1.createPointerMove(Duration.ofMillis(200), PointerInput.Origin.viewport(),
                        centerOfElement.getX() + 100, centerOfElement.getY() - 100))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        Sequence sequence2 = new Sequence(finger2, 1)
                .addAction(finger2.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
                .addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger2, Duration.ofMillis(200)))
                .addAction(finger2.createPointerMove(Duration.ofMillis(200), PointerInput.Origin.viewport(),
                        centerOfElement.getX() - 100, centerOfElement.getY() + 100))
                .addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(sequence1, sequence2));
    }

    public static Point getCenterOfElement(Point location, Dimension size) {
        int centerX = location.getX() + size.getWidth() / 2;
        int centerY = location.getY() + size.getHeight() / 2;
        return new Point(centerX, centerY);
    }
    
	public static void zoomIn(AppiumDriver driver, WebElement photoElement) {
	    int centerX = photoElement.getLocation().getX() + (photoElement.getSize().getWidth() / 2);
	    int centerY = photoElement.getLocation().getY() + (photoElement.getSize().getHeight() / 2);

	    PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
	    PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");

	    Sequence zoomIn1 = new Sequence(finger1, 1)
	            .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, centerY))
	            .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
	            .addAction(finger1.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), centerX + 200, centerY - 200))
	            .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

	    Sequence zoomIn2 = new Sequence(finger2, 1)
	            .addAction(finger2.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, centerY))
	            .addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
	            .addAction(finger2.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), centerX - 200, centerY + 200))
	            .addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

	    driver.perform(Arrays.asList(zoomIn1, zoomIn2));
	}

	public static void zoomOut(AppiumDriver driver,WebElement photoElement) {
	    int centerX = photoElement.getLocation().getX() + (photoElement.getSize().getWidth() / 2);
	    int centerY = photoElement.getLocation().getY() + (photoElement.getSize().getHeight() / 2);

	    PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
	    PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");

	    Sequence zoomOut1 = new Sequence(finger1, 1)
	            .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX + 200, centerY - 200))
	            .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
	            .addAction(finger1.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), centerX, centerY))
	            .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

	    Sequence zoomOut2 = new Sequence(finger2, 1)
	            .addAction(finger2.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX - 200, centerY + 200))
	            .addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
	            .addAction(finger2.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), centerX, centerY))
	            .addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

	    driver.perform(Arrays.asList(zoomOut1, zoomOut2));
	}

	public static void performTouchByOffset(AppiumDriver driver, int x, int y) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence touchSequence = new Sequence(finger, 0);
        
        touchSequence.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x, y));
        touchSequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        touchSequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(touchSequence));
    }
public static void main(String[] args) {
	initializeDriver();
}
}
