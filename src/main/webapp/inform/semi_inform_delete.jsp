<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
    int inform_num = (Integer) request.getAttribute("inform_num");
    String nowPage = (String) request.getAttribute("page");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>선정 도서관</title>
<style>
body {
    font-family: Arial, sans-serif;
}

#passForm {
    width: 400px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 10px;
}

label {
    display: block;
    margin-bottom: 10px;
    text-align: center;
}

input[type="password"] {
    width: calc(100% - 20px);
    padding: 10px;
    margin: 0 auto 10px; 
    border-radius: 5px;
    text-align: center;
    display: block;
    box-sizing: border-box; 
}

.bt {
    text-align: center;
}

input[type="submit"],
input[type="button"] {
    padding: 10px;
    border: 1px solid #000;
    border-radius: 5px;
    color: #000;
    cursor: pointer;
    background-color: #f0f0f0;
}

input[type="submit"]:hover,
input[type="button"]:hover {
    background-color: #45a049;
}
</style>
</head>
<body>
    <section id="passForm">
        <form name="deleteForm" action="informDeletePro.bo?inform_num=<%=inform_num %>" method="post">
            <input type="hidden" name="page" value="<%=nowPage %>">
            <label for="delete">삭제하시겠습니까? :</label>
            <input name="delete" type="text"  placeholder="삭제" required >
            <div class="bt">
                <input type="submit" value="삭제">
                <input type="button" value="돌아가기" onclick="javascript:history.go(-1)">
            </div>
        </form>
    </section>
</body>
</html>