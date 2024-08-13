package borrow.svc;

import static bookIn.db.JdbcUtil.*;


import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import borrow.dao.BookDAO;
import borrow.vo.BookVO;
import borrow.vo.SignVO;


public class BookListService {
	public int getListCount() throws Exception{
		
		int listCount = 0;
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		listCount = bookDAO.selectListCount();
		close(con);
		return listCount;
		
	}
	
	// 검색목록의 페이징
	public int getSearchCount(String word,String option) throws Exception{
		
		int listCount = 0;
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		listCount = bookDAO.selectSearchCount(word,option);
		close(con);
		return listCount;
		
	}
	
	//반납 대기중인 리스트 수 가져오기
	public int bookCount() throws Exception{
		
		int listCount = 0;
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		listCount = bookDAO.bookLoanCount();
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
	
	//반납 대기중인 리스트 가져오기
	public ArrayList<SignVO> getBbookList(int page, int limit) throws Exception{
		
		ArrayList<SignVO> articleList = null;
		
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		articleList = bookDAO.getbookLoan(page,limit);
		close(con);
		
		return articleList;
		
	}
	
	public int delayCount() throws Exception{
		// TODO Auto-generated method stub
		
		int listCount = 0;
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		listCount = bookDAO.delayListCount();
		close(con);
		return listCount;
		
	}
	
public ArrayList<SignVO> delayList(int page, int limit) throws Exception{
		
		ArrayList<SignVO> articleList = null;
		
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		articleList = bookDAO.delayListImport(page,limit);
		close(con);
		
		return articleList;
		
	}
	
}
