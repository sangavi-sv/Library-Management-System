package dao;

import model.Book;
import util.DBConnection;
import java.sql.*;
import java.util.*;

public class BookDAO {
    private Connection conn = DBConnection.getConnection();

    public void addBook(Book book) {
        String sql = "INSERT INTO books(title, author, quantity, category) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getQuantity());
            ps.setString(4, book.getCategory());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public List<Book> getAllBooks() {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Book(rs.getInt("id"), rs.getString("title"),
                        rs.getString("author"), rs.getInt("quantity"),
                        rs.getString("category")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public void updateBook(Book book) {
        String sql = "UPDATE books SET title=?, author=?, quantity=?, category=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getQuantity());
            ps.setString(4, book.getCategory());
            ps.setInt(5, book.getId());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void deleteBook(int id) {
        String sql = "DELETE FROM books WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
