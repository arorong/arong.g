package borrow.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import borrow.dao.BookDAO;
import borrow.svc.BookOverService;
import borrow.svc.BookSignService;
import borrow.vo.ActionForward;
import borrow.vo.SignVO;

public class BookBorrowAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ActionForward forward = null;
		SignVO signVO = new SignVO();
		BookDAO bookDAO = null;
		
		signVO.setNo(request.getParameter("no"));
		signVO.setId((String)session.getAttribute("id"));
		signVO.setB_pass("T");
			
		String idIsNull = signVO.getId();
		String noIsNull = signVO.getNo();
		String over = "";
		
		if(idIsNull == null) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("memberLogin.me");
			session.setAttribute("login","NO");
		}
		else {
			BookSignService bookSignService = new BookSignService();
			int isSignSuccess = bookSignService.registSign(signVO);
			BookOverService bookOverService = new BookOverService();
			over = bookOverService.registOver(idIsNull);
			
			if (isSignSuccess == 2) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('대출권수는 1인 최대 4권입니다.');");
				out.println("history.back();");
				out.println("</script>");
			} else if(isSignSuccess == 3) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				if(Integer.parseInt(over) == 0) {
					out.println("<script>");
					out.println("alert('책을 연체중입니다. 연체중인 책을 반납해주세요.');");
					out.println("history.back();");
					out.println("</script>");
				}else {
					out.println("<script>");
					out.println("alert('책을 연체한 기록이 있습니다. 연체하신 일 수 만큼 책을 대여할 수 없습니다. 남은 연체 일수 : " + over + "일');");
					out.println("history.back();");
					out.println("</script>");
				}
			
			} else {
				response.setContentType("text/html;charset=UTF-8");
				forward = new ActionForward();
				request.setAttribute("success","Y");
				forward.setPath("bookListUser.bok");
			}
			
			
			
		}
		return forward;
		
	}

}
