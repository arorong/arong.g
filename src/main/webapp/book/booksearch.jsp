<%@page import="bookIn.vo.BookBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="borrow.vo.PageInfo"%>
<%@ page import="borrow.vo.BookVO"%>
<%@ page import="borrow.vo.SignVO"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>

<%
ArrayList<BookBean> bookList = (ArrayList<BookBean>) request.getAttribute("bookList");
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
    <title>선정 도서관</title>
<style>
#pageList {
	border: 1px solid black;
}

table {
	width: 100%;
	border-collapse: collapse;
}

#tr_top {
	background-color: #e2e2e2;
}

th, td {
	padding: 20px 0px;
	border: 1px solid black;
}
</style>
<script>
	<%String count = request.getParameter("count");
if (count == null) {
	count = "0";
}
if (!count.equals("0")) {%>	alert("연체");
	<%}

	// 	if(signVO != null){
	// 		session.setAttribute("SignVO",null);
	// 		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(signVO.getR_due());
	// 		String newstring = new SimpleDateFormat("yyyy-MM-dd").format(date);%>
<%-- 	alert("대출되었습니다.\n<%=newstring %>까지 반납해 주세요."); --%>
<%-- 		<%} --%>
<%--  	%> --%>
	
</script>
</head>
<body>
	<jsp:include page="/book/header.jsp"></jsp:include>

	<h3>대출 목록</h3>
	<FORM action="bookSearch.ao">
		<SELECT name="col">
			<OPTION value="none">전체 목록</OPTION>
			<OPTION value="title">제목</OPTION>
			<OPTION value="author">저자</OPTION>
		</SELECT> <input type="text" name="word" value="" placeholder="검색어를 입력해주세요.">
		<button type="submit">검색</button>
	</FORM>



	<table>
		<%
		if (bookList != null) {
		%>
		<tr id="tr_top">
			<td align=center>번호</td>
			<td align=center>책제목</td>
			<td align=center>저자</td>
			<td align=center>장르</td>
			<td align=center>고유번호</td>
		</tr>
		<%
		for (int i = 0; i < bookList.size(); i++) {
		%>
		<tr>
			<td style="padding: 0px 10px"><%=bookList.get(i).getBookno()%></td>
			<td style="padding: 0px 10px"><a
				href="bookDetail.ao?no=<%=bookList.get(i).getNo()%>&page=<%=nowPage%>">
					<%=bookList.get(i).getTitle()%>
			</a></td>
			<td align=center><%=bookList.get(i).getAuthor()%></td>
			<td align=center><%=bookList.get(i).getGenre()%></td>
			<td align=center><%=bookList.get(i).getNo()%></td>
		</tr>
		<%
		}
		%>
		<tr>
			<td colspan="5" align=center><a
				href="<%=request.getContextPath()%>/Main.jsp">[홈으로]</a>&nbsp;

				<section id="pageList">
					<a href="<%=request.getContextPath()%>/Main.jsp">[홈으로]</a>&nbsp;
					<%
					if (nowPage <= 1) {
					%>
					[이전]&nbsp;
					<%
					} else {
					%>
					<a href="bookSearch.ao?page=<%=nowPage - 1%>&word=<%=request.getParameter("word")%>&col=<%=request.getParameter("col")%>">[이전]</a>&nbsp;
					<%
					}
					%>

					<%
					for (int a = startPage; a <= endPage; a++) {
						if (a == nowPage) {
					%>
					[<%=a%>]
					<%
					} else {
					%>
					<a href="bookSearch.ao?page=<%=a%>&word=<%=request.getParameter("word")%>&col=<%=request.getParameter("col")%>">[<%=a%>]
					</a>&nbsp;
					<%
					}
					%>
					<%
					}
					%>

					<%
					if (nowPage >= maxPage) {
					%>
					[다음]
					<%
					} else {
					%>
					<a href="bookSearch.ao?page=<%=nowPage + 1%>&word=<%=request.getParameter("word")%>&col=<%=request.getParameter("col")%>">[다음]</a>
					<%
					}
					%>
				</section></td>
		</tr>

	</table>
	<%
	} else {
	%>
	<section id="emptyArea">등록된 글이 없습니다.</section>
	<%
	}
	%>


</body>
</html>