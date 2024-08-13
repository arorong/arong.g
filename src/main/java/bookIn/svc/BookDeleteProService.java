package bookIn.svc;

import static bookIn.db.JdbcUtil.close;
import static bookIn.db.JdbcUtil.commit;
import static bookIn.db.JdbcUtil.getConnection;
import static bookIn.db.JdbcUtil.rollback;

import java.sql.Connection;

import bookIn.dao.BookDAO;

public class BookDeleteProService {
    public boolean deleteBook(String no) throws Exception {
        boolean isDeleteSuccess = false;
        Connection con = getConnection();
        BookDAO bookDAO = BookDAO.getInstance();
        bookDAO.setConnection(con);
        int deleteCount = bookDAO.deleteBook(no);

        if(deleteCount > 0) {
            commit(con);
            isDeleteSuccess = true;
        } else {
            rollback(con);
        }
        close(con);
        return isDeleteSuccess;
    }
}
