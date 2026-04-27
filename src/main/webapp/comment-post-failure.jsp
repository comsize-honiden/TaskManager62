<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>コメント投稿エラー画面</title>
</head>
<body>
	<h1>コメント投稿エラー画面</h1>
	<h3>コメントの投稿に失敗しました</h3>
	<form method="GET">
		<input type="submit" value="コメント投稿画面へ" formaction="comment-post-form.jsp">
		<input type="submit" value="一覧画面へ" formaction="task-list.jsp">
	</form>
</body>
</html>