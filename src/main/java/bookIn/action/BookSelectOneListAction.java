package bookIn.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookIn.svc.BookSelectOneListService;
import bookIn.vo.ActionForward;
import bookIn.vo.BookBean;

public class BookSelectOneListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		BookSelectOneListService svc = new BookSelectOneListService();
		BookBean book = svc.bookSelectOne(request.getParameter("no"));
		if(book != null) {
			request.setAttribute("book" , book);
			forward = new ActionForward();
			forward.setPath("/bookinsert/bookModify.jsp");
		}else {
			response.setContentType("text/html ; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>alert('없는 데이터입니다.'); location.href='bookList.bok'; </script>");
		}
		
		return forward;
		
	}
}
