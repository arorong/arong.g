package borrow.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import borrow.svc.BookDetailService;
import borrow.vo.ActionForward;
import borrow.vo.BookVO;

public class BorrowDetailAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int book_no=Integer.parseInt(request.getParameter("no"));
		String page = request.getParameter("page");
		BookDetailService bookDetailService = new BookDetailService();
		BookVO article = bookDetailService.getArticle(book_no);
		ActionForward forward = new ActionForward();
		request.setAttribute("page", page);
	   	request.setAttribute("article", article);
   		forward.setPath("/book/borrow_view.jsp");
   		return forward;
	}
}
