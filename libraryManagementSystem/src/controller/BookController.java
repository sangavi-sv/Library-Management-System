package controller;

import dao.BookDAO;
import model.Book;
import java.util.List;

public class BookController {
    private BookDAO dao = new BookDAO();

    public void addBook(Book book) { dao.addBook(book); }
    public List<Book> getAllBooks() { return dao.getAllBooks(); }
    public void updateBook(Book book) { dao.updateBook(book); }
    public void deleteBook(int id) { dao.deleteBook(id); }
}
