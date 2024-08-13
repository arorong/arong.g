package bookIn.svc;

import static bookIn.db.JdbcUtil.*;

import java.sql.*;

import bookIn.dao.*;
import bookIn.vo.*;

public class BookInsertService {
	public boolean registBook(BookBean bookBean) throws Exception{
		
		boolean isWriteSuccess = false;
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		int insertCount;
		try {
			insertCount = bookDAO.BookInsert(bookBean);
			
			if(insertCount > 0){
				commit(con);
				isWriteSuccess = true;
			}
			else{
				rollback(con);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(con);
		}
		return isWriteSuccess;
	}
}
