package com.mycompany.librarymanagementsystem;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateUtils {

    // Return today's date
    public static LocalDate getToday() {
        return LocalDate.now();
    }

    // Calculate due date after borrowing a book (default 14 days)
    public static LocalDate calculateDueDate(int borrowPeriodDays) {
        return LocalDate.now().plusDays(borrowPeriodDays);
    }

    // Calculate days between two dates
    public static long calculateDaysBetween(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    // Check if a date is overdue
    public static boolean isOverdue(LocalDate dueDate) {
        return LocalDate.now().isAfter(dueDate);
    }
}
