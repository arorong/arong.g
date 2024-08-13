package borrow.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static bookIn.db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import borrow.dao.BookDAO;
import borrow.svc.BookDetailService;
import borrow.vo.ActionForward;
import borrow.vo.BookVO;
import borrow.vo.DayVO;

public class BorrowExtend implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id");
		String no = request.getParameter("no");
		String success = "";
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		//추가
		DayVO dayVo = bookDAO.bExtend(id,no);
		
	
		ActionForward forward = new ActionForward();
		if(dayVo.getSuccess() > 0 && dayVo.getSuccess() == 100) {
			success ="over";
			
		}else if(dayVo.getSuccess() > 0 ){
			success = "T";
		}
		else{			
			success = "F";
		}
		//수정
		if(success.equals("F") || success.equals("over")) {
			forward.setPath("borrowList.ao?id="+id+"&success="+success);	

		}else {
			forward.setPath("borrowList.ao?id="+id+"&success="+success+"&day="+dayVo.getDay());
		
		}
		forward.setRedirect(true);
		
   		return forward;
	}
}
