<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="login.css">
<title>선정도서관</title>
<style>
div#div1 {
    text-align: center;
}

</style>
</head>
<body>
<jsp:include page="/header.jsp"></jsp:include>
   <%
   //       request.setCharacterEncoding("UTF-8");

   String id = request.getParameter("id");
   String passwd = request.getParameter("pw");
   String name = request.getParameter("nm");
   String birth = request.getParameter("birth");
   String phone = request.getParameter("ph");
   String email = request.getParameter("email");
   String postcode = request.getParameter("postcode");
   String addr = request.getParameter("addr");
   String addr1 = request.getParameter("addr1");
   %>
   <div class="wrap" id="wrap">
      <div id="Hearder">
         <h3><%=name%>님, 환영합니다!
         </h3>
      </div>
      <table>
         <tr>
            <td>아이디</td>
            <td><%=id%></td>
         </tr>
         <tr>
            <td>비밀번호</td>
            <td><%=passwd%></td>
         </tr>
         <tr>
            <td>이름</td>
            <td><%=name%></td>
         </tr>
         <tr>
            <td>생년월일</td>
            <td><%=birth%></td>
         </tr>
         <tr>
            <td>연락처</td>
            <td><%=phone%></td>
         </tr>
         <tr>
            <td>이메일</td>
            <td><%=email%></td>
         </tr>
         <tr>
            <td>주소</td>
            <td>(<%=postcode%>)&nbsp;&nbsp;<%=addr%>&nbsp;&nbsp;<%=addr1%></td>
         </tr>
      </table><br>
         <div id="div1"><button type="button" onclick="location.href='./Main.jsp';">메인으로 가기</button>
         </div>
   </div>
<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>