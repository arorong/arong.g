package login.svc;

import login.vo.MemberBean;
import static bookIn.db.JdbcUtil.*;
import java.sql.Connection;
import login.dao.MemberDAO;

public class MemberModifyService {

	public boolean modifyMember(MemberBean member) {
		boolean modifySuccess = false;
		MemberDAO memberDAO = MemberDAO.getInstance();
		Connection con = getConnection();
		memberDAO.setConnection(con);
		int modifyCount = memberDAO.modifyMember(member);
		if(modifyCount > 0){
			modifySuccess = true;
			commit(con);
		}
		else{
			rollback(con);
		}
		close(con);
		return modifySuccess;
	}
}
