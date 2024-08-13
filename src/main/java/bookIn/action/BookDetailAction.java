package bookIn.action;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookIn.svc.BookDetailService;
import bookIn.vo.*;

public class BookDetailAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String bookNo = request.getParameter("no");
		String page = request.getParameter("page");
		BookDetailService bookDetailService = new BookDetailService();
		BookBean book = bookDetailService.getBookDetail(bookNo);
		ActionForward forward = new ActionForward();
		request.setAttribute("page", page);
		request.setAttribute("book", book);
		forward.setPath("/bookinsert/bookView.jsp");
		return forward;
	}
}