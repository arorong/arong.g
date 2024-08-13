<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="borrow.vo.PageInfo"%>
<%@ page import="borrow.vo.BookVO"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%-- <%@ page import="inform.vo.InformBean"%> --%>
<%@ page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<%
ArrayList<BookVO> articleList = (ArrayList<BookVO>) request.getAttribute("articleList");
PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
int listCount = pageInfo.getListCount();
int nowPage = pageInfo.getPage();
int maxPage = pageInfo.getMaxPage();
int startPage = pageInfo.getStartPage();
int endPage = pageInfo.getEndPage();
String pass = null;
String style = null;
long overDay = 0;
int max = 0;
int wCount = 0;
int yCount = 0;
int tCount = 0;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="dd.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.css" />
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<!-- jQuery 추가 -->
<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.10.3/gsap.js"></script>
<script src="https://unpkg.com/swiper@8/swiper-bundle.js"></script>
<meta charset="UTF-8">
<title>선정 도서관</title>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.js"></script>

<style>


div#name {
	font-size: 35px;
	padding: 30px 0px;
	position: relative;
	margin: 0 auto;
	/*     left: 30px; */
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

ul {
	box-sizing: initial;
	width: 800px;
	padding-left: 170px;
}

li {
	box-sizing: initial;
	list-style: none;
}

#butt {
	width: 80%;
	height: 40px;
	border-radius: 5px;
	border: 1px solid black;
	background-color: #7d6f5d40;
}

#pageList {
	width: 100%;
	margin: 50px 0px;
	text-align: center;
}

.borrowImg {
	width: 140px;
	height: 190px;
	position: absolute;
	top: 20px;
}

.navbar-brand img {
	width: 100px;
	height: auto;
}

section#emptyArea {
	height: 60vh;
	text-align: center;
	line-height: 60vh;
	font-size: 25px;
	font-weight: bold;
}

.delaycount {
	color: #9b2020;
	position: relative;
	left: 50px;
	border: 2px solid #9b2020;
	border-radius: 10px;
	font-size: 25px;
	padding: 2px;
	width: 120px;
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

img.borrowImg.b {
	position: relative;
	height: 30px;
	width: 30px;
}

p#boxP1 {
	position: relative;
	height: 10px;
	left: -50px;
	top: 8px;
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

span {
	display: inline-block;
	width: 30px;
	/* border: 0px solid black; */
	border-radius: 100px;
	background-color: #ff00008f;
	text-align: center;
	margin-left: 10px;
	color: white;
	font-weight: bold;
}

a#button {
	display: inline;
}

.button {
	display: block;
	width: 64px;
	height: 24px;
	padding: 5px 10px;
	text-decoration: none;
	background-color: black;
	color: #fff;
	text-align: center;
	border-radius: 5px;
}
a.button {
	display: inline;
}

.button {
	display: block;
	width: 64px;
	height: 24px;
	padding: 5px 10px;
	text-decoration: none;
	background-color: black;
	color: #fff;
	text-align: center;
	border-radius: 5px;
}
.button:hover {
	background-color: #d7b997;
    color: black;
    border-color: #fff;
}
}
@media screen and (min-width: 530px) and (max-width: 768px) {
	.div2 {
		width: 90%;
		padding-bottom: 50px;
	}
	#box {
		margin: 10px;
	}
	.ul2 {
		width: 70%;
	}
	img.borrowImg {
		margin-right: 50px;
	}
}

</style>

<script>
	$(function() {
		$('.ul2>td:eq(1)').children('a').bind('click', false);
		// 		$('#tr_W>td:eq(0)').children('a').css("color", "red");
		// 		$('#tr_W').find('a:eq(0)').css("color", "red");
		// 		$('#tr_W').find('a:eq(1)').unbind('click');

	});
	<%String success = (String) request.getAttribute("success");
