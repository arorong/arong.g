<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*, java.text.SimpleDateFormat, bookIn.vo.PageInfo, bookIn.vo.BookBean"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>선정 도서관</title>
<link rel="shortcut icon" type="image/png" href="./favicon/favicon-16x16.png">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<style>
    *, ::after, ::before {
        box-sizing: border-box;
    }

    button:focus {
        outline: 0 ;
    }

    .inner_container {
    
        position: relative;
        max-width: 1200px;
        margin: 0 auto;
    }

    .seachFrm {
        position: relative;
        display: flex;
        flex-wrap: wrap;
        padding: 4px 0;
        align-items: stretch;
        width: 100%;
    }

    .btn {
        display: inline-block;
        padding: 6px 12px;
        font-size: 16px;
        font-weight: 400;
        text-align: center;
        vertical-align: middle;
        border: 1px solid transparent;
    }

    .word {
        position: relative;
        display: block;
        flex: 1 1 0%;
        width: 100%;
        font-size: 16px;
        line-height: 1.5;
        color: #495057;
        border: 1px solid #ced4da;
    }

    .seachfnc {
        color: #fff;
        background-color: #6c757d;
        border-color: #6c757d;
        border-radius: 0;
    }

    .booklist {
        color: #fff;
        background-color: #007bff;
        border-color: #007bff;
        border-radius: 0;
    }

    .table { margin: 8px 0 48px 0; }

    .table th, .table td {
        border-top: none !important;
    }

    .table thead th {
        border-bottom: 2px solid #ffd75e !important;
    }

    .info-header {
        font-size: 24px;  
        font-weight: 600;
    }

    .info-row th {
        width: 20%; /* 테이블 헤더 너비 설정 */
        background-color: #fffbf0; /* 테이블 헤더 배경색 */
        padding: 16px 15px;
        text-align: center;
        border-bottom: 16px;
        font-weight: 600;
    }

    .info-row td {
        width: 30%; /* 테이블 데이터 셀 너비 설정 */
    }

    .action-button {
        padding: 10px 20px;
        margin: 10px;
        color: #fff;
        background-color: #007bff;
        border: none; /* 버튼 테두리 제거 */
        border-radius: 5px;
    }

    .action-button:hover {
        color: #000;
        background-color: #076fde;
    }
</style>
<script>
    function view(no) {
        location.href = 'bookDetail.bok?no=' + no;
    }

    function seachfnc() {
        if (seachFrm.word.value == "") {
            alert("검색어를 입력해 주세요!");
            seachFrm.word.focus();
        } else {
            seachFrm.submit();
        }
    }
</script>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
    <div class="container mt-3">
        <div class="inner_container">
            <h2>도서 목록</h2>
            
            <form class="seachFrm" name="seachFrm" action="searchbook.bok">
                <input type="text" name="word" class="word" placeholder="검색어를 입력해주세요.">
                <button type="button" class="btn seachfnc" onclick="seachfnc()">검색</button>
                <button type="button" class="btn booklist" onclick="location.href='bookListUser.bok';">전체목록보기</button>
            </form>
            
            <table class="table">
                <c:forEach var="book" items="${bookList}">
                    <thead>
                        <tr>
                            <th class="info-header" colspan="3">
                                <c:out value="${book.title}" />
                            </th>
                            <th style="text-align: right;">
                                <button class="action-button" onclick="location.href='bookView.bok?no=${book.no}';">상세보기</button>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr class="info-row">
                            <th>등록번호</th>
                            <td><c:out value="${book.no}"/></td>
                            <th>장르</th>
                            <td><c:out value="${book.genre}"/></td>
                        </tr>
                        <tr class="info-row">
                            <th>제목</th>
                            <td><c:out value="${book.title}"/></td>
                            <th>저자</th>
                            <td><c:out value="${book.author}"/></td>
                        </tr>
                        <tr class="info-row">
                            <th>발행사항</th>
                            <td><c:out value="${book.pub}"/></td>
                            <th>청구기호</th>
                            <td><c:out value="${book.bookno}"/></td>
                        </tr>
                        <tr>
                            <td colspan="4" style="text-align: right;">
                                <button class="action-button" onclick="location.href='Main.jsp';">메인페이지로</button>
                            </td>
                        </tr>
                    </tbody>
                </c:forEach>
            </table>

            <nav>
                <ul class="pagination justify-content-center">
                    <!-- '처음'과 '이전' 페이지로의 링크 -->
                    <c:if test="${pageInfo.page > 1}">
                        <li class="page-item"><a class="page-link" href="searchbook.bok?page=1&word=${param.word}">처음</a></li>
                        <li class="page-item"><a class="page-link" href="searchbook.bok?page=${pageInfo.page - 1}&word=${param.word}">이전</a></li>
                    </c:if>

                    <!-- 현재 보여지는 페이지 범위 내의 페이지 번호들을 나열 -->
                    <c:forEach begin="${pageInfo.startPage}" end="${pageInfo.endPage}" var="i">
                        <li class="page-item ${pageInfo.page == i ? 'active' : ''}"><a class="page-link" href="searchbook.bok?page=${i}&word=${param.word}">${i}</a></li>
                    </c:forEach>

                    <!-- '다음'과 '마지막' 페이지로의 링크 -->
                    <c:if test="${pageInfo.page < pageInfo.maxPage}">
                        <li class="page-item"><a class="page-link" href="searchbook.bok?page=${pageInfo.page + 1}&word=${param.word}">다음</a></li>
                        <li class="page-item"><a class="page-link" href="searchbook.bok?page=${pageInfo.maxPage}&word=${param.word}">마지막</a></li>
                    </c:if>
                </ul>
            </nav>
        </div>
    </div>
    <jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>