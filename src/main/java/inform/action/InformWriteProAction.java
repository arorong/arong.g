package inform.action;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import inform.svc.InformWriteProService;
import inform.vo.ActionForward;
import inform.vo.InformBean;

public class InformWriteProAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;
		InformBean InformBean = null;
		String realFolder = "";
		String saveFolder = "/informUpload";
		int fileSize = 5 * 1024 * 1024;
//		ServletContext context = request.getServletContext();
//		realFolder = context.getRealPath(saveFolder);
		realFolder = "C:\\jspwork\\semiproject\\src\\main\\webapp\\"+saveFolder;
		File f = new File(realFolder);
		if(!f.exists()) f.mkdirs();
		
		MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "UTF-8",
				new DefaultFileRenamePolicy()); 
		InformBean = new InformBean();
//		InformBean.setInform_ID(multi.getParameter("inform_PASS"));
//		InformBean.setInform_NM(multi.getParameter("inform_NM"));
		InformBean.setInform_TITLE(multi.getParameter("inform_TITLE"));
		InformBean.setInform_TEXTS(multi.getParameter("inform_TEXTS"));
		InformBean.setInform_FILES(multi.getOriginalFileName((String) multi.getFileNames().nextElement()));
		
		
		InformWriteProService informWriteProService = new InformWriteProService();
		boolean isWriteSuccess = informWriteProService.registArticle(InformBean);

		if (!isWriteSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			if(InformBean.getInform_TITLE().length()>= 100) {
				out.println("<script>");
				out.println("alert('제목 수가 너무 많습니다.');");
				out.println("history.back();");
				out.println("</script>");
			}else if(InformBean.getInform_TEXTS().length()>= 1500) {
				out.println("<script>");
				out.println("alert('텍스트 수가 너무 많습니다.');");
				out.println("history.back();");
				out.println("</script>");
			}
			else {
				out.println("<script>");
				out.println("alert('등록실패');");
				out.println("history.back();");
				out.println("</script>");
			}
		} else {
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("informList.bo");
			}

		return forward;
	}
	
}
	
	