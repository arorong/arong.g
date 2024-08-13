package inform.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inform.svc.InformDetailService;
import inform.vo.ActionForward;
import inform.vo.InformBean;
// 게시판
 public class InformDetailAction2 implements Action {
	 
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{ 
	   	
		int inform_num=Integer.parseInt(request.getParameter("inform_num"));
		String page = request.getParameter("page");
		InformDetailService informDetailService = new InformDetailService();
		InformBean article = informDetailService.getArticle(inform_num);
		ActionForward forward = new ActionForward();
		request.setAttribute("page", page);
	   	request.setAttribute("article", article);
   		forward.setPath("/inform/semi_inform_view2.jsp");
   		return forward;

	 }
}
 