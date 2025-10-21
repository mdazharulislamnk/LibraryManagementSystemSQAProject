package com.mycompany.librarymanagementsystem;

import java.util.List;

public class Librarian extends User {

    public Librarian(String userId, String name, String password) {
        super(userId, name, password, "Librarian");
    }

    @Override
    public void showMenu() {
        System.out.println("\n===== Librarian Menu =====");
        System.out.println("1. Add Book");
        System.out.println("2. Edit Book Details");
        System.out.println("3. Remove Book");
        System.out.println("4. View All Books");
        System.out.println("5. Issue Book to Student");
        System.out.println("6. Receive Returned Book");
        System.out.println("7. View Borrowed Books");
        System.out.println("8. Logout");
    }

    // Librarian-specific operations

    public void addBook(List<Book> books, Book newBook) {
        books.add(newBook);
        System.out.println("✅ Book added successfully: " + newBook.getTitle());
    }

    public void editBook(Book book, String newTitle, String newAuthor, String newGenre, int newQuantity) {
        book.setTitle(newTitle);
        book.setAuthor(newAuthor);
        book.setGenre(newGenre);
        book.setQuantity(newQuantity);
        System.out.println("✅ Book details updated successfully.");
    }

    public void removeBook(List<Book> books, String bookId) {
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

    public void viewAllBooks(List<Book> books) {
        System.out.println("\n===== List of Books =====");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public boolean issueBook(Book book) {
        if (book.borrowBook()) {
            System.out.println("✅ Book issued successfully: " + book.getTitle());
            return true;
        } else {
            System.out.println("⚠️ Book is currently unavailable for issuing.");
            return false;
        }
    }

    public void receiveReturnedBook(Book book) {
        book.returnBook();
        System.out.println("✅ Book returned successfully: " + book.getTitle());
    }

    @Override
    public String toString() {
        return "[Librarian] " + name + " (ID: " + userId + ")";
    }
}
