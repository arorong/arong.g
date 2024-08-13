<%@page import="login.vo.PageInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="login.vo.MemberBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
int nowPage = pageInfo.getPage();
int maxPage = pageInfo.getMaxPage();
int startPage = pageInfo.getStartPage();
int endPage = pageInfo.getEndPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>선정도서관</title>

<link rel="stylesheet" href="login.css">
<style>
.pageList {
    text-align:center;
}
input>:hover[type="submit"] {
   background-color: #D7B997;
   color: black; 
   border: none;
}
a#button {
   display: inline;
}
#button {
   display: block;
   height: 35px;
   padding: 5px 10px;
   text-decoration: none;
   background-color: black;
   color: #fff;
   text-align: center;
   border-radius: 5px;
}
.button {
   display: block;
   width: 64px;
   height: 24px;
   padding: 5px 10px;
   text-decoration: none;
   background-color: white;
   color: black;
   text-align: center;
   border: 1px solid #ccc;
   border-radius: 5px;
}
a.button {
   display: inline;
}

.button:hover {
   background-color: #d7b997;
    color: black;
    border-color: #fff;
}

</style>
</head>

<body>
 <jsp:include page="/header.jsp"></jsp:include>
 <div class="wrap" name="wrap">
   <div class="search" style="margin:10px 0 10px 0; ">
          <form action="search.me" method="post">
             <div class="scwrap">
                ​​​​​​​​​​​​<select name="option">
                   <option value="id">아이디</option>
                   <option value="nm">이름</option>
                   <option value="ph">휴대폰번호</option>
                   <option value="unm">회원번호</option>
                </select> &nbsp;&nbsp; 
                <input type="search" class="search" name="search" placeholder="검색어를 입력해주세요."> 
                <input type="submit" class="search1" id="search" value="검색">
             </div>
          </form>
     </div>
         <h1>회원관리</h1>
      <table>
      <tr>
         <td style="width: 15%;">NO</td>
         <td style="width: 40%;">ID</td>
         <td>MENU</td>
      </tr>
        <c:forEach var="member" items="${memberList}" varStatus="status">
            <tr>
            <%
               if(nowPage == 1) {
            %>
               <td>${status.count}</td>
            <%} else { %>
               <td>${status.count + 10}</td>
               <%} %>
               <td>${member.id}</td>
               <td>
                  <div class="atag">
                     <a id="atag_admin" href="memberViewAction.me?id=${member.id}">상세보기 </a>
                     <a id="atag_admin" href="memberViewAction2.me?id=${member.id}">정보수정</a><%="<br>"%>
                  </div>
            </tr>
         </c:forEach>
      </table><br>
   
   <div class="pageList" id="pageList" style="position:relative;">
      <%   if (nowPage <= 1) {   %>
      
      <%   } else { %>
      <a class="button" href="memberListAction.me?page=<%=nowPage - 1%>">이전</a>&nbsp;&nbsp;
      <%   } %>

      <% for (int a = startPage; a <= endPage; a++) {
         if (a == nowPage) {
      %>
      <a class="button" id="button"><%=a%></a>&nbsp;&nbsp;
      <%   } else {
      %>
      <a class="button" href="memberListAction.me?page=<%=a%>"><%=a%>
      </a>&nbsp;&nbsp;
      <%   }
      %>
      <%   }
      %>
      
      <%   if (nowPage >= maxPage) {
      %>
      
      <%   } else {
      %>
      <a class="button" href="memberListAction.me?page=<%=nowPage + 1%>">다음&nbsp;&nbsp;</a>
      <%   }
      %>
   </div><br>
     <div class="atag">
         <button type="button" class="menu_btn" onclick="javascript:history.back()">뒤로가기</button>
      </div>   
   </div>
<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>