String day = (String) request.getAttribute("day");
if (success == null) {
} else if (success.equals("over")) {%>
	      alert("반납 예정일이 넘은 책은 연장할 수 없습니다.");
	   <%} else if (success.equals("T")) {
	if (day != null) {
		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(day);
		String newstring = new SimpleDateFormat("yyyy-MM-dd").format(date);%>
	         alert("연장되었습니다.\n반납 예정일은 <%=newstring%>
	입니다.");
<%}
} else {%>
	alert("연장 할 수 없습니다.책 연장은 한 번만 가능합니다.");
<%}%>
	// 	   $(function(){
	// 		 var pass = $('.ul2>li:eq(4)').text(); 
	// 		 alert(pass);
	// 		 console.log(pass);
	// 		 if($(pass) == '연체 중'){
	// 			console.log(pass);
	// 			 $('.ul2').children('li:eq(4)').css('color','led');
	// 		 }
	// 	   });
</script>
</head>
<body>
	<jsp:include page="/header.jsp"></jsp:include>

	<%
		if (articleList.size() != 0) { // if
			for (int i = 0; i < articleList.size(); i++) {  
				pass = articleList.get(i).getB_pass();
				if (pass != null && pass.equals("W") || pass.equals("R")) {
					//반납신청권수
						wCount++;
							if(pass.equals("R")){yCount++;}
						} else if (pass != null && pass.equals("Y") || pass.equals("R") || pass.equals("C")) {
							
							if(pass.equals("Y")){tCount++;}
						//연체권수
							yCount++;
						}else if(pass != null && pass.equals("T") || pass.equals("Y")){
							
						//현재대출권수
							tCount++;
						}
					}
				}
								
	%>

	<div id='boxFlex'>
		<div id='box'>
			<!-- book1 : 대출권수   book2 : 연체권수   book3 : 반납대기권수 -->
			<img class="borrowImg b" src='./img/book1.png'>
			<p id="boxP1">
				대출권수<span><%=tCount%></span>
			</p>
			<img class="borrowImg b" src='./img/book2.png'>
			<p id="boxP2">
				연체권수<span><%=yCount%></span>
			</p>
			<img class="borrowImg b" src='./img/book3.png'>
			<p id="boxP3">
				반납대기중<span><%=wCount%></span>
			</p>
		</div>
	</div>


	<section id="listForm" style="position: relative;">
		<%
		if (articleList.size() != 0) { // if
			System.out.println("articleList : " + articleList);
			for (int i = 0; i < articleList.size(); i++) {

				Date b_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(articleList.get(i).getB_date());
				String b_dateS = new SimpleDateFormat("yyyyMMdd").format(b_date);

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

				r_dueS = new SimpleDateFormat("yyyy/MM/dd").format(r_due);
				b_dateS = new SimpleDateFormat("yyyy/MM/dd").format(b_date);
		%><div>
			<%
			if (articleList.get(i).getB_pass() != null && articleList.get(i).getB_pass().equals("W")) {
				pass = "반납 처리 중";
				style = "#808080";
			%>
			<div class="div2">
				<%
				} else if (articleList.get(i).getB_pass() != null && articleList.get(i).getB_pass().equals("F")) {
				pass = "반납 완료";
				style = null;
				%>
				<div class="div2">
					<%
					} else if (articleList.get(i).getB_pass() != null && articleList.get(i).getB_pass().equals("C")) {
					pass = "(연체) 반납 완료";
					style = "#f00";
					%>
					<div class="div2">
						<%
						} else if (articleList.get(i).getB_pass() != null && articleList.get(i).getB_pass().equals("R")) {
						pass = "(연체) 반납 처리 중";
						style = "#808080";
						%>
						<div class="div2">
							<%
							} else if (overDay != 0) {
							pass = "연체 중";
							style = "#f00";
							%>
							<div class="div2">
								<%
								} else {
								pass = "대출 중";
								style = "#008000";
								%>
								<div class="div2">
									<%
									}
									%>

									<img class="borrowImg"
										src="./bookcoverupload/<%=articleList.get(i).getBookFile()%>">
									<ul class="ul2">
										<li>번호: <%=articleList.get(i).getBookno()%> &nbsp;&nbsp;
											책제목: <%=articleList.get(i).getTitle()%></li>
										<li>저자: <%=articleList.get(i).getAutho()%> &nbsp;&nbsp;
											장르: <%=articleList.get(i).getGenre()%>
										</li>
										<li>고유번호: <%=articleList.get(i).getNo()%> &nbsp;&nbsp;
											대여일자: <%=b_dateS%></li>
										<li>반납예정일: <%=r_dueS%></li>
										<li <%if (style != null) {%> style="color:<%=style%>" <%}%>>상태:
											<%=pass%></li>
										<li>연체일: <%
										if (pass != null && pass.equals("(연체) 반납 완료") && Integer.parseInt(articleList.get(i).getOver()) > 0) {
										%>
											<%=articleList.get(i).getOver()%>일 <%
											} else if (pass.equals("반납 완료")) {
											%>
											0일 <%
											} else {
											%> <%=overDay%>일 <%
 }
 %>
										</li>
										<li class="liButton">
											<%
											if (pass != null && pass.equals("반납 처리 중") || pass.equals("(연체) 반납 처리 중")) {
											%>
											<a style="color: gray"> 반납하기 </a>&nbsp;&nbsp; <a
											style="color: gray"> 연장하기 </a> <%
 } else if (pass != null && pass.equals("(연체) 반납 완료") || pass.equals("반납 완료")) {
 %>

											<%
											} else {
											%> <a class="button"
											href="bookReturn.ao?id=<%=session.getAttribute("id")%>&no=<%=articleList.get(i).getNo()%>&b_pass=<%=articleList.get(i).getB_pass()%>">반납하기</a>&nbsp;&nbsp;
											<a class="button"
											href="borrowExtend.ao?id=<%=session.getAttribute("id")%>&no=<%=articleList.get(i).getNo()%>"
											id='borrowExtend'>연장하기</a> <%
 }
 %>
										</li>
									</ul>
								</div>
							</div>
							<%
							}
							%>
						<br>
	</section>
	<%
	} else {
	System.out.println("articleList : " + articleList);
	%>
	<section id="emptyArea" style="position: relative;">대출한 책이
		없습니다.</section>
	<%
	}
	%>
	<section id="pageList" style="position: relative;">
		<a class="button" href="<%=request.getContextPath()%>/UserMypage.jsp">뒤로가기</a>&nbsp;
		<%
		if (nowPage <= 1) {
		%>
	
		<%
		} else {
		%>
		<a class="button" href="borrowList.ao?page=<%=nowPage - 1%>">이전</a>&nbsp;
		<%
		}
		%>
		
		<%
		for (int a = startPage; a <= endPage; a++) {
			if (a == nowPage) {
		%>
		<a class="button" id="button"><%=a%></a>&nbsp;
		<%
		} else {
		%>
		<a class="button" href="borrowList.ao?page=<%=a%>"><%=a%> </a>&nbsp;
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
		<a class="button" href="borrowList.ao?page=<%=nowPage + 1%>">다음</a>
		<%
		}
		%>
	</section>
	
<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>