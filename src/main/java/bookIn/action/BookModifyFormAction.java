package bookIn.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookIn.svc.BookDetailService;
import bookIn.vo.ActionForward;
import bookIn.vo.BookBean;

public class BookModifyFormAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String bookNo = request.getParameter("no");
        BookDetailService bookDetailService = new BookDetailService();
        BookBean book = bookDetailService.getBookDetail(bookNo);
        ActionForward forward = new ActionForward();
        request.setAttribute("book", book);
        forward.setPath("/bookinsert/bookModify.jsp");
        return forward;

    }
}