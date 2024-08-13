package inform.action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inform.svc.InformReplyProService;
import inform.vo.ActionForward;
import inform.vo.InformBean;

public class InformReplyProAction implements Action {
	
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
	 throws Exception{
		 
		 	ActionForward forward = null;
		    String nowPage = request.getParameter("page");
		 	InformBean article = new InformBean();  		
		 	article.setInform_NUM(Integer.parseInt(request.getParameter("inform_NUM")));
		 	article.setInform_PASS(request.getParameter("inform_PASS"));
		 	article.setInform_NM(request.getParameter("inform_NM"));
		 	article.setInform_TITLE(request.getParameter("inform_TITLE"));
		 	article.setInform_TEXTS(request.getParameter("inform_TEXTS"));
		 	article.setRE_REF(Integer.parseInt(request.getParameter("RE_REF")));
		 	article.setRE_LEV(Integer.parseInt(request.getParameter("RE_LEV")));
		 	article.setRE_SEQ(Integer.parseInt(request.getParameter("RE_SEQ")));	   		
		 	InformReplyProService informReplyProService = new InformReplyProService();
		 	boolean isReplySuccess = informReplyProService.replyArticle(article);
		 	
	   		if(isReplySuccess){
	   			forward = new ActionForward();
	   			forward.setRedirect(true);
	   			forward.setPath("informList.bo?page=" + nowPage);
	   		}
	   		else{
	   			response.setContentType("text/html;charset=UTF-8");
	   			PrintWriter out = response.getWriter();
	   			out.println("<script>");
	   			out.println("alert('답장실패')");
	   			out.println("history.back()");
	   			out.println("</script>");
	   		}
	   		
	   		return forward;
	   		
	}  	
	 
}