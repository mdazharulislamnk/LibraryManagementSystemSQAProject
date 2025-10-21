package com.mycompany.librarymanagementsystem;

import java.time.LocalDate;

public class Transaction {

    private String transactionId;
    private String userId;
    private String bookId;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private boolean isReturned;

    public Transaction(String transactionId, String userId, String bookId, LocalDate issueDate, LocalDate dueDate) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.bookId = bookId;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.isReturned = false;
    }

    // Getters
    public String getTransactionId() {
        return transactionId;
    }

    public String getUserId() {
        return userId;
    }

    public String getBookId() {
        return bookId;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public boolean isReturned() {
        return isReturned;
    }

    // Methods
    public void markAsReturned(LocalDate returnDate) {
        this.returnDate = returnDate;
        this.isReturned = true;
    }


    @Override
    public String toString() {
        return "Transaction ID: " + transactionId +
               ", User ID: " + userId +
               ", Book ID: " + bookId +
               ", Issued On: " + issueDate +
               ", Due On: " + dueDate +
               (isReturned ? ", Returned On: " + returnDate : ", Not yet returned");
    }
}
