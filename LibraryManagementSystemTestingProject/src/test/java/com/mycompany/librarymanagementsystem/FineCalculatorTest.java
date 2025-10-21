package com.mycompany.librarymanagementsystem;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class FineCalculatorTest {

    @Test
    public void testCalculateFine_NoFine() {
        LocalDate dueDate = LocalDate.now().plusDays(5); // Due in future
        LocalDate returnDate = LocalDate.now(); // Returned today

        double fine = FineCalculator.calculateFine(dueDate, returnDate);

        assertEquals(0.0, fine, 0.001);
    }

    @Test
    public void testCalculateFine_WithFine() {
        LocalDate dueDate = LocalDate.now().minusDays(3); // Due 3 days ago
        LocalDate returnDate = LocalDate.now(); // Returned today

        double fine = FineCalculator.calculateFine(dueDate, returnDate);

        assertEquals(30.0, fine, 0.001); // 3 × 10
    }

    @Test
    public void testCalculateFine_ReturnedOnDueDate() {
        LocalDate dueDate = LocalDate.now();
        LocalDate returnDate = LocalDate.now();

        double fine = FineCalculator.calculateFine(dueDate, returnDate);

        assertEquals(0.0, fine, 0.001);
    }

    @Test
    public void testCalculateFine_ReturnedBeforeDueDate() {
        LocalDate dueDate = LocalDate.now().plusDays(7);
        LocalDate returnDate = LocalDate.now();

        double fine = FineCalculator.calculateFine(dueDate, returnDate);

        assertEquals(0.0, fine, 0.001);
    }

    @Test
    public void testCalculateFine_LeapYearHandling() {
        LocalDate dueDate = LocalDate.of(2024, 2, 28);
        LocalDate returnDate = LocalDate.of(2024, 3, 1); // 2 days later

        double fine = FineCalculator.calculateFine(dueDate, returnDate);

        assertEquals(20.0, fine, 0.001); // 2 × 10
    }

    @Test
    public void testCalculateFine_LongOverdue() {
        LocalDate dueDate = LocalDate.now().minusDays(100);
        LocalDate returnDate = LocalDate.now();

        double fine = FineCalculator.calculateFine(dueDate, returnDate);

        assertEquals(1000.0, fine, 0.001); // 100 × 10
    }

    @Test
    public void testCalculateFine_NegativeDuration() {
        LocalDate dueDate = LocalDate.now();
        LocalDate returnDate = LocalDate.now().minusDays(1); // Returned early

        double fine = FineCalculator.calculateFine(dueDate, returnDate);

        assertEquals(0.0, fine, 0.001);
    }

    @Test
    public void testDisplayFine_NoFine() {
        LocalDate dueDate = LocalDate.now().plusDays(2);
        LocalDate returnDate = LocalDate.now();

        assertDoesNotThrow(() -> FineCalculator.displayFine(dueDate, returnDate));
    }

    @Test
    public void testDisplayFine_WithFine() {
        LocalDate dueDate = LocalDate.now().minusDays(5);
        LocalDate returnDate = LocalDate.now();

        assertDoesNotThrow(() -> FineCalculator.displayFine(dueDate, returnDate));
    }
}
