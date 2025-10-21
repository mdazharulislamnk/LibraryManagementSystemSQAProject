package com.mycompany.librarymanagementsystem;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReportGeneratorTest {

    @Test
    public void testGenerateBorrowedBooksReport() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("T001", "S001", "B001", LocalDate.now(), LocalDate.now().plusDays(7)));
        transactions.add(new Transaction("T002", "S002", "B002", LocalDate.now(), LocalDate.now().plusDays(10)));

        assertDoesNotThrow(() -> ReportGenerator.generateBorrowedBooksReport(transactions));
    }

    @Test
    public void testGenerateOverdueBooksReport() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("T003", "S003", "B003", LocalDate.now().minusDays(20), LocalDate.now().minusDays(5))); // overdue
        transactions.add(new Transaction("T004", "S004", "B004", LocalDate.now(), LocalDate.now().plusDays(5))); // not overdue

        assertDoesNotThrow(() -> ReportGenerator.generateOverdueBooksReport(transactions));
    }

    @Test
    public void testGenerateReservationsReport() {
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation("R001", "S005", "B005", LocalDate.now()));
        reservations.add(new Reservation("R002", "S006", "B006", LocalDate.now()));

        assertDoesNotThrow(() -> ReportGenerator.generateReservationsReport(reservations));
    }

    @Test
    public void testGenerateActiveBorrowersReport() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("T005", "S007", "B007", LocalDate.now(), LocalDate.now().plusDays(7)));

        List<User> users = new ArrayList<>();
        users.add(new Student("S007", "Student User", "password"));
        users.add(new Student("S008", "Another Student", "password"));

        assertDoesNotThrow(() -> ReportGenerator.generateActiveBorrowersReport(transactions, users));
    }
    
    @Test
public void testGenerateBorrowedBooksReport_EmptyList() {
    List<Transaction> transactions = new ArrayList<>();

    assertDoesNotThrow(() -> ReportGenerator.generateBorrowedBooksReport(transactions));
}

@Test
public void testGenerateOverdueBooksReport_AllReturned() {
    List<Transaction> transactions = new ArrayList<>();

    Transaction t1 = new Transaction("T006", "S009", "B008", LocalDate.now().minusDays(10), LocalDate.now().minusDays(5));
    t1.markAsReturned(LocalDate.now().minusDays(2)); // returned

    transactions.add(t1);

    assertDoesNotThrow(() -> ReportGenerator.generateOverdueBooksReport(transactions)); // should not list returned books
}

@Test
public void testGenerateReservationsReport_EmptyList() {
    List<Reservation> reservations = new ArrayList<>();

    assertDoesNotThrow(() -> ReportGenerator.generateReservationsReport(reservations)); // should handle empty input
}

@Test
public void testGenerateActiveBorrowersReport_NoMatchingUsers() {
    List<Transaction> transactions = new ArrayList<>();
    transactions.add(new Transaction("T007", "S010", "B009", LocalDate.now(), LocalDate.now().plusDays(5)));

    List<User> users = new ArrayList<>();
    users.add(new Student("S011", "Non-Borrowing Student", "pass"));

    assertDoesNotThrow(() -> ReportGenerator.generateActiveBorrowersReport(transactions, users)); // should skip unmatched
}

@Test
public void testGenerateActiveBorrowersReport_DuplicateTransactions() {
    List<Transaction> transactions = new ArrayList<>();
    transactions.add(new Transaction("T008", "S012", "B010", LocalDate.now(), LocalDate.now().plusDays(3)));
    transactions.add(new Transaction("T009", "S012", "B011", LocalDate.now(), LocalDate.now().plusDays(4))); // same user, multiple books

    List<User> users = new ArrayList<>();
    users.add(new Student("S012", "Duplicate Borrower", "pass"));

    assertDoesNotThrow(() -> ReportGenerator.generateActiveBorrowersReport(transactions, users)); // should not duplicate user in report
}

}
