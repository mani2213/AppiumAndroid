package com.Practise;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.time.Duration;

public class UploadPhotoToGallery {
	public static void uploadPhoto() {
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
		AndroidDriver driver = new AndroidDriver(new URL("http://localhost:4723"), caps);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Define Local File Path (Change as needed)
		File imgFile = new File("C:\\Users\\User\\Pictures\\sample.jpg"); // Change to actual file path
		byte[] fileContent = Files.readAllBytes(imgFile.toPath());

		// Define Target Path on Android Device
		String androidPath = "/sdcard/Pictures/sample.jpg";

		// Push File to Android Device
		driver.pushFile(androidPath, fileContent);
		System.out.println("File uploaded to: " + androidPath);

		// Open the Photos app
		driver.activateApp("com.google.android.apps.photos");
		Thread.sleep(5000); // Wait for Photos to load

		// Click on Photos Tab (Modify if needed)
		driver.findElement(AppiumBy.accessibilityId("Photos")).click();
		Thread.sleep(3000);

		// Verify Image is Present (Modify XPath if needed)
		boolean isImagePresent = driver.findElements(AppiumBy.xpath("//android.widget.ImageView")).size() > 0;
		if (isImagePresent) {
			System.out.println("Image successfully uploaded and visible in Photos.");
		} else {
			System.out.println("Image not found in Photos.");
		}
		driver.quit();
        }catch (Exception e) {
			System.out.println(e);
		}
	}
	public static void main(String[] args) {


	}
}

