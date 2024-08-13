<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="inform.vo.PageInfo"%>
<%@ page import="inform.vo.InformBean"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<%
    ArrayList<InformBean> articleList=(ArrayList<InformBean>)request.getAttribute("articleList");
    PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
    int listCount=pageInfo.getListCount();
    int nowPage=pageInfo.getPage();
    int maxPage=pageInfo.getMaxPage();
    int startPage=pageInfo.getStartPage();
    int endPage=pageInfo.getEndPage();
    request.setAttribute("nowPage", nowPage);
%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>선정 도서관</title>
<style type="text/css">

#listForm {
    width: 70%;
    margin: 20px auto;
    border-radius: 10px;
    padding: 20px;
    box-sizing: border-box;
}

h2 {
    text-align: center;
    margin-bottom: 20px;
}

.backg{
   position:relative;
   background-color:#fff;
   z-index:20;
}


table {
   position : relative;
    width: 70%;
    margin: 0 auto;
    border-collapse: collapse;
    margin-top: 50px;
}

table, th, td {
    border-bottom: 1px solid #ccc;

}
#tr_top {
    border-top: 2px solid #ccc;
    border-bottom: 2px solid #ccc;
    margin-top: 20px;
}
th, td {
    padding: 10px;
    text-align: left;
}



#pageList {
    text-align: center;
    margin-top: 20px;
    margin-bottom: 50px;
}

#emptyArea {
    text-align: center;
    margin-top: 20px;
}

a {
    color: #black;
    text-decoration: none;
}

a:hover {
    text-decoration: underline;
}
/* --------------------------------------------------------- */
.search-wrap {
   margin: 0 auto;
   text-align: center;
}

#search {
   width: 60%;
    padding: 10px;
    margin: 10px;
    height: 40px;
    font-size: 17px;
    margin-right: 0 !important;
    border-right: none;
    border-bottom: 2px solid #000;
}

.btn {
   width: 70px;
    padding: 12px 5px;
    margin: 10px;
    height: 40px;
    border: 2px solid #000;
    background-color: #fff;
    margin-left: 0px;
    vertical-align: bottom;
    border-left: none;
}

input {
  outline: none;
}

/* ----------------------- */

#pageList a, #pageList span {
    display: inline-block;
    padding: 5px 10px;
    margin: 0 5px;
    border: 1px solid #ddd;
    background-color: #f8f9fa;
    color: black;
    text-decoration: none;
    border-radius: 5px;
}

#pageList span {
	margin-right:0%;
    background-color: black;
    color: #fff;
}

#pageList a:hover {
    background-color: #d7b997;
    color: black;
    border-color: #fff;
}

#pageList a.disabled {
    color: #ccc;
    background-color: #f8f9fa;
    border-color: #ddd;
    pointer-events: none;
}



/* ----------------------------- */

@media screen and (max-width: 768px) {
   #listForm {
      width: 100%;
       margin: 20px auto;
       border-radius: 10px;
       padding: 20px;
       box-sizing: border-box;
   }
   table {
      position : relative;
       width: 95%;
       margin: 0 auto;
       border-collapse: collapse;
       margin-top: 50px;
   }
   
}
h2 a{
	text-decoration-line:none;
	color : black;
	
}

.td3, .td0{
	text-align: center;
	
	
}

.td0 a{
	border-radius:25px;
	margin: 10px;
	padding:5px 30px;
/*     background-color:black; */
    text-decoration: none;
    border:1px solid black;
    

	color:black; 
}

.td0 a:hover{
	 text-decoration: none;
   background-color: #D7B997;
   color: black; 
   border: none;
}

footer{
bottom :-400px;

}

</style>
</head>

<body>

<jsp:include page="../header.jsp"></jsp:include>

    <!-- 공지사항 리스트 -->
<div class="backg">
    <section id="listForm">
        <h2>
           
            
            <c:set var="id" value="${sessionScope.active }" />
			<c:choose>
			<c:when test="${active eq 'S' || active eq 'A' }">
            <a href="informWriteForm.bo">공지사항 작성</a>
            </c:when>
			<c:otherwise>
			 공지사항 목록<br>
			</c:otherwise>
			</c:choose>
	
          
<!--                 공지사항 -->
        
        </h2>
    <div id="board-search">
        <div class="container">
            <div class="search-window">
                <form action="informList.bo">
                    <div class="search-wrap">
                        <label for="search" class="blind"></label>
                        <input id="search" type="search" name="search" placeholder="검색어를 입력해주세요."><button type="submit" class="btn"><img src="./img/buttonimg.png"></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
        <table>
<% if(articleList != null && listCount > 0){ %>
            <tr id="tr_top" style="background-color:black; color:#fff;">
                <td class = "td1">번호</td>
                <td>제목</td>
                <td class = "td1">작성자</td>
                <td class = "td2">날짜</td>
                <td class = "td1">조회수</td>
 <%if(session.getAttribute("active") != null && !session.getAttribute("active").equals("Y")){ %>
                <td class="td3">수정/삭제</td>
                <%} %>
             
            </tr>
    <%for(int i = 0; i<articleList.size(); i++){ %>
            <tr>
                <td class = "td1"><%=articleList.get(i).getRownum()%></td>
                <td>
                   
                    <a style="color:black;" href="informDetail.bo?inform_num=<%=articleList.get(i).getInform_NUM()%>&page=<%=nowPage%>">
                        <%=articleList.get(i).getInform_TITLE().length() >=15 ?
                        articleList.get(i).getInform_TITLE().substring(0, 15) + "..." :
                        articleList.get(i).getInform_TITLE()%>
                    </a>
                </td>
                <td class="td1"><%=articleList.get(i).getInform_NM() %></td>
                <td class = "td2"><%=articleList.get(i).getCREATE_DATE() %></td>
                <td class = "td1"><%=articleList.get(i).getREADCOUNT() %></td>
                
 	<%if(session.getAttribute("active") != null && !session.getAttribute("active").equals("Y")){ %>
                <td class="td0"><a href="informModifyForm.bo?inform_num=<%=articleList.get(i).getInform_NUM()%>">수정</a>
                		<a href="informDeletePro.bo?inform_num=<%=articleList.get(i).getInform_NUM()%>">삭제</a></td>
                <%} %>
            </tr>
    <%} %>
        </table>
    </section>

    <section id="pageList">
    <% if(nowPage > 1) { %>
        <a href="informList.bo?page=<%=nowPage-1 %>">이전</a>&nbsp;
    <% } else { %>
    <% } %>

    <% for(int a=startPage; a<=endPage; a++){
            if(a == nowPage){ %>
                <span><%=a %></span>
            <% } else { %>
                <a href="informList.bo?page=<%=a %>"><%=a %></a>&nbsp;
            <% }
       } %>

    <% if(nowPage < maxPage) { %>
        <a href="informList.bo?page=<%=nowPage+1 %>">다음</a>
    <% } else { %>
    <% } %>
</section>
<% } else { %>
    <section id="emptyArea">등록된 글이 없습니다.</section>
<% } %>

<jsp:include page="/footer.jsp"></jsp:include>
</div>


</body>
</html>