<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>선정 도서관</title>
<link rel="stylesheet" href="login.css">
</head>
<body>
<jsp:include page="/header.jsp"></jsp:include>
	<div class="wrap" id="wrap">
		<h3>
			비밀번호를 찾고자하는 <br>아이디와 정보를 입력해주세요.
		</h3>
		<form action="./findPw.me" method="post">
			<table>
				<tr>
					<td><input type="text" id="id" name="id" placeholder="아이디"></td>
				</tr>
				<tr>
					<td><input type="text" id="nm" name="nm" placeholder="이름"></td>
				</tr>
				<tr>
					<td><input type="text" id="ph" name="ph" placeholder="휴대폰 번호"></td>
				</tr>
			</table>
			<div class="btnwrap">
				<button type="submit" class="menu_btn">다음</button>
			</div>
		</form>
		<div class="atag">
			<small>아이디가 기억나지 않으신다면? &nbsp;&nbsp;&nbsp; 
			<a href="findId.jsp">아이디 찾기</a></small>
		</div>
	</div>
<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>