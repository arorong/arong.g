<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*, java.text.SimpleDateFormat, bookIn.vo.PageInfo, bookIn.vo.BookBean"%>
<html>
<head>
<title>도서 목록</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.js"></script>
	<style>
		td:hover:nth-of-type(3) { cursor: pointer; text-decoration: underline; }
		body {
	    		font-family: 'BookkMyungjo-Bd';
    			src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2302@1.0/BookkMyungjo-Bd.woff2') format('woff2');
    			font-weight: 700;
    			font-style: normal;
			}
	</style>
</head>
<body>
<script>
	function view(no){
		location.href='bookDetail.bok?no=' + no;
	}
	
	function confirmDelete(no) {
            var confirmAction = confirm("정말 삭제하시겠습니까?");
            if (!confirmAction) {
                alert("삭제가 취소되었습니다.");
            } else {
               location.href='bookDelete.bok?no=' + no;
            }
        }
	
		function seachfnc() {
  			if(seachFrm.word.value == "") {
  				alert("검색어를 입력해 주세요!");
  				seachFrm.word.focus();
  			} else {
  				seachFrm.submit();
  			}
  		}
</script>
<div class="container mt-3">
    <title>선정 도서관</title>
     <form name="seachFrm" action="searchbook.bok">
		<input type="text" name="word" value="" placeholder="검색어를 입력해주세요.">
		<button type="button"  onclick="seachfnc()">검색</button>
		<button type="button"  onclick="location.href='bookList.bok';">전체목록보기</button><br><br>
  	</form>
    
    <table class="table table-bordered table-hover">
        <thead class="thead-dark">
            <tr>
            	<th>번호</th>
                <th>유형</th>
                <th>제목</th>
                <th>저자</th>
                <th>발행사항</th>
                <th>청구기호</th>
                <th>수정 및 삭제</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="book" items="${bookList}">
                <tr>
                	<td><c:out value="${book.no}" /></td>
                    <td><c:out value="${book.genre}" /></td>
                    <td onclick="view('${book.no}')"><c:out value="${book.title}"/></td>
                    <td><c:out value="${book.author}"/></td>
                    <td><c:out value="${book.pub}"/></td>
                    <td><c:out value="${book.bookno}"/></td>
                    <td>
                        <a href="bookModify.bok?no=${book.no}" class="btn btn-primary">수정</a>
                        <a href="javascript:confirmDelete('${book.no}');" class="btn btn-danger">삭제</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <nav>
	    <ul class="pagination justify-content-center">
	    <!-- '처음'과 '이전' 페이지로의 링크 -->
	        <c:if test="${pageInfo.page > 1}">
	            <li class="page-item">
	                <a class="page-link" href="bookListUser.bok?page=1&word=${param.word}">처음</a>
	            </li>
	            <li class="page-item">
	                <a class="page-link" href="bookListUser.bok?page=${pageInfo.page - 1}&word=${param.word}">이전</a>
	            </li>
	        </c:if>
	        
	        <!-- 현재 보여지는 페이지 범위 내의 페이지 번호들을 나열 -->
	        <c:forEach begin="${pageInfo.startPage}" end="${pageInfo.endPage}" var="i">
	            <li class="page-item ${pageInfo.page == i ? 'active' : ''}">
	                <a class="page-link" href="bookListUser.bok?page=${i}&word=${param.word}">${i}</a>
	            </li>
	        </c:forEach>
	        
	        <!-- '다음'과 '마지막' 페이지로의 링크 -->
	        <c:if test="${pageInfo.page < pageInfo.maxPage}">
	            <li class="page-item">
	                <a class="page-link" href="bookListUser.bok?page=${pageInfo.page + 1}&word=${param.word}">다음</a>
	            </li>
	            <li class="page-item">
	                <a class="page-link" href="bookListUser.bok?page=${pageInfo.maxPage}&word=${param.word}">마지막</a>
	            </li>
	        </c:if>
	    </ul>
	</nav>
    
    <a href="main.bok">도서관리 페이지로</a>
</div>

</body>
</html>