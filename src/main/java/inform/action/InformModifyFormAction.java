package inform.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inform.svc.InformDetailService;
import inform.vo.ActionForward;
import inform.vo.InformBean;

public class InformModifyFormAction implements Action {
	
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		 	ActionForward forward = new ActionForward();
			int inform_num=Integer.parseInt(request.getParameter("inform_num"));
			InformDetailService informDetailService = new InformDetailService();	
		   	InformBean article =informDetailService.getArticle(inform_num);
		   	request.setAttribute("page", request.getParameter("page"));
		   	request.setAttribute("article", article);
	   		forward.setPath("/inform/semi_inform_modify.jsp?inform_NUM="+request.getParameter("inform_NUM"));
	   		return forward;
	   		
	 }
	 
}