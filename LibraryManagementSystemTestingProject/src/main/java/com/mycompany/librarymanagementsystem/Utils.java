package com.mycompany.librarymanagementsystem;

import java.util.Scanner;

public class Utils {

    private static Scanner scanner = new Scanner(System.in);

    // Get non-empty input
    public static String getNonEmptyInput(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("⚠️ Input cannot be empty!");
            }
        } while (input.isEmpty());
        return input;
    }

    // Get integer input with error handling
    public static int getIntegerInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid number. Please enter a valid integer.");
            }
        }
    }

    // Pause the screen (Press Enter to continue)
    public static void pause() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    // Yes/No confirmation
    public static boolean getYesOrNo(String prompt) {
        while (true) {
            System.out.print(prompt + " (Y/N): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y")) {
                return true;
            } else if (input.equals("n")) {
                return false;
            } else {
                System.out.println("⚠️ Please enter Y or N.");
            }
        }
    }
}
