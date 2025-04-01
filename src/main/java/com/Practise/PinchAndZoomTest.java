package com.Practise;

import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class PinchAndZoomTest {
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
//        dragAndDrop(source, target);
        zoomIn(element);
        Thread.sleep(3000);
        
        driver.quit();
	} catch (Exception e) {
		System.err.println("Error initializing driver: " + e.getMessage());
	}
 

}
//	public static void zoomIn(WebElement element) {
//        Map<String, Object> params = new HashMap<>();
//        params.put("elementId", ((RemoteWebElement) element).getId());
//        params.put("percent", 100);  // Increase size (200% zoom)
//        params.put("speed", 200.1);    // Speed in ms
//
//        driver.executeScript("mobile: pinchOpenGesture", params);
//        System.out.println("Zoom In (Pinch Open) performed!");
//    }
public static void zoomIn(WebElement element) {
    Point center = getCenterOfElement(element.getLocation(), element.getSize());

    PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
    PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");

    Sequence finger1Sequence = new Sequence(finger1, 1)
            .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), center))
            .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
            .addAction(new Pause(finger1, Duration.ofMillis(200)))
            .addAction(finger1.createPointerMove(Duration.ofMillis(200), PointerInput.Origin.viewport(),
                    center.getX() + 200, center.getY() - 200))
            .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

    Sequence finger2Sequence = new Sequence(finger2, 1)
            .addAction(finger2.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), center))
            .addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
            .addAction(new Pause(finger2, Duration.ofMillis(200)))
            .addAction(finger2.createPointerMove(Duration.ofMillis(200), PointerInput.Origin.viewport(),
                    center.getX() - 200, center.getY() + 200))
            .addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

    driver.perform(Arrays.asList(finger1Sequence, finger2Sequence));
    System.out.println("Zoom In (Pinch Open) performed!");
}

public static Point getCenterOfElement(Point location, Dimension size) {
    int centerX = location.getX() + size.getWidth() / 2;
    int centerY = location.getY() + size.getHeight() / 2;
    return new Point(centerX, centerY);
}
    // 🔍 Zoom Out (Pinch Close Gesture)
    public static void zoomOut(WebElement element) {
        Map<String, Object> params = new HashMap<>();
        params.put("elementId", ((RemoteWebElement) element).getId());
        params.put("percent", 50);  // Reduce size (50% zoom out)
        params.put("speed", 300);   // Speed in ms

        driver.executeScript("mobile: pinchCloseGesture", params);
        System.out.println("Zoom Out (Pinch Close) performed!");
    }
    

    public static void main(String[] args) {
//    	CMDInteraction.readUserInput(); 
//    	CMDInteraction.stopAppiumServer();
    	initializeDriver();
    	
	}
}
