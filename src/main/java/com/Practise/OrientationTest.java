package com.Practise;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URI;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.JavascriptExecutor;



public class OrientationTest {

    static AndroidDriver driver;

    public static void main(String[] args) {
        try {
            // Desired Capabilities
            DesiredCapabilities caps = new DesiredCapabilities();
    		caps.setCapability("platformName", "Android");
    		caps.setCapability("appium:platformVersion", "14");
    		caps.setCapability("appium:deviceName", "632c58cb");
    		caps.setCapability("appium:automationName", "UiAutomator2");
    		caps.setCapability("appium:appPackage", "com.edgarmmann88.simpleswip");
    		caps.setCapability("appium:appActivity", "com.edgarmmann88.simpleswip.MainActivity");
    		caps.setCapability("appium:appWaitActivity", "*");
    		caps.setCapability("appium:noReset",false);
    		caps.setCapability("appium:newCommandTimeout", 3600);
    		caps.setCapability("appium:nativeWebScreenshot",true);
    		caps.setCapability("appium:disableWindowAnimation",true);
    		caps.setCapability("appium:allowTestPackages",true);
    		caps.setCapability("appium:ignoreHiddenApiPolicyError",true);

            URL serverURL = URI.create("http://127.0.0.1:4723/").toURL();
            driver = new AndroidDriver(serverURL, caps);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            System.out.println("Initial Orientation: " + driver.getOrientation());

            // Rotate to Landscape
          
            driver.rotate(ScreenOrientation.LANDSCAPE);
            Thread.sleep(2000);
            System.out.println("Orientation now: " + driver.getOrientation());

            // Rotate back to Portrait
            System.out.println("Rotating to PORTRAIT...");
            driver.rotate(ScreenOrientation.PORTRAIT);
            Thread.sleep(2000);
            System.out.println("Orientation now: " + driver.getOrientation());

            // Close Driver
            driver.quit();

        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
