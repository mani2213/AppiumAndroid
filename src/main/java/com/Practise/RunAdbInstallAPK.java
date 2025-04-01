package com.Practise;

import java.io.IOException;

public class RunAdbInstallAPK {

    public static void installAPK(String apkPath) {
        try {
            String[] command = {
                "cmd.exe", "/c", 
                "cd C:\\Users\\chell\\Downloads && adb install \"" + apkPath + "\""
            };
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            process.waitFor();
            System.out.println("APK Installation Completed!");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        installAPK("C:\\Users\\chell\\Downloads\\consumer.apk"); 
        installAPK("C:\\Users\\chell\\Downloads\\business 3.apk"); 

    }
}

