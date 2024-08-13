<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>선정도서관</title>
<link rel="stylesheet" href="login.css">
<%
if(session.getAttribute("login") != null && session.getAttribute("login").equals("NO")){
	
	%>
	<script>
	alert('로그인 후 이용이 가능합니다.');
	</script>
	<% 
	session.setAttribute("login",null);
}
%>
</head>
<body>
	<jsp:include page="/header.jsp"></jsp:include>
	<div class="wrap" id="wrap">
		<form name="loginform" action="./memberLoginAction.me" method="post">

			<table>
				<tr>
					<td>
						<h1>로그인 페이지</h1>
					</td>
				</tr>
				<tr>
					<td><input type="text" name="id" id="id" placeholder="아이디" /></td>
				</tr>
				<tr>
					<td><input type="password" name="pw" id="pw"
						placeholder="비밀번호" /></td>
				</tr>
			</table>
			<!-- 				<br> -->
			<div class="btnwrap">
				<button type="button" class="menu_btn"
					onclick="javascript:loginform.submit()">로그인</button>
			</div>
		</form>

		<div class="atag">
			<a href="findId.jsp"><small>아이디 찾기</small></a>&nbsp;&nbsp;&nbsp; <a
				href="terms.jsp"><small>회원가입</small></a>&nbsp;&nbsp;&nbsp; <a
				href="findPw.jsp"><small>비밀번호 찾기</small></a>
		</div>

	</div>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>