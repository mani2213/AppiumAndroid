package com.Practise;

import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class GlobalPayAutomation {
	
	
static AppiumDriver driver;     

	public static void initializeDriverForGlobalpayBusiness() {
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
	public static void initializeDriverForGlobalpayConsumer() {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName", "Android");
		caps.setCapability("appium:platformVersion", "14");
		caps.setCapability("appium:deviceName", "632c58cb");
		caps.setCapability("appium:automationName", "UiAutomator2");
		caps.setCapability("appium:appPackage", "com.globalpay.consumer");
//		caps.setCapability("appium:appActivity", "com.comviva.mobiquityconsumer.splash.SplashActivity");
		caps.setCapability("appium:appWaitActivity", "*");
		caps.setCapability("appium:noReset",false);
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
	public static void logExecutionTime(String status) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("Execution " + status + " Time: " + now.format(formatter));
    }
	public static void GlobalpayBusinessByMobileNumber() {
		try {
		WebElement username = driver.findElement(By.xpath("//android.widget.EditText[@content-desc='loginMobileNumber']"));
		SeleniumActions.waittillElement(username);
		username.sendKeys("77885500");

		WebElement password = driver.findElement(By.xpath("//android.widget.EditText[@content-desc='loginpin']"));
		SeleniumActions.waittillElement(password);
		password.click();
		password.sendKeys("1357");
		
		WebElement done = driver.findElement(By.xpath("//android.widget.ImageView[@content-desc='Done']"));
		SeleniumActions.waittillElement(done);
		done.click();
		
		WebElement login = driver.findElement(By.xpath("//android.widget.Button[@content-desc='loginButton']"));
		SeleniumActions.waittillElement(login);
		login.click();
		
		WebElement receivePayment = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.globalpay.merchant:id/homeServiceName' and @text='Receive payment']"));
		SeleniumActions.waittillElement(receivePayment);
		receivePayment.click();
		
		try {
		WebElement camPermission = driver.findElement(By.xpath("//android.widget.Button[@resource-id='com.android.permissioncontroller:id/permission_allow_foreground_only_button']"));
		SeleniumActions.waittillElement(camPermission);
		if (camPermission.isDisplayed()) {
			camPermission.click();
			System.out.println("camPermission is found and clicked.");
		}
		}catch (Exception e) {
			System.out.println(e);
		}
		
		WebElement mobileNumber = driver.findElement(By.xpath("//android.widget.TextView[@text='Mobile number / Payment handle']"));
		SeleniumActions.waittillElement(mobileNumber);
		mobileNumber.click();
		
		WebElement typemobileNumber = driver.findElement(By.xpath("//android.widget.EditText[normalize-space(@text)='Mobile number / Payment handle']"));
		SeleniumActions.waittillElement(typemobileNumber);
		typemobileNumber.sendKeys("96313435");
		
		WebElement Next = driver.findElement(By.xpath("//android.widget.Button[@resource-id='com.globalpay.merchant:id/merchantinitiateNextButton']"));
		SeleniumActions.waittillElement(Next);
		Next.click();


//		WebElement Next = driver.findElement(By.xpath("//android.widget.Button[@resource-id='com.globalpay.merchant:id/merchantinitiateNextButton']"));
//		SeleniumActions.waittillElement(Next);
//		Next.click();
//		
		
		WebElement amount = driver.findElement(By.xpath("//android.widget.EditText[normalize-space(@text)='0']"));
		SeleniumActions.waittillElement(amount);
		amount.sendKeys("1");
		
		WebElement next1 = driver.findElement(By.xpath("//android.widget.Button[@resource-id='com.globalpay.merchant:id/inputamountNextButton']"));
		SeleniumActions.waittillElement(next1);
		next1.click();
		
		WebElement Initiatepayment = driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id='com.globalpay.merchant:id/transactionconfirmation_next_button']"));
		SeleniumActions.waittillElement(Initiatepayment);
		Initiatepayment.click();
		
		WebElement enterPin = driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.globalpay.merchant:id/otp_edit_text']"));
		SeleniumActions.waittillElement(enterPin);
		enterPin.sendKeys("1357");
		
		WebElement verify = driver.findElement(By.xpath("//android.widget.Button[@resource-id='com.globalpay.merchant:id/verifypin_pin_button']"));
		SeleniumActions.waittillElement(verify);
		verify.click();
		
		Thread.sleep(3000);
		SwipeHelper.swipeAction(driver,506, 1795,516, 603);
		
		WebElement goHome = driver.findElement(By.xpath("//android.widget.Button[@resource-id='com.globalpay.merchant:id/transactionstatusGoToHomeButton']"));
		SeleniumActions.waittillElement(goHome);
		goHome.click();
		
		Thread.sleep(3000);
		SwipeHelper.swipeAction(driver,0, 1077,990, 1086);

		WebElement logout = driver.findElement(By.xpath("//android.widget.TextView[normalize-space(@text)='Logout']"));
		SeleniumActions.waittillElement(logout);
		logout.click();
		
		WebElement logout1 = driver.findElement(By.xpath("//android.widget.Button[@resource-id='com.globalpay.merchant:id/confirmation_action_button']"));
		SeleniumActions.waittillElement(logout1);
		logout1.click();
		
		}catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void GlobalpayBusinessByQR() {
		try {
		WebElement username = driver.findElement(By.xpath("//android.widget.EditText[@content-desc='loginMobileNumber']"));
		SeleniumActions.waittillElement(username);
		username.sendKeys("77885500");

		WebElement password = driver.findElement(By.xpath("//android.widget.EditText[@content-desc='loginpin']"));
		SeleniumActions.waittillElement(password);
		password.click();
		password.sendKeys("1357");
		
		WebElement done = driver.findElement(By.xpath("//android.widget.ImageView[@content-desc='Done']"));
		SeleniumActions.waittillElement(done);
		done.click();
		
		WebElement login = driver.findElement(By.xpath("//android.widget.Button[@content-desc='loginButton']"));
		SeleniumActions.waittillElement(login);
		login.click();
		
		WebElement receivePayment = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.globalpay.merchant:id/homeServiceName' and @text='Receive payment']"));
		SeleniumActions.waittillElement(receivePayment);
		receivePayment.click();
				
		try {
			WebElement camPermission = driver.findElement(By.xpath("//android.widget.Button[@resource-id='com.android.permissioncontroller:id/permission_allow_foreground_only_button']"));
			SeleniumActions.waittillElement(camPermission);
			if (camPermission.isDisplayed()) {
				camPermission.click();
				System.out.println("camPermission is found and clicked.");
			}
			}catch (Exception e) {
				System.out.println(e);
			}
		
		WebDriverWait  webDriverWait= new WebDriverWait(driver, Duration.ofSeconds(90));
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.ImageView[@resource-id='com.globalpay.merchant:id/scanQrGallery']"))).click();
//		WebElement selectQR = driver.findElement(By.id("com.globalpay.merchant:id/scanQrGallery"));
//		SeleniumActions.waittillElement(selectQR);
//		selectQR.click();
		
		WebElement selectQRfromGallery = driver.findElement(By.xpath("//com.oplus.gallery.business_lib.timeline.view.TimelineView[@content-desc=',Item 1,Photo,,24 March 2025 at 13:11']"));
		SeleniumActions.waittillElement(selectQRfromGallery);
		selectQRfromGallery.click();
		
		WebElement select = driver.findElement(By.xpath("//android.widget.Button[@content-desc='Select']"));
		SeleniumActions.waittillElement(select);
		select.click();
		
		WebElement amount = driver.findElement(By.xpath("//android.widget.EditText[normalize-space(@text)='0']"));
		SeleniumActions.waittillElement(amount);
		amount.sendKeys("1");
		
		WebElement next1 = driver.findElement(By.xpath("//android.widget.Button[@resource-id='com.globalpay.merchant:id/inputamountNextButton']"));
		SeleniumActions.waittillElement(next1);
		next1.click();
		
		WebElement Initiatepayment = driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id='com.globalpay.merchant:id/transactionconfirmation_next_button']"));
		SeleniumActions.waittillElement(Initiatepayment);
		Initiatepayment.click();
		
		WebElement enterPin = driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.globalpay.merchant:id/otp_edit_text']"));
		SeleniumActions.waittillElement(enterPin);
		enterPin.sendKeys("1357");
		
		WebElement verify = driver.findElement(By.xpath("//android.widget.Button[@resource-id='com.globalpay.merchant:id/verifypin_pin_button']"));
		SeleniumActions.waittillElement(verify);//android.widget.EditText[@resource-id='com.globalpay.merchant:id/otp_edit_text']
		verify.click();
		
		Thread.sleep(5000);
		SwipeHelper.swipeAction(driver,506, 1795,516, 603);
		
		WebElement goHome = driver.findElement(By.xpath("//android.widget.Button[@resource-id='com.globalpay.merchant:id/transactionstatusGoToHomeButton']"));
//		SeleniumActions.scrollToElement(goHome);
		SeleniumActions.waittillElement(goHome);
		goHome.click();
		
		Thread.sleep(3000);
		SwipeHelper.swipeAction(driver,0, 1077,990, 1086);

		WebElement logout = driver.findElement(By.xpath("//android.widget.TextView[normalize-space(@text)='Logout']"));
		SeleniumActions.waittillElement(logout);
		logout.click();
		
		WebElement logout1 = driver.findElement(By.xpath("//android.widget.Button[@resource-id='com.globalpay.merchant:id/confirmation_action_button']"));
		SeleniumActions.waittillElement(logout1);
		logout1.click();
		
		}catch (Exception e) {
			System.out.println(e);
		}
	}
	public static void GlobalPayConsumer() {
		try {
		WebElement userName = driver.findElement(By.xpath("//android.widget.EditText[@content-desc='loginMobileNumber']"));
		SeleniumActions.waittillElement(userName);
		userName.sendKeys("96313435");
		
		WebElement pin = driver.findElement(By.xpath("//android.widget.EditText[@content-desc='loginpin']"));
		SeleniumActions.waittillElement(pin);
		pin.click();
		pin.sendKeys("1357");
		
		WebElement done = driver.findElement(By.xpath("//android.widget.ImageView[@content-desc='Done']"));
		SeleniumActions.waittillElement(done);
		done.click();
		
		WebElement next = driver.findElement(By.xpath("//android.widget.Button[@content-desc='loginButton']"));
		SeleniumActions.waittillElement(next);
		next.click();
		
		WebElement skipTutorial = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.globalpay.consumer:id/gtSkipButton']"));
		SeleniumActions.waittillElement(skipTutorial);
		skipTutorial.click();
		
		
		Thread.sleep(5000);
		SwipeHelper.swipeAction(driver,0, 879,1008, 884);
		
		WebElement pendingTranscation = driver.findElement(By.xpath("//android.widget.TextView[@content-desc='hamburger_title_3']"));
		SeleniumActions.waittillElement(pendingTranscation);
		pendingTranscation.click();
		
		WebElement approve = driver.findElement(By.xpath("(//android.widget.Button[@resource-id='com.globalpay.consumer:id/approveButton'])[1]"));
		SeleniumActions.waittillElement(approve);
		approve.click();
		
		WebElement enterPin = driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.globalpay.consumer:id/otp_edit_text']"));
		SeleniumActions.waittillElement(enterPin);
		enterPin.sendKeys("1357");
		
		WebElement verifyPin = driver.findElement(By.xpath("//android.widget.Button[@resource-id='com.globalpay.consumer:id/verifypin_pin_button']"));
		SeleniumActions.waittillElement(verifyPin);
		verifyPin.click();
		
		WebElement back = driver.findElement(By.xpath("//android.widget.ImageView[@resource-id='com.globalpay.consumer:id/toolbar_icon']"));
		SeleniumActions.waittillElement(back);
		back.click();
		
		WebElement history = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.globalpay.consumer:id/navigation_bar_item_small_label_view' and @text='History']"));
		SeleniumActions.waittillElement(history);
		history.click();
		
		WebElement paymentHistory = driver.findElement(By.xpath("(//android.widget.TextView[@resource-id='com.globalpay.consumer:id/walletHistoryAmount'])[1]"));
		SeleniumActions.waittillElement(paymentHistory);
		paymentHistory.click();
		
		WebElement backfromHistory = driver.findElement(By.xpath("//android.widget.ImageView[@resource-id='com.globalpay.consumer:id/toolbar_icon']"));
		SeleniumActions.waittillElement(backfromHistory);
		backfromHistory.click();
		
		Thread.sleep(3000);
		SwipeHelper.swipeAction(driver,0, 879,1008, 884);
		
		WebElement logoutTab = driver.findElement(By.xpath("//android.widget.TextView[@content-desc='hamburger_title_6']"));
		SeleniumActions.waittillElement(logoutTab);
		logoutTab.click();
		
		WebElement logout = driver.findElement(By.xpath("//android.widget.Button[@resource-id='com.globalpay.consumer:id/confirmation_action_button']"));
		SeleniumActions.waittillElement(logout);
		logout.click();
		
		}catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void main(String[] args) {
		logExecutionTime("START");

		try {
			System.out.println("Executing Appium test...");
			initializeDriverForGlobalpayBusiness();
			GlobalpayBusinessByQR();
			if (driver != null) {
				driver.quit();
			}
//			initializeDriverForGlobalpayConsumer();            
//			GlobalPayConsumer();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (driver != null) {
				driver.quit();
			}
			logExecutionTime("END");
		}


	}

}
