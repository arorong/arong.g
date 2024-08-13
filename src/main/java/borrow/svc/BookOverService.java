package borrow.svc;

import static bookIn.db.JdbcUtil.*;


import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletResponse;

import borrow.dao.BookDAO;
import borrow.vo.SignVO;

public class BookOverService {
	public String registOver(String id) throws Exception {
		// 오라클 데이터베이스에 연결
				Connection con = getConnection();
				BookDAO bookDAO = BookDAO.getInstance();
				bookDAO.setConnection(con);
//				int count = bookDAO.selectNo(signVO);
				String over = bookDAO.over(id);

				close(con);
				return over;
	}
}
