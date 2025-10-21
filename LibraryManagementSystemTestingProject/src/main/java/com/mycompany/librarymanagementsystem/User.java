package com.mycompany.librarymanagementsystem;

public abstract class User {
    protected String userId;
    protected String name;
    protected String password;
    protected String role; // "Admin", "Librarian", "Student"

    public User(String userId, String name, String password, String role) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    // Getters
    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Common method to view profile
    public void viewProfile() {
        System.out.println("\n=== Profile Information ===");
        System.out.println("User ID   : " + userId);
        System.out.println("Name      : " + name);
        System.out.println("Role      : " + role);
    }

    // Abstract method - force subclasses to create their own menu
    public abstract void showMenu();
}
