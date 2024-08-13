<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="inform.vo.InformBean"%>
<%
	InformBean article = (InformBean)request.getAttribute("article");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> 
	<title>선정도서관</title>
	
<script type="text/javascript">
	function modifyinform(){
		modifyform.submit();
	}
</script>
	<style type="text/css">
   @import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap');



 * {
 			position : relative;
            font-family: 'Noto Sans KR',sans-serif !important;
            box-sizing: border-box;
            font-weight: 500;
     }
  
#writeForm {
	width: 60%;
    height: auto;
    margin: 20px auto;
    border-radius: 10px;
    padding: 20px;
    box-sizing: border-box;
}

h2 {
	text-align: center;
}

table {
    margin: auto;
    width: 100%;
    border-collapse: collapse;
}

td {
    padding: 10px;
    border-bottom: 1px solid #ccc;
}

.td_left {
    width: 30%;
    text-align: center;
    font-weight: bold;
}

.td_right {
    width: 70%;
}

input[type="text"],
input[type="password"],
textarea {
    width: 100%;
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 5px;
    box-sizing: border-box;
}

input[type="file"] {
    border: none;
}

textarea {
	resize: none;
}

#commandCell {
	text-align: center;
	margin-top: 20px;
}

.wow{
	margin: 10px;
	padding:5px 30px;
    background-color:black;
    text-decoration: none;

	color:#fff;
}

.wow:hover{
    text-decoration: none;
    background-color: #D7B997;
    color: black;
    border-color: none;
}

</style>
</head>
<body>
<!-- 게시판 등록 -->

<jsp:include page="/header.jsp"></jsp:include>

<section id = "writeForm">
    <title>선정 도서관</title>
<form action="informModifyPro.bo" method="post" name = "modifyform" >
<input type = "hidden" name = "inform_NUM" value = "<%=article.getInform_NUM()%>">
<input type = "hidden" name = "page" value = "${page }">
<table>
	<tr>
		<td class="td_left">
			<label for = "inform_NM">글쓴이</label>
		</td>
		<td class="td_right">
			<input type="text" name="inform_NM" id = "inform_NM" value = "<%=article.getInform_NM()%>" readonly>
		</td>
	</tr>
	<tr>
<!-- 		<td class="td_left"> -->
<!-- 			<label for = "inform_pass">비밀번호</label> -->
<!-- 		</td> -->
<!-- 		<td class="td_right"> -->
<!-- 			<input name="inform_pass" type="text" id = "inform_pass"> -->
<!-- 		</td> -->
	</tr>
	<tr>
		<td class="td_left">
			<label for = "inform_TITLE">제 목</label>
		</td>
		<td class="td_right">
			<input name="inform_TITLE" type="text" id = "inform_TITLE" value = "<%=article.getInform_TITLE()%>">
		</td>
	</tr>
	<tr>
		<td class="td_left">
			<label for = "inform_TEXTS">내 용</label>
		</td>
		<td>
			<textarea id = "inform_TEXTS" name="inform_TEXTS" cols="40" rows="15"><%=article.getInform_TEXTS()%></textarea>
		</td>
	</tr>
</table>
	<section id = "commandCell">
			<a class="wow" href="javascript:modifyinform()">수정</a>&nbsp;&nbsp;
			<a class="wow" href="javascript:history.go(-1)">뒤로</a>
	</section>
</form>
</section>
</body>
</html>