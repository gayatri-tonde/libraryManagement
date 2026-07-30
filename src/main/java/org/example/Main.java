package org.example;

import connection.mysqlconnection;
import model.Book;
import operation.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import model.Member;
import model.Issue;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection con = mysqlconnection.getConnection();

        Scanner sc = new Scanner(System.in);

        BookOperations bookOp = new BookOperations();
        MemberOperations memberOp = new MemberOperations();
        IssueOperations issueOp = new IssueOperations();
        ReturnBookOperation returnOp = new ReturnBookOperation();
        DisplayOperation displayOp = new DisplayOperation();

        int choice;
        do{
            System.out.println("====== LIBRARY MANAGEMENT SYSTEM ======");
            System.out.println("1. Book Operations");
            System.out.println("2. Member Operations");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Display Operations");
            System.out.println("6. Exit");
            System.out.print("Enter your choice : ");
            choice = sc.nextInt();
            switch (choice){
                case 1:
                    int bookChoice;
                    do {

                        System.out.println("\n====== BOOK OPERATIONS ======");
                        System.out.println("1. Add Book");
                        System.out.println("2. Display Books");
                        System.out.println("3. Search Book");
                        System.out.println("4. Update Book");
                        System.out.println("5. Delete Book");
                        System.out.println("6. Back");
                        System.out.print("Enter your choice: ");

                        bookChoice = sc.nextInt();
                        sc.nextLine(); // Consume newline

                        switch (bookChoice) {

                            case 1:

                                Book addBook = new Book();

                                System.out.print("Enter Book Id: ");
                                addBook.setBookId(sc.nextInt());
                                sc.nextLine();

                                System.out.print("Enter Book Name: ");
                                addBook.setBookName(sc.nextLine());

                                System.out.print("Enter Author Name: ");
                                addBook.setAuthorName(sc.nextLine());

                                System.out.print("Enter Publisher: ");
                                addBook.setPublisher(sc.nextLine());

                                System.out.print("Enter Price: ");
                                addBook.setPrice(sc.nextDouble());

                                System.out.print("Enter Quantity: ");
                                addBook.setQuantity(sc.nextInt());
                                sc.nextLine();

                                if (bookOp.addBook(con, addBook)) {
                                    System.out.println("Book Added Successfully.");
                                } else {
                                    System.out.println("Failed to Add Book.");
                                }

                                break;

                            case 2:

                                List<Book> books = bookOp.displayBooks(con);

                                if (books.isEmpty()) {
                                    System.out.println("No Books Available.");
                                } else {

                                    System.out.println("---------------------------------------------------------------------------------------");
                                    System.out.printf("%-5s %-20s %-20s %-15s %-10s %-10s%n",
                                            "ID", "Book Name", "Author", "Publisher", "Price", "Qty");
                                    System.out.println("---------------------------------------------------------------------------------------");

                                    for (Book book : books) {
                                        System.out.printf("%-5d %-20s %-20s %-15s %-10.2f %-10d%n",
                                                book.getBookId(),
                                                book.getBookName(),
                                                book.getAuthorName(),
                                                book.getPublisher(),
                                                book.getPrice(),
                                                book.getQuantity());
                                    }
                                }

                                break;

                            case 3:

                                System.out.print("Enter Book ID: ");
                                int searchId = sc.nextInt();

                                Book searchBook = bookOp.searchBook(con, searchId);

                                if (searchBook != null) {

                                    System.out.println("\nBook Found");
                                    System.out.println("----------------------------");
                                    System.out.println("Book ID     : " + searchBook.getBookId());
                                    System.out.println("Book Name   : " + searchBook.getBookName());
                                    System.out.println("Author Name : " + searchBook.getAuthorName());
                                    System.out.println("Publisher   : " + searchBook.getPublisher());
                                    System.out.println("Price       : " + searchBook.getPrice());
                                    System.out.println("Quantity    : " + searchBook.getQuantity());

                                } else {

                                    System.out.println("Book Not Found.");

                                }

                                break;

                            case 4:

                                Book updateBook = new Book();

                                System.out.print("Enter Book ID: ");
                                updateBook.setBookId(sc.nextInt());
                                sc.nextLine();

                                System.out.print("Enter New Price: ");
                                updateBook.setPrice(sc.nextDouble());

                                System.out.print("Enter New Quantity: ");
                                updateBook.setQuantity(sc.nextInt());
                                sc.nextLine();

                                if (bookOp.updateBook(con, updateBook)) {
                                    System.out.println("Book Updated Successfully.");
                                } else {
                                    System.out.println("Book ID Not Found.");
                                }

                                break;

                            case 5:

                                System.out.print("Enter Book ID: ");
                                int deleteId = sc.nextInt();
                                sc.nextLine();

                                if (bookOp.deleteBook(con, deleteId)) {
                                    System.out.println("Book Deleted Successfully.");
                                } else {
                                    System.out.println("Book ID Not Found.");
                                }

                                break;

                            case 6:

                                System.out.println("Returning to Main Menu...");

                                break;

                            default:

                                System.out.println("Invalid Choice.");

                        }

                    } while (bookChoice != 6);
                    break;

                case 2:

                    int memberChoice;

                    do {

                        System.out.println("\n====== MEMBER OPERATIONS ======");
                        System.out.println("1. Add Member");
                        System.out.println("2. Display Members");
                        System.out.println("3. Search Member");
                        System.out.println("4. Update Member");
                        System.out.println("5. Delete Member");
                        System.out.println("6. Back");
                        System.out.print("Enter your choice: ");

                        memberChoice = sc.nextInt();
                        sc.nextLine();

                        switch (memberChoice) {

                            case 1:

                                Member member = new Member();

                                System.out.print("Enter Member ID: ");
                                member.setMemberId(sc.nextInt());
                                sc.nextLine();

                                System.out.print("Enter Member Name: ");
                                member.setMemberName(sc.nextLine());

                                System.out.print("Enter Email: ");
                                member.setEmail(sc.nextLine());

                                System.out.print("Enter Mobile: ");
                                member.setMobile(sc.nextLine());

                                System.out.print("Enter Address: ");
                                member.setAddress(sc.nextLine());

                                if (memberOp.addMember(con, member))
                                    System.out.println("Member Added Successfully.");
                                else
                                    System.out.println("Failed to Add Member.");

                                break;

                            case 2:

                                List<Member> members = memberOp.displayMembers(con);

                                if (members.isEmpty()) {
                                    System.out.println("No Members Found.");
                                } else {

                                    System.out.println("--------------------------------------------------------------------------");
                                    System.out.printf("%-8s %-20s %-25s %-15s %-20s%n",
                                            "ID", "Name", "Email", "Mobile", "Address");
                                    System.out.println("--------------------------------------------------------------------------");

                                    for (Member m : members) {

                                        System.out.printf("%-8d %-20s %-25s %-15s %-20s%n",
                                                m.getMemberId(),
                                                m.getMemberName(),
                                                m.getEmail(),
                                                m.getMobile(),
                                                m.getAddress());
                                    }
                                }

                                break;

                            case 3:

                                System.out.print("Enter Member ID: ");
                                int searchId = sc.nextInt();

                                Member searchMember = memberOp.searchMember(con, searchId);

                                if (searchMember != null) {

                                    System.out.println("\nMember Found");
                                    System.out.println("---------------------------");
                                    System.out.println("Member ID : " + searchMember.getMemberId());
                                    System.out.println("Name      : " + searchMember.getMemberName());
                                    System.out.println("Email     : " + searchMember.getEmail());
                                    System.out.println("Mobile    : " + searchMember.getMobile());
                                    System.out.println("Address   : " + searchMember.getAddress());

                                } else {

                                    System.out.println("Member Not Found.");

                                }

                                break;

                            case 4:

                                Member updateMember = new Member();

                                System.out.print("Enter Member ID: ");
                                updateMember.setMemberId(sc.nextInt());
                                sc.nextLine();

                                System.out.print("Enter New Email: ");
                                updateMember.setEmail(sc.nextLine());

                                System.out.print("Enter New Mobile: ");
                                updateMember.setMobile(sc.nextLine());

                                System.out.print("Enter New Address: ");
                                updateMember.setAddress(sc.nextLine());

                                if (memberOp.updateMember(con, updateMember))
                                    System.out.println("Member Updated Successfully.");
                                else
                                    System.out.println("Member ID Not Found.");

                                break;

                            case 5:

                                System.out.print("Enter Member ID: ");
                                int deleteId = sc.nextInt();

                                if (memberOp.deleteMember(con, deleteId))
                                    System.out.println("Member Deleted Successfully.");
                                else
                                    System.out.println("Member ID Not Found.");

                                break;

                            case 6:

                                System.out.println("Returning to Main Menu...");
                                break;

                            default:

                                System.out.println("Invalid Choice.");
                        }

                    } while (memberChoice != 6);
                    break;


                case 3:

                    int issueChoice;

                    do {

                        System.out.println("\n====== ISSUED BOOK OPERATIONS ======");
                        System.out.println("1. Add Issued Book");
                        System.out.println("2. Display Issued Books");
                        System.out.println("3. Search Issued Book");
                        System.out.println("4. Update Issued Book");
                        System.out.println("5. Delete Issued Book");
                        System.out.println("6. Back");
                        System.out.print("Enter your choice: ");

                        issueChoice = sc.nextInt();
                        sc.nextLine();

                        switch (issueChoice) {

                            case 1:

                                Issue issue = new Issue();

                                System.out.print("Issue ID: ");
                                issue.setIssueId(sc.nextInt());

                                System.out.print("Book ID: ");
                                issue.setBookId(sc.nextInt());

                                System.out.print("Member ID: ");
                                issue.setMemberId(sc.nextInt());
                                sc.nextLine();

                                System.out.print("Issue Date (yyyy-mm-dd): ");
                                Date issueDate = Date.valueOf(sc.nextLine());
                                issue.setIssueDate(issueDate);

                                Date returnDate = Date.valueOf(issueDate.toLocalDate().plusDays(14));
                                issue.setReturnDate(returnDate);

                                issue.setStatus("Issued");

                                if (issueOp.addIssue(con, issue))
                                    System.out.println("Issue Added Successfully.");
                                else
                                    System.out.println("Failed.");

                                break;

                            case 2:

                                List<Issue> issues = issueOp.displayIssues(con);

                                if (issues.isEmpty()) {
                                    System.out.println("No Records Found.");
                                } else {

                                    System.out.printf("%-8s %-8s %-10s %-15s %-15s %-12s%n",
                                            "ID", "Book", "Member", "Issue Date", "Return Date", "Status");

                                    for (Issue i : issues) {

                                        System.out.printf("%-8d %-8d %-10d %-15s %-15s %-12s%n",
                                                i.getIssueId(),
                                                i.getBookId(),
                                                i.getMemberId(),
                                                i.getIssueDate(),
                                                i.getReturnDate(),
                                                i.getStatus());
                                    }
                                }

                                break;

                            case 3:

                                System.out.print("Enter Issue ID: ");
                                int id = sc.nextInt();

                                Issue search = issueOp.searchIssue(con, id);

                                if (search != null) {

                                    System.out.println("Issue ID : " + search.getIssueId());
                                    System.out.println("Book ID : " + search.getBookId());
                                    System.out.println("Member ID : " + search.getMemberId());
                                    System.out.println("Issue Date : " + search.getIssueDate());
                                    System.out.println("Return Date : " + search.getReturnDate());
                                    System.out.println("Status : " + search.getStatus());

                                } else {

                                    System.out.println("Issue Not Found.");
                                }

                                break;

                            case 4:

                                Issue update = new Issue();

                                System.out.print("Issue ID: ");
                                update.setIssueId(sc.nextInt());
                                sc.nextLine();

                                System.out.print("Issue Date (yyyy-mm-dd): ");
                                issueDate = Date.valueOf(sc.nextLine());
                                update.setIssueDate(issueDate);

                                returnDate = Date.valueOf(issueDate.toLocalDate().plusDays(14));
                                update.setReturnDate(returnDate);


                                if (issueOp.updateIssue(con, update))
                                    System.out.println("Updated Successfully.");
                                else
                                    System.out.println("Issue Not Found.");

                                break;

                            case 5:

                                System.out.print("Issue ID: ");
                                int deleteId = sc.nextInt();

                                if (issueOp.deleteIssue(con, deleteId))
                                    System.out.println("Deleted Successfully.");
                                else
                                    System.out.println("Issue Not Found.");

                                break;

                            case 6:
                                System.out.println("Returning...");
                                break;

                            default:
                                System.out.println("Invalid Choice.");
                        }

                    } while (issueChoice != 6);
                    break;


                case 4:

                    System.out.print("Enter Issue ID : ");
                    int issueId = sc.nextInt();

                    if(returnOp.returnBook(con, issueId))
                        System.out.println("Book Returned Successfully.");
                    else
                        System.out.println("Issue ID Not Found.");

                    break;

                case 5:

                    int displayChoice;

                    do{

                        System.out.println("\n====== DISPLAY OPERATIONS ======");
                        System.out.println("1. Display Available Books");
                        System.out.println("2. Display Issued Books");
                        System.out.println("3. Display Returned Books");
                        System.out.println("4. Back");

                        System.out.print("Enter Choice : ");
                        displayChoice = sc.nextInt();

                        switch(displayChoice){

                            case 1:
                                System.out.printf("%-10s %-10s %-10s%n",
                                        "ID", "Book ID", "Quantity");
                                displayOp.displayAvailableBooks(con);
                                break;

                            case 2:
                                System.out.printf("%-10s %-10s %-12s %-15s %-12s%n",
                                        "ID", "Book Name", "Member ID", "Issue Date", "Status");
                                displayOp.displayIssuedBooks(con);
                                break;

                            case 3:
                                System.out.printf("%-8s %-8s %-15s %-15s %-12s%n",
                                        "ID", "Book", "Member", "Return Date", "Status");
                                displayOp.displayReturnedBooks(con);
                                break;

                            case 4:
                                System.out.println("Returning...");
                                break;

                            default:
                                System.out.println("Invalid Choice");
                        }

                    }while(displayChoice!=4);

                    break;

                case 6:

                    System.out.println("Thank You...");
                    break;

                default:
                    System.out.println("You entered wrong choice.");
            }
        }while(choice != 6);




        sc.close();
        con.close();
    }

}
