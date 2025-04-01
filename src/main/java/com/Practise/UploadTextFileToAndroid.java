package com.Practise;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.devtools.Command;
import org.openqa.selenium.devtools.v130.fetch.model.AuthChallengeResponse.Response;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteExecuteMethod;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class UploadTextFileToAndroid {
	static AppiumDriver driver;

    public static void main(String[] args)  {
    	demoTest();
        try {
        	uploadFilebyADB("‪D:\\AppiumANDROID\\SQA.jpg", "/sdcard/DCIM/Camera/SQA.jpg", "632c58cb");
		} catch (IOException | InterruptedException e) {
			
			e.printStackTrace();
		}

       
//       String  androidFilePath="/sdcard/DCIM/Camera/SQA.jpg";
//        executeADBCommand((AndroidDriver) driver, androidFilePath);
    	
    }
    public static void demoTest() {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName", "Android");
		caps.setCapability("appium:platformVersion", "14"); // Change this to match your device/emulator
		caps.setCapability("appium:deviceName", "632c58cb"); // Check with `adb devices`
		caps.setCapability("appium:automationName", "UiAutomator2");
		caps.setCapability("appium:appPackage", "com.android.chrome");
		caps.setCapability("appium:appActivity", "com.google.android.apps.chrome.Main");
		caps.setCapability("appium:appWaitActivity", "*");
		caps.setCapability("appium:noReset", true);
		caps.setCapability("appium:newCommandTimeout", 3600);
		caps.setCapability("appium:nativeWebScreenshot", true);
		caps.setCapability("appium:disableWindowAnimation", true);
		caps.setCapability("appium:allowTestPackages", true);
		caps.setCapability("appium:ignoreHiddenApiPolicyError", true);

		try {
			URL serverURL = URI.create("http://127.0.0.1:4723/").toURL();
			
			 driver = new AndroidDriver(serverURL,caps);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
			System.out.println("Application Started");
		}catch (Exception e) {
			System.out.println(e);
		}
	}
    
    
    public static void uploadFilebyADB(String localFilePath, String androidFilePath, String deviceId) throws MalformedURLException, IOException, InterruptedException {    	
        localFilePath = localFilePath.replace("\u202A", "").replace("\u202C", "").trim();
    	ProcessBuilder processBuilder = new ProcessBuilder("adb", "push", localFilePath, androidFilePath);
        processBuilder.redirectErrorStream(true);  // Redirect error stream to output
        Process process = processBuilder.start();
        int exitCode = process.waitFor();
        if (exitCode == 0) {
            System.out.println("File uploaded successfully to: " + androidFilePath);
        } else {
            System.err.println("Failed to upload file.");
        }
        File textFile = new File(localFilePath);
        byte[] fileContent = Files.readAllBytes(textFile.toPath());
    }
    
    
    public static void executeADBCommand(AndroidDriver driver, String filePath) {
    	if (driver == null) {
            System.err.println("Driver is not initialized. Cannot execute ADB command.");
            return;
        }
        Map<String, Object> args = new HashMap<>();
        args.put("command", "am");
        args.put("args", new String[]{
                "broadcast", 
                "-a", "android.intent.action.MEDIA_SCANNER_SCAN_FILE", 
                "-d", "file://" + filePath
        });

        // Execute ADB shell command
        Object response = driver.executeScript("mobile: shell", args);
        System.out.println("ADB Command Response: " + response);
       
    }
}
