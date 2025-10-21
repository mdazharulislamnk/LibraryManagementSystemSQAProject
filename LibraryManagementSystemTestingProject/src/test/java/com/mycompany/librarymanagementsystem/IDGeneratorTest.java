package com.mycompany.librarymanagementsystem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IDGeneratorTest {

    @Test
    public void testGenerateUserId_Admin() {
        String rolePrefix = "A";
        String result = IDGenerator.generateUserId(rolePrefix);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.startsWith("A"));
    }

    @Test
    public void testGenerateUserId_Librarian() {
        String rolePrefix = "L";
        String result = IDGenerator.generateUserId(rolePrefix);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.startsWith("L"));
    }

    @Test
    public void testGenerateUserId_Student() {
        String rolePrefix = "S";
        String result = IDGenerator.generateUserId(rolePrefix);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.startsWith("S"));
    }

    @Test
    public void testGenerateBookId() {
        String result = IDGenerator.generateBookId();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.startsWith("B"));
    }

    @Test
    public void testGenerateTransactionId() {
        String result = IDGenerator.generateTransactionId();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.startsWith("T"));
    }

    @Test
    public void testGenerateReservationId() {
        String result = IDGenerator.generateReservationId();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.startsWith("R"));
    }
    
    @Test
public void testGenerateUserId_InvalidPrefix() {
    String rolePrefix = "Z"; // Not a known role
    String result = IDGenerator.generateUserId(rolePrefix);

    assertNotNull(result);
    assertTrue(result.startsWith("Z")); // Should still honor input
    assertTrue(result.length() > 1);    // Must generate something meaningful
}

@Test
public void testGenerateUserId_LengthConsistency() {
    String id1 = IDGenerator.generateUserId("A");
    String id2 = IDGenerator.generateUserId("A");

    assertEquals(id1.length(), id2.length()); // IDs should have consistent length
}

@Test
public void testGeneratedUserIds_AreUnique() {
    String id1 = IDGenerator.generateUserId("S");
    String id2 = IDGenerator.generateUserId("S");

    assertNotEquals(id1, id2); // Should not generate duplicate IDs
}

@Test
public void testGeneratedBookIds_AreUnique() {
    String id1 = IDGenerator.generateBookId();
    String id2 = IDGenerator.generateBookId();

    assertNotEquals(id1, id2); // Book IDs should also be unique
}

@Test
public void testGenerateTransactionId_Format() {
    String result = IDGenerator.generateTransactionId();

   assertTrue(result.startsWith("T"));  // Format should be T followed by digits
}

}
