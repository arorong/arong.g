package inform.action;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inform.svc.InformListService;
import inform.vo.ActionForward;
import inform.vo.InformBean;
import inform.vo.PageInfo;

 public class InformList2Action implements Action {
	 
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		ArrayList<InformBean> articleList=new ArrayList<InformBean>();
	  	int page=1;
		int limit=10;
		String urlString = "";
		int listCount= 0;
		
		if(request.getParameter("page")==null || request.getParameter("page").equals("")) page = 1;
		else page=Integer.parseInt(request.getParameter("page"));
		InformListService informListService = new InformListService();

		
		if(request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")).equals("/informList3.bo")) {
			listCount=informListService.getListCount1(); //총 리스트 수를 받아옴.
			articleList = informListService.getArticleList1(page,limit); //리스트를 받아옴.
			
			urlString = "/inform/mini_list3.jsp";
   		}else{
   			String search = request.getParameter("search")!=null ? request.getParameter("search"): "" ;
   			listCount=informListService.getListCount1(search); //총 리스트 수를 받아옴.
   			articleList = informListService.getArticleListsearch2(page,limit,search); //리스트를 받아옴.
   			urlString = "/inform/semi_inform_list2.jsp";
   		}
		
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
		forward.setPath(urlString);   			
	
   		return forward;
   		
	 }
	 
 }