package borrow.action;


import static bookIn.db.JdbcUtil.*;
import static borrow.btn.Overdue.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import borrow.vo.ActionForward;

//관리자가 반납 버튼을 누르면 실행
public class BorrowDeletW implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	Connection con = getConnection();
	PreparedStatement pstmt = null;
	String sql = "";
	int r = 0;
	int i = 0;
	String b_pass = request.getParameter("b_pass");

	
	if(b_pass.equals("W")) {
		sql ="update SIGN SET B_PASS = 'F' WHERE NO = ? and b_pass = 'W'";
		pstmt = con.prepareStatement(sql);
		String no = request.getParameter("no");
		pstmt.setString(1, no);
		i = pstmt.executeUpdate();
		
	} else if(b_pass.equals("R")) {
		sql ="update SIGN SET B_PASS = 'C' WHERE NO = ? and b_pass = 'R'";
		pstmt = con.prepareStatement(sql);
		String no = request.getParameter("no");
		pstmt.setString(1, no);
		i = pstmt.executeUpdate();
		
	}
	

	if (i > 0) {
		
		con.commit();
		// 반납 버튼을 누른 날짜
		sql = "update SIGN set R_DATE = sysdate where NO = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, request.getParameter("no"));
		i = pstmt.executeUpdate();

		if (i > 0) {
			con.commit();
			Date bDate = od(con, request.getParameter("no")); // r_due
			Date rDate = odr(con, request.getParameter("no")); // r_date

			long sec = (rDate.getTime() - bDate.getTime());
			long days = sec / (24 * 60 * 60 * 1000); // 일자수

			// 연체 여부
			if (days > 0) {
				long sum = days;
				odB(con, request.getParameter("no"), sum);
				sql = "update catalogue set bsign = 'Y' where no = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, request.getParameter("no"));
				r = pstmt.executeUpdate();
				commit(con);
			} else {
				sql = "update catalogue set bsign = 'Y' where no = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, request.getParameter("no"));
				r = pstmt.executeUpdate();
				
				commit(con);
			}
		}

	}
	
	
	ActionForward forward = new ActionForward();
	forward.setPath("borrowList.ao");
	
	close(pstmt);
	close(con);
	
	return forward;
}
}
