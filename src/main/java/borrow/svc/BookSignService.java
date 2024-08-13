package borrow.svc;

import static bookIn.db.JdbcUtil.*;


import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletResponse;

import borrow.dao.BookDAO;
import borrow.vo.SignVO;

public class BookSignService {
	public int registSign(SignVO signVO) throws Exception {
		// 오라클 데이터베이스에 연결
				Connection con = getConnection();
				BookDAO bookDAO = BookDAO.getInstance();
				bookDAO.setConnection(con);
//				int count = bookDAO.selectNo(signVO);
				int insertCount = bookDAO.insertBorrow(signVO);
				
				
				if(insertCount == 1){
					commit(con);
					insertCount = 1;
				}
				else{
					rollback(con);
				}
				
				close(con);
				return insertCount;
	}
}
