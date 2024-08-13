	<%@ page language="java" contentType="text/html; charset=UTF-8"
    import="java.util.*, bookIn.vo.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>선정 도서관</title>
    <style>
        h1 {
            text-align: center;
        }
        table {
            width: 800px;
            margin: 0 auto;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: center;
        }
    </style>
</head>
<body>
    <h1>도서 정보 조회</h1>
    <%
        ArrayList<BookBean> bookList = (ArrayList<BookBean>)request.getAttribute("bookList");
    %>
    <table>
        <tr>
            <th>장르</th>
            <th>제목</th>
            <th>저자</th>
            <th>발행사항</th>
            <th>등록번호</th>
            <th>청구기호</th>
        </tr>
        <%
            for(BookBean book : bookList) {
        %>
        <tr>
            <td><%= book.getGenre() %></td>
            <td><%= book.getTitle() %></td>
            <td><%= book.getAuthor() %></td>
            <td><%= book.getPub() %></td>
            <td><%= book.getNo() %></td>
            <td><%= book.getBookno() %></td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
