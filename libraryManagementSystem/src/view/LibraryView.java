package view;

import controller.BookController;
import controller.MemberController;
import controller.BorrowController;
import model.Book;
import model.Member;
import model.BorrowRecord;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class LibraryView {
    private static final Scanner sc = new Scanner(System.in);
    private static final BookController bookController = new BookController();
    private static final MemberController memberController = new MemberController();
    private static final BorrowController borrowController = new BorrowController();

    public void start() {
        System.out.println("===== Welcome to Library Management System =====");
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Update Book");
            System.out.println("4. Delete Book");
            System.out.println("5. Add Member");
            System.out.println("6. View All Members");
            System.out.println("7. Update Member");
            System.out.println("8. Delete Member");
            System.out.println("9. Borrow a Book");
            System.out.println("10. Return a Book");
            System.out.println("11. View Borrow Records");
            System.out.println("12. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addBook();
                case 2 -> viewBooks();
                case 3 -> updateBook();
                case 4 -> deleteBook();
                case 5 -> addMember();
                case 6 -> viewMembers();
                case 7 -> updateMember();
                case 8 -> deleteMember();
                case 9 -> borrowBook();
                case 10 -> returnBook();
                case 11 -> viewBorrowedBooks();
                case 12 -> {
                    System.out.println("Exiting... Thank you!");
                    return;
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }

    // ===================== BOOK SECTION =====================
    private static void addBook() {
        System.out.println("\n--- Add New Book ---");
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Author: ");
        String author = sc.nextLine();
        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Category: ");
        String category = sc.nextLine();

        Book book = new Book(id, title, author, qty, category);
        bookController.addBook(book);
        System.out.println("Book added successfully!");
    }

    private static void viewBooks() {
        System.out.println("\n--- All Books ---");
        List<Book> books = bookController.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books found!");
        } else {
            for (Book b : books) {
                System.out.println(b);
            }
        }
    }

    private static void updateBook() {
        System.out.println("\n--- Update Book ---");
        System.out.print("Enter Book ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter new Title: ");
        String title = sc.nextLine();
        System.out.print("Enter new Author: ");
        String author = sc.nextLine();
        System.out.print("Enter new Quantity: ");
        int qty = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter new Category: ");
        String category = sc.nextLine();

        Book book = new Book(id, title, author, qty, category);
        bookController.updateBook(book);
        System.out.println("Book updated successfully!");
    }

    private static void deleteBook() {
        System.out.println("\n--- Delete Book ---");
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        bookController.deleteBook(id);
        System.out.println("Book deleted successfully!");
    }

    // ===================== MEMBER SECTION =====================
    private static void addMember() {
        System.out.println("\n--- Add New Member ---");
        System.out.print("Enter Member ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        System.out.print("Enter Phone: ");
        String phone = sc.nextLine();

        Member member = new Member(id, name, email, phone);
        memberController.addMember(member);
        System.out.println("Member added successfully!");
    }

    private static void viewMembers() {
        System.out.println("\n--- All Members ---");
        List<Member> members = memberController.getAllMembers();
        if (members.isEmpty()) {
            System.out.println("No members found!");
        } else {
            for (Member m : members) {
                System.out.println(m);
            }
        }
    }

    private static void updateMember() {
        System.out.println("\n--- Update Member ---");
        System.out.print("Enter Member ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter new Name: ");
        String name = sc.nextLine();
        System.out.print("Enter new Email: ");
        String email = sc.nextLine();
        System.out.print("Enter new Phone: ");
        String phone = sc.nextLine();

        Member member = new Member(id, name, email, phone);
        memberController.updateMember(member);
        System.out.println("Member updated successfully!");
    }

    private static void deleteMember() {
        System.out.println("\n--- Delete Member ---");
        System.out.print("Enter Member ID: ");
        int id = sc.nextInt();
        memberController.deleteMember(id);
        System.out.println("Member deleted successfully!");
    }

    // ===================== BORROW SECTION =====================
    private static void borrowBook() {
        System.out.println("\n--- Borrow a Book ---");
        System.out.print("Enter Member ID: ");
        int memberId = sc.nextInt();
        System.out.print("Enter Book ID: ");
        int bookId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Borrow Date (YYYY-MM-DD): ");
        String borrowDate = sc.nextLine();
        System.out.print("Enter Return Date (YYYY-MM-DD): ");
        String returnDate = sc.nextLine();

        BorrowRecord record = new BorrowRecord(memberId, bookId, Date.valueOf(borrowDate), Date.valueOf(returnDate));
        borrowController.borrowBook(record);
        System.out.println("Book borrowed successfully!");
    }

    private static void returnBook() {
        System.out.println("\n--- Return a Book ---");
        System.out.print("Enter Borrow Record ID to return: ");
        int recordId = sc.nextInt();
        borrowController.returnBook(recordId);
    }

    private static void viewBorrowedBooks() {
        System.out.println("\n--- All Borrow Records ---");
        List<BorrowRecord> records = borrowController.getAllBorrowedBooks();
        if (records.isEmpty()) {
            System.out.println("No borrow records found!");
        } else {
            for (BorrowRecord r : records) {
                System.out.println(r);
            }
        }
    }
}
