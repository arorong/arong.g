package login.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.svc.MemberListService;
import login.svc.MemberViewService;
import login.vo.ActionForward;
import login.vo.MemberBean;
//사용자 마이페이지 - 내정보라 생각
public class MemberViewAction implements Action{
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 	//사용자 정보조회
		 	HttpSession session=request.getSession();
	   		String id=(String)session.getAttribute("id");
	   		
	   		ActionForward forward = null;
	   		if(id==null){
	   			forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("./memberLogin.me");
	   		}

	   		else{
		   		forward = new ActionForward();
		   		String viewId=request.getParameter("id");
		   		MemberViewService memberViewService = new MemberViewService();
		   		MemberBean member=memberViewService.getMember(viewId);
		   		request.setAttribute("member", member);
		   		if(request.getRequestURI().substring( request.getRequestURI().lastIndexOf("/") ).equals("/memberViewAction2.me")  ) {
		   			if(id.equals("admin"))	forward.setPath("/ModifyForm.jsp");
		   			else {
		   				forward.setPath("/ModifyFormUser.jsp");
		   			}
		   		}else if(id.equals("admin")){
		   			forward.setPath("/Member_info.jsp");
		   		}else {
		   			forward.setPath("/Member_infoUser.jsp");
		   		}
	   		}
	   		return forward;
	}
}