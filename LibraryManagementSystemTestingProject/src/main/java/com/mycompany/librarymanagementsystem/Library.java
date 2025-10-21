package com.mycompany.librarymanagementsystem;

import java.util.ArrayList;
import java.util.List;

public class Library {

    private List<User> users;
    private List<Book> books;

    public Library() {
        users = new ArrayList<>();
        books = new ArrayList<>();
    }

    // ===== User Management =====

    public void addUser(User user) {
        users.add(user);
        System.out.println("✅ User added successfully: " + user.getName() + " (" + user.getRole() + ")");
    }

    public void removeUser(String userId) {
        boolean found = false;
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                users.remove(user);
                System.out.println("✅ User removed successfully: " + user.getName());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("⚠️ User with ID " + userId + " not found.");
        }
    }

    public User searchUserById(String userId) {
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    public void viewAllUsers() {
        System.out.println("\n===== List of Users =====");
        for (User user : users) {
            System.out.println(user);
        }
    }

    public List<User> getUsers() {
        return users;
    }

    // ===== Book Management =====

    public void addBook(Book book) {
        books.add(book);
        System.out.println("✅ Book added successfully: " + book.getTitle());
    }

    public void removeBook(String bookId) {
        boolean found = false;
        for (Book book : books) {
            if (book.getBookId().equals(bookId)) {
                books.remove(book);
                System.out.println("✅ Book removed successfully: " + book.getTitle());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("⚠️ Book with ID " + bookId + " not found.");
        }
    }

    public Book searchBookById(String bookId) {
        for (Book book : books) {
            if (book.getBookId().equals(bookId)) {
                return book;
            }
        }
        return null;
    }

    public void viewAllBooks() {
        System.out.println("\n===== List of Books =====");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public List<Book> getBooks() {
        return books;
    }

    // ===== Utility Methods =====

    public void seedDummyData() {
        // Adds some dummy books and users to test easily
        books.add(new Book("B001", "The Alchemist", "Paulo Coelho", "Fiction", 5));
        books.add(new Book("B002", "Introduction to Algorithms", "Cormen", "Education", 3));
        users.add(new Admin("A001", "Alice Admin", "adminpass"));
        users.add(new Librarian("L001", "Bob Librarian", "librarianpass"));
        users.add(new Student("S001", "Charlie Student", "studentpass"));
        System.out.println("✅ Dummy data loaded.");
    }
}
