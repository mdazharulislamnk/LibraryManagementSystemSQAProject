package com.mycompany.librarymanagementsystem;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {

    private List<Book> borrowedBooks;
    private List<Book> reservedBooks;
    private static final int MAX_BORROW_LIMIT = 5;

    public Student(String userId, String name, String password) {
        super(userId, name, password, "Student");
        this.borrowedBooks = new ArrayList<>();
        this.reservedBooks = new ArrayList<>();
    }

    @Override
    public void showMenu() {
        System.out.println("\n===== Student Menu =====");
        System.out.println("1. View All Books");
        System.out.println("2. Borrow Book");
        System.out.println("3. Return Book");
        System.out.println("4. Reserve Book");
        System.out.println("5. View Borrowed Books");
        System.out.println("6. View Reserved Books");
        System.out.println("7. Logout");
    }

    // Student-specific operations

    public void borrowBook(Book book) {
        if (borrowedBooks.size() >= MAX_BORROW_LIMIT) {
            System.out.println("⚠️ You have reached the maximum borrow limit (" + MAX_BORROW_LIMIT + " books).");
            return;
        }
        if (book.borrowBook()) {
            borrowedBooks.add(book);
            System.out.println("✅ Book borrowed successfully: " + book.getTitle());
        } else {
            System.out.println("⚠️ Book is not available for borrowing. You can reserve it instead.");
        }
    }

    public void returnBook(Book book) {
        if (borrowedBooks.remove(book)) {
            book.returnBook();
            System.out.println("✅ Book returned successfully: " + book.getTitle());
        } else {
            System.out.println("⚠️ This book is not in your borrowed list.");
        }
    }

    public void reserveBook(Book book) {
        if (!reservedBooks.contains(book)) {
            reservedBooks.add(book);
            System.out.println("✅ Book reserved successfully: " + book.getTitle());
        } else {
            System.out.println("⚠️ You have already reserved this book.");
        }
    }

    public void viewBorrowedBooks() {
        System.out.println("\n===== Your Borrowed Books =====");
        if (borrowedBooks.isEmpty()) {
            System.out.println("You have not borrowed any books.");
        } else {
            for (Book book : borrowedBooks) {
                System.out.println(book);
            }
        }
    }

    public void viewReservedBooks() {
        System.out.println("\n===== Your Reserved Books =====");
        if (reservedBooks.isEmpty()) {
            System.out.println("You have not reserved any books.");
        } else {
            for (Book book : reservedBooks) {
                System.out.println(book);
            }
        }
    }

    @Override
    public String toString() {
        return "[Student] " + name + " (ID: " + userId + ")";
    }
    
    public List<Book> getBorrowedBooks() {
    return borrowedBooks;
}

public List<Book> getReservedBooks() {
    return reservedBooks;
}

}

