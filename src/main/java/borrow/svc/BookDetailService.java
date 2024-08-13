package borrow.svc;

import static bookIn.db.JdbcUtil.*;


import java.sql.Connection;

import borrow.dao.BookDAO;
import borrow.vo.BookVO;

public class BookDetailService {
public BookVO getArticle(int book_no) throws Exception{
		
		BookVO article = null;
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		
		article = bookDAO.selectBook(book_no);
		close(con);
		return article;
		
	}
}
