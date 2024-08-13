package inform.svc;

import static bookIn.db.JdbcUtil.*;
import java.sql.Connection;
import java.util.ArrayList;
import inform.dao.InformDAO;
import inform.vo.InformBean;

public class InformList2Service{

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

	public ArrayList<InformBean> getArticleList(int page, int limit) throws Exception{
		
		ArrayList<InformBean> articleList = null;
		Connection con = getConnection();
		InformDAO informDAO = InformDAO.getInstance();
		informDAO.setConnection(con);
		articleList = informDAO.selectArticleList2(page,limit);
		close(con);
		return articleList;
		
	}

}
