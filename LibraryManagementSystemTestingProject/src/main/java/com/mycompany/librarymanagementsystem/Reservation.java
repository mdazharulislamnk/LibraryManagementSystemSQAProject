package com.mycompany.librarymanagementsystem;

import java.time.LocalDate;

public class Reservation {

    private String reservationId;
    private String userId;
    private String bookId;
    private LocalDate reservationDate;
    private boolean isFulfilled;

    public Reservation(String reservationId, String userId, String bookId, LocalDate reservationDate) {
        this.reservationId = reservationId;
        this.userId = userId;
        this.bookId = bookId;
        this.reservationDate = reservationDate;
        this.isFulfilled = false;
    }

    // Getters
    public String getReservationId() {
        return reservationId;
    }

    public String getUserId() {
        return userId;
    }

    public String getBookId() {
        return bookId;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public boolean isFulfilled() {
        return isFulfilled;
    }

    // Methods
    public void fulfillReservation() {
        this.isFulfilled = true;
    }

    @Override
    public String toString() {
        return "Reservation ID: " + reservationId +
               ", User ID: " + userId +
               ", Book ID: " + bookId +
               ", Reserved On: " + reservationDate +
               (isFulfilled ? ", Status: Fulfilled" : ", Status: Pending");
    }
}
