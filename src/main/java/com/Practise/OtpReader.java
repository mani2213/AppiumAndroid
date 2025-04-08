package com.Practise;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class OtpReader {
	public static String extractOtp(String messageBody) {
	    Pattern pattern = Pattern.compile("\\b\\d{4,8}\\b");
	    Matcher matcher = pattern.matcher(messageBody);
	    if (matcher.find()) {
	        return matcher.group();
	    }
	    return null;
	}

	public static void readOtp() {
	    try {
	        Process process = Runtime.getRuntime().exec("adb shell content query --uri content://sms/inbox --projection body --sort \"date DESC\" --limit 1");
	        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

	        String line;
	        while ((line = reader.readLine()) != null) {
	            if (line.contains("body=")) {
	                String body = line.split("body=")[1].trim();
	                String otp = extractOtp(body);
	                System.out.println("📩 SMS Body: " + body);
	                System.out.println("🔐 Extracted OTP: " + otp);
	            }
	        }

	        process.waitFor();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public static String getOtpFromLatestSms() {
	    try {
	        Process process = Runtime.getRuntime().exec("adb shell content query --uri content://sms/inbox --projection body --sort \"date DESC\" --limit 1");
	        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

	        String line;
	        while ((line = reader.readLine()) != null) {
	            if (line.contains("body=")) {
	                String body = line.split("body=")[1].trim();
	                Pattern pattern = Pattern.compile("\\b\\d{4,8}\\b");
	                Matcher matcher = pattern.matcher(body);
	                if (matcher.find()) {
	                    return matcher.group(); // ✅ return the OTP
	                }
	            }
	        }

	        process.waitFor();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null; // ❌ no OTP found
	}

public static void main(String[] args) {
	getOtpFromLatestSms();
}
}
