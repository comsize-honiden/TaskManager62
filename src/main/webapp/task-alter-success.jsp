<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, model.entity.TaskBean, model.entity.CategoryBean, model.entity.UserBean,
     model.entity.StatusBean, java.time.LocalDate"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>変更登録成功</title>
</head>
<body>
	<h1>次のデータを変更登録しました</h1>
	<%
	TaskBean task = (TaskBean)session.getAttribute("updateTask");
	
	List<CategoryBean> categoryBeanList = (List<CategoryBean>)session.getAttribute("categoryBeanList");
	List<UserBean> userBeanList = (List<UserBean>)session.getAttribute("userBeanList");
	List<StatusBean> statusBeanList = (List<StatusBean>)session.getAttribute("statusBeanList");
	
	//カテゴリIDに一致するCategoryBeanオブジェクトをCategoryBeanListから取得
	CategoryBean category = new CategoryBean();
	
	int categoryId = task.getCategoryId();
	
	for (CategoryBean newCategory : categoryBeanList) {
		
		if(newCategory.getCategoryId() == categoryId) {
			
			category = newCategory;
			
		}
		
	}
	
	//期限の取得
	LocalDate limitDate = task.getLimitDate();
	
	
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
	<table border="1">
		<tr>
			<th>タスク名</th>
			<td>
				<%=task.getTaskName() %>
			</td>
		</tr>
		<tr>
			<th>カテゴリ情報</th>
			<td>
				<%=category.getCategoryName()%>
			</td>
		</tr>
		<tr>
			<th>期限</th>
			<td>
				<%= task.getLimitDate().format(
					    java.time.format.DateTimeFormatter.ofPattern("yyyy年M月d日")
						) %>
			</td>
		</tr>
		<tr>
			<th>担当者情報</th>
			<td>
				<%=user.getUserName() %>
			</td> 
		</tr>
		<tr>
			<th>ステータス情報</th>
			<td>
				<%=status.getStatusName() %>
			</td> 
		</tr>
		<tr>
			<th>メモ</th>
			<td>
				<%=task.getMemo() %>
			</td> 
		</tr>
	</table>
	<%
	session.removeAttribute("taskId");
	%>
	<br>
	<form action="menu.jsp" method="GET">
	<input type="submit" value="メニュー画面へ">
</body>
</html>