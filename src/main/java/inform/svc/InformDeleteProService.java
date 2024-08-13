package inform.svc;

import static bookIn.db.JdbcUtil.*;

import java.sql.Connection;
import inform.dao.InformDAO;

public class InformDeleteProService {

	public boolean isArticleWriter(int inform_num) throws Exception {
		// TODO Auto-generated method stub
		
		boolean isArticleWriter = false;
		Connection con = getConnection();
		InformDAO informDAO = InformDAO.getInstance();
		informDAO.setConnection(con);
		isArticleWriter = informDAO.isArticleinformWriter(inform_num);
		close(con);
		return isArticleWriter;
		
	}

	public boolean removeArticle(int inform_num) throws Exception{
		// TODO Auto-generated method stub
		
		boolean isRemoveSuccess = false;
		Connection con = getConnection();
		InformDAO informDAO = InformDAO.getInstance();
		informDAO.setConnection(con);
		int deleteCount = informDAO.deleteArticle(inform_num);
		
		if(deleteCount > 0){
			commit(con);
			isRemoveSuccess=true;
		}
		else{
			rollback(con);
		}
		
		close(con);
		return isRemoveSuccess;
	}

}
