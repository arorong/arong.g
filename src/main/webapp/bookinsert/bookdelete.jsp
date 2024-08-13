<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="bookIn.vo.BookBean"%>
<% BookBean book = (BookBean)request.getAttribute("book"); %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>선정 도서관</title>
    <style>
        #deleteForm { width: 400px; margin: auto; border: 1px solid orange; }
    </style>
    <script>
        function confirmDelete() {
            var confirmAction = confirm("정말 삭제하시겠습니까?");
            if (!confirmAction) {
                alert("삭제가 취소되었습니다.");
                event.preventDefault();
            }
        }
    </script>
</head>
<body>
    <section id="deleteForm">
    <h2>도서 정보 삭제</h2>
        <form action="bookDelete.bok" method="post" name="deleteForm" onsubmit="confirmDelete()">
            <%-- <input type="hidden" name="page" value="<%=nowPage %>"> --%>
            <table>
                <tr>
                    <td class="td_left"><label for="bookno">도서 번호:</label></td>
                    <td class="td_right"><input type="text" name="no" id="no" value="<%=book.getNo()%>"></td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" value="삭제">&nbsp;
                        <input type="button" value="돌아가기" onclick="javascript:history.go(-1)">
                    </td> 
                </tr>
            </table>
        </form>
    </section>
</body>
</html>