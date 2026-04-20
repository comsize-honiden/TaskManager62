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

	TaskBean task = (TaskBean) session.getAttribute("task");
	List<StatusBean> statusList = (List<StatusBean>) session.getAttribute("statusList");
	%>
	<h1>タスク削確認画面</h1>

	<h5>下記のタスクを本当に削除しますか？</h5>

	<form method="POST" action="task-delete-servlet">
		<table border="1" height="100">
			<tr>
				<th>タスク名</th>
				<th>ステータス情報</th>
			</tr>
			<tr>
				<td><%=task.getTaskName()%></td>
				<td>
					<%
					String statusName = "";
					for (StatusBean status : statusList) {
						if (task.getStatusCode().equals(status.getStatusCode())) {
							statusName = status.getStatusName();
						}
					}
					%><%=statusName%>
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