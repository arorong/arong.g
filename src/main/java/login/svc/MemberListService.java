package login.svc;

import java.sql.Connection;
import java.util.ArrayList;
import login.dao.MemberDAO;
import static bookIn.db.JdbcUtil.*;
import login.vo.MemberBean;

public class MemberListService {

	public ArrayList<MemberBean> getMemberList() {
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		ArrayList<MemberBean> memberList = memberDAO.selectMemberList();
		close(con);
		return memberList;
	}

	public int getListCount() throws Exception { //회원전체리스트

		int listCount = 0;
		try (Connection con = getConnection()) {
			MemberDAO memberDAO = MemberDAO.getInstance();
			memberDAO.setConnection(con);
			listCount = memberDAO.selectListCount();
			close(con);
		}
		return listCount;
	}

	public ArrayList<MemberBean> getMemberList(int page, int limit) throws Exception {

		ArrayList<MemberBean> memberList = null;
		try (Connection con = getConnection()) {
			MemberDAO memberDAO = MemberDAO.getInstance();
			memberDAO.setConnection(con);
			memberList = memberDAO.selectMemberList(page, limit);
			close(con);
		}
		return memberList;
	}
	
	public int getSearchCount(String search,String option) throws Exception {
		
		int listCount = 0;
		try (Connection con = getConnection()) {
			MemberDAO memberDAO = MemberDAO.getInstance();
			memberDAO.setConnection(con);
			listCount = memberDAO.selectSearchCount(search,option);
			close(con);
		}
		return listCount;
	}
}
