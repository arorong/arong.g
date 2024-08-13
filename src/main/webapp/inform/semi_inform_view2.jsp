<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="inform.vo.InformBean"%>
<%
   InformBean article = (InformBean)request.getAttribute("article");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>선정도서관</title>
<style type="text/css">
 @import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap');



 * {
            font-family: 'Noto Sans KR',sans-serif !important;
/*            font-family: 'good'; */
            box-sizing: border-box;
            font-weight: 500;
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

.wow{
	margin: 10px;
	padding:5px 30px;
    background-color:black;
    text-decoration: none;

	color:#fff;
}

.wow:hover{
    text-decoration: none;
    background-color: #D7B997;
    color: black;
    border-color: none;
}
</style>
<script>
    function showDiv() {
        var imgDiv = document.getElementById("imgDiv");
        imgDiv.classList.toggle("active");
    }
    
    function hideDiv() {
        document.getElementById("imgDiv").classList.remove("active");
    }
    </script>
</head>
<body>
   <!-- 게시판 등록 -->
<jsp:include page="../header.jsp"></jsp:include>

   <section id="writeForm" style="position:relative">
      <h2>게시판 상세보기</h2>
      <form action="informWritePro.bo" method="post" enctype="multipart/form-data" name="informform">
         <table>
         	<tr>
         	<td class="td_left"><label for="inform_NM">글쓴이</label></td>
         	<td><%=article.getInform_NM()%></td>
         	</tr>
            <tr>
               <td class="td_left"><label for="inform_TITLE">제 목</label></td>
               <td class="td_right"> <%=article.getInform_TITLE()%></td>
            </tr>
            <tr>
                <td class="td_left"><label for="inform_FILES"> 파일 첨부 </label></td>
                <td class="td_right">
                     <%if(!(article.getInform_FILES()==null)){ %>
                    <img style="width:300px;" src="./informUpload/<%=article.getInform_FILES()%>">
                    <br>
<!--                     <a href="javascript:showDiv();">이미지 보기</a> -->
<!--                     <div id="imgDiv" onclick="hideDive()"></div> -->
            <%} %>
         </div>
                
                </td>
            </tr>
            <tr style="height:250px;">
               <td class="td_left"><label for="inform_TEXTS">내 용</label></td>
               <td><%=article.getInform_TEXTS() %></td>
            </tr>
         </table>
         <section id="commandCell">
          <%if(article.getInform_NM().equals(session.getAttribute("id"))){%>
         	<a class="wow" href="informModifyForm.bo?inform_num=<%=article.getInform_NUM() %>&page=${page }"> 수정 </a>
      		<a class="wow" href="informDeletePro2.bo?inform_num=<%=article.getInform_NUM() %>&page=${page }"> 삭제 </a>
      		      		<%} %>
      		
            <a class="wow" href="informList2.bo?page=${page }"> 목록 </a>&nbsp;&nbsp;
         </section>
      </form>
   </section>
   <!-- 게시판 등록 -->
  
</body>
</html>