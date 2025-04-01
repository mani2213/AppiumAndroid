package com.Practise;

import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;

import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class SwipeHelper1 extends ActionsPractise{
	static URL serverURL;
	static DesiredCapabilities caps = new DesiredCapabilities();

	static AppiumDriver driver = new AndroidDriver(serverURL,caps);
   public static void swipe(String direction) {
	   try {
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("platformName", "Android");
			caps.setCapability("appium:platformVersion", "16"); // Change this to match your device/emulator
			caps.setCapability("appium:deviceName", "emulator-5556"); // Check with `adb devices`
			caps.setCapability("appium:automationName", "UiAutomator2");
			caps.setCapability("appium:app","‪C:\\Users\\chell\\Downloads\\calculator.apk");
			caps.setCapability("appium:ensureWebviewsHavePages", true);
			caps.setCapability("appium:nativeWebScreenshot", true);
			caps.setCapability("appium:newCommandTimeout", 3600);

//			URL serverURL = new URL("http://127.0.01:4723");////wd//hub
			URL serverURL = URI.create("http://127.0.0.1:4723/").toURL();

			AppiumDriver driver = new AndroidDriver(serverURL,caps);
			
			
			}catch (Exception e) {
				// TODO: handle exception
			}
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

       swipeAction(startX, startY, endX, endY);
//		}catch (Exception e) {
//			System.out.println(e);
//		}
   }

   public static void swipeAction(int startX, int startY, int endX, int endY) {
       PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
       Sequence swipe = new Sequence(finger, 1);
       swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
       swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
       swipe.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), endX, endY));
       swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
       
	driver.perform(Collections.singletonList(swipe));
   }
  
}
