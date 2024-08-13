package login.action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.dao.MemberDAO;
import login.svc.MemberModifyService;
import login.vo.ActionForward;
import login.vo.MemberBean;

public class SetPwAction implements Action{
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
	 	throws Exception{
		 MemberDAO dao = new MemberDAO();
		 
		 boolean setPwResult=false;
	   	 ActionForward forward = null;	
	   	 String id = request.getParameter("id");
	   	 String pw = request.getParameter("npw");
	   	 int pwModify = dao.modifyPw(id, pw);
	  
			   		
	   		if(pwModify > 0){
	   			response.setContentType("text/html;charset=UTF-8");
	   			PrintWriter out = response.getWriter();
	   			out.println("<script>");
	   			out.println("alert('비밀번호 변경 성공\n로그인 페이지로 이동합니다.')");
	   			out.println("history.back()");
	   			out.println("</script>");
	   			forward = new ActionForward();
		   		forward.setRedirect(true);
		   		forward.setPath("./LoginForm.jsp");
		   	}
	   		else{		   	    
		   		response.setContentType("text/html;charset=UTF-8");
	   			PrintWriter out = response.getWriter();
	   			out.println("<script>");
	   			out.println("alert('비밀번호 변경 실패')");
	   			out.println("history.back()");
	   			out.println("</script>");
	   		}	   		
	   		return forward;
	}
}