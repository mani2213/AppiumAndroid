package com.Practise;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class MobileInteraction {
	    //  Core method to execute any ADB command
	    public static void runAdbCommand(String command) {
	        try {
	            Process process = Runtime.getRuntime().exec(command);
	            process.waitFor();

	            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
	            String line;
	            while ((line = reader.readLine()) != null) {
	                System.out.println(line);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    //  Wi-Fi
	    public static void enableWifi() {
	        runAdbCommand("adb shell svc wifi enable");
	    }

	    public static void disableWifi() {
	        runAdbCommand("adb shell svc wifi disable");
	    }

	    //  Bluetooth (opens settings)
	    public static void openBluetoothSettings() {
	        runAdbCommand("adb shell am start -a android.settings.BLUETOOTH_SETTINGS");
	    }

	    //  Airplane Mode
	    public static void enableAirplaneMode() {
	        runAdbCommand("adb shell settings put global airplane_mode_on 1");
	        runAdbCommand("adb shell am broadcast -a android.intent.action.AIRPLANE_MODE --ez state true");
	    }

	    public static void disableAirplaneMode() {
	        runAdbCommand("adb shell settings put global airplane_mode_on 0");
	        runAdbCommand("adb shell am broadcast -a android.intent.action.AIRPLANE_MODE --ez state false");
	    }

	    //  Location (GPS)
	    public static void enableLocation() {
	        runAdbCommand("adb shell settings put secure location_mode 3");
	    }

	    public static void disableLocation() {
	        runAdbCommand("adb shell settings put secure location_mode 0");
	    }

	    //  Dark Mode
	    public static void enableDarkMode() {
	        runAdbCommand("adb shell settings put secure ui_night_mode 2");
	    }
	    
	    public static void enableDarkModeOnePlus() {
	        runAdbCommand("adb shell am start -a android.settings.DISPLAY_SETTINGS");
	        try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    	tap(727,856);
	    }

	    public static void disableDarkMode() {
	        runAdbCommand("adb shell settings put secure ui_night_mode 1");
	    }
	    
	    //  Dark Mode for OnePlus kind of devices
	    public static void disableDarkModeOnePlus() {
	        runAdbCommand("adb shell am start -a android.settings.DISPLAY_SETTINGS");
	        try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    	tap(349,854);
	    }

	    //  Tap on screen
	    public static void tap(int x, int y) {
	        runAdbCommand("adb shell input tap " + x + " " + y);
	    }

	    //  Swipe on screen
	    public static void swipe(int x1, int y1, int x2, int y2, int duration) {
	        runAdbCommand("adb shell input swipe " + x1 + " " + y1 + " " + x2 + " " + y2 + " " + duration);
	    }

	    //  Screenshot
	    public static void takeScreenshot(String filename) {
	        runAdbCommand("adb shell screencap -p /sdcard/DCIM/Camera/" + filename);
	        runAdbCommand("adb pull /sdcard/DCIM/Camera/" + filename);
	    }

	     //  Record screen
	    public static void recordScreen(String filename) {
	        runAdbCommand("adb shell screenrecord --time-limit 10 /sdcard/DCIM/Camera/" + filename);
	        runAdbCommand("adb pull /sdcard/DCIM/Camera/" + filename);
	    }

	    // 🏁 Sample runner
	    public static void main(String[] args) {
//	    	enableWifi();
//	    	openBluetoothSettings();  
//	    	try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//	    	tap(942,413);
//	    	runAdbCommand("adb devices");
//	    	takeScreenshot("display.png");
//	    	disableDarkModeOnePlus();
//	    	disableLocation();
	    }
	    	
	  
	}


