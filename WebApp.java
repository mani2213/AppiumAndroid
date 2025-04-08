package AppsTypes;

import java.net.URI;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class WebApp {
	static AppiumDriver driver;
	public static void initializeDriverForGlobalpayBusiness() {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName", "Android");
		caps.setCapability("appium:platformVersion", "14");
		caps.setCapability("appium:deviceName", "632c58cb");
		caps.setCapability("appium:automationName", "UiAutomator2");
//		caps.setCapability("appium:appWaitActivity", "*");
	    caps.setCapability("appium:browserName", "Chrome");
		caps.setCapability("appium:chromedriverExecutable","C:\\Users\\manikanta.c\\Downloads\\chromedriver-win64\\chromedriver.exe");


		try {
			URL serverURL = URI.create("http://127.0.0.1:4723/").toURL();
			driver = new AndroidDriver(serverURL, caps);
		} catch (Exception e) {
			System.err.println("Error initializing driver: " + e.getMessage());
		}
	}
	public static void ChromeLaunch() {
		for (String context : ((AndroidDriver) driver).getContextHandles()) {
		    System.out.println("Available Context: " + context);
		    if (context.toLowerCase().contains("webview") || context.toLowerCase().contains("chromium")) {
		    	((AndroidDriver) driver).context(context);
		        System.out.println("Switched to context: " + context);
		        break;
		    }
		}
		System.out.println("Current Context: " + ((AndroidDriver) driver).getContext());
		System.out.println("Available Contexts: " + ((AndroidDriver) driver).getContextHandles());
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));

		 driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		 driver.findElement(By.name("username")).sendKeys("admin");
		 driver.findElement(By.name("password")).sendKeys("admin123");
		 try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 WebElement btn = driver.findElement(By.xpath("//button[normalize-space()='Login']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
//		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		 WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Login']")));
//		 loginButton.click();
		 driver.findElement(By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']")).click();
		 driver.findElement(By.xpath("//a[.='Logout']")).click();
//		 search.submit();


	        
	       
	}
	
	public static void main(String[] args) throws Exception {
		initializeDriverForGlobalpayBusiness();
		ChromeLaunch();
		
       
    }

}
