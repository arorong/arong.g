package inform.svc;

import static bookIn.db.JdbcUtil.*;


import java.rmi.activation.ActivationGroup_Stub;
import java.sql.Connection;
import java.util.ArrayList;
import inform.dao.InformDAO;
import inform.vo.InformBean;

public class InformListService {

	public int getListCount() throws Exception{
		// TODO Auto-generated method stub
		
		int listCount = 0;
		Connection con = getConnection();
		InformDAO informDAO = InformDAO.getInstance();
		informDAO.setConnection(con);
		listCount = informDAO.selectListCount();
		close(con);
		return listCount;
	}
	
	public int getListCount1() throws Exception{
		// TODO Auto-generated method stub
		
		int listCount = 0;
		Connection con = getConnection();
		InformDAO informDAO = InformDAO.getInstance();
		informDAO.setConnection(con);
		listCount = informDAO.selectListCount1();
		close(con);
		return listCount;
	}
	
	// 공지사항 카운터
	public int getListCounts(String search) throws Exception{
		// TODO Auto-generated method stub
		
		int listCount = 0;
		Connection con = getConnection();
		InformDAO informDAO = InformDAO.getInstance();
		informDAO.setConnection(con);
		listCount = informDAO.selectListCounts(search);
		close(con);
		return listCount;
	}
	
	public int getListCount1(String search) throws Exception{
		// TODO Auto-generated method stub
		
		int listCount = 0;
		Connection con = getConnection();
		InformDAO informDAO = InformDAO.getInstance();
		informDAO.setConnection(con);
		listCount = informDAO.selectListCount1(search);
		close(con);
		return listCount;
	}
	//공지사항 메인
	public ArrayList<InformBean> getArticleList(int page, int limit) throws Exception{
		ArrayList<InformBean> articleList = null;
		Connection con = getConnection();
		InformDAO informDAO = InformDAO.getInstance();
		informDAO.setConnection(con);
		articleList = informDAO.selectarticleList(page,limit);
		close(con);
		return articleList;
	}
	//게시판 메인
	public ArrayList<InformBean> getArticleList1(int page, int limit) throws Exception{
		ArrayList<InformBean> articleList = null;
		Connection con = getConnection();
		InformDAO informDAO = InformDAO.getInstance();
		informDAO.setConnection(con);
		articleList = informDAO.selectArticleList2(page,limit);
		close(con);
		return articleList;
	}
	
	// 공지사항 검색
	public ArrayList<InformBean> getArticleListsearch(int page, int limit, String search) throws Exception{
		
		ArrayList<InformBean> articleList = null;
		Connection con = getConnection();
		InformDAO informDAO = InformDAO.getInstance();
		informDAO.setConnection(con);
		articleList = informDAO.selectArticlesearch(page,limit, search);
		close(con);
		return articleList;
	}
	
	// 게시판 검색
	public ArrayList<InformBean> getArticleListsearch2(int page, int limit, String search) throws Exception{
		
		ArrayList<InformBean> articleList = null;
		Connection con = getConnection();
		InformDAO informDAO = InformDAO.getInstance();
		informDAO.setConnection(con);
		articleList = informDAO.selectArticlesearch2(page,limit, search);
		close(con);
		return articleList;
	}
}
