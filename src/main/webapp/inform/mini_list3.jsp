<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="inform.vo.PageInfo"%>
<%@ page import="inform.vo.InformBean"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>

<%
    ArrayList<InformBean> articleList=(ArrayList<InformBean>)request.getAttribute("articleList");
    PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
    int listCount=pageInfo.getListCount();
    int nowPage=pageInfo.getPage();
    int maxPage=pageInfo.getMaxPage();
    int startPage=pageInfo.getStartPage();
    int endPage=pageInfo.getEndPage();
    String id = (String) session.getAttribute("id");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
    <title>선정 도서관</title>
<style type="text/css">


h2 {
    margin-bottom: 20px;
}

table {
    width: 100%;
    overflow: hidden;
    text-overflow: ellipsis;
}

th, td {
    border: none;
}

th, td {
    padding: 10px;
    text-align: left;
}


#pageList {
    text-align: center;
    margin-top: 20px;
}

#emptyArea {
    text-align: center;
    margin-top: 20px;
}

a {
    color: #007bff;
    text-decoration: none;
}

a:hover {
    text-decoration: underline;
}

.td1 {
   display: flex;
    flex-direction: row-reverse;
    width: 120px;
}

.tdd {
   width:500px;
}

.tdd > a {
   color: #000;
   text-decoration: none;

}
</style>
</head>

<body>
    <!-- 게시판 리스트 -->


        <table>
<% if(articleList != null && listCount > 0){ %>
            <tr id="tr_top">
<!--                 <td>번호</td> -->
<!--                 <td>제목</td> -->
<!--                 <td>작성자</td> -->
<!--                 <td>날짜</td> -->
<!--                 <td>조회수</td> -->
            </tr>
    <%for(int i=0;i<Math.min(3, articleList.size());i++){ %>
            <tr>
<%--                 <td><%=articleList.get(i).getRownum()%></td> --%>
                <td class="tdd">
                    <%if(articleList.get(i).getRE_LEV()!=0){ 
                        for(int a=0;a<=articleList.get(i).getRE_LEV()*2;a++){ %>&nbsp;<% } %>  
                    <%}else{ %>  <%} %> 
                    <a href="informDetail.bo?inform_num=<%=articleList.get(i).getInform_NUM()%>&page=<%=nowPage%>">
                        <%=articleList.get(i).getInform_TITLE().length() >=15 ?
                        articleList.get(i).getInform_TITLE().substring(0, 15) + "..." :
                        articleList.get(i).getInform_TITLE()%>
                    </a>
                </td>
<%--                 <td><%=articleList.get(i).getInform_NM() %></td> --%>
                <td class="td1"><%=articleList.get(i).getCREATE_DATE() %></td>
<%--                 <td><%=articleList.get(i).getREADCOUNT() %></td> --%>
            </tr>
    <%} %>
        </table>

<% } else { %>
    <section id="emptyArea">등록된 글이 없습니다.</section>
<% } %>

</body>
</html>