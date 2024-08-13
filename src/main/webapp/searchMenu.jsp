<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>선정 도서관</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.js"></script>
</head>
<body>
	<jsp:include page="/header.jsp"></jsp:include>

<div class="container mt-3">
    <h2>도서 목록</h2>
    <table class="table table-bordered table-hover">
        <thead class="thead-dark">
            <tr>
            	<th>표지</th>
                <th>정보</th>
                <th>수정 및 삭제</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="book" items="${bookList}">
                <tr>
                	<td><img onclick="view('${book.no}')" style="width:200px;" src="./img/${book.bookfile}"/></td>
                    <td style="text-align:left;">
                    <span>장르 : </span><c:out value="${book.genre}" /><br>
                	 <span>책번호 : </span><c:out value="${book.no}" /><br>
                     <span>제목 : </span><c:out value="${book.title}"/><br>
                    <span>글쓴이 : </span><c:out value="${book.author}"/><br>
                    <span>출판사 : </span><c:out value="${book.pub}"/><br>
                    </td>
                    <td>
                        <a href="bookModify.bok?no=${book.no}" class="btn btn-primary">수정</a>
                        <a href="javascript:confirmDelete('${book.no}');" class="btn btn-danger">삭제</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="javascript:history.go(-1)">뒤로가기</a>
    <a href="bookUpForm.bok">돌아가기</a>
    <a href="bookmain.bok">메인으로</a>
</div>
    <script>
        function confirmDelete(no) {
            var confirmAction = confirm("정말 삭제하시겠습니까?");
            if (!confirmAction) {
                alert("삭제가 취소되었습니다.");
            } else {
               location.href='bookDelete.bok?no=' + no;
            }
        }
    </script>
</body>
</html>