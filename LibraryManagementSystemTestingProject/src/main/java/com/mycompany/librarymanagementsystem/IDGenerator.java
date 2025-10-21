package com.mycompany.librarymanagementsystem;

import java.util.UUID;

public class IDGenerator {

    public static String generateUserId(String rolePrefix) {
        return rolePrefix.toUpperCase() + "-" + UUID.randomUUID().toString().substring(0, 5);
    }

    public static String generateBookId() {
        return "B-" + UUID.randomUUID().toString().substring(0, 5);
    }

    public static String generateTransactionId() {
        return "T-" + UUID.randomUUID().toString().substring(0, 6);
    }

    public static String generateReservationId() {
        return "R-" + UUID.randomUUID().toString().substring(0, 6);
    }
}
