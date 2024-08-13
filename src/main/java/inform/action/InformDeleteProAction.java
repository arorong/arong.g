package inform.action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import inform.svc.InformDeleteProService;
import inform.vo.ActionForward;

public class InformDeleteProAction implements Action {

   public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
         throws Exception{   

      ActionForward forward = null;
      int inform_num=Integer.parseInt(request.getParameter("inform_num"));
      String nowPage = request.getParameter("page");
      InformDeleteProService informDeleteProService = new InformDeleteProService();
      boolean isArticleWriter =informDeleteProService.isArticleWriter(inform_num);

      if(!isArticleWriter){
         response.setContentType("text/html;charset=UTF-8");
         PrintWriter out=response.getWriter();
         out.println("<script>");
         out.println("alert('삭제할 권한이 없습니다');");
         out.println("history.back();");
         out.println("</script>");
         out.close();
      }

      else{
         
         boolean isDeleteSuccess = informDeleteProService.removeArticle(inform_num);

         if(!isDeleteSuccess){
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out=response.getWriter();
            out.println("<script>");
            out.println("alert('삭제실패');");
            out.println("history.back();");
            out.println("</script>");
            out.close();
         }
         else{
            forward = new ActionForward();
            forward.setRedirect(true);
            forward.setPath("informList.bo?page=");
         }
         
      }


      return forward;
   }

}