<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>コメント投稿画面</title>
</head>
<body>
	<h1>コメント投稿画面</h1>
	<%
	TaskBean task = (TaskBean) session.getAttribute("task");
	%>
	<table border="1">
		<tr>
			<th>タスク名</th>
			<td><%=task.getTaskName()%></td>
		</tr>
		<tr>
			<th>カテゴリ</th>
			<td><%=task.getCategoryName() %></td>
		</tr>
		<tr>
			<th>期限</th>
			<td><%=task.getLimitDate()%></td>
		</tr>
		<tr>
			<th>担当者情報</th>
			<td><%=task.getAssigneeName%></td>
		</tr>
		<tr>
			<th>ステータス</th>
			<td><%=task.getStatusName()%></td>
		</tr>
		<tr>
			<th>メモ</th>
			<td><%=task.getMemo()%></td>
		</tr>
	</table><br>
	<h3>コメント</h3>
	<form action="comment-post-servlet" method="POST">
		<textarea name="comment" maxlength="100" required></textarea><br>
		<input type="submit" value="投稿する">
	</form>
</body>
</html>