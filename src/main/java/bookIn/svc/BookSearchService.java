package bookIn.svc;

import static bookIn.db.JdbcUtil.*;

import java.sql.Connection;

import bookIn.dao.BookDAO;
import bookIn.vo.BookBean;	

public class BookSearchService {

	public boolean findBook(BookBean bookBean) throws Exception{
		
		boolean isWriteSuccess = false;
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		int listCount = bookDAO.selectListCount();
		
		try {
			listCount = bookDAO.selectBookList(bookBean);
			
			if(listCount > 0){
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