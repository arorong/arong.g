<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="login.vo.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="common/cacheRemove.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>선정도서관</title>
<link rel="stylesheet" href="login.css">
<style>
div#wrapIn {
    border-top: 1px solid black;
    height: 250px;
    margin: 0 auto;
    border-bottom: 1px solid black;
    padding-left: 30px;
    padding-top: 20px;
    
}

h3 {
	margin: 0 auto;
    font-size: x-large;
/*     text-align: initial; */
    margin-bottom: 20px;
}

div#wrapIn > a {
    display: block;
    text-decoration: none;
    color: black;
    font-weight: normal;
    font-size: 16px;
    margin-bottom: 30px;
}

@media screen and (max-width: 768px) {
.wrap {
	width: 100%;
}

}
</style>
</head>
<body>
<jsp:include page="/header.jsp"></jsp:include>

<div class="wrap" id="wrap">

	<h3>${id}님</h3>
	<div id="wrapIn">
      <a href="memberViewAction.me?id=${id}">&gt; 내정보  </a>
	  <a href="memberViewAction2.me?id=${id}">&gt; 회원정보수정</a>
	  <a href="borrowList.ao?id=${id}">&gt; 대출목록관리</a>
	  <a id="delete" href="javascript:delBtn('${id}')" style="color:gray;">&gt; 회원탈퇴</a>
	</div>

      <script>
      function delBtn(mid){
         if(confirm("탈퇴시 6개월간 재가입은 불가합니다. 탈퇴를 진행하시겠습니까?")){
            location.href='memberWithdrawalAction.me?id='+mid;
         }
      }
      </script>
      
</div>
<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>