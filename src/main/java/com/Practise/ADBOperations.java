package com.Practise;




import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class ADBOperations {
	static AndroidDriver driver;

	public static void main(String[] args) {
//		initializeDriver();
//		executeADBForStoragePath();
//		startAppiumServer();
//		stopAppiumServer();
		
//		try {
//			uploadAndOpenTextFile("C:\\Users\\chell\\Downloads\\QR2.png", "/sdcard/DCIM/Camera/QR2.png", "632c58cb");
//		} catch (IOException | InterruptedException e) {
//			e.printStackTrace();
//		}

		// Execute ADB command to refresh media
		String androidFilePath = "/sdcard/DCIM/Camera/QR1.png";
		executeADBCommand(androidFilePath);
	}

	// Method to initialize Appium driver
	public static void initializeDriver() {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName", "Android");
		caps.setCapability("appium:platformVersion", "14");
		caps.setCapability("appium:deviceName", "632c58cb");
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
			driver = new AndroidDriver(serverURL, caps);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
			System.out.println("Application Started");
		} catch (Exception e) {
			System.err.println("Error initializing driver: " + e.getMessage());
		}
	}

	// Method to upload file to Android device
	public static void uploadAndOpenTextFile(String localFilePath, String androidFilePath, String deviceId)
			throws MalformedURLException, IOException, InterruptedException {
		localFilePath = localFilePath.replace("\u202A", "").replace("\u202C", "").trim();

		ProcessBuilder processBuilder = new ProcessBuilder("adb", "push", localFilePath, androidFilePath);
		processBuilder.redirectErrorStream(true);

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

	// Method to execute ADB command
	public static void executeADBCommand(String filePath) {
	    try {
	        ProcessBuilder processBuilder = new ProcessBuilder(
	            "adb", "shell", "am", "broadcast",
	            "-a", "android.intent.action.MEDIA_SCANNER_SCAN_FILE",
	            "-d", "file://" + filePath
	        );
	        processBuilder.redirectErrorStream(true);
	        Process process = processBuilder.start();
	        int exitCode = process.waitFor();
	        if (exitCode == 0) {
	            System.out.println("ADB Command executed successfully.");
	        } else {
	            System.err.println("Failed to execute ADB command.");
	        }
	    } catch (IOException | InterruptedException e) {
	        System.out.println(e);
	    }
	}
	public static void executeADBForStoragePath() {
	    try {
            // Corrected ADB command
            ProcessBuilder processBuilder = new ProcessBuilder(
                "cmd.exe", "/c", "adb shell ls /sdcard"
            );
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            // Read the command output
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println(" ADB Command executed successfully.");
                System.out.println(" Storage Contents:\n" + output);
            } else {
                System.err.println(" Failed to execute ADB command.");
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("⚠️ Error executing ADB command: " + e.getMessage());
        }
    }
	public static Process appiumProcess;

    public static void startAppiumServer() {
        try {
            // Start Appium server using cmd command
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "appium");
            processBuilder.redirectErrorStream(true); // Merge error stream into output

            appiumProcess = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(appiumProcess.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // Print server logs

                // Check if Appium has started successfully
                if (line.contains("Appium REST http interface listener started")) {
                    System.out.println("Appium Server is Running!");
                    break; // Stop reading logs (optional)
                }
            }

        } catch (IOException e) {
            System.out.println("Failed to start Appium Server!");
            e.printStackTrace();
        }
    }

    public static void stopAppiumServer() {
        if (appiumProcess != null) {
            appiumProcess.destroy();
            System.out.println("Appium Server Stopped Successfully!");
        } else {
            System.out.println("No running Appium server found.");
        }
    }

}


