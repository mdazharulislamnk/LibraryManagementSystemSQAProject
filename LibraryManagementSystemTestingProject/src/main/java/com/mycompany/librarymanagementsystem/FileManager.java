package com.mycompany.librarymanagementsystem;

import java.io.*;
import java.util.*;
import java.time.LocalDate;

public class FileManager {

    // Save Users
    public static void saveUsers(List<User> users, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (User user : users) {
                writer.write(user.getUserId() + "," + user.getName() + "," + user.getPassword() + "," + user.getRole());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("❌ Error saving users: " + e.getMessage());
        }
    }

    // Load Users
    public static List<User> loadUsers(String filename) {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String id = parts[0];
                    String name = parts[1];
                    String password = parts[2];
                    String role = parts[3];
                    switch (role.toLowerCase()) {
                        case "admin" -> users.add(new Admin(id, name, password));
                        case "librarian" -> users.add(new Librarian(id, name, password));
                        case "student" -> users.add(new Student(id, name, password));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Error loading users: " + e.getMessage());
        }
        return users;
    }

    // Save Books
    public static void saveBooks(List<Book> books, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Book book : books) {
                writer.write(book.getBookId() + "," + book.getTitle() + "," + book.getAuthor() + "," +
                             book.getGenre() + "," + book.getQuantity());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("❌ Error saving books: " + e.getMessage());
        }
    }

    // Load Books
    public static List<Book> loadBooks(String filename) {
        List<Book> books = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    books.add(new Book(parts[0], parts[1], parts[2], parts[3], Integer.parseInt(parts[4])));
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Error loading books: " + e.getMessage());
        }
        return books;
    }

    // Save Transactions
    public static void saveTransactions(List<Transaction> transactions, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Transaction t : transactions) {
                writer.write(t.getTransactionId() + "," + t.getUserId() + "," + t.getBookId() + "," +
                             t.getIssueDate() + "," + t.getDueDate() + "," +
                             (t.isReturned() ? t.getReturnDate() : "null"));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("❌ Error saving transactions: " + e.getMessage());
        }
    }

    // Load Transactions
    public static List<Transaction> loadTransactions(String filename) {
        List<Transaction> transactions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    Transaction t = new Transaction(
                        parts[0], parts[1], parts[2],
                        LocalDate.parse(parts[3]),
                        LocalDate.parse(parts[4])
                    );
                    if (!parts[5].equals("null")) {
                        t.markAsReturned(LocalDate.parse(parts[5]));
                    }
                    transactions.add(t);
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Error loading transactions: " + e.getMessage());
        }
        return transactions;
    }

    // Save Reservations
    public static void saveReservations(List<Reservation> reservations, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Reservation r : reservations) {
                writer.write(r.getReservationId() + "," + r.getUserId() + "," + r.getBookId() + "," +
                             r.getReservationDate() + "," + r.isFulfilled());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("❌ Error saving reservations: " + e.getMessage());
        }
    }

    // Load Reservations
    public static List<Reservation> loadReservations(String filename) {
        List<Reservation> reservations = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    Reservation r = new Reservation(
                        parts[0], parts[1], parts[2],
                        LocalDate.parse(parts[3])
                    );
                    if (Boolean.parseBoolean(parts[4])) {
                        r.fulfillReservation();
                    }
                    reservations.add(r);
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Error loading reservations: " + e.getMessage());
        }
        return reservations;
    }
}
