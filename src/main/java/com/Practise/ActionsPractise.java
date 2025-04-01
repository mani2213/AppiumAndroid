package com.Practise;

import java.awt.Point;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class ActionsPractise {
	
	
	public static void SwipeLeft() {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName", "Android");
		caps.setCapability("appium:platformVersion", "12"); // Change this to match your device/emulator
		caps.setCapability("appium:deviceName", "emulator-5554"); // Check with `adb devices`
		caps.setCapability("appium:automationName", "UiAutomator2");
		caps.setCapability("appium:appPackage", "com.wdiodemoapp");
		caps.setCapability("appium:appActivity", "com.wdiodemoapp.MainActivity");
		caps.setCapability("appium:appWaitActivity", "*");
		caps.setCapability("appium:noReset", true);
		caps.setCapability("appium:newCommandTimeout", 3600);
		caps.setCapability("appium:nativeWebScreenshot", true);

		try {
			URL serverURL = URI.create("http://127.0.0.1:4723/").toURL();
			
			AppiumDriver driver = new AndroidDriver(serverURL,caps);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
			System.out.println("Application Started");
			driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Swipe\")")).click();
			SwipeHelper.swipeAction(driver, 921, 1544, 169, 1544);
			Thread.sleep(2000);
			SwipeHelper.swipeAction(driver, 921, 1544, 169, 1544);
			SwipeHelper.swipe(driver, "up");
		}catch (Exception e) {
			System.out.println(e);
		}
	}
