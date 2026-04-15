<%@page import="model.entity.StatusBean"%>
<%@page import="model.entity.UserBean"%>
<%@page import="model.entity.CategoryBean"%>
<%@page import="java.util.List"%>
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
	<h3>タスク一覧画面</h3>
	<%
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html charset=UTF-8");

	List<TaskBean> taskList = (List<TaskBean>) request.getAttribute("taskList");
	List<CategoryBean> categoryList = (List<CategoryBean>) session.getAttribute("categoryList");
	List<UserBean> userList = (List<UserBean>) session.getAttribute("userList");
	List<StatusBean> statusList = (List<StatusBean>) session.getAttribute("statusList");

	%>
	<table border=1>
		<th>タスク名</th>
		<th>カテゴリ</th>
		<th>期限</th>
		<th>担当者情報</th>
		<th>メモ</th>

		<%
		for (TaskBean task : taskList) {
			int taskId = task.getTaskId();
		%>
		<tr>
			<td><a
				href="task-detail-servlet?taskId=<%=task.getTaskId()%>">
					<%task.getTaskName();%>
			</a></td>
			<td>
				<%
				String categoryName = "";
				for (CategoryBean category : categoryList) {
					if (task.getCategoryId() == category.getCategoryId()) {
						categoryName = category.getCategoryName();
					}
				}
				%><%=categoryName%>
			</td>
			<td><%=task.getLimitDate()%></td>
			<td>
				<%
				String userName = "";
				for (UserBean user : userList) {
					if (task.getUserId() == user.getUserId()) {
						userName = user.getUserName();
					}
				}
				%><%=userName%>
			</td>
			<td>
				<%
				String statusName = "";
				for (StatusBean status : statusList) {
					if (task.getStatusCode() == status.getStatusCode()) {
						statusName = status.getStatusName();
					}
				}
				%><%=statusName%>
			</td>
			<td><%=task.getMemo()%></td>
		</tr>
			<%
			}
			%>
	</table>
</body>
</html>