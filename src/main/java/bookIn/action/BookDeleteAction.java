package bookIn.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bookIn.dao.BookDAO;
import bookIn.vo.ActionForward;
import static bookIn.db.JdbcUtil.*;

import java.sql.Connection;

public class BookDeleteAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String no = request.getParameter("no");
        BookDAO bookDAO = BookDAO.getInstance();
        Connection con = getConnection();
        bookDAO.setConnection(con);
        
        int deleteCount = bookDAO.deleteBook(no);
        
        ActionForward forward = new ActionForward();
        if(deleteCount > 0) {
            commit(con);
            forward.setPath("bookListAdmin.bok");
            forward.setRedirect(true);
        } else {
            rollback(con);
            request.setAttribute("error", "책 삭제에 실패했습니다. 다시 시도해 주세요.");
            forward.setPath("/bookinsert/bookDelete.jsp"); // 삭제 실패 시 보여줄 JSP 페이지 경로
            forward.setRedirect(false);
        }
        close(con);
        return forward;
    }
}