//	public static void performSwipe(AppiumDriver driver,Point start, Point end) {
//        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
//        Sequence swipe = new Sequence(finger, 1);
//        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start.getX(), start.getY()));
//        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
//        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), end.getX(), end.getY()));
//        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
//        driver.perform(Arrays.asList(swipe));
//    }
	public static void LoginApp() {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName", "Android");
		caps.setCapability("appium:platformVersion", "12"); // Change this to match your device/emulator
		caps.setCapability("appium:deviceName", "emulator-5554"); // Check with `adb devices`
		caps.setCapability("appium:automationName", "UiAutomator2");
		caps.setCapability("appium:appPackage", "com.swaglabsmobileapp");
		caps.setCapability("appium:appActivity", "com.swaglabsmobileapp.SplashActivity");
		caps.setCapability("appium:appWaitActivity", "*");
		caps.setCapability("appium:noReset", true);
		caps.setCapability("appium:newCommandTimeout", 3600);
		caps.setCapability("appium:nativeWebScreenshot", true);

		try {
			URL serverURL = URI.create("http://127.0.0.1:4723/").toURL();
			
			AppiumDriver driver = new AndroidDriver(serverURL,caps);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
			System.out.println("Application Started");
			
			driver.findElement(By.xpath("//android.widget.TextView[@text='standard_user']")).click();
			driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='test-LOGIN']")).click();
			SwipeHelper.scrollUntilElementVisible(driver, By.xpath("//android.widget.ScrollView[@content-desc='test-PRODUCTS']/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.ImageView"));
			driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='test-Menu']/android.view.ViewGroup/android.widget.ImageView")).click();
			driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"LOGOUT\")")).click();
			System.out.println("Logged Out");
			Thread.sleep(2000);
			driver.quit();
		}catch (Exception e) {
			System.out.println(e);
		}
	}
	public static void customSwipe() {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName", "Android");
		caps.setCapability("appium:platformVersion", "12"); // Change this to match your device/emulator
		caps.setCapability("appium:deviceName", "emulator-5554"); // Check with `adb devices`
		caps.setCapability("appium:automationName", "UiAutomator2");
		caps.setCapability("appium:appPackage", "com.swaglabsmobileapp");
		caps.setCapability("appium:appActivity", "com.swaglabsmobileapp.SplashActivity");
		caps.setCapability("appium:appWaitActivity", "*");
		caps.setCapability("appium:noReset", true);
		caps.setCapability("appium:newCommandTimeout", 3600);
		caps.setCapability("appium:nativeWebScreenshot", true);

		try {
			URL serverURL = URI.create("http://127.0.0.1:4723/").toURL();
			
			AppiumDriver driver = new AndroidDriver(serverURL,caps);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
			System.out.println("Application Started");
			driver.findElement(By.xpath("//android.widget.TextView[@text='standard_user']")).click();
			driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='test-LOGIN']")).click();
			Thread.sleep(2000);
			SwipeHelper.swipe(driver, "up");
			Thread.sleep(2000);
			driver.quit();
		}catch (Exception e) {
			System.out.println(e);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "deprecation" })
	public static void ActionsPrac() {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName", "Android");
		caps.setCapability("appium:platformVersion", "12"); // Change this to match your device/emulator
		caps.setCapability("appium:deviceName", "emulator-5554"); // Check with `adb devices`
		caps.setCapability("appium:automationName", "UiAutomator2");
//		caps.setCapability("appium:app","C:\\Users\\chell\\Downloads\\App Files\\Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");
//		caps.setCapability("appPackage","com.swaglabsmobileapp");
//		caps.setCapability("appActivity", "com.swaglabsmobileapp.MainActivity");
		caps.setCapability("appium:appPackage", "com.swaglabsmobileapp");
		caps.setCapability("appium:appActivity", "com.swaglabsmobileapp.SplashActivity");
		caps.setCapability("appium:appWaitActivity", "*");
		caps.setCapability("appium:noReset", true);
		caps.setCapability("appium:newCommandTimeout", 3600);
		caps.setCapability("appium:nativeWebScreenshot", true);

		try {
			URL serverURL = URI.create("http://127.0.0.1:4723/").toURL();


			AppiumDriver driver = new AndroidDriver(serverURL,caps);
			Thread.sleep(5000);
			System.out.println("Application Started");
			
			WebElement ele1 = driver.findElement(By.xpath("//android.widget.TextView[@text='secret_sauce']"));
//			AndroidTouchAction action = new AndroidTouchAction((PerformsTouchActions) driver);
			TouchAction action = new TouchAction((PerformsTouchActions) driver);

//			TouchAction<TouchAction<T>> action = new TouchAction<TouchAction<T>>((PerformsTouchActions) driver);
//			PointOption<PointOption<T>> p = new PointOption<PointOption<T>>();
			PointOption p = new PointOption();

			org.openqa.selenium.Dimension d=driver.manage().window().getSize();
			int h1 = (int)(d.getHeight()*0.8);
			int h2 = (int)(d.getHeight()*0.2);
			action.press(p.point(0, h1)).waitAction(new WaitOptions().withDuration(Duration.ofMillis(600))).moveTo(p.point(0, h2)).release().perform();		
			Thread.sleep(5600);
//			driver.findElement(AppiumBy.androidUIAutomator(
//				    "new UiScrollable(new UiSelector().scrollable(true))" +
//				    ".scrollIntoView(new UiSelector().text(\"secret_sauce\"))"));
//			Actions action = new Actions(driver);
//			action.scrollToElement(ele1);
//			String elementId = "//android.widget.TextView[@text='secret_sauce']";
//			Map<String, Object> scrollParams = new HashMap<>();
//	        scrollParams.put("strategy", "accessibility id"); 			// or "xpath", "id"
//	        scrollParams.put("selector", elementId);
//	        scrollParams.put("direction", "down"); 						// Can be "up", "left", "right"
//	        ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", scrollParams);
		} catch (MalformedURLException | InterruptedException e) {
			e.printStackTrace();
		}	
	}
	public static void main(String[] args) {
//		ActionsPrac();
		LoginApp();
//		customSwipe();
//		SwipeLeft();
	}
}
class SwipeHelper {
    public static void swipe(AppiumDriver driver, String direction) {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        int startX = width / 2, startY = height / 2, endX = startX, endY = startY;

        switch (direction.toLowerCase()) {
            case "up":    endY = (int) (height * 0.2); break;
            case "down":  endY = (int) (height * 0.8); break;
            case "left":  endX = (int) (width * 0.2);  break;
            case "right": endX = (int) (width * 0.8);  break;
            default: throw new IllegalArgumentException("Invalid direction: " + direction);
        }
        swipeAction(driver, startX, startY, endX, endY);
    }

    public static void swipeAction(AppiumDriver driver, int startX, int startY, int endX, int endY) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), endX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(swipe));
    }
    public static void scrollUntilElementVisible(AppiumDriver driver, By by) {
        int maxScrolls = 10; 
        int scrollCount = 0;
        while (scrollCount < maxScrolls) {
            if (driver.findElements(by).size() > 0) {
                System.out.println("Element Found!");
                break;
            }
            performScroll(driver);
            scrollCount++;
        }
    }

    public static void performScroll(AppiumDriver driver) {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;

        int startX = width / 2;
        int startY = (int) (height * 0.8);  // Start near the bottom
        int endY = (int) (height * 0.2);    // Scroll upwards

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence scroll = new Sequence(finger, 1);

        scroll.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        scroll.addAction(finger.createPointerMove(Duration.ofMillis(700), PointerInput.Origin.viewport(), startX, endY));
        scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(scroll));
    }
}
