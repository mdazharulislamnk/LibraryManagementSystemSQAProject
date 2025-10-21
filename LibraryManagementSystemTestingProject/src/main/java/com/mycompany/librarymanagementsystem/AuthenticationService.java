package com.mycompany.librarymanagementsystem;

import java.util.List;
import java.util.Scanner;

public class AuthenticationService {

    private static User loggedInUser = null;

    // Login method
    public static User login(List<User> users, String userId, String password) {
        for (User user : users) {
            if (user.getUserId().equals(userId) && user.getPassword().equals(password)) {
                loggedInUser = user;
                System.out.println("✅ Login successful. Welcome " + user.getName() + " (" + user.getRole() + ")");
                return user;
            }
        }
        System.out.println("❌ Invalid user ID or password!");
        return null;
    }

    // Logout method
    public static void logout() {
        if (loggedInUser != null) {
            System.out.println("👋 " + loggedInUser.getName() + " logged out successfully.");
            loggedInUser = null;
        } else {
            System.out.println("⚠️ No user is currently logged in.");
        }
    }

    // Reset password method
    public static void resetPassword(List<User> users, String userId) {
        Scanner scanner = new Scanner(System.in);
        User user = null;
        for (User u : users) {
            if (u.getUserId().equals(userId)) {
                user = u;
                break;
            }
        }
        if (user != null) {
            System.out.print("Enter new password: ");
            String newPassword = scanner.nextLine();
            user.setPassword(newPassword);
            System.out.println("✅ Password reset successfully for user " + user.getName());
        } else {
            System.out.println("⚠️ User not found!");
        }
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }
}
