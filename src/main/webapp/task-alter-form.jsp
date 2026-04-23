<%@page import="java.time.LocalDate"%>
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
	request.setCharacterEncoding("UTF-8");

	//セッションで受け取る必要あり
	Object taskIdObj = null;
	taskIdObj = session.getAttribute("taskId");
		
	if (taskIdObj == null) {
			
		RequestDispatcher rd = request.getRequestDispatcher("task-alter-servlet");
		rd.forward(request, response);
		return;
	}

	int taskId = (int)taskIdObj;
	
	
	List<TaskBean> taskBeanList = (List<TaskBean>)session.getAttribute("taskBeanList");
	List<CategoryBean> categoryBeanList = (List<CategoryBean>)session.getAttribute("categoryBeanList");
	List<UserBean> userBeanList = (List<UserBean>)session.getAttribute("userBeanList");
	List<StatusBean> statusBeanList = (List<StatusBean>)session.getAttribute("statusBeanList");
	
	//タスクIDに一致するTaskBeanオブジェクトをtaskBeanListから取得
	TaskBean task = new TaskBean();
	
	for (TaskBean newtask : taskBeanList) {
		
		if(newtask.getTaskId() == taskId) {
			
			task = newtask;
			
		}
		
	}
	
	//カテゴリIDに一致するCategoryBeanオブジェクトをCategoryBeanListから取得
	CategoryBean category = new CategoryBean();
	
	int categoryId = task.getCategoryId();
	
	for (CategoryBean newCategory : categoryBeanList) {
		
		if(newCategory.getCategoryId() == categoryId) {
			
			category = newCategory;
			
			System.out.println("テスト" + category.getCategoryName());
		}
		
	}
	
	//期限の取得
	LocalDate limitDate = task.getLimitDate();
	//今日の日付を取得
	LocalDate today = LocalDate.now();
	
	
	//ユーザーIDに一致するUserBeanオブジェクトをUserBeanListから取得
	UserBean user = new UserBean();
	
	String userId = task.getUserId();
	
	for (UserBean newUser : userBeanList) {
		
		
		if(newUser.getUserId().equals(userId)) {
			
			user = newUser;
			
		}
		
	}
	
	//ステータスコードに一致するStatusBeanオブジェクトをStatusBeanListから取得
	StatusBean status = new StatusBean();
	
	String stausCode = task.getStatusCode();
	
	for (StatusBean newStatus : statusBeanList) {
		
		if(newStatus.getStatusCode().equals(stausCode)) {
			
			status = newStatus;
			
		}
		
	}
	
	
	
	%>
	<form action="task-alter-servlet" method="POST">
	<table border="1">
		<tr>
			<th>タスク名</th>
			<td>
				<input type="text" maxlength="50" size="60" name="taskName"  value="<%=task.getTaskName()%>" required>
			</td>
		</tr>
		<tr>
			<th>カテゴリ情報</th>
			<td>
			 <select name="categoryId">
			 <%
			 for (int i = 0; i < categoryBeanList.size(); i++) {
				
				 String categoryName = category.getCategoryName();
				 String eachCategoryName = categoryBeanList.get(i).getCategoryName();
				
				 if (categoryName.equals(eachCategoryName)) {
			%>		
					<option value="<%=category.getCategoryId()%>" selected>
						<%=categoryName%>
					</option>	
				<%		 
				 }else {
				%>	 
					<option value="<%=categoryBeanList.get(i).getCategoryId()%>">
						<%=categoryBeanList.get(i).getCategoryName()%>
					</option>
				<% 
				 }
			 }
			 %>
			 </select>
			</td>
		</tr>
		<tr>
			<th>期限</th>
			<td>
			<input type="date" name="limitdate" required value="<%=limitDate %>" min="<%=today%>"/>
			</td>
		</tr>
		<tr>
			<th>担当者情報</th>
			<td> <select name="userId">
			 <%
			 for (int i = 0; i < userBeanList.size(); i++) {
				
				 if (userBeanList.get(i).getUserId().equals(user.getUserId())) {
			%>		
					 <option value="<%=user.getUserId()%>" selected>
						<%=user.getUserName()%>
					</option>	
				<%	
				 }else {
				%>	 
					<option value="<%=userBeanList.get(i).getUserId()%>">
						<%=userBeanList.get(i).getUserName()%>
					</option>
				<% 
				 }
			 }
			 %>
			 </select>
			</td> 
		</tr>
		<tr>
			<th>ステータス情報</th>
			<td> <select name="statusCode">
			 <%
			 for (int i = 0; i < statusBeanList.size(); i++) {
				
				 if (statusBeanList.get(i).getStatusCode().equals(status.getStatusCode())) {
			%>		
					 <option value="<%=status.getStatusCode()%>" selected>
						<%=status.getStatusName()%>
					</option>	
				<%	
				System.out.println("真偽値" + statusBeanList.get(i).getStatusCode().equals(status.getStatusCode()));
				System.out.println("syokiti" + status.getStatusName());
				 }else {
				%>	 
					<option value="<%=statusBeanList.get(i).getStatusCode()%>">
						<%=statusBeanList.get(i).getStatusName()%>
					</option>
				<% 
				System.out.println("test" + statusBeanList.get(i).getStatusName());
				 }
			 }
			 %>
			 </select>
			</td> 
		</tr>
		<tr>
			<th>メモ</th>
			<td>
				<input type="text" maxlength="100" size="110" name="memo"  value="<%=task.getMemo()%>">
			</td> 
		</tr>
	</table>
	<br>
	<table>
		<tr>
			<td>
				<input type="submit" value="変更する">
				</form>
			</td>
			<td>
				<form action="task-detail.jsp" method="GET">
				<input type="submit" value="詳細画面へ">
				</form>
			</td>
		</tr>
	</table>
</body>
</html>