<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*, java.text.SimpleDateFormat, bookIn.vo.PageInfo, bookIn.vo.BookBean"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>선정 도서관</title>
	<link rel="shortcut icon" href="./favicon/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.js"></script>
	<style>
		div {
			position: relative;
		}
		
		#allform {
			width: 100%!important;
			margin: 50px auto;
		}
		
		#alltb{
			table-layout: fixed;
			margin: 40px auto;
		}
		
   		 .seachFrm {
     	    position: relative;
       		display: flex;
        	flex-wrap: wrap;
        	padding: 4px 0;
        	align-items: stretch;
        	width: 100%;
   		}
    
   		.seachfnc {
       		color: white;
   	     	background-color: black;
        	border-color: black;
		}
    	
    	.word {
    	    position: relative;
       	 	display: block;
        	flex: 1 1 0%;
        	width: 98%;
        	font-size: 16px;
        	line-height: 1.5;
        	color: #495057;
        	border: 1px solid #ced4da;
    	}
    	
    	.booklist {
    		margin: 0 0 0 5px;
        	color: white;
        	background-color: black;
        	border-color: black;
    	}
		
		.first {
            font-size: 18px;
            background-color: black; 
            color: white; 
            text-align: center;
        }
        
        .center-text {
        	text-align: center;
   		}
        
		#admain, #admbook {
 			margin: 20px 0px 20px 960px;
            background-color: black;
            color: white;
            border-radius: 5px;
            padding: 10px;
            margin: 5px;
        }
        
        #admain:hover, #admbook:hover {
      		background-color: #d7b997;
      		color: black;
      		text-decoration: none;
   		}
		
		h2 {
			font-size: 30px;
            margin-top: 50px;
		}
		
		hr {
			border: 1px solid black;
		}
				
        .table td {
            vertical-align: middle;
        }
	
		.center-text {
	    	text-align: center;
		}
		
		.btn-primary {
			background-color: #E9E5E4;
			color: black;
			border: none;
			border-radius: 100px;
		}
		
		.btn-primary:hover {
			background-color: #E9E5E4;
            opacity: 0.9;
		}
		
		.btn-danger {
		    background-color: #b93d38;
    		color: white;
   			border: none;
			border-radius: 100px;
    	} 
		
 		.page-item.active .page-link {
    		background-color: #000 !important;
    		border: 1px solid #000;
    	}
    	
    	.page-link {
      		color: #000;
      		border: none;
     		outline: none !important; 
   		}
   
   		.page-link:hover {
      		background-color: #d7b997;
      		color: black;
   		}

    	#paging {
            margin-top: 30px;
   	 	}
   	 	
	</style>
</head>
<body>
<jsp:include page="../header.jsp" />
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
<div class="container mt-3" id="allform">
    <h2>도서 목록</h2>
    <form class="seachFrm" name="seachFrm" action="searchbookAdmin.bok">
          <input type="text" name="word" class="word" placeholder="검색어를 입력해주세요.">
          <button type="button" class="btn seachfnc" onclick="seachfnc()">검색</button>
          <button type="button" class="btn booklist" onclick="location.href='bookListUser.bok';">전체목록보기</button>
    </form>
  	<hr></hr>
    <table class="table table-hover" id="alltb">
        <thead>
            <tr class="first">
            	<th style="width: 7%;">번호</th>
                <th style="width: 9%;">유형</th>
                <th style="width: 30%;">제목</th>
                <th>저자</th>
                <th>발행사항</th>
                <th>청구기호</th>
                <th>수정/삭제</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="book" items="${bookList}">
                <tr>
                	<td class="center-text"><c:out value="${book.no}" /></td>
                    <td class="center-text"><c:out value="${book.genre}" /></td>
                    <td>${fn:substring(book.title, 0,24 )}<c:if test="${fn:length(book.title) >=25}">...</c:if></td>
                    <td>${fn:substring(book.author, 0,9 )}<c:if test="${fn:length(book.author) >=10}">...</c:if></td>
                    <td>${fn:substring(book.pub, 0,9 )}<c:if test="${fn:length(book.pub) >=10}">...</c:if></td>
                    <td><c:out value="${book.bookno}"/></td>
                    <td class="center-text">
                        <a href="bookModify.bok?no=${book.no}" class="btn btn-primary">수정</a>
                        <a href="javascript:confirmDelete('${book.no}');" class="btn btn-danger">삭제</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="alink" style="margin-left: 850px;">
    <a href="bookUpForm.bok" id="admbook">도서 등록</a>
    <a href="adminWork.jsp" id="admain">관리 페이지로</a>
    </div>
    <nav>
	    <ul class="pagination justify-content-center" id="paging">
	    <!-- '처음'과 '이전' 페이지로의 링크 -->
	        <c:if test="${pageInfo.page > 1}">
	            <li class="page-item">
	                <a class="page-link" href="bookListAdmin.bok?page=1&word=${param.word}">&lt;&lt;</a>
	            </li>
	            <li class="page-item">
	                <a class="page-link" href="bookListAdmin.bok?page=${pageInfo.page - 1}&word=${param.word}">&lt;</a>
	            </li>
	        </c:if>
	        
	        <!-- 현재 보여지는 페이지 범위 내의 페이지 번호들을 나열 -->
	        <c:forEach begin="${pageInfo.startPage}" end="${pageInfo.endPage}" var="i">
	            <li class="page-item ${pageInfo.page == i ? 'active' : ''}">
	                <a class="page-link" href="bookListAdmin.bok?page=${i}&word=${param.word}">${i}</a>
	            </li>
	        </c:forEach>
	        
	        <!-- '다음'과 '마지막' 페이지로의 링크 -->
	        <c:if test="${pageInfo.page < pageInfo.maxPage}">
	            <li class="page-item" ">
	                <a class="page-link" href="bookListAdmin.bok?page=${pageInfo.page + 1}&word=${param.word}">&gt;</a>
	            </li>
	            <li class="page-item">
	                <a class="page-link"  href="bookListAdmin.bok?page=${pageInfo.maxPage}&word=${param.word}">&gt;&gt;</a>
	            </li>
	        </c:if>
	    </ul>
	</nav>
    
</div>
<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>