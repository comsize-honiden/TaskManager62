<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
</head>
<body>
	
	<h1>ログイン画面</h1>
	<form action="login-servlet" method="POST">
		<table border="1" style="border-collapse: collapse">
			<tr>
				<th>ユーザーID</th>
				<td><input type ="text" name="userId"></td>
			</tr>	
			<tr>
				<th>パスワード</th>
				<td><input type ="password" name="pass"></td>
			</tr>
		</table><br>
		<input type="submit" value="ログイン">
		<input type="reset" value="クリア">
	</form>
</body>
</html>