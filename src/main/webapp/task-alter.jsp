<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, model.entity.TaskBean, model.entity.CategoryBean"%>
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
	TaskBean task = (TaskBean)session.getAttribute("Task");
	List<CategoryBean> categoryBeanList = (List<CategoryBean>)session.getAttribute("categoryBeanList");
	
	%>
	<table>
		<tr>
			<th>タスク名</th>
			<td><%=task.getTaskName()%></td>
		</tr>
		<tr>
			<th>カテゴリ情報</th>
			<td><%=itemCategoryBean.getItemName()%></td>
		</tr>
		<tr>
			<th>価格</th>
			<td><%=itemCategoryBean.getPrice()%>円</td>
		</tr>
	</table>
	<%
	
	
	%>
</body>
</html>