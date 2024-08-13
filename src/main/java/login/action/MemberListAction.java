package login.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import login.svc.MemberListService;
import login.vo.ActionForward;
import login.vo.MemberBean;
import login.vo.PageInfo;

//관리자 마이페이지 라 생각
public class MemberListAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		ActionForward forward = null;
		int page=1;
		int limit=10;

		if(request.getParameter("page")==null || request.getParameter("page").equals("")) page = 1;
		else page=Integer.parseInt(request.getParameter("page"));
		
		MemberListService memberListService = new MemberListService();
		int listCount=memberListService.getListCount();
		
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
   	    
		
		if (id == null) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./memberLogin.me");
		} else if (!id.equals("admin")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("location.href='./Main.jsp';");
			out.println("</script>");
		} else {
			ArrayList<MemberBean> memberList = memberListService.getMemberList(page,limit);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("memberList", memberList);
			
			forward = new ActionForward();
			forward.setPath("./AdminMypage.jsp");
		}
		return forward;
	}
}