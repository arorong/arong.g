package inform.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inform.svc.InformDetailService;
import inform.vo.ActionForward;
import inform.vo.InformBean;

public class InformReplyFormAction implements Action {
	
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
	 	throws Exception{
		 
		 	ActionForward forward = new ActionForward();
	   		String nowPage = request.getParameter("page");
	   		int inform_num=Integer.parseInt(request.getParameter("inform_num"));
	   		InformDetailService informDetailService = new InformDetailService();
	   		InformBean article=informDetailService.getArticle(inform_num);	
	   		
	   		request.setAttribute("article", article);
	   		request.setAttribute("page", nowPage);
	   		forward.setPath("/inform/semi_inform_reply.jsp");
	   		return forward;
	   		
	}
	 
}