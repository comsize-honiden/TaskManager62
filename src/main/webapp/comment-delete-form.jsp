<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import=""%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>コメント削除確認画面</title>
</head>
<body>
	<h1>コメント削除画面</h1>
	<br>
	下記のコメントを削除します。よろしいですか？
	<br>
	<%
	//セッションからタスクIDを受け取る 
	int taskId = (int)session.getAttribute("taskId");
	
	TaskBean task = new TaskBean();
	%>
	
	
	
	タスク名：<%= %>

</body>
</html>