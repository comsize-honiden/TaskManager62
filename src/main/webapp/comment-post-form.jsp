<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="model.entity.CommentBean"%>
<%@page import="java.util.List"%>
<%@page import="model.entity.UserBean"%>
<%@page import="model.entity.CategoryBean"%>
<%@page import="model.entity.StatusBean"%>
<%@page import="model.entity.TaskBean"%>
<%@page import="model.dao.TaskDAO"%>
<%@page import="model.dao.CategoryDAO"%>
<%@page import="model.dao.StatusDAO"%>
<%@page import="model.dao.UserDAO"%>
<%@page import = "java.util.ArrayList" %>
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

	//詳細画面でセッションに詰められた対象タスクの詳細を取得する
	//TaskBean task = (TaskBean) session.getAttribute("task");
	
	//テスト用にタスクの詳細を取得する
	TaskDAO taskDao = new TaskDAO();
	TaskBean task = taskDao.getTaskDetail(8);
	
	//対象タスクが別ブラウザで削除された場合は投稿失敗画面を表示する
	if (task.getTaskId() == 0 && task.getTaskName() == null 
			&& task.getCategoryId() == 0 && task.getUserId() == null
			&& task.getStatusCode() == null) {
		RequestDispatcher rd = request.getRequestDispatcher("comment-post-failure.jsp");
		rd.forward(request, response);
		
	} else {
		//全ユーザーリストを生成する
		List<UserBean> userList = new ArrayList<>();
		UserDAO userDao = new UserDAO();
		userList = userDao.getUserList();
		
		//カテゴリーリストを生成する
		List<CategoryBean> categoryList = new ArrayList<>();
		CategoryDAO categoryDao = new CategoryDAO();
		categoryList = categoryDao.getCategoryList();
		
		//ステータスリストを生成する
		List<StatusBean> statusList = new ArrayList<>();
		StatusDAO statusDao = new StatusDAO();
		statusList = statusDao.getStatusList();
		
		//対象タスクの詳細を表示する
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
			<textarea rows="4" cols="25" name="comment" maxlength="100" required></textarea><br>
			<input type="submit" value="投稿する">
		</form><br>
		<form action="task-list.jsp" method="GET">
			<input type="submit" value="一覧画面へ" formaction="task-list.jsp">
		</form>
	<% }%>
</body>
</html>