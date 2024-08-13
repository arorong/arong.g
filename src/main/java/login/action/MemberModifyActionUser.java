package login.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.dao.MemberDAO;
import login.svc.MemberListService;
import login.svc.MemberModifyService;
import login.svc.MemberModifyServiceUser;
import login.vo.ActionForward;
import login.vo.MemberBean;

public class MemberModifyActionUser implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session=request.getSession();
   		String id=(String)session.getAttribute("id");
		
		MemberBean member = new MemberBean();
		boolean modifyResult = false;
		
		member.setId(request.getParameter("id"));
		member.setPw(request.getParameter("pw"));
		member.setNm(request.getParameter("nm"));
		member.setBirth(request.getParameter("birth"));
		member.setPh(request.getParameter("ph"));
		member.setEmail(request.getParameter("email"));
		member.setPostcode(request.getParameter("postcode"));
		member.setAddr(request.getParameter("addr"));
		member.setAddr1(request.getParameter("addr1"));

		MemberModifyServiceUser memberModifyServiceUser = new MemberModifyServiceUser();
		modifyResult = memberModifyServiceUser.modifyMemberUser(member);
		

		ActionForward forward = null;
		if (modifyResult == false) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원정보수정 실패')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./Logout.jsp"); //로그아웃 경로로 이동
		}
		return forward;
	}
}