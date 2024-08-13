<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="bookIn.vo.BookBean, bookIn.vo.PageInfo" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>선정 도서관</title>
<link rel="shortcut icon" type="image/png" href="./favicon/favicon-16x16.png" />
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<style>
    *, ::after, ::before {
        box-sizing: border-box;
    }

    .book {
        font-size: 24px;
    }

    button:focus {
        outline: none !important;
        border: none !important;
    }

    #wrap {
        padding: 40px 0 50px 0;
        position: relative;
    }

    .inner_container {
    	padding : 70px;
        position: relative;
        width: 84%;
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

    .seachFrm::before {
        position: absolute;
        content: '';
        bottom: -20px;
        width: 100%;
        height: 1px;
        border: 1px solid #000;
    }

    .btn {
        display: inline-block;
        padding: 6px 12px;
        font-size: 16px;
        font-weight: 400;
        /*         text-align: center; */
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
        background-color: #000;
        border-color: #000;
    }

    .seachfnc:hover {
        color: #000;
        background-color: #d7b997;
        border: 1px solid #d7b997;
    }

    .booklist {
        margin: 0 0 0 5px;
        color: #fff;
        background-color: #000;
        border-color: #000;
    }

    .table {
        margin: 8px 0 48px 0;
    }

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
        width: 20%;
        /* 테이블 헤더 너비 설정 */
        background-color: #fffbf0;
        /* 테이블 헤더 배경색 */
        padding: 16px 15px;
        text-align: center;
        border-bottom: 16px;
        font-weight: 600;
    }

    .info-row td {
        width: 30%;
        /* 테이블 데이터 셀 너비 설정 */
    }

    .action-button {
        padding: 10px 20px;
        margin: 10px;
        color: #fff;
        background-color: #000;
        border: none;
        border-radius: 5px;
    }

    .action-button:hover, .booklist:hover, .backbtn:hover {
        color: #000;
        background-color: #d7b997;
        border: 1px solid #d7b997;
    }

    .book_re {
        border-top: 1px solid #000;
    }

    #th_st {
        background-color: #f3f3f3;
        margin: 0;
    }

    #tr_st {
        border-bottom: 1px solid #efefef;
        padding: 16px 0 16px 0;
    }

    .row {
        margin-right: 0;
    }

    .tit {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-top: 20px;
    }

    .backbtn {
        position: absolute;
        right: 9%;
        background-color: #000;
        color: #fff;
    }

    .viewbtn {
        height: 40px;
        font-size: 15px !important;
        background-color: #000 !important;
        border: #000 !important;
    }
    
    .viewbtn:hover {
        color: #000;
        background-color: #d7b997!important;
        border: 0 solid #d7b997!important;
    }

    .page-item.active .page-link {
        background-color: #000 !important;
        /* border-color: #000 !important; */
        border: 1px solid #000;
    }

    .img_magin {
        margin-top: 35px;
    }

    .page-link {
        color: #000;
        border: none;
        outline: none !important;
    }

    .page-link:hover {
        background-color: #d7b997;
        color: #000;
    }

    @media screen and (max-width: 768px) {
    .my-1 { margin-bottom: 0px!important; }
.img_magin { margin-top: 0px!important; }

        /* 768px 이하의 화면 너비에 대한 스타일 */
        .inner_container {
            width: 90%;
            /* 컨테이너의 너비를 화면 너비의 90%로 설정 */
            padding: 20px;
            /* 패딩을 좀 더 넓게 설정 */
        }

        .seachFrm::before {
            /* 가로 라인을 화면이 작아서 가려줌 */
            display: none;
        }

        .word {
            /* 검색어 입력 필드 너비를 조정하여 버튼 공간 확보 */
            width: calc(100% - 86px);
        }

		.backbtn {
			margin-top : 2px;
			right: 9%;
		}
		
		.col-sm-3 {
			flex: auto;
			max-width: 100%;
		}
		.viewbtn { height: auto; white-space: nowrap;}
	    
	    .action-button { padding: 10px 25px; }
	    }
	    
	    .book { font-size: 20px; }
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
    } <%
    System.out.println("request.getAttribute('success') : " + request.getAttribute("success"));
    if (request.getAttribute("success") != null && request.getAttribute("success").equals("Y")) {
        %> alert("대출이 완료되었습니다."); 
    <%}%>
