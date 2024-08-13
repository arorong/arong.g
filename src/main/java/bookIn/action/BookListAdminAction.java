package bookIn.action;

import java.util.*;

import javax.servlet.http.*;
import bookIn.svc.*;
import bookIn.vo.*;

public class BookListAdminAction implements Action {
public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		ArrayList<BookBean> bookList = new ArrayList<BookBean>();
		int page = 1;
		int limit = 10;

		String pageStr = request.getParameter("page");
		if(pageStr != null && !pageStr.equals("")) {
		    page = Integer.parseInt(pageStr);
		}
		
		if(request.getParameter("page") == null || request.getParameter("page").equals("")) {
			page = 1;
		} else {
			page = Integer.parseInt(request.getParameter("page"));			
		}
		BookListAdminService bookListService = new BookListAdminService();
		int listCount = bookListService.getListCount(); // 총 리스트 수를 받아옴.
		bookList = bookListService.getBookList(page, limit); // 리스트를 받아옴.
		// 총 페이지 수.
		int maxPage=(int)((double)listCount / limit + 0.95); // 0.95를 더해서 올림 처리.
		// 현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등..)
		int startPage = (((int) ((double)page / 10 + 0.9)) - 1) * 10 + 1;
		// 현재 페이지에 보여줄 마지막 페이지 수.(10, 20, 30 등 ..)
		int endPage = startPage + 10 - 1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo();
		pageInfo.setEndPage(endPage);
		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("bookList", bookList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/bookinsert/bookListAdmin.jsp");
		return forward;
	}
}
