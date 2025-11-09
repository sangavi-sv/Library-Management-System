package model;

import java.sql.Date;

public class BorrowRecord {
    private int id;
    private int memberId;
    private int bookId;
    private Date borrowDate;
    private Date returnDate;

    public BorrowRecord() {}

    public BorrowRecord(int memberId, int bookId, Date borrowDate, Date returnDate) {
        this.memberId = memberId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public BorrowRecord(int id, int memberId, int bookId, Date borrowDate, Date returnDate) {
        this.id = id;
        this.memberId = memberId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getMemberId() { return memberId; }
    public void setMemberId(int memberId) { this.memberId = memberId; }

    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }

    public Date getBorrowDate() { return borrowDate; }
    public void setBorrowDate(Date borrowDate) { this.borrowDate = borrowDate; }

    public Date getReturnDate() { return returnDate; }
    public void setReturnDate(Date returnDate) { this.returnDate = returnDate; }

    @Override
    public String toString() {
        return id + " | Member: " + memberId + " | Book: " + bookId +
                " | Borrow: " + borrowDate + " | Return: " + returnDate;
    }
}
