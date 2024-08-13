<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="bookIn.vo.BookBean" %>
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

    .btn:focus {
        outline: 0;
    }

    .inner_container {
        position: relative;
        width: 700px;
        max-width: 1200px;
        margin: 0 auto;
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

    .table {
/*         margin: 8px 0 48px 0; */
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
        background-color: #007bff;
        border: none;
        border-radius: 5px;
    }

    .action-button:hover {
        color: #000;
        background-color: #076fde;
    }
    .book-re { border-top: 1px solid #000; }
    #tr_st { border-bottom: 1px solid #efefef; }
	#th_st { background-color: #f3f3f3; margin: 0;  }
	.brorrow_btn, .back_btn {
		background-color: #000!important;
		color: #fff!important;
		cursor: pointer;
		border: none!important;
	}

	.brorrow_btn:hover, .back_btn:hover {
		color: #000!important;
		background-color: #d7b997!important;
		border: 0 solid #d7b997!important;
	}
	#d_btn { background-color: #dc3545!important; color: #fff!important; border: none!important;}
	
	        @media screen and (max-width: 768px) {
            .inner_container {
                padding: 10px;
                width: initial;
            }

/*             .btn { */
/*                 display: block; */
/*                 width: 100%; */
/*                 margin-bottom: 10px; */
/*             } */

            .brorrow_btn {
                margin-bottom: 0;
            }
        }
</style>
</head>

<body>
<jsp:include page="/header.jsp"></jsp:include>
	<div class="container mb-5">
	    <div class="inner_container">
	        <h2 class="my-4">${book.title}</h2>
	        <!-- 도서 상세 정보 -->
	        <div class="col-md-8">
	            <table class="table book-re">
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
	                        <td>${book.title}</td>
	                    </tr>
	                    <tr id="tr_st">
	                        <th id="th_st">저자</th>
	                        <td>${book.author}</td>
	                    </tr>
	                    <tr id="tr_st">
	                        <th id="th_st">청구기호</th>
	                        <td>${book.bookno}</td>
	                    </tr>
	                </tbody>
	            </table>
	<% BookBean b = (BookBean)request.getAttribute("book"); %>
                 <!-- 대출하기 버튼 -->
                 <%if( b.getBsign() != null && b.getBsign().equals("Y")){ %>
				<a href="bookBorrowForm.ao?id=${sessionScope.id}&no=${book.no}" class="btn btn-success brorrow_btn">대출하기</a>
				<%} else { %>
				<a id="d_btn" class="btn btn-success brorrow_btn back_btn">대출불가도서</a>
				<%} %>
				
                 <!-- 목록으로 돌아가기 버튼 -->
	            <a href="bookListUser.bok" class="btn btn-primary back_btn">도서목록으로</a>
	        </div>
	    </div>
	</div>
<jsp:include page="/footer.jsp"></jsp:include>
</body>

</html>