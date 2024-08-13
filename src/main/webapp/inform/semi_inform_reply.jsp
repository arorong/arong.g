<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="inform.vo.InformBean"%>
<%
	InformBean article=(InformBean)request.getAttribute("article");
    String nowPage = (String)request.getAttribute("page");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
    <title>선정 도서관</title>
<script language="javascript">
	</script>
<style type="text/css">
/* #registForm { */
/* 	width: 500px; */
/* 	height: 610px; */
/* 	border: 1px solid red; */
/* 	margin: auto; */
/* } */

/* h2 { */
/* 	text-align: center; */
/* } */

/* table { */
/* 	margin: auto; */
/* 	width: 450px; */
/* } */

/* .td_left { */
/* 	width: 150px; */
/* 	background: orange; */
/* } */

/* .td_right { */
/* 	width: 300px; */
/* 	background: skyblue; */
/* } */

/* #commandCell { */
/* 	text-align: center; */
 } 
</style>
</head>
<body>
	<!-- 게시판 답변 -->


	<section id="writeForm">
		<h2>게시판글등록</h2>
		<form action="informReplyPro.bo" method="post" name="informform">
			<input type="hidden" name="page" value="<%=nowPage %>" >
			<input type="hidden" name="inform_NUM" value="<%=article.getInform_NUM() %>">
			<input type="hidden" name="inform_RE_REF" value="<%=article.getRE_REF() %>">
			<input type="hidden" name="inform_RE_LEV" value="<%=article.getRE_LEV() %>">
			<input type="hidden" name="inform_RE_SEQ" value="<%=article.getRE_SEQ() %>">
			<table>
				<tr>
					<td class="td_left"><label for="inform_NAME">글쓴이</label></td>
					<td class="td_right"><input type="text" name="inform_NAME" id="inform_NAME" ></td>
				</tr>
				<tr>
					<td class="td_left"><label for="inform_PASS">비밀번호</label></td>
					<td class="td_right"><input name="inform_PASS" type="password" id="inform_PASS" ></td>
				</tr>
				<tr>
					<td class="td_left"><label for="inform_SUBJECT">제 목</label></td>
					<td class="td_right"><input name="inform_SUBJECT" type="text" id="inform_SUBJECT" ></td>
				</tr>
				<tr>
					<td class="td_left"><label for="inform_CONTENT">내 용</label></td>
					<td><textarea id="inform_CONTENT" name="inform_CONTENT" cols="40" rows="15"></textarea></td>
				</tr>
			</table>
			<section id="commandCell">
				<input type="submit" value="답변글등록">&nbsp;&nbsp;
				<input type="reset" value="다시작성">
			</section>
		</form>
	</section>
</body>
</html>