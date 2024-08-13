package inform.svc;

import static bookIn.db.JdbcUtil.*;
import java.sql.Connection;
import inform.vo.InformBean;
import inform.dao.InformDAO;

public class InformModifyProService {

	public boolean isArticleWriter(int inform_num, String pass) throws Exception {
		// TODO Auto-generated method stub
		
		boolean isArticleWriter = false;
		Connection con = getConnection();
		InformDAO informDAO = InformDAO.getInstance();
		informDAO.setConnection(con);
		isArticleWriter = informDAO.isArticleinformWriter(inform_num);
		close(con);
		return isArticleWriter;
		
	}

	public boolean modifyArticle(InformBean article) throws Exception {
		// TODO Auto-generated method stub
		
		boolean isModifySuccess = false;
		Connection con = getConnection();
		InformDAO informDAO = InformDAO.getInstance();
		informDAO.setConnection(con);
		int updateCount = informDAO.updateArticle(article);
		
		if(updateCount > 0){
			commit(con);
			isModifySuccess=true;
		}
		else{
			rollback(con);
		}
		
		close(con);
		return isModifySuccess;
		
	}

}
