package login.svc;

import login.vo.MemberBean;
import static bookIn.db.JdbcUtil.*;
import java.sql.Connection;
import login.dao.MemberDAO;

public class MemberModifyServiceUser {

	public boolean modifyMemberUser(MemberBean member) {
		boolean modifySuccess = false;
		MemberDAO memberDAO = MemberDAO.getInstance();
		Connection con = getConnection();
		memberDAO.setConnection(con);
		int modifyCount = memberDAO.modifyMemberUser(member);
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
