package com.mycompany.librarymanagementsystem;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class AuthenticationServiceTest {

    @Test
    public void testSuccessfulLogin() {
        List<User> users = new ArrayList<>();
        users.add(new Admin("A001", "Admin User", "admin123"));

        User loggedInUser = AuthenticationService.login(users, "A001", "admin123");

        assertNotNull(loggedInUser);
        assertEquals("Admin User", loggedInUser.getName());
    }

    @Test
    public void testFailedLoginWrongPassword() {
        List<User> users = new ArrayList<>();
        users.add(new Librarian("L001", "Lib User", "libpass"));

        User loggedInUser = AuthenticationService.login(users, "L001", "wrongpass");

        assertNull(loggedInUser);
    }

    @Test
    public void testFailedLoginWrongUserId() {
        List<User> users = new ArrayList<>();
        users.add(new Student("S001", "Student User", "studentpass"));

        User loggedInUser = AuthenticationService.login(users, "WRONG_ID", "studentpass");

        assertNull(loggedInUser);
    }

    @Test
    public void testLogout() {
        List<User> users = new ArrayList<>();
        users.add(new Admin("A002", "Another Admin", "adminpass"));

        AuthenticationService.login(users, "A002", "adminpass");

        assertNotNull(AuthenticationService.getLoggedInUser());

        AuthenticationService.logout();

        assertNull(AuthenticationService.getLoggedInUser());
    }
    
    @Test
public void testLoginWithEmptyUserList() {
    List<User> users = new ArrayList<>();

    User result = AuthenticationService.login(users, "A003", "nopass");

    assertNull(result); // No users to authenticate
}

@Test
public void testMultipleUsersSamePassword() {
    List<User> users = new ArrayList<>();
    users.add(new Admin("A004", "Admin A", "commonpass"));
    users.add(new Librarian("L004", "Librarian B", "commonpass"));
    users.add(new Student("S004", "Student C", "commonpass"));

    // Correct ID + correct shared password
    User loggedIn = AuthenticationService.login(users, "L004", "commonpass");

    assertNotNull(loggedIn);
    assertEquals("Librarian B", loggedIn.getName());
}

@Test
public void testLoginWithNullUserIdAndPassword() {
    List<User> users = new ArrayList<>();
    users.add(new Student("S005", "Null Test Student", "studentpass"));

    User loggedIn = AuthenticationService.login(users, null, null);

    assertNull(loggedIn); // Should safely fail
}

@Test
public void testLoggedInUserConsistencyAfterLogin() {
    List<User> users = new ArrayList<>();
    Admin admin = new Admin("A006", "State Admin", "statepass");
    users.add(admin);

    AuthenticationService.login(users, "A006", "statepass");

    assertEquals(admin, AuthenticationService.getLoggedInUser());
}

@Test
public void testLogoutWithoutLogin() {
    // Ensure nothing breaks when logout is called without a user being logged in
    AuthenticationService.logout(); // No login before

    assertNull(AuthenticationService.getLoggedInUser()); // Should still be null
}

}
