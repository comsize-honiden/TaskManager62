<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>削除機能サンプルJSP</h1>
	<%
	//仮のコメントID
	int commentId = 1;
	%>
	削除するコメント
	<a href="comment-delete-form.jsp?commentId=<%=commentId %>"><%=commentId %></a>
</body>
</html>