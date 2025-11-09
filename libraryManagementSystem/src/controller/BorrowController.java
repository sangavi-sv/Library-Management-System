package controller;

import dao.BorrowDAO;
import model.BorrowRecord;
import java.util.List;

public class BorrowController {
    private BorrowDAO dao = new BorrowDAO();

    public boolean borrowBook(BorrowRecord record) {
        return dao.borrowBook(record);
    }

    public void returnBook(int recordId) {
        dao.returnBook(recordId);
    }

    public List<BorrowRecord> getAllBorrowedBooks() {
        return dao.getAllBorrowedBooks();
    }
}
