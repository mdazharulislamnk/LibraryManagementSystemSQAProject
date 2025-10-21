package com.mycompany.librarymanagementsystem;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    @Test
    public void testAddAndRemoveUser() {
        Library library = new Library();
        User user = new Student("S001", "Student Name", "password");

        library.addUser(user);
        assertEquals(1, library.getUsers().size());
        assertEquals("S001", library.getUsers().get(0).getUserId());

        library.removeUser("S001");
        assertEquals(0, library.getUsers().size());
    }

    @Test
    public void testSearchUserById() {
        Library library = new Library();
        User user = new Admin("A001", "Admin Name", "password");
        library.addUser(user);

        User foundUser = library.searchUserById("A001");
        assertNotNull(foundUser);
        assertEquals("Admin Name", foundUser.getName());

        User notFound = library.searchUserById("X999");
        assertNull(notFound);
    }

    @Test
    public void testAddAndRemoveBook() {
        Library library = new Library();
        Book book = new Book("B001", "Book Title", "Author Name", "Genre", 5);

        library.addBook(book);
        assertEquals(1, library.getBooks().size());
        assertEquals("B001", library.getBooks().get(0).getBookId());

        library.removeBook("B001");
        assertEquals(0, library.getBooks().size());
    }

    @Test
    public void testSearchBookById() {
        Library library = new Library();
        Book book = new Book("B002", "Test Book", "Test Author", "Test Genre", 3);
        library.addBook(book);

        Book foundBook = library.searchBookById("B002");
        assertNotNull(foundBook);
        assertEquals("Test Book", foundBook.getTitle());

        Book notFoundBook = library.searchBookById("X999");
        assertNull(notFoundBook);
    }

    @Test
    public void testGetUsersAndGetBooks() {
        Library library = new Library();
        assertNotNull(library.getUsers());
        assertTrue(library.getUsers().isEmpty());

        assertNotNull(library.getBooks());
        assertTrue(library.getBooks().isEmpty());
    }

    @Test
    public void testSeedDummyData() {
        Library library = new Library();
        library.seedDummyData();

        List<User> users = library.getUsers();
        List<Book> books = library.getBooks();

        assertTrue(users.size() > 0.0);
        assertTrue(books.size() > 0.0);
    }
    
    @Test
public void testAddDuplicateUserId() {
    Library library = new Library();
    User user1 = new Student("S002", "Student One", "pass1");
    User user2 = new Student("S002", "Duplicate Student", "pass2");

    library.addUser(user1);
    library.addUser(user2); // Should add or overwrite?

    assertEquals(2, library.getUsers().size()); // ❗Update to 1 if duplicates are prevented
}

@Test
public void testAddDuplicateBookId() {
    Library library = new Library();
    Book book1 = new Book("B003", "Original Book", "Author", "Genre", 2);
    Book book2 = new Book("B003", "Duplicate Book", "Another Author", "Genre", 1);

    library.addBook(book1);
    library.addBook(book2); // Should both exist?

    assertEquals(2, library.getBooks().size()); // ❗Update if duplicates are rejected
}

@Test
public void testSearchUserById_CaseInsensitive() {
    Library library = new Library();
    library.addUser(new Admin("A004", "Case Test Admin", "adminpass"));

    User found = library.searchUserById("a004"); // Lowercase input

    assertNull(found); // ❗Update to assertNotNull if logic is case-insensitive
}

@Test
public void testRemoveUser_NonExistentId() {
    Library library = new Library();
    library.addUser(new Librarian("L001", "Librarian", "pass"));

    library.removeUser("L999"); // ID not in list

    assertEquals(1, library.getUsers().size()); // Nothing should be removed
}

@Test
public void testSeedDummyDataIdPatterns() {
    Library library = new Library();
    library.seedDummyData();

    for (User user : library.getUsers()) {
        assertTrue(user.getUserId().matches("[ALS]\\d+")); // IDs start with A, L, or S
    }

    for (Book book : library.getBooks()) {
        assertTrue(book.getBookId().startsWith("B"));
    }
}

}
