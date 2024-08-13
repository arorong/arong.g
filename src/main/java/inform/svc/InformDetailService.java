package inform.svc;

import java.sql.Connection;
import inform.dao.InformDAO;
import inform.vo.InformBean;
import static bookIn.db.JdbcUtil.*;

public class InformDetailService {

	public InformBean getArticle(int inform_num) throws Exception{
		
		Connection con = getConnection();
		InformDAO informDAO = InformDAO.getInstance();
		informDAO.setConnection(con);
		int updateCount = informDAO.updateReadCount(inform_num);
		
		if(updateCount > 0){
			commit(con);
		}
		else{
			rollback(con);
		}
		
		InformBean article = informDAO.selectArticle(inform_num);
		close(con);
		return article;
		
	}

}
