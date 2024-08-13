package borrow.svc;

import static bookIn.db.JdbcUtil.*;


import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import borrow.dao.BookDAO;
import borrow.vo.BookVO;

public class BorrowListService {
	public int getListCount() throws Exception{
		// TODO Auto-generated method stub
		
		int listCount = 0;
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		listCount = bookDAO.borrowListCount();
		close(con);
		return listCount;
		
	}

	public ArrayList<BookVO> getArticleList(HttpServletRequest request, HttpServletResponse response,int page, int limit) throws Exception{
		
		ArrayList<BookVO> articleList = null;
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		articleList = bookDAO.blist(request, response,page,limit);
		close(con);
		return articleList;
		
	}
}
