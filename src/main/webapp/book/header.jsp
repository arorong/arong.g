<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="inform.vo.PageInfo"%>
<%@ page import="java.sql.*"%>
<%@ page import="inform.vo.PageInfo"%>
<%@ page import="inform.vo.InformBean"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>선정 도서관</title>
   <style>
   body{
         margin : 0px;
         
   }
    @font-face {
    font-family: 'KorailRoundGothicBold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2212@1.0/KorailRoundGothicBold.woff2') format('woff2');
    font-weight: 300;
    font-style: normal;
   }
   
   @font-face{
          font-family: 'good';
          src: url("/font/Typo_SSiGothic150.ttf.ttf");
             font-weight: 300;
          font-style: normal;
       
       }
   * {
           font-family: 'good';
            box-sizing: border-box;
     }
  .navbar_main {
  background-color: rgba(0, 0, 0, 0.5);
  /* opacity: 0.5; */
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem 0;
  color: #fff;
  z-index: 999;
  animation: navbarBottom 1s forwards;
}

.navbar_links a {
  text-decoration: none;
  color: #fff;
  margin-right: 4rem;
  text-decoration-line: none;
  font-size: 1.2rem;
  font-weight: 600;
  position: relative;
  padding: 0.5rem 0;
}

.navbar_links a:last-child {
  margin-right: 0;
  
}

.navbar_links a::before {
  content: '';
  position: absolute;
  bottom: -1px;
  left: 50%;
  width: 0;
  text-decoration-line: none;
  height: 3px;
  background-color: #fff;
  transition: width 0.2s ease-in-out, left 0.2s ease-in-out;
}



.navbar_links a:hover::before {
  width: 100%;
  left: 0;
}

.navbar_logo {
  margin-left: 10%;
  text-decoration: none;
  color: #000;
  font-size: 2.3rem;
  font-weight: 500;
  opacity: 1;
}

.navbar_scroll {
  background-color: #fff;
  z-index: 5;
  border-bottom: 1px solid gray;
}

.navbar_scroll div a {
  color: black;
}

.navbar_scroll a {
  color: black;
}

@keyframes navbarBottom {
  0% {
    transform: translateY(-70%);
    /* opacity: 0; */
  }
  100% {
    transform: translateY(0);
    /* opacity: 1; */
  }
}

.top-bar a{
  text-decoration : none;
  color: #fff;
  margin-right: 10px;

}

.navbar_links.active {
      display: flex;
     flex-direction: column;
     position: absolute;
     top: 95px;
     left: 10px;
     background-color: rgba(0, 0, 0, 0.5);
     border-radius: 5px;
     width: 150px;
     padding: 10px;
     z-index: 2;
     text-align: center;
  }

  .navbar_links.active a {
      margin: 10px 0;
  }
  
  .menu-toggle {
     display: none;
     font-size: 40px;
     color: #fff;
     cursor: pointer;
     position: absolute;
     /* 토글 버튼의 위치를 절대적으로 설정 */
     left: 10px;
     /* 왼쪽 끝으로 이동 */
   }
   
   
  
  @media screen and (max-width: 768px) {
   .bbb {
   display: flex;
    flex-direction: row-reverse;
   }
   
  .navbar_links {
    display: none;
  }

  .menu-toggle {
    display: inline;
  }
  
  
  .navbar_logo img {
     margin:0 auto;
  }
  
  .navbar_logo{
  margin-left:45%;
  }



  .menu.active li {
    margin: 10px 0;
  }
}

.bb1 a{
color : #fff;
text-decoration : none;
}
.bb1 span{
color : #fff;   
}

    </style>
    
</head>
<body>


  <div style="height: 150px; "><img style="width: 100%; height:770px" src="./img/배경3.jpg"></div>
  
  <!-- PC 버전 -->
<div class="navbar_main">
  <a class="navbar_logo" href="Main.jsp"><img style="width:70px;" src="./img/logow.png"></a>
  <div class="menu-toggle">&#9776;</div>
  <div class="navbar_links">
   <a style="text-decoration-line: none;" href="informList.bo">공지사항</a>
   <a style="text-decoration-line: none;"href="bookListUser.bok">도서정보</a>
   <a style="text-decoration-line: none;"href="semi_sr.jsp">소개</a>
   <a style="text-decoration-line: none;"href="informList2.bo">게시판</a>
   
   
   <c:set var="id" value="${sessionScope.active }" />
   <c:choose>
   <c:when test="${active eq null }">
   </c:when>
   <c:when test="${active ne 'Y' }">
   <a style="text-decoration-line: none;"href="adminWork.jsp">관리자페이지</a>
   </c:when>
   <c:otherwise>
   <a style="text-decoration-line: none;"href="UserMypage.jsp">마이페이지</a>
   </c:otherwise>
   </c:choose>
   
   
  </div>
  
  
  
  <c:set var="id" value="${sessionScope.active }" />
      <c:choose>
         <c:when test="${active eq null }">
            <div class="top-bar">
                <a href="memberLogin.me">로그인</a>
                <a href="terms.jsp">회원가입</a>
            </div>
         </c:when>
         <c:when test="${active ne 'Y' }">
            <div class="bbb">
               <form class="bb1" action="Logout.jsp" method="post">
               <span style="color: #fff;"><%=session.getAttribute("id") + "(관리자)님"%></span>
                  <input type="hidden" name="id" autocomplete='off'>
                  <input type="hidden" name="pwd" autocomplete='off'> 
                  <a href="logout">로그아웃</a>                  
               </form>
            </div>
         </c:when>


         <c:otherwise>
            <div class="bbb">
               <form class="bb1" action="Logout.jsp" method="post">
                  <%=session.getAttribute("id") + "님"%>
                  <input type="hidden" name="id" autocomplete='off'> 
                  <input type="hidden" name="pwd" autocomplete='off'> 
                    <a href="logout">로그아웃</a>                  
               </form>
            </div>
         </c:otherwise>
      </c:choose>

</div>
      <div class="back" style=" background-color:#fff; width: 100%; height: 700px; position: absolute;  margin: 0; z-index: 0;">
      </div>

</body>
      <script>
      document.querySelector('.menu-toggle').addEventListener('click', function() {
        document.querySelector('.navbar_links').classList.toggle('active');
      });
    </script>
</html>