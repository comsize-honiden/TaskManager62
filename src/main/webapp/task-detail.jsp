<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="model.entity.CommentBean"%>
<%@page import="java.util.List"%>
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

	UserBean user = (UserBean) session.getAttribute("user");
	TaskBean task = (TaskBean) session.getAttribute("task");
	List<CommentBean> commentList = (List<CommentBean>) session.getAttribute("commentList");
	List<CategoryBean> categoryList = (List<CategoryBean>) session.getAttribute("categoryList");
	List<StatusBean> statusList = (List<StatusBean>) session.getAttribute("statusList");
	List<UserBean> userList = (List<UserBean>) session.getAttribute("userList");
	%>
	<h1>タスク詳細画面</h1>
	<table border=1 height="150">
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
				if (!(task.getLimitDate() == null)) {
				%> <%=task.getLimitDate()%>
				<%
				}
 				%>
			</td>
		</tr>
		<tr>
			<th>担当者情報</th>
			<td>
				<%
				String userName = "";
				for (UserBean userBean : userList) {
					if (task.getUserId().equals(userBean.getUserId())) {
						userName = userBean.getUserName();
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
	</table>
	<%
	if (user.getUserId().equals(task.getUserId())) {
	%>
	<table>
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
		</tr>
	</table>
	<%
	}
	%>
	<h3>コメント</h3>
	<form method="POST" action="comment-post-form.jsp">
		<input type="submit" value="コメントを投稿">
	</form>
	<%
	int i = 1;
	for (CommentBean comment : commentList) {
	%>
	<%
	if (user.getUserId().equals(comment.getUserId())) {
	%>
	<a href="comment-delete-servlet?commentId=<%=comment.getCommentId()%>">
	<%} %>
	<%=i%>
	</a>
	<%
	String CommentUserName = "";
	for (UserBean userBean : userList) {
		if (comment.getUserId().equals(userBean.getUserId())) {
			CommentUserName = userBean.getUserName();
		}
	}
	i++;
	%>
	投稿者:
	<%=CommentUserName%>
	<%
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	%>
	投稿日時:
	<%=comment.getUpdateDateTime().format(formatter)%>
	<table border=1>
		<tr>
			<td><%=comment.getComment()%></td>
		</tr>
	</table>
	<%
	}
	%>
</body>
</html>