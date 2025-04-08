package com.Practise;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class Appium2NotificationHandler {

    static AndroidDriver driver;

    public static void main(String[] args) {
        try {
            UiAutomator2Options options = new UiAutomator2Options()
                    .setPlatformName("Android")
                    .setDeviceName("Android Device")
                    .setAppPackage("your.app.package")     // 👉 Replace with your package
                    .setAppActivity("your.app.activity")   // 👉 Replace with your activity
                    .autoGrantPermissions();               // Automatically grants runtime permissions

            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            runAdbCommand("adb shell cmd statusbar expand-notifications");
            Thread.sleep(2000);

            readNotifications();

            // ✅ Handle permission popups
            handlePermissionPopups();

            // ✅ Dismiss common app popups
            dismissCommonPopups();

            // ✅ Collapse notification panel
            runAdbCommand("adb shell cmd statusbar collapse");

            // ✅ Done
            driver.quit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //   ADB Command Runner
    public static void runAdbCommand(String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("ADB ➜ " + line);
            }
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //   Notification Reader
    public static void readNotifications() {
        try {
            Process process = Runtime.getRuntime().exec("adb shell dumpsys notification");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            System.out.println("\n🔔 Notifications:");
            while ((line = reader.readLine()) != null) {
                if (line.contains("tickerText=") || line.contains("title=") || line.contains("text=")) {
                    System.out.println("  " + line.trim());
                }
            }
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //   Handle Permissions
    public static void handlePermissionPopups() {
        try {
            Thread.sleep(1000);
            List<WebElement> allowButtons = driver.findElements(AppiumBy.id("com.android.packageinstaller:id/permission_allow_button"));
            if (!allowButtons.isEmpty()) {
                allowButtons.get(0).click();
                System.out.println("✅ Permission allowed (by ID)");
            } else {
                List<WebElement> allowText = driver.findElements(AppiumBy.xpath("//*[@text='Allow']"));
                if (!allowText.isEmpty()) {
                    allowText.get(0).click();
                    System.out.println("✅ Permission allowed (by text)");
                } else {
                    System.out.println("ℹ️ No permission popup detected.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //   Dismiss App Popups
    public static void dismissCommonPopups() {
        try {
            List<WebElement> cancelButtons = driver.findElements(AppiumBy.xpath("//*[@text='Cancel']"));
            if (!cancelButtons.isEmpty()) {
                cancelButtons.get(0).click();
                System.out.println("❌ Cancelled a popup");
                return;
            }

            List<WebElement> notNow = driver.findElements(AppiumBy.xpath("//*[@text='Not Now']"));
            if (!notNow.isEmpty()) {
                notNow.get(0).click();
                System.out.println("❌ Dismissed update prompt");
                return;
            }

            System.out.println("ℹ️ No common popup found.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
