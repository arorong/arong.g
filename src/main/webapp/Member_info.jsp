<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="login.dao.MemberDAO"%>
<%@ page import="login.vo.MemberBean"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>선정도서관</title>
<link rel="stylesheet" href="login.css">
</head>
<body>
<jsp:include page="/header.jsp"></jsp:include>
	<div class="wrap" id="wrap">
		<table>
			<tr>
				<td colspan=2><h1>${id }님 회원정보</h1></td>
			</tr>
				<td>회원번호</td>
				<td>${member.unm}</td>
			<tr>
				<td>아이디</td>
				<td>${member.id}</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				      <td>${fn:substring(member.pw,0,3)}<c:forEach begin="3"
               end="${fn:length(member.pw)-1}">*</c:forEach></td>
			</tr>
			<tr>
				<td>이름</td>
				<td>${member.nm}</td>
			</tr>
			<tr>
				<td>생년월일</td>
				<td>${member.birth}</td>
			</tr>
			<tr>
				<td>연락처</td>
				<td>${member.ph}</td>
			</tr>
			<tr>
				<td>이메일 주소</td>
				<td>${member.email}</td>
			</tr>
			<tr>
				<td>주소</td>
				<td>${member.addr},${member.addr1}(${member.postcode})</td>
			</tr>
			<tr>
				<td>활성화 여부</td>
				<td>${member.active}</td>
			</tr>
		</table>
		<div class="btnwrap">
			<button type="button" class="menu_btn" onclick="javascript:history.back()">뒤로가기</button>
			<button type="button" class="menu_btn" onclick="location.href='memberViewAction2.me?id=${member.id}';">회원정보수정</button>
		</div>
</div>
<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>