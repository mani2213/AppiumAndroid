package com.Practise;

import java.net.URI;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class BusinessApp {
	static AppiumDriver driver;

	public static void initializeDriver() {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName", "Android");
		caps.setCapability("appium:platformVersion", "14");
		caps.setCapability("appium:deviceName", "632c58cb");
		caps.setCapability("appium:automationName", "UiAutomator2");
		caps.setCapability("appium:appPackage", "com.globalpay.merchant");
		caps.setCapability("appium:appActivity", "com.comviva.mobiquitychanneluser.splash.SplashActivity");
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

		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void app() {
		WebElement phoneNumber = driver.findElement(By.xpath("//android.widget.EditText[@content-desc='loginMobileNumber']"));
		phoneNumber.sendKeys("77885500");
		WebElement pin = driver.findElement(By.xpath("//android.widget.EditText[@content-desc='loginpin']"));
		pin.click();
		pin.sendKeys("1357");
		driver.findElement(By.xpath("//android.widget.ImageView[@content-desc='Done']")).click();
		driver.findElement(By.xpath("//android.widget.Button[@content-desc='loginButton']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.globalpay.merchant:id/homeServiceName\" and @text=\"Receive payment\"]")).click();
		driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\"]")).click();
		WebElement upload = driver.findElement(By.xpath("(//android.widget.ImageView[@context-clickable='false'])[3]"));
		upload.click();
		driver.findElement(By.xpath("(//com.oplus.gallery.business_lib.timeline.view.TimelineView[@context-clickable='false'])[3]")).click();
		driver.findElement(By.xpath("//android.widget.EditText[@input-type='2']")).sendKeys("1");
		driver.findElement(By.xpath("//android.widget.Button[normalize-space(@text)='Next']")).click();
		driver.findElement(By.xpath("//android.widget.Button[normalize-space(@text)='Initiate payment']")).click();
		driver.findElement(By.xpath("//android.widget.EditText[@input-type='18']")).sendKeys("1357");
		driver.findElement(By.xpath("//android.widget.Button[normalize-space(@text)='Verify']")).click();

	}
	public static void main(String[] args) {
		initializeDriver();
		app();
	}
}
