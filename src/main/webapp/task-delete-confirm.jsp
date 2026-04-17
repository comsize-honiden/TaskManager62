<%@page import="java.util.List"%>
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
	List<StatusBean> status = (List<StatusBean>) session.getAttribute("status");
	%>
	<h3>タスク削確認画面</h3>

	<h5>下記のタスクを本当に削除しますか？</h5>

	<form method="POST" action="task-delete-servlet">
		<table>
			<tr>
				<td>タスク名</td>
				<td>ステータス情報</td>
			</tr>
			<tr>
				<td><%=task.getTaskName()%></td>
				<td>
					<%
					String statusName = "";
					for (StatusBean statusList : status) {
						if (statusList.getStatusCode() == task.getStatusCode()) {
							statusName = statusList.getStatusName();
						}
					}
					%>
					<%=statusName%>
				</td>
			</tr>
		</table>
		<input type="submit" value="削除する">
	</form>
	<form method="POST" action="task-detail.jsp">
		<input type="submit" value="詳細画面へ">
	</form>
</body>
</html>