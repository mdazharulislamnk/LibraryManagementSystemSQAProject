package com.mycompany.librarymanagementsystem;

import java.util.List;

public class Admin extends User {

    public Admin(String userId, String name, String password) {
        super(userId, name, password, "Admin");
    }

    @Override
    public void showMenu() {
        System.out.println("\n===== Admin Menu =====");
        System.out.println("1. Add Librarian");
        System.out.println("2. Remove Librarian");
        System.out.println("3. View All Librarians");
        System.out.println("4. View All Students");
        System.out.println("5. View All Books");
        System.out.println("6. View Reports");
        System.out.println("7. Logout");
    }

    // Admin-specific operations

    public void addLibrarian(List<User> users, Librarian librarian) {
        users.add(librarian);
        System.out.println("✅ Librarian added successfully: " + librarian.getName());
    }

    public void removeLibrarian(List<User> users, String librarianId) {
        boolean found = false;
        for (User user : users) {
            if (user instanceof Librarian && user.getUserId().equals(librarianId)) {
                users.remove(user);
                System.out.println("✅ Librarian removed successfully: " + user.getName());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("⚠️ Librarian with ID " + librarianId + " not found.");
        }
    }

    public void viewLibrarians(List<User> users) {
        System.out.println("\n===== List of Librarians =====");
        for (User user : users) {
            if (user instanceof Librarian) {
                System.out.println(user);
            }
        }
    }

    public void viewStudents(List<User> users) {
        System.out.println("\n===== List of Students =====");
        for (User user : users) {
            if (user instanceof Student) {
                System.out.println(user);
            }
        }
    }

    public void viewAllBooks(List<Book> books) {
        System.out.println("\n===== List of Books =====");
        for (Book book : books) {
            System.out.println(book);
        }
    }
    
    public void viewReports() {
        System.out.println("\n📄 Report Generation is under construction (You can connect with ReportGenerator class later)");
    }

    @Override
    public String toString() {
        return "[Admin] " + name + " (ID: " + userId + ")";
    }
}
