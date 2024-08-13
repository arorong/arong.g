package borrow.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static borrow.dao.BookDAO.getInstance;

import borrow.dao.BookDAO;
import bookIn.svc.BookSearchService;
import borrow.vo.ActionForward;
import bookIn.vo.BookBean;
import borrow.svc.BookListService;
import borrow.vo.PageInfo;

public class BookSearchAction implements Action {
	
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	BookDAO bookDAO = getInstance();
    	ArrayList<BookBean> bookList = new ArrayList<BookBean>();
    	ActionForward forward = null;
		int page=1;
		int limit=10;
		String word = request.getParameter("word");
		String option = request.getParameter("col");
		if (option.equals("none")) {
			option = "title";
		}
		 
		if(request.getParameter("page")==null || request.getParameter("page").equals("")) page = 1;
		else page=Integer.parseInt(request.getParameter("page"));
		
		BookListService bookListService = new BookListService();
		int listCount=bookListService.getSearchCount(word,option);
		
   		int maxPage=(int)((double)listCount/limit+0.95);
   		int startPage = (((int) ((double)page / 10 + 0.9)) - 1) * 10 + 1;
   	    int endPage = startPage+10-1;

   		if (endPage> maxPage) endPage= maxPage;

   		PageInfo pageInfo = new PageInfo();
   		pageInfo.setEndPage(endPage);
   		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);	
		
		request.setAttribute("pageInfo", pageInfo);
		
        // 요청 파라미터에서 검색어를 가져옴
        
        
        
        // BookSearch 메서드를 호출하여 책 검색
        bookList = bookDAO.bookSearch(word,option,page,limit);

        // 검색 결과를 request 속성에 저장
        request.setAttribute("bookList", bookList);

//        BookSearchService bookSearchService = new BookSearchService();
//		boolean isWriteSuccess = bookSearchService.findBook(bookBean);

		if ( word == "" ) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('검색어를 입력해 주세요.');");
			out.println("history.back();");
			out.println("</script>");
			
		} else if (bookList.size() == 0 ) {
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('검색된 자료가 없습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		else {
			forward = new ActionForward();
	   		forward.setPath("/book/booksearch.jsp");
		}
		
		
		
        return forward;
    }
}