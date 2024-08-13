package bookIn.action;

import static bookIn.db.JdbcUtil.*;

import java.io.File;
import java.sql.*;
import javax.servlet.http.*;
import javax.swing.plaf.multi.MultiButtonUI;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import bookIn.dao.*;
import bookIn.vo.*;

public class BookModifyAction implements Action {
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        BookBean book = new BookBean();
        String realFolder = "";
		String saveFolder = "/bookcoverupload";
		int fileSize = 5 * 1024 * 1024;
		realFolder = "C:\\jspwork\\semiproject\\src\\main\\webapp\\" + saveFolder;

		File f = new File(realFolder);
		if(!f.exists()) f.mkdirs();
        
        MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "UTF-8",
				new DefaultFileRenamePolicy()); 
        String bk =multi.getFilesystemName((String)multi.getFileNames().nextElement()) ;
        book.setNo(multi.getParameter("no"));
        book.setGenre(multi.getParameter("genre"));
        book.setTitle(multi.getParameter("title"));
        book.setAuthor(multi.getParameter("author"));
        book.setPub(multi.getParameter("pub"));
        book.setBookno(multi.getParameter("bookno"));
        book.setBookfile(bk);
        
        
        BookDAO bookDAO = BookDAO.getInstance();
        Connection con = getConnection();
        bookDAO.setConnection(con);
        
        String originFile = bookDAO.deleteBookFile(multi.getParameter("no"));
        
        int updateCount = bookDAO.updateBook(book);
        
        ActionForward forward = new ActionForward();
        if (updateCount > 0) {
            if (!(bk == null || bk.equals(""))) {
                File deleteFile = new File(realFolder + "/" + originFile);
                if (deleteFile.exists()) {
                    if (deleteFile.delete()) {
                        System.out.println("파일삭제 성공");
                    } else {
                        System.out.println("파일삭제 실패");
                    }
                } else {
                    System.out.println("파일이 존재하지 않습니다.");
                }
            }
            forward.setPath("bookListAdmin.bok"); // 성공 시 돌아갈 페이지 설정
            forward.setRedirect(true); // 리다이렉트 방식 사용
        } else {
            request.setAttribute("error_msg", "책 정보 수정에 실패하였습니다. 입력한 정보를 확인해 주세요.");
        }
        return forward;
    }
}
