<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>선정도서관</title>

<link rel="stylesheet" href="login.css">
<style>
</style>
</head>
<body>
<jsp:include page="/header.jsp"></jsp:include>
	<div class="wrap" id="wrap">
		<form action="./findId.me" method="post">
			<h1>아이디 찾기</h1>
			<br>
			<p>  아이디는 가입시 입력하신 정보를 통해 찾을 수 있습니다.</p>
			<br>
			<table>
				<tr>
					<td><input type="text" name="nm" placeholder="사용자 이름"></td>
				</tr>
				<tr>
					<td><input type="tel" name="ph" placeholder="휴대폰 번호"></td>
				</tr>
			</table>
			<div class="btnwrap">
				<button type="submit" class="menu_btn">찾기</button>
			</div>
		</form>
	</div>
<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>