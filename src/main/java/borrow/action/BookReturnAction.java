package borrow.action;

import static bookIn.db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import borrow.vo.ActionForward;

public class BookReturnAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		Connection con = null;
		PreparedStatement pstmt = null;

		int r = 0;
		String id = request.getParameter("id");
		String no = request.getParameter("no");
		String b_pass = request.getParameter("b_pass");
		con = getConnection();
		// 대출 여부 W(반납 대기상태)로 바꾸기
		String sql = "";
		
		try {
			if(b_pass.equals("T")) {
				sql = "update SIGN set B_PASS='W' where NO=? and b_pass = 'T'";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, no);
				
				int i = pstmt.executeUpdate();
				commit(con);				
			} else if(b_pass.equals("Y")) {
				sql = "update SIGN set B_PASS='R' where NO=? and b_pass = 'Y'";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, no);
				
				int i = pstmt.executeUpdate();
				commit(con);								
			}
			
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("borrowList.ao?id=" + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(con);
		}

		return forward;
	}
}
