<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>선정 도서관</title>
    <style>
        body {
/*             background-color: #f4f4f4; */
        }

 		.bookup { 
        	position:relative;
            padding: 20px;
            border-radius: 5px;
            max-width: 650px;
            margin: auto;
            margin-top: 30px;
        }

        h2 {
            color: #333;
            margin: 10px 20px;
			padding: 0 0 0 40px;
        }

        hr {
            width: 550px;
            border: 1px solid black;
            margin-top: 20px;
            margin-bottom: 20px;
        }

        span {
            font-size: 13px;
			margin-left: 185px;
        }

        label {
            flex: 1;
            padding: 8px;
            margin: 20px 30px;
            border-radius: 4px;
            box-sizing: border-box;
        }
        
        #selgen{
            width: 400px;
            height: 45px;
            border-radius: 5px;
            padding: 10px;
        }

        .bookform {
            margin: 20px;
            position: relative;
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
	
		.chi1, .chi2 {
			display: inline-block;
		}
		
        .chi2 {
            margin-left: 300px;
        }

		#author{
			margin: 0 0 20 32px;
		}
		
        input[type="submit"],
        input[type="reset"] {
            background-color: black;
            color: white;
            font-weight: bold;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type="submit"]:hover,
        input[type="reset"]:hover {
        	background-color: #D7B997;
        	color: black;
            opacity: 0.7;
        }
        
        #re1 {
            background-color: #dc3545;
            color: white;
            font-weight: bold;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
        }
        
        .bookform input[type="text"] {
  			padding-right: 25px; 
		}

		.bookform .btnClear {
  			position: absolute; 
  			right: 40px; 
  			top: 50%; 
  			transform: translateY(-110%); 
  			background-color: transparent; 
  			border: none; 
  			width: 20px; 
  			height: 20px; 
  			padding: 0; 
  			cursor: pointer; 
		}

		.bookform .btnClear::after {
  			content: '×';
  			font-size: 16px;
  			color: gray; 
  			font-weight: bold;
		}
        
    </style>
</head>

<body>
<jsp:include page="/header.jsp" />
    <form class="bookup"   action="bookUpAc.bok" method="post" enctype="multipart/form-data">
        <h2>도서 등록</h2>
        <hr></hr>
        <div class="bookform">
           <label for="genre"><b>도서유형</b></label>
           
            <!-- <select name="selgen" id="selgen">
            <option value="novel">소설</option>
            <option value="essay">에세이</option>
            <option value="poetry">시</option>
            <option value="history">역사</option>
            <option value="selfhelp">자기계발</option>
            <option value="philosophy">철학</option>
            <option value="science">과학</option>
            <option value="economics">경제</option>
        </select> -->
        
        <input type="text" id="genre" name="genre" required>
            <button class="btnClear"></button>

        </div>

        <div class="bookform">
            <label for="title"><b>도서제목</b></label>
            <input type="text" id="title" name="title" required>
            <button class="btnClear"></button>
        </div>

        <div class="bookform">
            <label for="author" style="margin: 0 46px;"><b>저자</b></label>
            <input type="text" id="author" name="author" required>
            <button class="btnClear"></button>
        </div>

        <div class="bookform">
            <label for="pub"><b>발행사항</b></label>
            <input type="text" id="pub" name="pub" required>
            <button class="btnClear"></button>
        </div>

        <div class="bookform">
            <label for="bookno"><b>청구기호</b></label>
            <input type="text" id="bookno" name="bookno" required>
            <button class="btnClear"></button>
        </div>

        <div class="fileform" style="margin: 10px 20px;">
            <label for="bookfile"><b>파일등록</b></label>
            <input type="file" id="bookfile" name="bookfile">
            <button type="button" id="bookfile1" onclick="deleteFile()">파일 삭제</button>
		</div>
            <span>※ 이미지는 5MB 이하 파일만 등록이 가능합니다.</span>
		<div class="allreset">
        </div>
        <hr></hr>
        <div class="pa" style="margin-left: 34px;">
            <div class="chi1">
            <input type="reset" value="전체 삭제" id="re1"></button>
            </div>
       		<div class="chi2">
        	   	<input type="submit" value="등록">
           		<input type="reset" onclick="history.go(-1)" value="취소" class="btn">
       	 	</div>
        </div>
    </form>

    <script>
        function deleteFile() {
            var fileInput = document.getElementById("bookfile");
            fileInput.value = ""; // 파일 입력 필드의 값을 비움
        }

        var btnClear = document.querySelectorAll('.btnClear');
        btnClear.forEach(function(btn){
            btn.addEventListener('click', function(e){
                btn.parentNode.querySelector('input').value = "";
                e.preventDefault(); // 기본 이벤트 동작 막기
            })
        })
    </script>
<jsp:include page="/footer.jsp"></jsp:include>
</body>

</html>