</script>
</head>

<body>
<jsp:include page="/header.jsp"></jsp:include>
    <div class="container mt-3">
        <div class="inner_container">
            <h2>도서 목록</h2>
            <form class="seachFrm" name="seachFrm" action="searchbook.bok">
                <input type="text" name="word" class="word" placeholder="검색어를 입력해주세요.">
                <button type="button" class="btn seachfnc" onclick="seachfnc()">검색</button>
                <button type="button" class="btn booklist" onclick="location.href='bookListUser.bok';">전체목록보기</button>
            </form>
            <c:forEach var="book" items="${bookList}">
                <%-- 도서 제목 --%>
                <div class="tit">
                    <h2 class="my-4 book">
                        <c:set var="strlen" value="${fn:length(book.title)}" />
                        ${fn:substring(book.title, 0,21 )}<c:if test="${strlen >20}"><br>${fn:substring(book.title, 21, strlen )}</c:if>
                    </h2>
                    <button type="button" class="btn action-button viewbtn" onclick="location.href='bookView.bok?no=${book.no}';">상세보기</button>
                </div>
                <div class="row">
                    <%-- 도서 이미지 --%>
                    <div class="col-sm-3 my-1">
                        <img src="./bookcoverupload/${book.bookfile}" class="img-fluid shadow img_magin" alt="${book.title} 표지" title="${book.title}" />
                    </div>
                    <%-- 도서 상세 정보 --%>
                    <div class="col-md-8">
                        <table class="table book_re">
                            <tbody>
                                <tr id="tr_st">
                                    <th id="th_st">등록번호</th>
                                    <td>${book.no}</td>
                                </tr>
                                <tr id="tr_st">
                                    <th id="th_st">유형(장르)</th>
                                    <td>${book.genre}</td>
                                </tr>
                                <tr id="tr_st">
                                    <th id="th_st">제목</th>
                                    <td>${fn:substring(book.title, 0,25 )}<c:if test="${fn:length(book.title) >=24}">...</c:if>
                                    </td>
                                </tr>
                                <tr id="tr_st">
                                    <th id="th_st">저자</th>
                                    <td>${book.author}</td>
                                </tr>
                                <tr id="tr_st">
                                    <th id="th_st">발행사항</th>
                                    <td>${book.pub}</td>
                                </tr>
                                <tr id="tr_st">
                                    <th id="th_st">청구기호</th>
                                    <td>${book.bookno}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <hr>
            </c:forEach>
            <a href="Main.jsp" class="backbtn btn">돌아가기</a>
            <nav>
                <ul class="pagination justify-content-center">
                    <!-- '처음'과 '이전' 페이지로의 링크 -->
                    <c:if test="${pageInfo.page > 1}">
                        <li class="page-item"><a class="page-link" href="bookListUser.bok?page=1&word=${param.word}">&lt;</a></li>
                        <li class="page-item"><a class="page-link" href="bookListUser.bok?page=${pageInfo.page - 1}&word=${param.word}">&lt;&lt;</a></li>
                    </c:if>

                    <!-- 현재 보여지는 페이지 범위 내의 페이지 번호들을 나열 -->
                    <c:forEach begin="${pageInfo.startPage}" end="${pageInfo.endPage}" var="i">
                        <li class="page-item ${pageInfo.page == i ? 'active' : ''}"><a class="page-link" href="bookListUser.bok?page=${i}&word=${param.word}">${i}</a></li>
                    </c:forEach>

                    <!-- '다음'과 '마지막' 페이지로의 링크 -->
                    <c:if test="${pageInfo.page < pageInfo.maxPage}">
                        <li class="page-item"><a class="page-link" href="bookListUser.bok?page=${pageInfo.page + 1}&word=${param.word}">&gt;</a></li>
                        <li class="page-item"><a class="page-link" href="bookListUser.bok?page=${pageInfo.maxPage}&word=${param.word}">&gt;&gt;</a></li>
                    </c:if>
                </ul>
            </nav>
        </div>
    </div>
<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>