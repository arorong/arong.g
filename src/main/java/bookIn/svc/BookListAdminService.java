package bookIn.svc;

import static bookIn.db.JdbcUtil.*;
import java.sql.*;
import java.util.*;
import bookIn.dao.*;
import bookIn.vo.*;
import inform.vo.InformBean;

public class BookListAdminService {
	public int getListCount() throws Exception {
	        
	        int listCount = 0;
	        try (Connection con = getConnection()) {
	            BookDAO bookDAO = BookDAO.getInstance();
	            bookDAO.setConnection(con);
	            listCount = bookDAO.selectListCount();
	        }
	        return listCount;
	    }
	    
	    public ArrayList<BookBean> getBookList(int page, int limit) throws Exception {
	        
	    	ArrayList<BookBean> bookList = null;
	        try (Connection con = getConnection()) {
	            BookDAO bookDAO = BookDAO.getInstance();
	            bookDAO.setConnection(con);
	            bookList = bookDAO.selectBookList(page, limit);
	        }
	        return bookList;
	    }
	    
	    public int getListCounts(String search) throws Exception{
			// TODO Auto-generated method stub
			
			int listCount = 0;
			Connection con = getConnection();
			BookDAO bookDAO = BookDAO.getInstance();
			bookDAO.setConnection(con);
			listCount = bookDAO.selectListCounts(search);
			close(con);
			return listCount;
		}
	    
	    public ArrayList<BookBean> getBookListsearch(int page, int limit, String search) throws Exception{
			
			ArrayList<BookBean> articleList = null;
			Connection con = getConnection();
			BookDAO bookDAO = BookDAO.getInstance();
			bookDAO.setConnection(con);
			articleList = bookDAO.selectbooksearch(page,limit, search);
			close(con);
			return articleList;
		}
	    
	    
	    
}