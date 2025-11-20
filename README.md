# 📚 Library Management System (LMS) with Full Unit Testing

![Java](https://img.shields.io/badge/Java-21-orange)
![JUnit](https://img.shields.io/badge/JUnit-5-green)
![Maven](https://img.shields.io/badge/Build-Maven-blue)
![Coverage](https://img.shields.io/badge/Test%20Coverage-90%25-brightgreen)
![Pass Rate](https://img.shields.io/badge/Pass%20Rate-100%25-success)

## 📝 Abstract
This project is a robust, console-based **Library Management System** developed in Java. Unlike standard management systems, this project was engineered with a primary focus on **Software Quality Assurance (SQA)**. It features a modular architecture with extensive **Unit Testing** using the **JUnit 5** framework to ensure reliability, maintainability, and regression prevention.

The system streamlines core library operations—including user authentication, book inventory management, and borrowing/returning transactions—while validating each module against rigorous test cases (positive, negative, and boundary conditions).

## 🚀 Features
* **Role-Based Access Control:** Distinct functionalities for **Admins**, **Librarians**, and **Students**.
* **Book Management:** Complete CRUD operations for inventory (Add, Edit, Remove, Search).
* **Transaction Processing:** Automated handling of borrowing and returning books with validation.
* **Fine Calculation:** Logic to calculate fines based on due dates and overdue duration.
* **Data Persistence:** File-based storage (`.txt`) for Users, Books, Transactions, and Reservations.
* **Reporting:** Generation of reports for overdue books, active borrowers, and transaction history.

## 🛠️ Technologies Used
* **Language:** Java 21
* **Testing Framework:** JUnit 5
* **Build Tool:** Maven
* **IDE:** NetBeans 25 / IntelliJ IDEA
* **Data Storage:** Text-based File I/O

## 🧪 Testing Strategy & Metrics
This project adheres to strict **Software Testing Principles** to ensure a bug-free release.

### Test Scope
* **Total Test Classes:** 14
* **Total Test Methods:** 100+
* **Code Coverage:** ~90%
* **Pass Rate:** 100%

### Testing Techniques Implemented
| Technique | Description |
| :--- | :--- |
| **Unit Testing** | Independent validation of every core class (e.g., `BookTest`, `UserTest`). |
| **Positive Testing** | Verifying expected behavior with valid inputs. |
| **Negative Testing** | Ensuring the system handles invalid inputs gracefully (e.g., null values, duplicates). |
| **Boundary Testing** | Testing edge cases like zero quantity, empty lists, and leap years. |
| **Exception Handling** | Verifying that the system does not crash under unexpected conditions. |

### Key Test Modules
* **`AuthenticationServiceTest`:** Validates login logic, password security, and session consistency.
* **`TransactionTest`:** Ensures borrow/return dates and fine calculations are accurate.
* **`FileManagerTest`:** Verifies data integrity during file Read/Write operations.
* **`FineCalculatorTest`:** Tests complex logic for overdue fines, including leap year handling.

## 📂 Project Structure
```bash
src/
├── main/java/com/mycompany/librarymanagementsystem/
│   ├── LibraryManagementSystem.java  # Main Entry Point [cite: 35]
│   ├── Library.java [cite: 36]
│   ├── User.java, Admin.java, Librarian.java, Student.java [cite: 37, 39, 40, 41]
│   ├── Book.java, Transaction.java, Reservation.java [cite: 38, 42, 43]
│   ├── AuthenticationService.java, FileManager.java [cite: 46, 47]
│   ├── FineCalculator.java, ReportGenerator.java [cite: 44, 45]
│   └── DateUtils.java, Utils.java [cite: 48, 49]
│
├── test/java/com/mycompany/librarymanagementsystem/
│   ├── AdminTest.java [cite: 102]
│   ├── AuthenticationServiceTest.java [cite: 102]
│   ├── BookTest.java [cite: 102]
│   ├── DateUtilsTest.java [cite: 102]
│   ├── FileManagerTest.java [cite: 102]
│   ├── FineCalculatorTest.java [cite: 102]
│   ├── IDGeneratorTest.java [cite: 103]
│   ├── LibrarianTest.java [cite: 103]
│   ├── LibraryTest.java [cite: 103]
│   ├── ReportGeneratorTest.java [cite: 103]
│   ├── ReservationTest.java [cite: 103]
│   ├── StudentTest.java [cite: 103]
│   ├── TransactionTest.java [cite: 103]
│   └── UserTest.java [cite: 103]
│
├── resources/ [cite: 52]
│   ├── users.txt [cite: 53]
│   ├── books.txt [cite: 54]
│   ├── transactions.txt [cite: 55]
│   └── reservations.txt [cite: 56]
│
├── pom.xml [cite: 57]
└── README.md [cite: 58]
