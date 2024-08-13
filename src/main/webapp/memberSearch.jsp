<%@page import="bookIn.vo.BookBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="login.vo.PageInfo"%>
<%@ page import="login.vo.MemberBean"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
    
<%
ArrayList<MemberBean> memberList = (ArrayList<MemberBean>) request.getAttribute("memberList");
PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
int listCount = pageInfo.getListCount();
int nowPage = pageInfo.getPage();
int maxPage = pageInfo.getMaxPage();
int startPage = pageInfo.getStartPage();
int endPage = pageInfo.getEndPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>선정도서관</title>
<link rel="stylesheet" href="login.css">
<style>
.pageList {
    text-align:center;
}
input:hover[type="submit"] {
   background-color: #D7B997;
   color: black; 
   border: none;
}
 #td1{align:center; width:auto;
}
</style>
</head>
<body>
<jsp:include page="/header.jsp"></jsp:include>
<div class="wrap">
	<h3>유저 목록</h3>
	<div class="search" style="margin:10px 0 10px 0; ">
          <form action="search.me" method="post">
             <div class="scwrap">
                ​​​​​​​​​​​​<select name="option">
                   <option value="id">아이디</option>
                   <option value="nm">이름</option>
                   <option value="ph">휴대폰번호</option>
                   <option value="unm">회원번호</option>
                </select> &nbsp;&nbsp; 
                <input type="search" class="search" name="search" placeholder="검색어를 입력해주세요."> 
                <input type="submit" class="search1" id="search" value="검색">
             </div>
          </form>
     </div>
		<table>
		<%if(memberList != null) { %>
				<tr id="tr_top">
				<td id="td1" >고유번호</td>
				<td id="td1">아이디</td>
				<td id="td1">이름</td>
				<td id="td1">휴대폰번호</td>
				<td id="td1">회원상태</td>
				<td id="td1">더보기</td>
			</tr>
			<%
			for (int i = 0; i < memberList.size(); i++) {
			%>
			<tr>
				<td  id="td1"><%=memberList.get(i).getUnm()%></td>
				<td  id="td1"><%=memberList.get(i).getId()%></td>
				<td  id="td1"><%=memberList.get(i).getNm()%></td>
				<td  id="td1"><%=memberList.get(i).getPh()%></td>
				<td  id="td1"><%=memberList.get(i).getActive()%></td>
				<td  id="td1"><a href="memberViewAction.me?id=<%=memberList.get(i).getId()%>">더보기</a>
				</td>
			</tr>
			<%	}	%>
			</table><br>		
			
		<div class="pageList" id="pageList" style="position:relative;">
		<%
		if (nowPage <= 1) {
		%>
		[이전]&nbsp;
		<%	} else {	%>
		<a href="search.me?page=<%=nowPage - 1%>&search=<%=request.getParameter("search")%>">[이전]</a>&nbsp;
		<%	}	%>

		<% for (int a = startPage; a <= endPage; a++) {
			if (a == nowPage) {
		%>
		<a class="button"><%=a%></a>&nbsp;&nbsp;
		<%	} else {
		%>
		<a href="search.me?page=<%=a%>&search=<%=request.getParameter("search")%>">[<%=a%>]
		</a>&nbsp;
		<%	}	%>
		<%}	%>

		<%	if (nowPage >= maxPage) {
		%>	[다음]
		<%	} else {
		%>	<a href="search.me?page=<%=nowPage + 1%>&search=<%=request.getParameter("search")%>">[다음]</a>
		<%	}
		%>
		<%	} else {	%>
		<section id="emptyArea">검색된 유저가 없습니다.</section>
		<%	}	%>
		</div><br>
		<div class="atag">
        	<button type="button" class="menu_btn" onclick="javascript:history.back()">뒤로가기</button>
        </div>	
	</div>
<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>