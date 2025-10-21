package com.mycompany.librarymanagementsystem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    // A simple subclass to instantiate User because User is abstract
    public static class UserImpl extends User {
        public UserImpl(String userId, String name, String password, String role) {
            super(userId, name, password, role);
        }

        @Override
        public void showMenu() {
            // Dummy menu for testing
            System.out.println("UserImpl Menu");
        }
    }

    @Test
    public void testGetters() {
        User user = new UserImpl("U001", "Test User", "pass123", "Student");

        assertEquals("U001", user.getUserId());
        assertEquals("Test User", user.getName());
        assertEquals("pass123", user.getPassword());
        assertEquals("Student", user.getRole());
    }

    @Test
    public void testSetters() {
        User user = new UserImpl("U002", "Old Name", "oldpass", "Student");

        user.setName("New Name");
        user.setPassword("newpass");
        user.setRole("Admin");

        assertEquals("New Name", user.getName());
        assertEquals("newpass", user.getPassword());
        assertEquals("Admin", user.getRole());
    }

    @Test
    public void testViewProfile() {
        User user = new UserImpl("U003", "Profile User", "password", "Librarian");

        assertDoesNotThrow(() -> user.viewProfile());
    }

    @Test
    public void testShowMenu() {
        User user = new UserImpl("U004", "Menu User", "password", "Student");

        assertDoesNotThrow(() -> user.showMenu());
    }
    
    @Test
public void testUserIdImmutability() {
    User user = new UserImpl("U005", "Immutable ID User", "password", "Student");

    assertEquals("U005", user.getUserId());
    // Attempting to change ID should not be possible if there's no setter
    // Uncommenting below line should cause compile error if enforced correctly:
    // user.setUserId("U999");
}

@Test
public void testEmptyStringsInConstructor() {
    User user = new UserImpl("", "", "", "");

    assertEquals("", user.getUserId());
    assertEquals("", user.getName());
    assertEquals("", user.getPassword());
    assertEquals("", user.getRole());
}

@Test
public void testNullValuesHandling() {
    User user = new UserImpl(null, null, null, null);

    assertNull(user.getUserId());
    assertNull(user.getName());
    assertNull(user.getPassword());
    assertNull(user.getRole());
}

@Test
public void testSettersWithEmptyValues() {
    User user = new UserImpl("U006", "Name", "pass", "Student");

    user.setName("");
    user.setPassword("");
    user.setRole("");

    assertEquals("", user.getName());
    assertEquals("", user.getPassword());
    assertEquals("", user.getRole());
}

@Test
public void testMultipleUsersWithSameId() {
    User user1 = new UserImpl("U007", "User One", "pass1", "Student");
    User user2 = new UserImpl("U007", "User Two", "pass2", "Librarian");

    // If equals() isn't overridden, they are not equal by object
    assertNotEquals(user1, user2);
    assertEquals(user1.getUserId(), user2.getUserId());
}

}
