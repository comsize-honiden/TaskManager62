<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー画面</title>
</head>
<body>
	<h1>メニュー画面</h1>
	<form action="task-register-servlet" method="GET">
		<input type="submit" value="タスク登録">
	</form><br>
	<form action="task-list-servlet" method="POST">
		<input type="submit" value="タスク一覧">
	</form><br>
	<form action="logout.jsp" method="POST">
		<input type="submit" value="ログアウト">
	</form>
</body>
</html>