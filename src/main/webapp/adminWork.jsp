<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="login.vo.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="common/cacheRemove.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>선정도서관</title>

<link rel="stylesheet" href="login.css">
<style>
 	#wrap {background-color: #fff; border:none;} 	
 	.menu_btn {margin: 0 auto;}
 	.atag{text-align: center;}
 	#hidenarea{visibility:hidden;padding: 0px; margin: 0px;}
 	
.boxWork {
    height: 100px;
    border-bottom: 1px solid black;
    margin-top: 30px;
    line-height: 30px;
    display: flex;
    justify-content: center;
    font-weight: 500;  
    width: 600px;
    flex-direction: column;
    padding-left: 30px;
    padding-bottom: 30px;
}

.a1{
     font-weight: 500; 
    text-decoration: none;
    color: black;
    display: block;
/*     width: 200px; */
}
.a2{
 	font-weight: 500; 
    text-decoration: none;
    color: black;
    display: block;
/*     width: 200px; */
}

.boxWorkIn {
    margin-bottom: 13px;
}

div#mainBox {
	display: flex;
	flex-direction: column;
	align-items: center;
}

@media screen and (max-width: 768px) {

	.boxWork {
		width: 70%;
	}
	.boxWork {
		padding-left: 20px;
	}
	div#mainBox {
		display: flex;
		flex-direction: column;
		align-items: center;
	}
	.a2 {
		   width: auto;
    margin-left: 5%;
        width: 80%;
	}
	
	.a1 {
		   width: auto;
    margin-left: 5%;
        width: 80%;
	}
	.wrap {
		width: 100%;
	}
}

</style>
</head>
<body>
	<jsp:include page="/header.jsp"></jsp:include>

	<div class="wrap" id="wrap">

		<h1>도서관 관리 페이지</h1>
		<div id="mainBox">
			<c:set var="id" value="${sessionScope.active }" />
			<c:choose>
				<c:when test="${active eq 'S' }">
					<div class="boxWork" style="height: 70px;">
						<div class="boxWorkIn">MEMBER</div>
						<a class="a1" href="memberListAction.me">&gt; 회원관리 </a>
					</div>
				</c:when>
				<c:otherwise>
					<div class="boxWork" style="height: 70px;">
						<div class="boxWorkIn">MEMBER</div>
						<a class="a1" style="color: gray;">&gt; 회원관리 </a>
					</div>
				</c:otherwise>
			</c:choose>
		
	<div class="boxWork">
		<div class="boxWorkIn">BOOK</div>
		<a class="a1" href="bookUpForm.bok">&gt; 도서등록하기</a> <a class="a2"
			href="bookListAdmin.bok">&gt; 도서목록보기(수정, 삭제)</a>
	</div>

	<div class="boxWork">
		<div class="boxWorkIn">BORROW</div>
		<a class="a1" href="borrowList.ao?id=${id}">&gt; 대출반납확인</a> <a
			class="a2" href="delayL.ao">&gt; 연체자관리</a>
	</div>

	<div class="boxWork">
		<div class="boxWorkIn">BOARD</div>
			<a class="a1" href="informWriteForm.bo">&gt; 공지등록</a>
			<a class="a2" href="informList2.bo">&gt; 게시판관리</a>
	</div>
	</div>
	<br>

	</div>
<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>