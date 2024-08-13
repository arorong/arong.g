//package bookIn.action;
//
//import java.io.File;
//import java.io.PrintWriter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.oreilly.servlet.MultipartRequest;
//import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
//
//import bookIn.svc.BookFileService;
//import bookIn.vo.ActionForward;
//import bookIn.vo.BookBean;
//
//public class BookFileAction implements Action{
//
//	@Override
//	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		ActionForward forward = null;
//		BookBean bookFile = null;
//		String realFolder = "";
//		String saveFolder = "/bookcoverupload";
//		int fileSize = 5 * 1024 * 1024;
//		realFolder = "C:\\semiproject\\semiproject\\src\\main\\webapp" + saveFolder;
//		
//		File f = new File(realFolder);
//		
//		if(!f.exists()) {
//			f.mkdirs();
//		}
//		
//		MultipartRequest multi = new MultipartRequest(request,realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy()); 
//		
//		String no = multi.getParameter("NO");
//		
//		bookFile = new BookBean();
//		
//		bookFile.setGenre(multi.getParameter("genre"));
//		bookFile.setTitle(multi.getParameter("title"));
//		bookFile.setAuthor(multi.getParameter("author"));
//		bookFile.setPub(multi.getParameter("pub"));
//		bookFile.setNo(multi.getParameter("no"));
//		bookFile.setBookno(multi.getParameter("bookno"));
//		
//		BookFileService BookfileSc = new BookFileService();
//		boolean isModifySuccess = BookfileSc.modiFile(bookFile);
//		
//		if (!isModifySuccess) {
//            response.setContentType("text/html;charset=UTF-8");
//            PrintWriter out = response.getWriter();
//            out.println("<script>");
//            out.println("alert('수정실패');");
//            out.println("history.back()");
//            out.println("</script>");
//        } else {
//            forward = new ActionForward();
////            forward.setRedirect(true);
//            forward.setPath("bookListAdmin.bok");
//        }
//
//        return forward;
//	}
//
//}
