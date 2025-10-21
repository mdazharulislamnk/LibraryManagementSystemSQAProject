package com.mycompany.librarymanagementsystem;

import java.time.LocalDate;
import java.util.List;

public class ReportGenerator {

    public static void generateBorrowedBooksReport(List<Transaction> transactions) {
        System.out.println("\n===== Borrowed Books Report =====");
        for (Transaction transaction : transactions) {
            if (!transaction.isReturned()) {
                System.out.println(transaction);
            }
        }
    }

    public static void generateOverdueBooksReport(List<Transaction> transactions) {
        System.out.println("\n===== Overdue Books Report =====");
        LocalDate today = LocalDate.now();
        for (Transaction transaction : transactions) {
            if (!transaction.isReturned() && transaction.getDueDate().isBefore(today)) {
                System.out.println(transaction);
            }
        }
    }

    public static void generateReservationsReport(List<Reservation> reservations) {
        System.out.println("\n===== Reservations Report =====");
        for (Reservation reservation : reservations) {
            if (!reservation.isFulfilled()) {
                System.out.println(reservation);
            }
        }
    }

    public static void generateActiveBorrowersReport(List<Transaction> transactions, List<User> users) {
        System.out.println("\n===== Active Borrowers Report =====");
        for (User user : users) {
            if (user instanceof Student) {
                long borrowedCount = transactions.stream()
                        .filter(t -> t.getUserId().equals(user.getUserId()) && !t.isReturned())
                        .count();
                if (borrowedCount > 0) {
                    System.out.println(user.getName() + " (ID: " + user.getUserId() + ") - Borrowed: " + borrowedCount + " book(s)");
                }
            }
        }
    }
}
