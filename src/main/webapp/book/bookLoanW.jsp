<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="borrow.vo.PageInfo"%>
<%@ page import="borrow.vo.SignVO"%>
<%@ page import="java.util.*"%>
<%
ArrayList<SignVO> articleList = (ArrayList<SignVO>) request.getAttribute("articleList");
PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
int listCount = pageInfo.getListCount();
int nowPage = pageInfo.getPage();
System.out.println("nowPage: " + nowPage);
int maxPage = pageInfo.getMaxPage();
System.out.println("maxPage: " + maxPage);
int startPage = pageInfo.getStartPage();
int endPage = pageInfo.getEndPage();
String pass = "";
int wCount = 0;
int yCount = 0;
int tCount = 0;
%>
<!DOCTYPE html>
<html>
    <title>선정 도서관</title>

<head>
<style>
div#name {
	font-size: 35px;
	padding: 30px 0px;
	position: relative;
	margin:0 auto;
/* 	left: 30px; */
}

div {
	box-sizing: initial;
	display: flex;
	justify-content: center;
}

.div2 {
	box-sizing: initial;
	display: inline-block;
	border-bottom: 1px solid gray;
	line-height: 30px;
	position: relative;
	height: 240px;
}

.borrowImg {
	width: 140px;
	height: 190px;
	position: absolute;
	top: 20px;
}

#pageList {
	width: 100%;
	margin: 50px 0px;
	text-align: center;
}

#table1 {
	width: 70%;
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

section#emptyArea {
	height: 60vh;
	text-align: center;
	line-height: 60vh;
	font-size: 25px;
	font-weight: bold;
}

h1 {
	display: inline-block;
	width: 90vw;
	padding: 0 0 30px 0;
	border-bottom: 1px solid black;
}

div#boxFlex {
	display: flex;
	justify-content: center;
}

div#box {
	width: 970px;
	height: 70px;
	border: 1.5px solid gray;
	border-radius: 10px;
	z-index: 10;
	justify-content: space-around;
	margin-top: 10px;
}

.loanDiv1 {
	text-align: center;
}

img.borrowImg.b {
	position: relative;
	height: 30px;
	width: 30px;
}

p#boxP2 {
	position: relative;
	height: 10px;
	left: -50px;
	top: 8px;
}

p#boxP3 {
	position: relative;
	height: 10px;
	left: -50px;
	top: 8px;
}

span.count {
	display: inline-block;
	width: 30px;
	/* border: 0px solid black; */
	border-radius: 100px;
	background-color: #ff00008f;
	text-align: center;
	align-items:center;
	margin-left: 10px;
	color: white;
	font-weight: bold;
}

#listFormW {
	position: relative;
	z-index: 10;
}

#LoanB {
    width: 64px;
    height: 24px;
    text-decoration: none;
    color: white;
    margin: 0 auto;
    background-color: #ff7070;
    text-align: center;
    border-radius: 10px;
    display: flex;
    align-items: center;
    justify-content: center;
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
#back {
	/* margin-right: 50px; */
}

@media screen and (max-width: 768px) {
	.div2 {
		width: 90%;
	}
	#box {
		margin: 10px;
	}

	
	#table1 {
	width: 90%;
   
	
}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<!-- jQuery 추가 -->
<meta charset="UTF-8">
<title>선정 도서관</title>
</head>
<body>
	<jsp:include page="/header.jsp"></jsp:include>

	<%
	if (articleList.size() != 0) { // if
		for (int i = 0; i < articleList.size(); i++) {
			pass = articleList.get(i).getB_pass();
			
			 if (pass != null && pass.equals("R")) {
			//연체권수
			yCount++;
			} else if (pass != null && pass.equals("W")) {
			//현재대출권수
			tCount++;
			}
		}
	}
	%>

	<div id='boxFlex'>
		<div id='box'>
			<img class="borrowImg b" src='./img/book2.png'>
			<p id="boxP2">
				 (연체)반납대기중<span class="count"><%=yCount%></span>
			</p>
			<img class="borrowImg b" src='./img/book3.png'>
			<p id="boxP3">
				 반납대기중<span class="count"><%=tCount%></span>
			</p>
		</div>
	</div>

	
	<section id="listFormW" style="position:relative;">

		<table id="table1" class="table1">
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
				<th>반납확인</th>
			</tr>
			<%
			for (int i = 0; i < articleList.size(); i++) {
			%>

			<tr>
				<td><%=articleList.get(i).getNo()%></td>
				<td><%=articleList.get(i).getId()%></td>
				<%
				if (articleList.get(i).getB_pass() != null && articleList.get(i).getB_pass().equals("R")) {
					pass = "(연체)반납처리중";
				%>
				<%
				} else {
					pass = "반납처리중";
				}
				%>
				<td><%=pass%></td>
				<td><%=articleList.get(i).getB_date()%></td>
				<td><%=articleList.get(i).getR_due()%></td>
				<td><%=articleList.get(i).getExtend()%></td>
				<td><a id="LoanB"
					href="deleteWait.ao?no=<%=articleList.get(i).getNo()%>&b_pass=<%=articleList.get(i).getB_pass()%>">확인</a></td>
			</tr>

			<%
			}
			%>
		</table>
	</section>
	<%
	} else {
	%>
	<section id="emptyArea" style="position:relative;">반납된 책이 없습니다.</section>
	<%
	}
	%>
	<section id="pageList" style="position:relative; z-index:1000px">
		<a class="button" id="back" href="./adminWork.jsp">뒤로가기</a>&nbsp;&nbsp;
		<%
		if (nowPage <= 1) {
		%>
		
		<%
		} else {
		%>
		<a class="button" href="borrowList.ao?page=<%=nowPage - 1%>">이전</a>&nbsp;&nbsp;
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
		<a class="button" href="borrowList.ao?page=<%=a%>"><%=a%>
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
		<a class="button" href="borrowList.ao?page=<%=nowPage + 1%>">다음&nbsp;&nbsp;</a>
		<%
		}
		%>
	</section>


<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>