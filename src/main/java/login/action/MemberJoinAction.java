package login.action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import login.svc.MemberJoinService;
import login.vo.ActionForward;
import login.vo.MemberBean;

public class MemberJoinAction implements Action{
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 	
		 MemberBean member=new MemberBean();
	   		boolean joinResult=false;
	   		
	   		member.setId(request.getParameter("id"));
	   		member.setPw(request.getParameter("pw"));
	   		member.setNm(request.getParameter("nm"));
	   		member.setBirth(request.getParameter("birth"));
	   		member.setPh(request.getParameter("ph"));
	   		member.setEmail(request.getParameter("email"));
	   		member.setPostcode(request.getParameter("postcode"));
	   		member.setAddr(request.getParameter("addr"));
	   		member.setAddr1(request.getParameter("addr1"));
	   		
	   		MemberJoinService memberJoinService = new MemberJoinService();
	   		joinResult=memberJoinService.joinMember(member);
	   		
	   		ActionForward forward = null;
	   		if(joinResult==false){
	   			response.setContentType("text/html;charset=UTF-8");
	   			PrintWriter out = response.getWriter();
	   			out.println("<script>");
	   			out.println("alert('회원가입 실패');");
	   			out.println("history.back()");
	   			out.println("</script>");
		   	}
	   		else{
	   	    forward = new ActionForward();
	   		forward.setRedirect(false);
	   		forward.setPath("./JoinPro.jsp");
	   		}
	   		return forward;
	}
}