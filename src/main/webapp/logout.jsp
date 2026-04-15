<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,model.entity.UserBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログアウト画面</title>
</head>
<body>
	<div style="text-align: center;">
		<h1>ログアウト画面</h1>
		<%
			UserBean user = (UserBean) session.getAttribute("user");
		%>
		お疲れ様でした！ <%=user.getUserName() %>さん
		<% session.invalidate(); %>
		<h3>ログアウトしました</h3>
		<form action="login.jsp" method="POST">
			<input type="submit" value="ログイン画面へ">
		</form>
	</div>
</body>
</html>