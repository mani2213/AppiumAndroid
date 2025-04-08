package com.Practise;
import java.io.*;

public class UserInPutCMD {

    private static Process process;
    private static BufferedReader reader;
    private static BufferedWriter writer;


    public static void sendCommand(String command) {
        try {
            writer.write(command);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.out.println("Error sending command!");
            e.printStackTrace();
        }
    }

    public static void stopCMD() {
        try {
            sendCommand("exit"); // Close CMD
            if (process != null) {
                process.destroy();
                System.out.println("CMD Closed Successfully!");
            }
        } catch (Exception e) {
            System.out.println("Error stopping CMD!");
            e.printStackTrace();
        }
    }

    public static void readUserInput() {
    	try {
            // Start CMD process
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe");
            processBuilder.redirectErrorStream(true);
            process = processBuilder.start();

            // Initialize reader/writer to communicate with CMD
            reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));

            // Start a thread to print real-time CMD output
            new Thread(() -> {
                try {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);  // Print CMD output to console
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            System.out.println("CMD Started! You can now send commands.");

        } catch (IOException e) {
            System.out.println("Failed to start CMD!");
            e.printStackTrace();
        }
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter CMD commands (type 'exit' to quit):");

        while (true) {
            try {
                System.out.print("> ");  
                String input = userInput.readLine();

                if ("exit".equalsIgnoreCase(input)) {
                    stopCMD();
                    break;
                }

                sendCommand(input);  

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void stopAppiumServer() {
        try {
            Runtime.getRuntime().exec("taskkill /F /IM node.exe");  // Windows
            // Runtime.getRuntime().exec("pkill -f appium"); // macOS/Linux
            System.out.println("Appium server stopped.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
//        startCMD(); 
        readUserInput(); 
//        stopAppiumServer();
    }
}
