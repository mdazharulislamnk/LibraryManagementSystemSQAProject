package com.mycompany.librarymanagementsystem;

public class Book {
    private String bookId;
    private String title;
    private String author;
    private String genre;
    private int quantity;
    private int totalCopies; // To track original number of copies

    public Book(String bookId, String title, String author, String genre, int quantity) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.quantity = quantity;
        this.totalCopies = quantity;
    }

    // Getters
    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.totalCopies = quantity;
    }

    // Methods
    public boolean borrowBook() {
        if (quantity > 0) {
            quantity--;
            return true; // Successful borrow
        } else {
            return false; // No copies available
        }
    }

    public void returnBook() {
        if (quantity < totalCopies) {
            quantity++;
        }
    }

public void increaseQuantity(int amount) {
    if (amount > 0) {
        this.quantity += amount;
        this.totalCopies += amount;
    }
}


public void decreaseQuantity(int amount) {
    if (amount > 0 && amount <= quantity) {
        this.quantity -= amount;
        this.totalCopies -= amount;
    }
}


    @Override
    public String toString() {
        return "Book ID: " + bookId +
               ", Title: " + title +
               ", Author: " + author +
               ", Genre: " + genre +
               ", Available: " + quantity + "/" + totalCopies;
    }
}
