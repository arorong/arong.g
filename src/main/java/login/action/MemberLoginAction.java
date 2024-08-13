package login.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.*;

import login.svc.MemberListService;
import login.svc.MemberLoginService;
import login.vo.*;

public class MemberLoginAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		MemberBean member = new MemberBean();

		member.setId(request.getParameter("id"));
		member.setPw(request.getParameter("pw"));

		MemberLoginService memberLoginService = new MemberLoginService();
		String active = memberLoginService.login(member);
		ActionForward forward = null;
		member.setActive(active);
				
		if (active == null || active.equals("N")) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('아이디와 비밀번호를 확인해주세요.');");
			out.println("location.href='./memberLogin.me';");
			out.println("</script>");
		}
		else {
			forward = new ActionForward();
			session.setAttribute("active", member.getActive());
			session.setAttribute("id", member.getId());
			forward.setRedirect(true);
			forward.setPath("./Main.jsp");
		}
		return forward;
	}
}