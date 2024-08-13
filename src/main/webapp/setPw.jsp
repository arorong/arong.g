<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>선정도서관</title>

<link rel="stylesheet" href="login.css">
<style>
    #hidenarea{visibility:hidden;padding: 0px; margin: 0px;}
</style>
</head>
<body>
 <jsp:include page="/header.jsp"></jsp:include>
<div class="wrap" id="wrap">
    <div id="wrap">
   <h3>비밀번호를 재설정 하세요.</h3>


   <form name="frm" action="./setPw.me" method="post">
   <input type="hidden" id="id" name="id" value="<%= request.getParameter("id")%>">
   <table>
      <tr>
         <td><input type="password" id="npw" name="npw" minlength="8" maxlength="16" pattern="[a-zA-Z0-9_\-~!@#$%^&*()]+" placeholder="새 비밀번호" required></td>
      </tr>
      <tr>
         <td><input type="password" id="npw1" name="npw1" minlength="8" maxlength="16" pattern="[a-zA-Z0-9_\-~!@#$%^&*()]+" placeholder="비밀번호 확인" required></td>
      </tr>      
   </table>
         <div class="btnwrap">
            <button type="button" class="menu_btn" onclick="pwchk()">변경하기</button>
         </div>
   <small style="color:red;">** 비밀번호는 8~16자 내, <br>영어 대소문자와 영어, 특수문자[ _\-~!@#$%^&*() ]만 사용 가능합니다.</small>
</form>
</div>
</div>
<script>
   function pwchk(){
      var p1 = document.getElementById('npw').value;
      var p2 = document.getElementById('npw1').value;
      
      if(p1 == ""){
         alert('비밀번호를 입력해주세요.');
         document.getElementById('npw').focus();
         return false;
      } else if(p2 == ""){
         alert('비밀번호를 확인해주세요.');
         document.getElementById('npw1').focus();
         return false;
      } else if(p1 != p2){
         alert('비밀번호가 일치하지 않습니다. \n다시 확인해주세요.');
         return false;
      } else{
         alert("비밀번호가 재설정 되었습니다.\n로그인 페이지로 이동합니다.");
         frm.submit();
      }
   }
</script>
<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>