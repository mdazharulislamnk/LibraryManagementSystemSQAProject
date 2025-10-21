package com.mycompany.librarymanagementsystem;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.*;

public class FileManagerTest {

    @Test
    public void testSaveAndLoadUsers() {
        List<User> users = new ArrayList<>();
        users.add(new Admin("A001", "Admin User", "admin123"));
        users.add(new Librarian("L001", "Librarian User", "libpass"));

        String filename = "test_users.txt";
        FileManager.saveUsers(users, filename);

        List<User> loadedUsers = FileManager.loadUsers(filename);

        assertNotNull(loadedUsers);
        assertEquals(2, loadedUsers.size());
        assertEquals("A001", loadedUsers.get(0).getUserId());

        new File(filename).delete(); // Clean up
    }

    @Test
    public void testSaveAndLoadBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("B001", "Test Book", "Author", "Genre", 5));

        String filename = "test_books.txt";
        FileManager.saveBooks(books, filename);

        List<Book> loadedBooks = FileManager.loadBooks(filename);

        assertNotNull(loadedBooks);
        assertEquals(1, loadedBooks.size());
        assertEquals("B001", loadedBooks.get(0).getBookId());

        new File(filename).delete(); // Clean up
    }

    @Test
    public void testSaveAndLoadTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("T001", "S001", "B001", java.time.LocalDate.now(), java.time.LocalDate.now().plusDays(14)));

        String filename = "test_transactions.txt";
        FileManager.saveTransactions(transactions, filename);

        List<Transaction> loadedTransactions = FileManager.loadTransactions(filename);

        assertNotNull(loadedTransactions);
        assertEquals(1, loadedTransactions.size());
        assertEquals("T001", loadedTransactions.get(0).getTransactionId());

        new File(filename).delete(); // Clean up
    }

    @Test
    public void testSaveAndLoadReservations() {
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation("R001", "S001", "B001", java.time.LocalDate.now()));

        String filename = "test_reservations.txt";
        FileManager.saveReservations(reservations, filename);

        List<Reservation> loadedReservations = FileManager.loadReservations(filename);

        assertNotNull(loadedReservations);
        assertEquals(1, loadedReservations.size());
        assertEquals("R001", loadedReservations.get(0).getReservationId());

        new File(filename).delete(); // Clean up
    }
    
    @Test
public void testSaveEmptyUserListAndLoad() {
    List<User> users = new ArrayList<>();
    String filename = "empty_users.txt";

    FileManager.saveUsers(users, filename);
    List<User> loaded = FileManager.loadUsers(filename);

    assertNotNull(loaded);
    assertEquals(0, loaded.size());

    new File(filename).delete();
}

@Test
public void testLoadFromNonexistentFile() {
    String filename = "nonexistent_file.txt";

    List<User> loadedUsers = FileManager.loadUsers(filename);
    assertNotNull(loadedUsers);  // Should not return null
    assertEquals(0, loadedUsers.size());  // Should return an empty list
}

@Test
public void testSaveAndLoadBooksWithZeroQuantity() {
    List<Book> books = new ArrayList<>();
    books.add(new Book("B999", "Zero Stock Book", "Unknown Author", "Rare", 0));

    String filename = "zero_quantity_books.txt";
    FileManager.saveBooks(books, filename);

    List<Book> loadedBooks = FileManager.loadBooks(filename);
    assertEquals(1, loadedBooks.size());
    assertEquals(0, loadedBooks.get(0).getQuantity());

    new File(filename).delete();
}

@Test
public void testTransactionDateIntegrity() {
    LocalDate borrowDate = LocalDate.of(2025, 1, 1);
    LocalDate dueDate = LocalDate.of(2025, 1, 15);

    List<Transaction> transactions = new ArrayList<>();
    transactions.add(new Transaction("T999", "S999", "B999", borrowDate, dueDate));

    String filename = "date_integrity_transactions.txt";
    FileManager.saveTransactions(transactions, filename);

    List<Transaction> loaded = FileManager.loadTransactions(filename);
    assertEquals(borrowDate, loaded.get(0).getIssueDate());
    assertEquals(dueDate, loaded.get(0).getDueDate());

    new File(filename).delete();
}

@Test
public void testCorruptedFileGracefullyHandled() throws Exception {
    String filename = "corrupted_data.txt";
    File corrupted = new File(filename);
    java.nio.file.Files.write(corrupted.toPath(), "Not a valid serialized object".getBytes());

    List<Book> loadedBooks = FileManager.loadBooks(filename);
    assertNotNull(loadedBooks); // Should return empty list, not null
    assertEquals(0, loadedBooks.size());

    corrupted.delete();
}

}
