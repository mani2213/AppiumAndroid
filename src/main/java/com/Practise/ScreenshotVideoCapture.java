package com.Practise;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.net.URL;
import java.time.Duration;
import java.util.Base64;

public class ScreenshotVideoCapture {

    static AndroidDriver driver;

    public static void main(String[] args) {
        try {
            UiAutomator2Options options = new UiAutomator2Options()
                    .setPlatformName("Android")
                    .setDeviceName("Android Device")
                    .setAppPackage("your.app.package")     // 👉 Replace this
                    .setAppActivity("your.app.activity")   // 👉 Replace this
                    .autoGrantPermissions();

            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            startRecording();

            captureScreenshot("before_action");

            Thread.sleep(2000); // Just dummy wait

            captureScreenshot("after_action");

            stopRecording("test_flow");

            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 📸 Capture Screenshot
    public static void captureScreenshot(String filename) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File("screenshots/" + filename + ".png");
            FileUtils.copyFile(src, dest);
            System.out.println("Screenshot saved at: " + dest.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  Start Recording
    public static void startRecording() {
        try {
            driver.startRecordingScreen();
            System.out.println("🎥 Recording started...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  Stop Recording
    public static void stopRecording(String filename) {
        try {
            String base64Video = driver.stopRecordingScreen();
            byte[] videoData = Base64.getDecoder().decode(base64Video);
            File videoFile = new File("recordings/" + filename + ".mp4");
            FileUtils.writeByteArrayToFile(videoFile, videoData);
            System.out.println("Video saved at: " + videoFile.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
