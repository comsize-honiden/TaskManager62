<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, 
    	java.time.LocalDate, 
    	
    	model.entity.TaskBean, 
    	model.entity.CategoryBean, 
    	model.entity.UserBean, 
    	model.entity.StatusBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク登録画面</title>
</head>
<body>
	<h1>タスク登録画面</h1>
	<%
		session = request.getSession(false);
		request.setCharacterEncoding("UTF-8");	
	
		if (session == null) {
			RequestDispatcher rd = request.getRequestDispatcher("login-servlet.jsp");
			rd.forward(request, response);
		}
		
		List<TaskBean> taskBeanList
			= (List<TaskBean>)session.getAttribute("taskList");
		
		List<CategoryBean> categoryList
			= (List<CategoryBean>)session.getAttribute("categoryList");
		
		List<UserBean> userList
			= (List<UserBean>)session.getAttribute("userList");
		
		List<StatusBean> statusList
			= (List<StatusBean>)session.getAttribute("statusList");
	%>
	<form action="task-register-servlet" method="POST">
		<table border="1" >
			<tr>
				<th align="center">タスク名</th>
				<td>
					<input type="text" name="taskName" maxlength="50" required>
				</td>
			</tr>
			<tr>
				<th align="center">カテゴリ情報</th>
				<td>
					<select name="categoryId">
					
					<%
						for (CategoryBean category : categoryList) {
					%>	
							<option value="<%=category.getCategoryId()%>"><%=category.getCategoryName()%></option>
					<%	
						}
					%>
					</select>
				
				</td>
			</tr>
			<tr>
				<th align="center">期限</th>
				<td>
					<input type="date" name="limitDate" value="" min=<%=LocalDate.now()%>>
				</td>
			</tr>
			<tr>
				<th align="center">担当者情報</th>
				<td>
					<select name="assigneeId">
					<%
						for (UserBean user : userList) {
					%>	
							<option value="<%=user.getUserId()%>"><%=user.getUserName()%></option>
					<%	
						}
					%>
					</select>
				</td>
			</tr>
			<tr>
				<th align="center">ステータス情報</th>
				<td>
					<select name="statusCode">
					<%
						for (StatusBean status : statusList) {
					%>	
							<option value="<%=status.getStatusCode()%>"><%=status.getStatusName()%></option>
					<%	
						}
					%>
					</select>
				</td>
			</tr>
			<tr>
				<th align="center">メモ</th>
				<td>
					<input type="text" name="memo" value="" maxlength="100">
				</td>
			</tr>
		</table>
		<table>
			<tr>
				<td>
					<input type="submit" value="登録する">
				</td>
				<td>
					<input type="reset" value="クリア">
				</td>
		</table>
	</form>
	<form action="menu.jsp" method="GET">
		<input type="submit" value="メニュー画面へ">
	</form>
</body>
</html>