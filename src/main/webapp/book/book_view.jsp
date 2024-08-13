<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="borrow.vo.BookVO"%>
<%@page import="borrow.vo.SignVO"%>
<%
BookVO article = (BookVO) request.getAttribute("article");
SignVO member = (SignVO) request.getAttribute("id");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>선정 도서관</title>
<script>src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.js"></script>
<style>
	section{
		border : 1px solid black;
	}
	
	table{
		width:100%;
		border-collapse:collapse;
	}
	
	th,td{
		border : 1px solid black;
	}
</style>
</head>
<body>
<jsp:include page="/book/header.jsp"></jsp:include>

	<section id="articleForm">
		<h2>글 내용 상세보기</h2>
		<table>
			<tr><td rowspan=8 width=180px align=center> 이미지 </td></tr>
			<tr>
				<th>제 목 </th><td style="padding:0px 10px"><%=article.getTitle()%></td>
			</tr>
			<tr>
				<th>장 르 </th><td style="padding:0px 10px"><%=article.getGenre()%></td>
			</tr>
			<tr>
				<th>저 자 </th><td style="padding:0px 10px"><%=article.getAutho()%></td>
			</tr>
			<tr>
				<th>출 판 사 </th><td style="padding:0px 10px"><%=article.getPub()%></td>
			</tr>
			<tr>
				<th>고 유 번 호 </th><td style="padding:0px 10px"><%=article.getNo()%></td>
			</tr>
			<tr>
				<th>번 호 </th><td style="padding:0px 10px"><%=article.getBookno()%></td>
			</tr>
			      <tr>
            <th colspan=2>
                <a href="<%=request.getContextPath() %>/Main.jsp">[홈으로]</a>&nbsp; 
				<a href="bookList.ao?page=${page }"> [목록] </a>&nbsp;&nbsp;
				<%if(article.getBsign().equals("Y")){ %>
				<a href="bookBorrowForm.ao?id=<%=session.getAttribute("id") %>&no=<%=article.getNo()%>"> [대출하기] </a>
				<%} else { %>
				[대출불가도서]
				<%} %>
            </th>
         </tr>
		</table>
	</section>
</body>
</html>