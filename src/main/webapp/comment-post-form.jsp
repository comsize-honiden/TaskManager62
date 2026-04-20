<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="model.entity.CommentBean"%>
<%@page import="java.util.List"%>
<%@page import="model.entity.UserBean"%>
<%@page import="model.entity.CategoryBean"%>
<%@page import="model.entity.StatusBean"%>
<%@page import="model.entity.TaskBean"%>
<%@page import="model.dao.TaskDAO"%>
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
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html charset=UTF-8");

	//テスト用の記述
	TaskDAO taskDao = new TaskDAO();
	TaskBean task = taskDao.getTaskDetail(1);
	
	//TaskBean task = (TaskBean) session.getAttribute("task");
	List<CategoryBean> categoryList = (List<CategoryBean>) session.getAttribute("categoryBeanList");
	List<StatusBean> statusList = (List<StatusBean>) session.getAttribute("statusBeanList");
	List<UserBean> userList = (List<UserBean>) session.getAttribute("userBeanList");
	%>
	<table border="1">
		<tr>
			<th>タスク名</th>
			<td><%=task.getTaskName()%></td>
		</tr>
		<tr>
			<th>カテゴリ</th>
			<td><%=categoryList.get(task.getCategoryId() - 1).getCategoryName()%></td>
		</tr>
		<tr>
			<th>期限</th>
			<td>
				<%
				if(!(task.getLimitDate() == null)){
				%>
				<%=task.getLimitDate()%>
				<%} %>
			</td>
		</tr>
		<tr>
			<th>担当者情報</th>
			<td>
				<%
				String userName = "";
				for (UserBean user : userList) {
					if (task.getUserId().equals(user.getUserId())) {
						userName = user.getUserName();
					}
				}
				%> <%=userName%>
			</td>
		</tr>
		<tr>
			<th>ステータス</th>
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
		<tr>
			<th>メモ</th>
			<td><%=task.getMemo()%></td>
		</tr>
	</table><br>
	<h3>コメント</h3>
	<form action="comment-post-servlet" method="POST">
		<input type="hidden" name="taskId" value="<%=task.getTaskId()%>">
		<textarea rows="4" cols="25" name="commentText" maxlength="100" required></textarea><br>
		<input type="submit" value="投稿する">
	</form>
</body>
</html>