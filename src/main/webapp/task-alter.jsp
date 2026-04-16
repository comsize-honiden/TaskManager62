<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, model.entity.TaskBean, model.entity.CategoryBean, model.entity.UserBean,
     model.entity.StatusBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク編集画面</title>
</head>
<body>
	<h1>タスク編集画面</h1>
	<%
	//セッションで受け取る必要あり
	int taskId = (int)(request.getAttribute("taskId"));
	
	List<TaskBean> taskBeanList = (List<TaskBean>)session.getAttribute("taskBeanList");
	List<CategoryBean> categoryBeanList = (List<CategoryBean>)session.getAttribute("categoryBeanList");
	List<UserBean> userBeanList = (List<UserBean>)session.getAttribute("userBeanList");
	List<StatusBean> statusBeanList = (List<StatusBean>)session.getAttribute("statusBeanList");
	
	TaskBean task = new TaskBean();
	
	for (TaskBean newtask : taskBeanList) {
		
		if(newtask.getTaskId() == taskId) {
			
			task = newtask;
			
		}
		
	}
	
	%>
	<table border="1">
		<tr>
			<th>タスク名</th>
			<td><%=task.getTaskName()%></td>
		</tr>
		<tr>
			<th>カテゴリ情報</th>
			<td><%=task.getTaskName()%></td>
		</tr>
		<tr>
			<th>期限</th>
			<td><%=task.getTaskName()%></td>
		</tr>
		<tr>
			<th>担当者情報</th>
			<td><%=task.getTaskName()%></td>
		</tr>
		
	</table>
	<%
	
	
	%>
</body>
</html>