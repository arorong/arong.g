<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="login.vo.MemberBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>선정 도서관</title>
<style>
#memberListArea {
	width: 500px;
	border: 1px solid gray;
	margin: auto;
}

table {
	width: 500px;
	margin: auto;
	text-align: center;
}

td {
	border: 1px solid gray;
}
</style>

</head>
<body>
	<section id="memberListArea">
		<table>
			<tr>
				<td colspan="2"><h1>회원관리</h1></td>
			</tr>

			<tr>
				<td>회원ID</td>
				<td>관리메뉴</td>
			</tr>

			<c:forEach var="member" items="${memberList}">
				<tr>
					<td>${member.id}</td>
					<td><a href="memberViewAction.me?id=${member.id}">상세보기 </a><%="<br>"%>
						<a href="memberModify.me?id=${member.id}">정보수정</a><%="<br>"%> <a
						href="memberDeleteAction.me?id=${member.id}">강제탈퇴</a><%="<br>"%></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan=2><a href="javascript:history.go(-1)">뒤로가기</a></td>
			</tr>
			<tr>
				<td colspan=2><a href="logout">로그아웃</a></td>
			</tr>
			<tr>
				<td colspan=2><a href="Main.jsp">메인화면</a></td>
			</tr>
		</table>
	</section>
</body>
</html>