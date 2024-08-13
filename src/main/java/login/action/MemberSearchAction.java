package login.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static login.dao.MemberDAO.getInstance;


import login.dao.MemberDAO;
import login.svc.MemberListService;
import login.vo.ActionForward;
import login.vo.MemberBean;
import login.vo.PageInfo;

public class MemberSearchAction implements Action {
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	MemberDAO MemberDAO = getInstance();
    	ArrayList<MemberBean> memberList = new ArrayList<MemberBean>();
    	ActionForward forward = null;
		int page=1;
		int limit=10;
		String search = request.getParameter("search");
		String option = request.getParameter("option");
		 
		if(request.getParameter("page")==null || request.getParameter("page").equals("")) page = 1;
		else page=Integer.parseInt(request.getParameter("page"));
		
		MemberListService memberListService = new MemberListService();
		int listCount=memberListService.getSearchCount(search,option);
		
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
		  
        
        // MemberSearch 메서드를 호출하여 책 검색
        memberList = MemberDAO.MemberSearch(search, option);

        // 검색 결과를 request 속성에 저장
        request.setAttribute("memberList", memberList);

		if ( search == "" ) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('검색어를 입력해 주세요.');");
			out.println("history.back();");
			out.println("</script>");
			
		} else if (memberList.size() == 0 ) {
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('검색된 유저가 없습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		else {
			forward = new ActionForward();
	   		forward.setPath("/memberSearch.jsp");
		}		
		
        return forward;
    }
}