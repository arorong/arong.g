package borrow.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import borrow.svc.BookListService;
import borrow.vo.ActionForward;
import borrow.vo.BookVO;
import borrow.vo.PageInfo;

public class BookListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ArrayList<BookVO> articleList=new ArrayList<BookVO>();
	  	int page=1;
		int limit=10;
		
		if(request.getParameter("page")==null || request.getParameter("page").equals("")) page = 1;
		else page=Integer.parseInt(request.getParameter("page"));
		
		BookListService bookListService = new BookListService();
		int listCount=bookListService.getListCount(); //총 리스트 수를 받아옴.
		articleList = bookListService.getArticleList(request,response,page,limit); //리스트를 받아옴.
		//총 페이지 수.
   		int maxPage=(int)((double)listCount/limit+0.95); //0.95를 더해서 올림 처리.
   		//현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
   		int startPage = (((int) ((double)page / 10 + 0.9)) - 1) * 10 + 1;
   		//현재 페이지에 보여줄 마지막 페이지 수.(10, 20, 30 등...)
   	        int endPage = startPage+10-1;

   		if (endPage> maxPage) endPage= maxPage;

   		PageInfo pageInfo = new PageInfo();
   		pageInfo.setEndPage(endPage);
   		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);	
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("articleList", articleList);
		
		ActionForward forward= new ActionForward();
   		forward.setPath("/book/book_list.jsp");
   		return forward;
		
	}

}
