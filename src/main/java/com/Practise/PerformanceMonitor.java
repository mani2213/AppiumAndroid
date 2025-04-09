package com.Practise;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PerformanceMonitor {

	static AndroidDriver driver;
	public static void initializeDriverForMojave() {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName", "Android");
		caps.setCapability("appium:platformVersion", "14");
		caps.setCapability("appium:deviceName", "632c58cb");
		caps.setCapability("appium:automationName", "UiAutomator2");
		caps.setCapability("appium:appPackage", "com.globalpay.merchant");
		//		caps.setCapability("appium:appActivity", "com.comviva.mobiquitychanneluser.splash.SplashActivity");
		caps.setCapability("appium:appWaitActivity", "*");
		caps.setCapability("appium:noReset",false);
		//		caps.setCapability("appium:autoGrantPermissions", true);
		caps.setCapability("appium:newCommandTimeout", 3600);
		caps.setCapability("appium:nativeWebScreenshot",true);
		caps.setCapability("appium:disableWindowAnimation",true);
		caps.setCapability("appium:allowTestPackages",true);
		caps.setCapability("appium:ignoreHiddenApiPolicyError",true);

		try {
			URL serverURL = URI.create("http://127.0.0.1:4723/").toURL();
			driver = new AndroidDriver(serverURL, caps);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
		} catch (Exception e) {
			System.err.println("Error initializing driver: " + e.getMessage());
		}

	}
	public static void getPerformanceStats(AppiumDriver driver, String packageName) {
		try {
			System.out.println("====== MEMORY USAGE ======");
			Map<String, Object> memArgs = new HashMap<>();
			memArgs.put("command", "dumpsys");
			memArgs.put("args", List.of("meminfo", packageName));
			memArgs.put("timeout", 5000);

			String memOutput = (String) ((JavascriptExecutor) driver).executeScript("mobile: shell", memArgs);
			System.out.println(memOutput);


			System.out.println("\n====== CPU USAGE ======");
			Map<String, Object> cpuArgs = new HashMap<>();
			cpuArgs.put("command", "top");
			cpuArgs.put("args", List.of("-n", "1"));
			cpuArgs.put("timeout", 5000);

			String cpuOutput = (String) ((JavascriptExecutor) driver).executeScript("mobile: shell", cpuArgs);
			System.out.println(cpuOutput);

			System.out.println("\n====== BATTERY INFO ======");
			Map<String, Object> batteryArgs = new HashMap<>();
			batteryArgs.put("command", "dumpsys");
			batteryArgs.put("args", List.of("battery"));
			batteryArgs.put("timeout", 5000);

			String batteryOutput = (String) ((JavascriptExecutor) driver).executeScript("mobile: shell", batteryArgs);
			System.out.println(batteryOutput);

		} catch (Exception e) {
			System.out.println("Error while getting performance stats: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		initializeDriverForMojave();
		getPerformanceStats(driver, "com.globalpay.merchant");

	}
}
