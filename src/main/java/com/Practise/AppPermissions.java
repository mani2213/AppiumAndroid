package com.Practise;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppPermissions {
	//  Common permission constants
	public static final String CAMERA = "android.permission.CAMERA";
	public static final String MICROPHONE = "android.permission.RECORD_AUDIO";
	public static final String LOCATION = "android.permission.ACCESS_FINE_LOCATION";
	public static final String STORAGE_READ = "android.permission.READ_EXTERNAL_STORAGE";
	public static final String STORAGE_WRITE = "android.permission.WRITE_EXTERNAL_STORAGE";
	public static final String CONTACTS = "android.permission.READ_CONTACTS";

	public static void runAdbCommand(String command) {
		try {
			Process process = Runtime.getRuntime().exec(command);
			process.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//  Grant a permission to the app
	public static void grantPermission(String packageName, String permission) {
		runAdbCommand("adb shell pm grant " + packageName + " " + permission);
		System.out.println("Granted: " + permission);
	}

	//  Revoke a permission from the app
	public static void revokePermission(String packageName, String permission) {
		runAdbCommand("adb shell pm revoke " + packageName + " " + permission);
		System.out.println("Revoked: " + permission);
	}

	//  List all permissions (declared and granted) for the app
	public static void listPermissions(String packageName) {
	//	runAdbCommand("adb shell dumpsys package " + packageName + " | grep permission");
		try {
	        Process process = Runtime.getRuntime().exec("adb shell dumpsys package " + packageName);
	        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

	        String line;
	        System.out.println("Permissions for: " + packageName);
	        while ((line = reader.readLine()) != null) {
	            if (line.contains("permission")) {
	                System.out.println("  " + line.trim());
	            }
	        }

	        process.waitFor();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	//  Toggle (revoke and then re-grant — simple version)
	public static void togglePermission(String packageName, String permission) {
		revokePermission(packageName, permission);
		sleep(500);
		grantPermission(packageName, permission);
	}

	// Utility sleep method
	public static void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		revokePermission("com.globalpay.merchant",CONTACTS);
		listPermissions("com.globalpay.merchant");

	}
}
