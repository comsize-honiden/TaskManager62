<%@page import="model.entity.UserBean"%>
<%@page import="model.entity.CategoryBean"%>
<%@page import="model.entity.StatusBean"%>
<%@page import="model.entity.TaskBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TaskManager62</title>
</head>
<body>
	<%
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html charset=UTF-8");

	TaskBean task = (TaskBean) request.getAttribute("task");
	CategoryBean category = (CategoryBean) request.getAttribute("category");
	UserBean user = (UserBean) request.getAttribute("user");
	StatusBean status = (StatusBean) request.getAttribute("status");
	session.setAttribute("taskId", task.getTaskId());
	%>
	<h3>タスク詳細画面</h3>
	<table>
		<tr>
			<th>タスク名</th>
			<td><%=task.getTaskName()%></td>
		</tr>
		<tr>
			<th>カテゴリ</th>
			<td><%=category.getCategoryName()%></td>
		</tr>
		<tr>
			<th>期限</th>
			<td><%=task.getLimitDate()%></td>
		</tr>
		<tr>
			<th>担当者情報</th>
			<td><%=user.getUserName()%></td>
		</tr>
		<tr>
			<th>ステータス</th>
			<td><%=status.getStatusName()%></td>
		</tr>
		<tr>
			<td>
				<form method="POST" action="task-alter-form.jsp">
					<input type="submit" value="編集">
				</form>
			</td>

			<td>
				<form method="POST" action="task-delete-confirm.jsp">
					<input type="submit" value="削除">
				</form>
			</td>
		<tr>
	</table>
</body>
</html>