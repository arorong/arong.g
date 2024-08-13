package inform.action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inform.svc.InformModifyProService;
import inform.vo.ActionForward;
import inform.vo.InformBean;

public class InformModifyProAction implements Action {

	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
			throws Exception{

		ActionForward forward = null;
		boolean isModifySuccess = false;
		int inform_num=Integer.parseInt(request.getParameter("inform_NUM"));
		InformBean article=new InformBean();
		InformModifyProService informModifyProService = new InformModifyProService();
		boolean isRightUser=informModifyProService.isArticleWriter(inform_num, request.getParameter("inform_ID"));

		if(!isRightUser){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('수정할 권한이 없습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		else{
			article.setInform_NUM(inform_num);
			article.setInform_TITLE(request.getParameter("inform_TITLE"));
			article.setInform_TEXTS(request.getParameter("inform_TEXTS")); 
			isModifySuccess = informModifyProService.modifyArticle(article);

			if(!isModifySuccess){
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('수정실패');");
				out.println("history.back()");
				out.println("</script>");
			}
			else{
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("informDetail.bo?inform_num="+article.getInform_NUM()+"&page="+request.getParameter("page")); 
			}

		}

		return forward;
	}

}