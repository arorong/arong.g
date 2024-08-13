package bookIn.action;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import bookIn.vo.*;
import bookIn.svc.*;

public class BookInsertAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;
		BookBean bookBean = null;
		String realFolder = "";
		String saveFolder = "/bookcoverupload";
		int fileSize = 5 * 1024 * 1024;
		realFolder = "C:\\jspwork\\semiproject\\src\\main\\webapp\\" + saveFolder;

		File f = new File(realFolder);
		if(!f.exists()) f.mkdirs();
		
		MultipartRequest multi = new MultipartRequest(request,realFolder, fileSize, "UTF-8",
				new DefaultFileRenamePolicy()); 
		bookBean = new BookBean();
		
		bookBean.setGenre(multi.getParameter("genre"));
		bookBean.setTitle(multi.getParameter("title"));
		bookBean.setAuthor(multi.getParameter("author"));
		bookBean.setPub(multi.getParameter("pub"));
		bookBean.setNo(multi.getParameter("no"));
		bookBean.setBookno(multi.getParameter("bookno"));
		bookBean.setBookfile(multi.getOriginalFileName((String) multi.getFileNames().nextElement()));
		
		BookInsertService bookInsertService = new BookInsertService();
		boolean isWriteSuccess = bookInsertService.registBook(bookBean);

		if (!isWriteSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패');");
			out.println("history.back();");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("bookListAdmin.bok");
		}
		return forward;
	}
}
