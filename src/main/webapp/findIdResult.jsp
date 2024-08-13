<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>선정도서관</title>
<link rel="stylesheet" href="login.css">
</head>
<body>
<%-- <jsp:include page="/header.jsp"></jsp:include> --%>


	<div class="wrap" id="wrap">
			<h2>아이디 찾기</h2>
	<table>
		<tr>
			<td colspan="2" class="an">가입하신 아이디는 ${fn:substring(id,0,3) }<c:forEach begin="3" end="${fn:length(id)-1}">*</c:forEach> 입니다.
			</td>
		</tr>
	</table>
		<div class="btnwrap">
			<button type="button" onclick="javascript:location.href='LoginForm.jsp'" class="menu_btn">로그인 하기</button>
        	<button type="button" onclick="javascript:location.href='findPw.jsp'" class="menu_btn">비밀번호 찾기</button>
		</div>
	</div>
<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>