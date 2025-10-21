package com.mycompany.librarymanagementsystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    @Test
    public void testBorrowBook() {
        Student student = new Student("S001", "Student Name", "password");
        Book book = new Book("B001", "Borrowable Book", "Author", "Genre", 1);

        student.borrowBook(book);

        assertEquals(1, student.getBorrowedBooks().size());
        assertEquals("B001", student.getBorrowedBooks().get(0).getBookId());
        assertEquals(0, book.getQuantity()); // Book quantity reduced
    }

    @Test
    public void testReturnBook() {
        Student student = new Student("S002", "Student Name", "password");
        Book book = new Book("B002", "Returnable Book", "Author", "Genre", 1);

        student.borrowBook(book); // First borrow
        assertEquals(0, book.getQuantity());

        student.returnBook(book);
        assertEquals(1, book.getQuantity()); // Quantity restored
        assertEquals(0, student.getBorrowedBooks().size()); // No more borrowed
    }

    @Test
    public void testReserveBook() {
        Student student = new Student("S003", "Student Name", "password");
        Book book = new Book("B003", "Reservable Book", "Author", "Genre", 1);

        student.reserveBook(book);

        assertEquals(1, student.getReservedBooks().size());
        assertEquals("B003", student.getReservedBooks().get(0).getBookId());
    }

    @Test
    public void testViewBorrowedBooksAndReservedBooks() {
        Student student = new Student("S004", "Student Name", "password");
        Book book1 = new Book("B004", "Borrowed Book", "Author", "Genre", 1);
        Book book2 = new Book("B005", "Reserved Book", "Author", "Genre", 1);

        student.borrowBook(book1);
        student.reserveBook(book2);

        assertEquals(1, student.getBorrowedBooks().size());
        assertEquals(1, student.getReservedBooks().size());
    }

    @Test
    public void testToString() {
        Student student = new Student("S005", "Test Student", "password");

        String output = student.toString();

        assertNotNull(output);
        assertTrue(output.contains("Test Student"));
        assertTrue(output.contains("Student"));
    }


    @Test
    public void testBorrowBook_NoAvailableCopies() {
        Student student = new Student("S006", "No Copy Student", "password");
        Book book = new Book("B006", "Unavailable Book", "Author", "Genre", 0);

        student.borrowBook(book);
        assertEquals(0, student.getBorrowedBooks().size());
        assertEquals(0, book.getQuantity());
    }

    @Test
    public void testReturnBook_NotBorrowed() {
        Student student = new Student("S007", "Return Fail", "password");
        Book book = new Book("B007", "Never Borrowed", "Author", "Genre", 1);

        student.returnBook(book); // Should do nothing
        assertEquals(1, book.getQuantity());
        assertEquals(0, student.getBorrowedBooks().size());
    }

    @Test
    public void testReserveSameBookTwice_PreventDuplicate() {
        Student student = new Student("S008", "Dup Reserver", "password");
        Book book = new Book("B008", "Popular Book", "Author", "Genre", 1);

        student.reserveBook(book);
        student.reserveBook(book);

        assertEquals(1, student.getReservedBooks().size()); // Duplicate should not be added
    }

    @Test
    public void testBorrowAndReserveSameBook() {
        Student student = new Student("S009", "Dual User", "password");
        Book book = new Book("B009", "Dual Book", "Author", "Genre", 1);

        student.reserveBook(book);
        student.borrowBook(book);

        assertEquals(1, student.getBorrowedBooks().size());
        assertEquals(1, student.getReservedBooks().size()); // May or may not be removed based on design
    }

    @Test
    public void testBorrowAndReturnMultipleBooks() {
        Student student = new Student("S010", "Bulk Borrower", "password");
        Book book1 = new Book("B010", "Book One", "Author", "Genre", 1);
        Book book2 = new Book("B011", "Book Two", "Author", "Genre", 1);

        student.borrowBook(book1);
        student.borrowBook(book2);

        assertEquals(2, student.getBorrowedBooks().size());
        assertEquals(0, book1.getQuantity());
        assertEquals(0, book2.getQuantity());

        student.returnBook(book1);
        student.returnBook(book2);

        assertEquals(1, book1.getQuantity());
        assertEquals(1, book2.getQuantity());
        assertEquals(0, student.getBorrowedBooks().size());
    }
}
