<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC 게시판</title>
<style type="text/css">
* {
   box-sizing: border-box;
   position:relative;
}

h2 {
   text-align: center;
   padding : 20px;
}

#writeForm {
    padding: 20px;
}

table {
    margin: auto;
    width: 700px;
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

button{
    width : 150px;
    height : 35px;
   background-color: E8D8C8;
/*     border: 1px solid #fff; */
/*     border-radius: 5px; */
    text-align: center;
    background-color : black;
    color: white;
}



</style>
</head>
<body>
   <!-- 게시판 등록 -->
<jsp:include page="../header.jsp"></jsp:include>

   <section id="writeForm" style="position:relative">
    <title>선정 도서관</title>
      <form action="informWritePro.bo" method="post" enctype="multipart/form-data" name="informform">
         <table>
<!--             <tr> -->
<!--                <td class="td_left"><label for="inform_NM">글쓴이</label></td> -->
<!--                <td class="td_right"><input type="text" name="inform_NM" id="inform_NM" required></td> -->
<!--             </tr> -->
            <tr>
               <td class="td_left"><label for="inform_TITLE">제 목</label></td>
               <td class="td_right"><input name="inform_TITLE" type="text" id="inform_TITLE" required></td>
            </tr>
<!--             <tr> -->
<!--                <td class="td_left"><label for="inform_PASS">비밀번호</label></td> -->
<!--                <td class="td_right"><input type="text" name="inform_PASS" id="inform_PASS" required></td> -->
<!--             </tr> -->
            <tr>
               <td class="td_left"><label for="inform_TEXTS">내 용</label></td>
               <td><textarea id="inform_TEXTS" name="inform_TEXTS" cols="40" rows="15" required></textarea></td>
            </tr>
            <tr>
               <td class="td_left"><label for="inform_FILES"> 파일 첨부 </label></td>
               <td class="td_right"><input name="inform_FILES" type="file" id="inform_FILES"></td>
            </tr>
         </table>
         <section id="commandCell">
            <button type="submit">등록</button>&nbsp;&nbsp;
            <button type="reset">다시쓰기</button>
         </section>
      </form>
   </section>
   <!-- 게시판 등록 -->
</body>
</html>