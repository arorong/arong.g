package login.svc;

import java.sql.Connection;
import java.util.ArrayList;
import login.dao.MemberDAO;
import static bookIn.db.JdbcUtil.*;
import login.vo.MemberBean;

public class FindIdService {

	public ArrayList<MemberBean> getFindIdList() {
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		ArrayList<MemberBean> findIdList = memberDAO.selectMemberList();
		close(con);
		return findIdList;
	}
}
