package com.mycompany.librarymanagementsystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    @Test
    public void testGetters() {
        Book book = new Book("B001", "Book Title", "Author Name", "Fiction", 5);

        assertEquals("B001", book.getBookId());
        assertEquals("Book Title", book.getTitle());
        assertEquals("Author Name", book.getAuthor());
        assertEquals("Fiction", book.getGenre());
        assertEquals(5, book.getQuantity());
        assertEquals(5, book.getTotalCopies());
    }

    @Test
    public void testSetters() {
        Book book = new Book("B002", "Old Title", "Old Author", "Old Genre", 3);

        book.setTitle("New Title");
        book.setAuthor("New Author");
        book.setGenre("New Genre");
        book.setQuantity(7);

        assertEquals("New Title", book.getTitle());
        assertEquals("New Author", book.getAuthor());
        assertEquals("New Genre", book.getGenre());
        assertEquals(7, book.getQuantity());
    }

    @Test
    public void testBorrowBook() {
        Book book = new Book("B003", "Borrowable Book", "Author", "Genre", 2);

        boolean success = book.borrowBook();
        assertTrue(success);
        assertEquals(1, book.getQuantity());

        boolean successAgain = book.borrowBook();
        assertTrue(successAgain);
        assertEquals(0, book.getQuantity());

        boolean failBorrow = book.borrowBook();
        assertFalse(failBorrow);
        assertEquals(0, book.getQuantity());
    }

    @Test
    public void testReturnBook() {
        Book book = new Book("B004", "Returnable Book", "Author", "Genre", 2);

        book.borrowBook();
        book.borrowBook(); // Now quantity = 0

        book.returnBook(); // should increase quantity
        assertEquals(1, book.getQuantity());

        book.returnBook();
        assertEquals(2, book.getQuantity()); // back to totalCopies

        book.returnBook(); // should not go above totalCopies
        assertEquals(2, book.getQuantity());
    }

@Test
public void testIncreaseAndDecreaseQuantity() {
    Book book = new Book("B005", "Adjustable Book", "Author", "Genre", 2);

    book.increaseQuantity(3);
    assertEquals(5, book.getQuantity());     // quantity = 2 + 3 = 5
    assertEquals(5, book.getTotalCopies());  // totalCopies = 2 + 3 = 5

    book.decreaseQuantity(2);
    assertEquals(3, book.getQuantity());     // quantity = 5 - 2 = 3
    assertEquals(3, book.getTotalCopies());  // totalCopies = 5 - 2 = 3
}


    @Test
    public void testToString() {
        Book book = new Book("B006", "String Book", "String Author", "String Genre", 1);

        String bookString = book.toString();
        assertTrue(bookString.contains("B006"));
        assertTrue(bookString.contains("String Book"));
        assertTrue(bookString.contains("String Author"));
    }
    
    @Test
public void testBorrowBookUntilFailure() {
    Book book = new Book("B007", "Limited Book", "Edge Author", "Drama", 1);

    assertTrue(book.borrowBook());  // quantity becomes 0
    assertFalse(book.borrowBook()); // cannot borrow anymore
    assertEquals(0, book.getQuantity());
}

@Test
public void testReturnBookWhenAlreadyAtMax() {
    Book book = new Book("B008", "Overflow Book", "Max Author", "Genre", 2);

    book.returnBook(); // quantity already 2, should stay 2
    assertEquals(2, book.getQuantity());
    assertEquals(2, book.getTotalCopies());
}

@Test
public void testNegativeIncreaseAndDecreaseQuantity() {
    Book book = new Book("B009", "Negative Book", "Author", "Genre", 3);

    book.increaseQuantity(-2); // should ideally do nothing or be ignored
    assertEquals(3, book.getQuantity()); // unchanged if negative is handled properly
    assertEquals(3, book.getTotalCopies());

    book.decreaseQuantity(-1); // should do nothing or be ignored
    assertEquals(3, book.getQuantity());
    assertEquals(3, book.getTotalCopies());
}

@Test
public void testDecreaseQuantityBelowZero() {
    Book book = new Book("B010", "Underflow Book", "Author", "Genre", 1);

    book.decreaseQuantity(2); // more than available — ignored
    assertEquals(1, book.getQuantity()); // stays 1 if decrease is blocked
    assertEquals(1, book.getTotalCopies());
}


@Test
public void testBookWithZeroInitialQuantity() {
    Book book = new Book("B011", "Zero Book", "Author", "Genre", 0);

    assertFalse(book.borrowBook()); // Can't borrow from 0
    book.returnBook(); // should return to 1 only if allowed — depends on design

    assertTrue(book.getQuantity() <= book.getTotalCopies());
    assertTrue(book.getTotalCopies() >= 0);
}

}
