package dao;

import model.BorrowRecord;
import util.DBConnection;
import java.sql.*;
import java.util.*;

public class BorrowDAO {
    private Connection conn = DBConnection.getConnection();

    public boolean borrowBook(BorrowRecord record) {
        try {
            PreparedStatement checkBook = conn.prepareStatement("SELECT quantity FROM books WHERE id=?");
            checkBook.setInt(1, record.getBookId());
            ResultSet rs = checkBook.executeQuery();
            if (rs.next()) {
                int qty = rs.getInt("quantity");
                if (qty > 0) {
                    PreparedStatement ps = conn.prepareStatement(
                            "INSERT INTO borrow_records(member_id, book_id, borrow_date, return_date) VALUES (?, ?, ?, ?)");
                    ps.setInt(1, record.getMemberId());
                    ps.setInt(2, record.getBookId());
                    ps.setDate(3, record.getBorrowDate());
                    ps.setDate(4, record.getReturnDate());
                    ps.executeUpdate();

                    PreparedStatement updateQty = conn.prepareStatement("UPDATE books SET quantity = quantity - 1 WHERE id=?");
                    updateQty.setInt(1, record.getBookId());
                    updateQty.executeUpdate();

                    return true;
                } else {
                    System.out.println("Book not available for borrowing.");
                    return false;
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public void returnBook(int recordId) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT book_id FROM borrow_records WHERE id=?");
            ps.setInt(1, recordId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int bookId = rs.getInt("book_id");

                PreparedStatement del = conn.prepareStatement("DELETE FROM borrow_records WHERE id=?");
                del.setInt(1, recordId);
                del.executeUpdate();

                PreparedStatement upd = conn.prepareStatement("UPDATE books SET quantity = quantity + 1 WHERE id=?");
                upd.setInt(1, bookId);
                upd.executeUpdate();

                System.out.println("Book returned successfully.");
            } else {
                System.out.println("Borrow record not found!");
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public List<BorrowRecord> getAllBorrowedBooks() {
        List<BorrowRecord> list = new ArrayList<>();
        String sql = "SELECT * FROM borrow_records";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new BorrowRecord(rs.getInt("id"),rs.getInt("member_id"),rs.getInt("book_id"),rs.getDate("borrow_date"),rs.getDate("return_date")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
}
