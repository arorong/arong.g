package login.action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import login.svc.MemberDeleteService;
import login.vo.ActionForward;

public class MemberWithdrawalAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		ActionForward forward = null;

		String deleteId = request.getParameter("id");
		MemberDeleteService memberDeleteService = new MemberDeleteService();

		    boolean deleteResult = memberDeleteService.deleteMember(deleteId);
		    
			if (deleteResult == true) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('회원탈퇴에 성공하였습니다.');");
				out.println("location.href='./Logout.jsp';");
				out.println("</script>");
			} else {
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("./memberListAction.me");
			}
		
		return forward;
	}
}