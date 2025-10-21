package com.mycompany.librarymanagementsystem;

import java.util.*;
import java.time.LocalDate;

public class LibraryManagementSystem {

    private static final String USERS_FILE = "users.txt";
    private static final String BOOKS_FILE = "books.txt";
    private static final String TRANSACTIONS_FILE = "transactions.txt";
    private static final String RESERVATIONS_FILE = "reservations.txt";

    private static Library library = new Library();
    private static List<Transaction> transactions = new ArrayList<>();
    private static List<Reservation> reservations = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        loadData();

        System.out.println("===== Welcome to Library Management System =====");

        while (true) {
            System.out.println("\n1. Login");
            System.out.println("2. Exit");
            int choice = Utils.getIntegerInput("Enter your choice: ");

            switch (choice) {
                case 1 -> loginAndRun();
                case 2 -> {
                    saveData();
                    System.out.println("✅ Thank you for using the Library Management System!");
                    System.exit(0);
                }
                default -> System.out.println("❌ Invalid choice. Try again!");
            }
        }
    }

    private static void loginAndRun() {
        String userId = Utils.getNonEmptyInput("Enter User ID: ");
        String password = Utils.getNonEmptyInput("Enter Password: ");

        User user = AuthenticationService.login(library.getUsers(), userId, password);

        if (user != null) {
            if (user instanceof Admin admin) {
                adminMenu(admin);
            } else if (user instanceof Librarian librarian) {
                librarianMenu(librarian);
            } else if (user instanceof Student student) {
                studentMenu(student);
            }
        }
    }

    private static void adminMenu(Admin admin) {
        while (true) {
            admin.showMenu();
            int choice = Utils.getIntegerInput("Enter your choice: ");

            switch (choice) {
                case 1 -> {
                    String id = IDGenerator.generateUserId("LIB");
                    String name = Utils.getNonEmptyInput("Enter Librarian Name: ");
                    String password = Utils.getNonEmptyInput("Enter Librarian Password: ");
                    admin.addLibrarian(library.getUsers(), new Librarian(id, name, password));
                }
                case 2 -> {
                    String id = Utils.getNonEmptyInput("Enter Librarian ID to remove: ");
                    admin.removeLibrarian(library.getUsers(), id);
                }
                case 3 -> admin.viewLibrarians(library.getUsers());
                case 4 -> admin.viewStudents(library.getUsers());
                case 5 -> admin.viewAllBooks(library.getBooks());
                case 6 -> ReportGenerator.generateBorrowedBooksReport(transactions);
                case 7 -> {
                    AuthenticationService.logout();
                    return;
                }
                default -> System.out.println("❌ Invalid choice!");
            }
            Utils.pause();
        }
    }

    private static void librarianMenu(Librarian librarian) {
        while (true) {
            librarian.showMenu();
            int choice = Utils.getIntegerInput("Enter your choice: ");

            switch (choice) {
                case 1 -> {
                    String id = IDGenerator.generateBookId();
                    String title = Utils.getNonEmptyInput("Enter Book Title: ");
                    String author = Utils.getNonEmptyInput("Enter Author Name: ");
                    String genre = Utils.getNonEmptyInput("Enter Genre: ");
                    int quantity = Utils.getIntegerInput("Enter Quantity: ");
                    librarian.addBook(library.getBooks(), new Book(id, title, author, genre, quantity));
                }
                case 2 -> {
                    String id = Utils.getNonEmptyInput("Enter Book ID to edit: ");
                    Book book = library.searchBookById(id);
                    if (book != null) {
                        String newTitle = Utils.getNonEmptyInput("Enter new Title: ");
                        String newAuthor = Utils.getNonEmptyInput("Enter new Author: ");
                        String newGenre = Utils.getNonEmptyInput("Enter new Genre: ");
                        int newQuantity = Utils.getIntegerInput("Enter new Quantity: ");
                        librarian.editBook(book, newTitle, newAuthor, newGenre, newQuantity);
                    } else {
                        System.out.println("⚠️ Book not found!");
                    }
                }
                case 3 -> {
                    String id = Utils.getNonEmptyInput("Enter Book ID to remove: ");
                    librarian.removeBook(library.getBooks(), id);
                }
                case 4 -> librarian.viewAllBooks(library.getBooks());
                case 5 -> {
                    String id = Utils.getNonEmptyInput("Enter Book ID to issue: ");
                    Book book = library.searchBookById(id);
                    if (book != null) {
                        librarian.issueBook(book);
                        String userId = Utils.getNonEmptyInput("Enter Student ID: ");
                        Transaction t = new Transaction(IDGenerator.generateTransactionId(), userId, book.getBookId(), DateUtils.getToday(), DateUtils.calculateDueDate(14));
                        transactions.add(t);
                    } else {
                        System.out.println("⚠️ Book not found!");
                    }
                }
                case 6 -> {
                    String id = Utils.getNonEmptyInput("Enter Book ID to receive: ");
                    Book book = library.searchBookById(id);
                    if (book != null) {
                        librarian.receiveReturnedBook(book);
                        for (Transaction t : transactions) {
                            if (t.getBookId().equals(book.getBookId()) && !t.isReturned()) {
                                t.markAsReturned(DateUtils.getToday());
                                break;
                            }
                        }
                    } else {
                        System.out.println("⚠️ Book not found!");
                    }
                }
                case 7 -> ReportGenerator.generateBorrowedBooksReport(transactions);
                case 8 -> {
                    AuthenticationService.logout();
                    return;
                }
                default -> System.out.println("❌ Invalid choice!");
            }
            Utils.pause();
        }
    }

    private static void studentMenu(Student student) {
        while (true) {
            student.showMenu();
            int choice = Utils.getIntegerInput("Enter your choice: ");

            switch (choice) {
                case 1 -> library.viewAllBooks();
                case 2 -> {
                    String id = Utils.getNonEmptyInput("Enter Book ID to borrow: ");
                    Book book = library.searchBookById(id);
                    if (book != null) {
                        student.borrowBook(book);
                        transactions.add(new Transaction(IDGenerator.generateTransactionId(), student.getUserId(), book.getBookId(), DateUtils.getToday(), DateUtils.calculateDueDate(14)));
                    } else {
                        System.out.println("⚠️ Book not found!");
                    }
                }
                case 3 -> {
                    String id = Utils.getNonEmptyInput("Enter Book ID to return: ");
                    Book book = library.searchBookById(id);
                    if (book != null) {
                        student.returnBook(book);
                        for (Transaction t : transactions) {
                            if (t.getBookId().equals(book.getBookId()) && !t.isReturned()) {
                                t.markAsReturned(DateUtils.getToday());
                                FineCalculator.displayFine(t.getDueDate(), t.getReturnDate());
                                break;
                            }
                        }
                    } else {
                        System.out.println("⚠️ Book not found!");
                    }
                }
                case 4 -> {
                    String id = Utils.getNonEmptyInput("Enter Book ID to reserve: ");
                    Book book = library.searchBookById(id);
                    if (book != null) {
                        student.reserveBook(book);
                        reservations.add(new Reservation(IDGenerator.generateReservationId(), student.getUserId(), book.getBookId(), DateUtils.getToday()));
                    } else {
                        System.out.println("⚠️ Book not found!");
                    }
                }
                case 5 -> student.viewBorrowedBooks();
                case 6 -> student.viewReservedBooks();
                case 7 -> {
                    AuthenticationService.logout();
                    return;
                }
                default -> System.out.println("❌ Invalid choice!");
            }
            Utils.pause();
        }
    }

    private static void loadData() {
        library.getUsers().addAll(FileManager.loadUsers(USERS_FILE));
        library.getBooks().addAll(FileManager.loadBooks(BOOKS_FILE));
        transactions.addAll(FileManager.loadTransactions(TRANSACTIONS_FILE));
        reservations.addAll(FileManager.loadReservations(RESERVATIONS_FILE));
    }

    private static void saveData() {
        FileManager.saveUsers(library.getUsers(), USERS_FILE);
        FileManager.saveBooks(library.getBooks(), BOOKS_FILE);
        FileManager.saveTransactions(transactions, TRANSACTIONS_FILE);
        FileManager.saveReservations(reservations, RESERVATIONS_FILE);
    }
}
