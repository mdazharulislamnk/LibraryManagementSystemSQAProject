package com.mycompany.librarymanagementsystem;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationTest {

    @Test
    public void testGetters() {
        Reservation reservation = new Reservation("R001", "S001", "B001", LocalDate.of(2024, 5, 1));

        assertEquals("R001", reservation.getReservationId());
        assertEquals("S001", reservation.getUserId());
        assertEquals("B001", reservation.getBookId());
        assertEquals(LocalDate.of(2024, 5, 1), reservation.getReservationDate());
        assertFalse(reservation.isFulfilled());
    }

    @Test
    public void testFulfillReservation() {
        Reservation reservation = new Reservation("R002", "S002", "B002", LocalDate.now());

        assertFalse(reservation.isFulfilled());

        reservation.fulfillReservation();

        assertTrue(reservation.isFulfilled());
    }

    @Test
    public void testToString() {
        Reservation reservation = new Reservation("R003", "S003", "B003", LocalDate.now());

        String output = reservation.toString();

        assertNotNull(output);
        assertTrue(output.contains("R003"));
        assertTrue(output.contains("S003"));
        assertTrue(output.contains("B003"));
    }
    
    @Test
public void testReservationDateInFuture() {
    LocalDate futureDate = LocalDate.now().plusDays(5);
    Reservation reservation = new Reservation("R004", "S004", "B004", futureDate);

    assertEquals(futureDate, reservation.getReservationDate());
    assertFalse(reservation.isFulfilled());
}

@Test
public void testReservationDateInPast() {
    LocalDate pastDate = LocalDate.now().minusDays(10);
    Reservation reservation = new Reservation("R005", "S005", "B005", pastDate);

    assertEquals(pastDate, reservation.getReservationDate());
    assertFalse(reservation.isFulfilled());
}

@Test
public void testMultipleFulfillCalls() {
    Reservation reservation = new Reservation("R006", "S006", "B006", LocalDate.now());

    reservation.fulfillReservation();
    assertTrue(reservation.isFulfilled());

    reservation.fulfillReservation(); // Call again — should remain true
    assertTrue(reservation.isFulfilled());
}

@Test
public void testFulfilledStatusDoesNotAffectDate() {
    LocalDate date = LocalDate.now();
    Reservation reservation = new Reservation("R007", "S007", "B007", date);

    reservation.fulfillReservation();
    assertEquals(date, reservation.getReservationDate());
}

@Test
public void testToStringAfterFulfilled() {
    Reservation reservation = new Reservation("R008", "S008", "B008", LocalDate.now());
    reservation.fulfillReservation();

    String output = reservation.toString();
    assertTrue(output.toLowerCase().contains("fulfilled")); // or similar keyword
}

}
