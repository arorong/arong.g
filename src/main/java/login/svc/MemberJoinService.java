package login.svc;

import login.vo.MemberBean;
import static bookIn.db.JdbcUtil.*;
import java.sql.Connection;
import login.dao.MemberDAO;

public class MemberJoinService {

	public boolean joinMember(MemberBean member) {
		boolean joinSuccess = false;
		MemberDAO memberDAO = MemberDAO.getInstance();
		
		Connection con = getConnection();
		memberDAO.setConnection(con);
		int insertCount = memberDAO.insertMember(member);
		if(insertCount > 0){
			joinSuccess = true;
			commit(con);
		}
		else{
			rollback(con);
		}
		close(con);
		return joinSuccess;
	}

}
