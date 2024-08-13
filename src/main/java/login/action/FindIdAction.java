package login.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.dao.MemberDAO;
import login.svc.FindIdService;
import login.svc.MemberListService;
import login.svc.MemberLoginService;
import login.vo.ActionForward;
import login.vo.MemberBean;

public class FindIdAction implements Action {
	@Override
		public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			MemberDAO dao = new MemberDAO(); //0. sql객체 생성
			 String nm = request.getParameter("nm");
			 String ph = request.getParameter("ph"); //1. 입력된 값 가져오기
			 String id = dao.findId(nm, ph); //2. 입력된 값nm, ph으로 sql id정보 가져오기
			 request.setAttribute("id", id); //3. 가져온 값id를 name값에 셋팅 
			 
			ActionForward forward = null;			
			if (id != null) {		
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("findIdResult.jsp");				
			} else {				
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('가입하신 정보를 확인해주세요.');");
				out.println("location.href='./findId.jsp';");
				out.println("</script>");
			}
			return forward;
		}
}