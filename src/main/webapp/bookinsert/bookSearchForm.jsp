<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>선정 도서관</title>
</head>
<body>
 	<jsp:include page="/header.jsp"></jsp:include>
  <FORM action="searchbook.bok">
      <SELECT name="col"> 
        <OPTION value="none">전체 목록</OPTION>
        <OPTION value="title">제목</OPTION>
        <OPTION value="author">저자</OPTION>
      </SELECT>
      <input type="text" name="word" value="" placeholder="검색어를 입력해주세요.">
      <button type="submit">검색</button><br><br>
	  <input type="button" onclick="cancle()" value="뒤로 가기" >
  </FORM>


	<script>
    function cancle() {
        window.location.href = "bookIndex.jsp"; // 뒤로 가기 시 bookIndex.jsp로 이동
    }
    
	</script>

    
</body>
</html>