package login.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.dao.MemberDAO;
import login.svc.MemberListService;
import login.svc.MemberLoginService;
import login.vo.ActionForward;
import login.vo.MemberBean;

public class FindPwAction implements Action {
	@Override
		public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			MemberDAO dao = new MemberDAO();
			 String id = request.getParameter("id");
			 String nm = request.getParameter("nm");
			 String ph = request.getParameter("ph");
			 boolean userChk = dao.userCheck(id, nm, ph);
			
			ActionForward forward = null;			
			if (userChk) {		//true인 경우
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("setPw.jsp");				
			} else {				
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('가입하신 정보를 확인해주세요.');");
				out.println("location.href='./findPw.jsp';");
				out.println("</script>");
			}
			return forward;
		}
}