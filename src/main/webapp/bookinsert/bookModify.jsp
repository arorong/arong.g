<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="bookIn.vo.PageInfo"%>
<%@ page import="java.sql.*"%>
<%@ page import="bookIn.vo.PageInfo"%>
<%@ page import="bookIn.vo.BookBean"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="bookIn.vo.BookBean" %>
<%
    ArrayList<BookBean> articleList=(ArrayList<BookBean>)request.getAttribute("articleList");
   PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
%>



<% BookBean book=(BookBean)request.getAttribute("book"); %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>선정 도서관</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.js"></script>
    <style>
        
       body {
/*             background-color: #f4f4f4; */
        }

        .container {
        	position:relative;
/*             background-color: #FFEBB2;    */
            padding: 20px;
            border-radius: 5px;
            max-width: 690px;
            margin: auto;
            margin-top: 50px;
            margin-bottom: 50px;
        } 

		h2 {
            color: #333;
            font-size: 1.5em;
            font-weight: bold;
            margin: 10px 20px;
			padding: 0 0 0 40px;
        }

        hr {
            width: 550px;
            border: 1px solid black;
            margin-bottom: 20px;
            margin-top: 30px;
        }
        
        label {
            flex: 1;
            padding: 8px;
            margin: 0 40px;
            box-sizing: border-box;
            font-weight: bold;
        }
        
        input[type="text"] {
            width: 400px;
            height: 45px;
            padding: 8px;
            margin-bottom: 20px;
            border-radius: 4px;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }
        
        .modiform label[for="genre"], .modiform label[for="title"],.modiform label[for="author"]   {
        	margin: 0 56px;
        }
		
		.modiform label[for="pub"] {
			margin: 0 48px;
		}
		
        .modiform{
        	margin: 20px;
        }

		.bokfile{
			 margin: 10px 20px;
		}

        .modican > .btn {
			background-color:#3e2b2c;
			color: white;
			padding: 6px 16px;
			
        }
        
		 .btn:hover {
            opacity: 0.7;
        }

        
    
    </style>
    <script>
        function validateForm() {
            var genre = document.getElementById('genre').value;
            var title = document.getElementById('title').value;
            var author = document.getElementById('author').value;
            var pub = document.getElementById('pub').value;
            if (genre === '' || title === '' || author === '' || pub === '') {
                alert('모든 필드를 채워주세요.');
                return false;
            }
            return true;
        }

        function modifyBook() {
            if (validateForm()) { // 유효성 검사 후 문제가 없으면 제출
            	let frm = document.modifyForm;
            	frm.action ="bookModifyForm.bok";
            	frm.method="post";
            	frm.enctype="multipart/form-data";
            	frm.submit();
            }
        }

    </script>
</head>
<body>
<jsp:include page="/header.jsp" />

    <div class="container">
        <h2>도서 정보 수정</h2>
        <hr></hr>
        
        <form action="bookModifyForm.bok" method="post" name="modifyForm" enctype="multipart/form-data"
            class="needs-validation" novalidate onsubmit="return validateForm()">
            
            <div class="modiform">
                <label for="genre">장르</label>
                <input type="text" name="genre" id="genre" value="<%=book.getGenre()%>"
                    required>
            </div>
            <div class="modiform">
                <label for="title">제목</label>
                <input type="text" name="title" id="title" value="<%=book.getTitle()%>"
                    required>
            </div>
            <div class="modiform">
                <label for="author">저자</label>
                <input type="text" name="author" id="author"
                    value="<%=book.getAuthor()%>" required>
            </div>
            <div class="modiform">
                <label for="pub">출판사</label>
                <input type="text" name="pub" id="pub" value="<%=book.getPub()%>"
                    required>
            </div>
            <div class="modiform">
                <label for="no">등록번호</label>
                <input type="text" name="no" id="no" value="<%=book.getNo()%>"
                    readonly>
            </div>
            <div class="modiform">
                <label for="bookno">청구기호</label>
                <input type="text" name="bookno" id="bookno"
                    value="<%=book.getBookno()%>" required>
            </div>
            <div class="bokfile">
                <label for="bookfile">파일 변경</label>
                <input type="file" name="bookfile" id="bookfile">
            </div>
            <div class="bokfile">
                <label for="bookfile">첨부파일</label>
                <%=book.getBookfile()%>
            </div>
            <hr></hr>
           <div class="modican" style="margin-left: 460px;">
                <button type="button" class="btn" onclick="modifyBook()">수정</button>
                <a href="javascript:history.go(-1)" class="btn" >취소</a>
            </div>
        </form>
    </div>
<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>