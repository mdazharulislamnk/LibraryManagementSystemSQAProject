package com.mycompany.librarymanagementsystem;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {

    @Test
    public void testGetters() {
        LocalDate issueDate = LocalDate.of(2024, 4, 1);
        LocalDate dueDate = LocalDate.of(2024, 4, 15);
        Transaction transaction = new Transaction("T001", "S001", "B001", issueDate, dueDate);

        assertEquals("T001", transaction.getTransactionId());
        assertEquals("S001", transaction.getUserId());
        assertEquals("B001", transaction.getBookId());
        assertEquals(issueDate, transaction.getIssueDate());
        assertEquals(dueDate, transaction.getDueDate());
        assertNull(transaction.getReturnDate());
        assertFalse(transaction.isReturned());
    }

    @Test
    public void testMarkAsReturned() {
        LocalDate issueDate = LocalDate.of(2024, 4, 1);
        LocalDate dueDate = LocalDate.of(2024, 4, 15);
        Transaction transaction = new Transaction("T002", "S002", "B002", issueDate, dueDate);

        assertFalse(transaction.isReturned());

        LocalDate returnDate = LocalDate.of(2024, 4, 10);
        transaction.markAsReturned(returnDate);

        assertTrue(transaction.isReturned());
        assertEquals(returnDate, transaction.getReturnDate());
    }

    @Test
    public void testToString() {
        LocalDate issueDate = LocalDate.of(2024, 4, 1);
        LocalDate dueDate = LocalDate.of(2024, 4, 15);
        Transaction transaction = new Transaction("T003", "S003", "B003", issueDate, dueDate);

        String output = transaction.toString();

        assertNotNull(output);
        assertTrue(output.contains("T003"));
        assertTrue(output.contains("S003"));
        assertTrue(output.contains("B003"));
    }

    @Test
    public void testMarkAsReturned_AfterDueDate() {
        Transaction transaction = new Transaction("T004", "S004", "B004",
                LocalDate.of(2024, 3, 1),
                LocalDate.of(2024, 3, 10));

        LocalDate returnDate = LocalDate.of(2024, 3, 15);
        transaction.markAsReturned(returnDate);

        assertTrue(transaction.isReturned());
        assertEquals(returnDate, transaction.getReturnDate());
    }

    @Test
    public void testTransaction_NotReturnedYet() {
        Transaction transaction = new Transaction("T005", "S005", "B005",
                LocalDate.now().minusDays(10),
                LocalDate.now());

        assertFalse(transaction.isReturned());
        assertNull(transaction.getReturnDate());
    }

    @Test
    public void testReturnDateDoesNotAffectIssueOrDue() {
        LocalDate issue = LocalDate.of(2024, 1, 1);
        LocalDate due = LocalDate.of(2024, 1, 10);
        Transaction transaction = new Transaction("T006", "S006", "B006", issue, due);

        transaction.markAsReturned(LocalDate.of(2024, 1, 15));

        assertEquals(issue, transaction.getIssueDate());
        assertEquals(due, transaction.getDueDate());
    }

    @Test
    public void testMultipleMarkAsReturnedCalls() {
        Transaction transaction = new Transaction("T007", "S007", "B007",
                LocalDate.of(2024, 2, 1),
                LocalDate.of(2024, 2, 10));

        transaction.markAsReturned(LocalDate.of(2024, 2, 12));
        transaction.markAsReturned(LocalDate.of(2024, 2, 13)); // second call

        assertTrue(transaction.isReturned());
        assertEquals(LocalDate.of(2024, 2, 13), transaction.getReturnDate()); // last value kept
    }

    @Test
    public void testInvalidDates_AcceptedAsIs() {
        LocalDate issue = LocalDate.of(2024, 5, 10);
        LocalDate due = LocalDate.of(2024, 5, 5); // due before issue

        Transaction transaction = new Transaction("T008", "S008", "B008", issue, due);

        assertEquals(issue, transaction.getIssueDate());
        assertEquals(due, transaction.getDueDate());
    }
}
