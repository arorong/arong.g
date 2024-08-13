<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="borrow.vo.PageInfo"%>
<%@ page import="borrow.vo.SignVO"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%
ArrayList<SignVO> articleList = (ArrayList<SignVO>) request.getAttribute("articleList");
PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
int listCount = pageInfo.getListCount();
int nowPage = pageInfo.getPage();
int maxPage = pageInfo.getMaxPage();
int startPage = pageInfo.getStartPage();
int endPage = pageInfo.getEndPage();
long overDay = 0;
String pass = "";
String style = null;
String r_date = null;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>선정 도서관</title>
<script src="https://code.jquery.com/jquery-3.6.4.js"></script>
<!-- jQuery 추가 -->
<style>

div#name {
	font-size: 35px;
	padding: 30px 0px;
	text-align: center;
}

div {
	box-sizing: initial;
	display: flex;
	justify-content: center;
}

#table1 {
	width: 55%;
	border-collapse: collapse;
	text-align: center;
	margin: 0 auto;
	font-size: 14px;
}

th, td {
	height: 60px;
}

tr {
	border-bottom: 1px solid gray;
}

#pageList {
    width: 100%;
    margin: 50px 0px;
    display: flex;
    justify-content: center;
}

h1 {
	display: inline-block;
	width: 90vw;
	padding: 0 0 30px 0;
	border-bottom: 1px solid black;
}

.delayDiv1 {
	text-align: center;
}
a.button {
	/* display: inline; */
}

a#button {
	display: inline;
}
#button {
	display: block;
	height: 35px;
	padding: 5px 10px;
	text-decoration: none;
	background-color: black;
	color: #fff;
	text-align: center;
	border-radius: 5px;
}
.button {
    display: block;
    /* width: 75px; */
   	height: 35px;
    padding: 3px 7px;
    text-decoration: none;
    background-color: #000000;
    color: #fff;
    display: flex;
    justify-content: center;
    align-items: center;
    border-radius: 5px;
}

.button:hover {
    background-color: #D7B997;
    color: black;
}


section#emptyArea {
	height: 60vh;
	text-align: center;
	line-height: 60vh;
	font-size: 25px;
	font-weight: bold;
}





@media screen and (max-width: 768px) {
	#table1 {
		width: 90%;
		border-collapse: collapse;
		text-align: center;
		margin: 0 auto;
		font-size: 14px;
	}
}
</style>
</head>
<body>
	<jsp:include page="/header.jsp"></jsp:include>

	<section id="listFormW" style="position:relative;">
		<table id="table1">
			<%
			if (articleList.size() != 0) {
			%>
			<tr id="tr_top">
				<th>번호</th>
				<th>ID</th>
				<th>대출여부</th>
				<th>대출일</th>
				<th>반납예정일</th>
				<th>연장여부</th>
				<th>연체일</th>
			</tr>
			<%
			for (int i = 0; i < articleList.size(); i++) {
			%>
			<tr>
				<td><%=articleList.get(i).getNo()%></td>
				<td><%=articleList.get(i).getId()%></td>
				<%
				if (articleList.get(i).getB_pass() != null && articleList.get(i).getB_pass().equals("T")) {
					pass = "대출 중";

				} else if (articleList.get(i).getB_pass() != null && articleList.get(i).getB_pass().equals("F")) {
					pass = "반납";

				} else if (articleList.get(i).getB_pass() != null && articleList.get(i).getB_pass().equals("Y")) {
					pass = "연체";

				} else if (articleList.get(i).getB_pass() != null && articleList.get(i).getB_pass().equals("C")) {
					pass = "(연체) 반납";
				} else if (articleList.get(i).getB_pass() != null && articleList.get(i).getB_pass().equals("R")) {
					pass = "(연체) 반납 처리 중";
				} else {
					pass = "반납 처리 중";
				}
				%>
				<td><%=pass%></td>
				<td><%=articleList.get(i).getB_date()%></td>
				<td style="color: red;"><%=articleList.get(i).getR_due()%></td>
				<td><%=articleList.get(i).getExtend()%></td>
				<%
				if (articleList.get(i).getOver() != null && articleList.get(i).getOver().equals("0")) {
					Date r_due = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(articleList.get(i).getR_due());
					String r_dueS = new SimpleDateFormat("yyyyMMdd").format(r_due);
					r_due = new SimpleDateFormat("yyyyMMdd").parse(r_dueS);

					Date today = new Date();
					String todayS = new SimpleDateFormat("yyyyMMdd").format(today);
					today = new SimpleDateFormat("yyyyMMdd").parse(todayS);

					boolean over = today.getTime() > r_due.getTime() ? true : false;
					if (over) {
						overDay = (today.getTime() - r_due.getTime()) / (24 * 60 * 60 * 1000);
					} else {
						overDay = 0;
					}
				%>
				<td style="color: red;"><%=overDay%>일</td>
				<%
				} else {
				%>
				<td style="color: red;"><%=articleList.get(i).getOver()%>일</td>
				<%
				}
				%>
			</tr>

			<%
			}
			%>
		</table>
	</section>
	<%
	} else {
	%>
	<section id="emptyArea" style="position:relative;">연체자가 없습니다.</section>
	<%
	}
	%>
	<section id="pageList" style="position:relative;">
		<a class="button" href="./adminWork.jsp">뒤로가기</a>&nbsp;&nbsp;
		<%
		if (nowPage <= 1) {
		%>
		
		<%
		} else {
		%>
		<a class="button" href="delayL.ao?page=<%=nowPage - 1%>">이전</a>&nbsp;&nbsp;
		<%
		}
		%>

		<%
		for (int a = startPage; a <= endPage; a++) {
			if (a == nowPage) {
		%>
		<a class="button" id="button"><%=a%></a>&nbsp;&nbsp;
		<%
		} else {
		%>
		<a class="button" href="delayL.ao?page=<%=a%>"><%=a%>
		</a>&nbsp;&nbsp;
		<%
		}
		%>
		<%
		}
		%>

		<%
		if (nowPage >= maxPage) {
		%>
		
		<%
		} else {
		%>
		<a class="button" href="delayL.ao?page=<%=nowPage + 1%>">다음&nbsp;&nbsp;</a>
		<%
		}
		%>
	</section>
	
<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>