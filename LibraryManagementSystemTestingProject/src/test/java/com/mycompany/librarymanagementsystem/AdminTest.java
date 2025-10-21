package com.mycompany.librarymanagementsystem;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class AdminTest {

    @Test
    public void testAddLibrarian() {
        List<User> users = new ArrayList<>();
        Admin admin = new Admin("A001", "Admin User", "adminpass");

        Librarian librarian = new Librarian("L001", "Librarian User", "libpass");
        admin.addLibrarian(users, librarian);

        assertEquals(1, users.size());
        assertTrue(users.get(0) instanceof Librarian);
        assertEquals("L001", users.get(0).getUserId());
    }

    @Test
    public void testRemoveLibrarian() {
        List<User> users = new ArrayList<>();
        Admin admin = new Admin("A002", "Another Admin", "adminpass");

        Librarian librarian = new Librarian("L002", "Remove Librarian", "libpass");
        users.add(librarian);

        admin.removeLibrarian(users, "L002");

        assertEquals(0, users.size());
    }

    @Test
    public void testViewLibrarians() {
        List<User> users = new ArrayList<>();
        Admin admin = new Admin("A003", "Viewer Admin", "adminpass");

        users.add(new Librarian("L003", "Lib1", "pass1"));
        users.add(new Student("S001", "Student1", "pass2")); // Should not be printed

        assertDoesNotThrow(() -> admin.viewLibrarians(users));
    }

    @Test
    public void testViewStudents() {
        List<User> users = new ArrayList<>();
        Admin admin = new Admin("A004", "Viewer Admin", "adminpass");

        users.add(new Student("S002", "Student2", "pass2"));
        users.add(new Librarian("L004", "Lib2", "pass3")); // Should not be printed

        assertDoesNotThrow(() -> admin.viewStudents(users));
    }

    @Test
    public void testViewAllBooks() {
        List<Book> books = new ArrayList<>();
        Admin admin = new Admin("A005", "Book Viewer Admin", "adminpass");

        books.add(new Book("B001", "Book One", "Author One", "Genre", 3));

        assertDoesNotThrow(() -> admin.viewAllBooks(books));
    }

    @Test
    public void testViewReports() {
        Admin admin = new Admin("A006", "Report Admin", "adminpass");
        assertDoesNotThrow(admin::viewReports);
    }

    @Test
    public void testToString() {
        Admin admin = new Admin("A007", "Admin User", "adminpass");
        String output = admin.toString();
        assertTrue(output.contains("Admin User"));
        assertTrue(output.contains("A007"));
    }
    
    @Test
public void testAddMultipleLibrarians() {
    List<User> users = new ArrayList<>();
    Admin admin = new Admin("A008", "Bulk Admin", "adminpass");

    Librarian lib1 = new Librarian("L005", "Librarian One", "pass1");
    Librarian lib2 = new Librarian("L006", "Librarian Two", "pass2");
    Librarian libDuplicate = new Librarian("L005", "Duplicate Librarian", "pass3"); // same ID as lib1

    admin.addLibrarian(users, lib1);
    admin.addLibrarian(users, lib2);
    admin.addLibrarian(users, libDuplicate); // check if it handles duplicates

    assertEquals(3, users.size()); // Adjust if duplicates are not allowed
    assertEquals("L005", users.get(0).getUserId());
    assertEquals("L006", users.get(1).getUserId());
}

@Test
public void testRemoveNonExistentLibrarian() {
    List<User> users = new ArrayList<>();
    Admin admin = new Admin("A009", "Remove Tester", "adminpass");

    users.add(new Librarian("L007", "Existing Librarian", "pass"));

    admin.removeLibrarian(users, "L999"); // Non-existent ID

    assertEquals(1, users.size()); // List should remain unchanged
    assertEquals("L007", users.get(0).getUserId());
}

@Test
public void testViewLibrariansWithOnlyStudents() {
    List<User> users = new ArrayList<>();
    Admin admin = new Admin("A010", "Student Checker", "adminpass");

    users.add(new Student("S003", "Stud1", "pass"));
    users.add(new Student("S004", "Stud2", "pass"));

    assertDoesNotThrow(() -> admin.viewLibrarians(users)); // Should run without error
}

@Test
public void testViewStudentsWithNoStudents() {
    List<User> users = new ArrayList<>();
    Admin admin = new Admin("A011", "No Student Admin", "adminpass");

    users.add(new Librarian("L008", "LibOnly", "pass"));

    assertDoesNotThrow(() -> admin.viewStudents(users)); // Should run without error
}

@Test
public void testAddLibrarianWithNullFields() {
    List<User> users = new ArrayList<>();
    Admin admin = new Admin("A012", "Null Admin", "adminpass");

    Librarian nullLibrarian = new Librarian(null, null, null);

    admin.addLibrarian(users, nullLibrarian);

    assertEquals(1, users.size());
    assertNull(users.get(0).getUserId());
    assertNull(users.get(0).getName());
}

}
