# Library Management System

A menu-driven **Library Management System** built with Java, JDBC, and MySQL. The application runs in the terminal and provides operations for managing books, library members, book issues, and returns.

**Repository:** [gayatri-tonde/libraryManagement](https://github.com/gayatri-tonde/libraryManagement)

## Overview

This project demonstrates how a Java application can connect to a relational database using JDBC and perform database operations with SQL prepared statements. Users interact with the system through a console menu.

## Features

### Book management
- Add a book with its ID, name, author, publisher, price, and quantity.
- Display all books.
- Search for a book by ID.
- Update a book's price and quantity.
- Delete a book by ID.

### Member management
- Register a member with their name, email, mobile number, and address.
- Display all members.
- Search for a member by ID.
- Update a member's email, mobile number, and address.
- Delete a member by ID.

### Issue and return management
- Record a book issue using the issue ID, book ID, member ID, and issue date.
- Calculate a planned return date 14 days after the issue date.
- Display, search, update, and delete issue records.
- Mark an issued book as returned and update its return date.
- Display available-book, issued-book, and returned-book information.

### Database access
- Uses JDBC to connect Java code to MySQL.
- Uses `PreparedStatement` for SQL operations.
- Separates model classes, database connection code, and operation classes.

## Technology Stack

- **Language:** Java
- **Database:** MySQL
- **Database connectivity:** JDBC
- **Build and dependency management:** Maven
- **Database driver:** MySQL Connector/J 8.0.33
- **Application interface:** Command-line / console

## Project Structure

```text
libraryManagement/
├── pom.xml
└── src/
    └── main/
        └── java/
            ├── connection/
            │   └── mysqlconnection.java
            ├── model/
            │   ├── Book.java
            │   ├── Issue.java
            │   └── Member.java
            ├── operation/
            │   ├── BookOperations.java
            │   ├── DisplayOperation.java
            │   ├── IssueOperations.java
            │   ├── MemberOperations.java
            │   └── ReturnBookOperation.java
            └── org/
                └── example/
                    └── Main.java
```

### Main components

| Component | Responsibility |
|---|---|
| `Main.java` | Displays the main menu and collects user input. |
| `Book.java` | Represents book details. |
| `Member.java` | Represents library member details. |
| `Issue.java` | Represents a book issue record. |
| `mysqlconnection.java` | Creates a connection to MySQL. |
| `BookOperations.java` | Implements book-related database operations. |
| `MemberOperations.java` | Implements member-related database operations. |
| `IssueOperations.java` | Implements issue-record operations. |
| `ReturnBookOperation.java` | Handles the return process. |
| `DisplayOperation.java` | Displays available, issued, and returned book information. |

## Data Model

The application works with three main types of records:

- **Book:** book ID, title/name, author, publisher, price, and quantity.
- **Member:** member ID, name, email, mobile number, and address.
- **Issue:** issue ID, book ID, member ID, issue date, return date, and status.

The issue table stores the IDs of the book and member associated with each issue. The suggested schema uses foreign keys to help preserve referential integrity.

## Learning Outcomes

This project provides practice with:

- Java classes and object-oriented programming.
- JDBC database connectivity.
- SQL `INSERT`, `SELECT`, `UPDATE`, and `DELETE` operations.
- `PreparedStatement` and result-set processing.
- Organizing a small Java application into model, connection, and operation packages.
- Designing a basic relational schema for books, members, and issue records.

## Author

**Gayatri Tonde**

- GitHub: [gayatri-tonde](https://github.com/gayatri-tonde)
- LinkedIn: [Gayatri Tonde](https://www.linkedin.com/in/gayatri-tonde/)

---

If you find this project useful for learning Java, JDBC, and MySQL, feel free to explore the repository and suggest improvements.
