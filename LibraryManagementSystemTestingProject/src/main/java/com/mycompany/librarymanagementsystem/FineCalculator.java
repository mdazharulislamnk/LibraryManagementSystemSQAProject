package com.mycompany.librarymanagementsystem;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FineCalculator {

    private static final double FINE_PER_DAY = 10.0; // You can change fine rate here

    // Calculate fine based on due date and actual return date
    public static double calculateFine(LocalDate dueDate, LocalDate returnDate) {
        if (returnDate.isAfter(dueDate)) {
            long daysLate = ChronoUnit.DAYS.between(dueDate, returnDate);
            return daysLate * FINE_PER_DAY;
        } else {
            return 0.0;
        }
    }

    // Display fine nicely
    public static void displayFine(LocalDate dueDate, LocalDate returnDate) {
        double fine = calculateFine(dueDate, returnDate);
        if (fine > 0) {
            System.out.println("⚠️ Book was returned late!");
            System.out.println("Fine to be paid: " + fine + " units.");
        } else {
            System.out.println("✅ Book returned on time. No fine!");
        }
    }
}
