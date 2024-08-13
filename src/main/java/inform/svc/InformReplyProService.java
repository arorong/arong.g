package inform.svc;

import static bookIn.db.JdbcUtil.*;
import java.sql.Connection;
import inform.dao.InformDAO;
import inform.vo.InformBean;

public class InformReplyProService {

	public boolean replyArticle(InformBean article) throws Exception{
		
		boolean isReplySuccess = false;
		int insertCount = 0;
		Connection con = getConnection();
		InformDAO informDAO = InformDAO.getInstance();
		informDAO.setConnection(con);
		insertCount = informDAO.insertArticle(article);
		
		if(insertCount > 0){
			commit(con);
			isReplySuccess = true;
		}
		else{
			rollback(con);
		}
		
		close(con);
		return isReplySuccess;
		
	}

}
