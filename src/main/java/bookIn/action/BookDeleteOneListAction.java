package bookIn.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookIn.svc.BookDeleteOneListService;
import bookIn.vo.ActionForward;
import bookIn.vo.BookBean;

public class BookDeleteOneListAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		BookDeleteOneListService svc = new BookDeleteOneListService();
		BookBean book = svc.bookDeleteOne(request.getParameter("no"));
		System.out.println("book: " + book);
		if(book != null) {
			request.setAttribute("book", book);
			forward = new ActionForward();
			forward.setPath("/bookinsert/bookDelete.jsp");
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>alret('없는 데이터입니다.'); location.href='bookList.bok';</script>");
		}
		return forward;
	}
}