package com.mycompany.librarymanagementsystem;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LibrarianTest {

    @Test
    public void testAddBook() {
        Librarian librarian = new Librarian("L001", "Librarian Name", "password");
        List<Book> books = new ArrayList<>();
        Book newBook = new Book("B001", "Test Book", "Test Author", "Test Genre", 5);

        librarian.addBook(books, newBook);

        assertEquals(1, books.size());
        assertEquals("B001", books.get(0).getBookId());
    }

    @Test
    public void testEditBook() {
        Librarian librarian = new Librarian("L002", "Librarian Name", "password");
        Book book = new Book("B002", "Old Title", "Old Author", "Old Genre", 3);

        librarian.editBook(book, "New Title", "New Author", "New Genre", 10);

        assertEquals("New Title", book.getTitle());
        assertEquals("New Author", book.getAuthor());
        assertEquals("New Genre", book.getGenre());
        assertEquals(10, book.getQuantity());
    }

    @Test
    public void testRemoveBook() {
        Librarian librarian = new Librarian("L003", "Librarian Name", "password");
        List<Book> books = new ArrayList<>();
        books.add(new Book("B003", "Title1", "Author1", "Genre1", 2));
        books.add(new Book("B004", "Title2", "Author2", "Genre2", 3));

        librarian.removeBook(books, "B003");

        assertEquals(1, books.size());
        assertEquals("B004", books.get(0).getBookId());
    }

    @Test
    public void testIssueBook() {
        Librarian librarian = new Librarian("L004", "Librarian Name", "password");
        Book book = new Book("B005", "Issue Book", "Author", "Genre", 1);

        boolean result = librarian.issueBook(book);

        assertTrue(result);
        assertEquals(0, book.getQuantity());

        // Trying to issue again should fail
        boolean secondTry = librarian.issueBook(book);
        assertFalse(secondTry);
    }

    @Test
    public void testReceiveReturnedBook() {
        Librarian librarian = new Librarian("L005", "Librarian Name", "password");
        Book book = new Book("B006", "Return Book", "Author", "Genre", 2);

        // Simulate borrow first
        book.borrowBook(); // quantity should become 1

        librarian.receiveReturnedBook(book);

        assertEquals(2, book.getQuantity()); // Back to full
    }

    @Test
    public void testToString() {
        Librarian librarian = new Librarian("L006", "Test Librarian", "password");

        String output = librarian.toString();

        assertTrue(output.contains("Test Librarian"));
        assertTrue(output.contains("Librarian"));
    }
    
    @Test
public void testAddDuplicateBook() {
    Librarian librarian = new Librarian("L007", "Duplicate Checker", "pass");
    List<Book> books = new ArrayList<>();

    Book book1 = new Book("B007", "Book One", "Author", "Genre", 2);
    Book book2 = new Book("B007", "Duplicate Book", "Author", "Genre", 3); // Same ID

    librarian.addBook(books, book1);
    librarian.addBook(books, book2); // Should allow or prevent?

    assertEquals(2, books.size()); // ❗ Adjust if your logic prevents duplicates
}

@Test
public void testEditBookToEmptyValues() {
    Librarian librarian = new Librarian("L008", "Edit Checker", "pass");
    Book book = new Book("B008", "Original Title", "Original Author", "Original Genre", 5);

    librarian.editBook(book, "", "", "", 0);

    assertEquals("", book.getTitle());
    assertEquals("", book.getAuthor());
    assertEquals("", book.getGenre());
    assertEquals(0, book.getQuantity());
}

@Test
public void testRemoveNonexistentBook() {
    Librarian librarian = new Librarian("L009", "Remover", "pass");
    List<Book> books = new ArrayList<>();
    books.add(new Book("B010", "Exists", "Author", "Genre", 1));

    librarian.removeBook(books, "NON_EXISTENT_ID");

    assertEquals(1, books.size()); // Nothing removed
    assertEquals("B010", books.get(0).getBookId());
}

@Test
public void testIssueBookWithZeroQuantity() {
    Librarian librarian = new Librarian("L010", "Zero Issuer", "pass");
    Book book = new Book("B011", "Unavailable Book", "Author", "Genre", 0);

    boolean result = librarian.issueBook(book);

    assertFalse(result);
    assertEquals(0, book.getQuantity());
}

@Test
public void testReceiveBookBeyondTotalCopies() {
    Librarian librarian = new Librarian("L011", "OverReceiver", "pass");
    Book book = new Book("B012", "Overflow Book", "Author", "Genre", 1);

    book.borrowBook(); // now quantity = 0

    librarian.receiveReturnedBook(book); // back to 1
    librarian.receiveReturnedBook(book); // attempt to exceed totalCopies

    assertEquals(1, book.getQuantity()); // Should not exceed original totalCopies
}

}
