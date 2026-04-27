<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    import="java.time.LocalDate, java.util.List, model.entity.TaskBean, model.entity.CategoryBean,
    		model.entity.UserBean, model.entity.StatusBean, model.entity.CommentBean, model.entity.CommentBean,
    		java.time.LocalDateTime, java.time.format.DateTimeFormatter"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>コメント削除確認画面</title><% %>
</head>
<body>
	<h1>コメント削除画面</h1>
	下記のコメントを削除します。よろしいですか？
	<br><br>
	<%
	
	//コメントIDを受けとる
	int commentId = Integer.parseInt(request.getParameter("commentId"));
	System.out.println("コメントID：" + commentId);
	
	List<TaskBean> taskBeanList = (List<TaskBean>)session.getAttribute("taskBeanList");
	List<CategoryBean> categoryBeanList = (List<CategoryBean>)session.getAttribute("categoryBeanList");
	List<UserBean> userBeanList = (List<UserBean>)session.getAttribute("userBeanList");
	List<StatusBean> statusBeanList = (List<StatusBean>)session.getAttribute("statusBeanList");
	List<CommentBean> commentBeanList = (List<CommentBean>)session.getAttribute("commentBeanList");
	
	//コメントIDに一致するCommentBeanオブジェクトをCommentBeanListから取得
	CommentBean comment = new CommentBean();
	
	for (CommentBean newComment : commentBeanList) {
		
		if(newComment.getCommentId() == commentId) {
			
			comment = newComment;
			
		}
		
	}
	
	int taskId = comment.getTaskId();
	
	
	//タスクIDに一致するTaskBeanオブジェクトをtaskBeanListから取得
	TaskBean task = new TaskBean();
	
	for (TaskBean newtask : taskBeanList) {
		
		if(newtask.getTaskId() == taskId) {
			
			task = newtask;
			
		}
		
	}
	
	//期限の取得
	LocalDate limitDate = task.getLimitDate();
	
	//コメントをしたユーザーIDに一致するUserBeanオブジェクトをUserBeanListから取得
	UserBean user = new UserBean();
	
	String userId = comment.getUserId();
	
	for (UserBean newUser : userBeanList) {
		
		
		if(newUser.getUserId().equals(userId)) {
			
			user = newUser;
			
		}
		
	}
	
	
	//投稿日時の表示形式を整える
	LocalDateTime updateDate = comment.getUpdateDateTime();

	DateTimeFormatter formatter =
	    DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH時mm分");

	
	%>
	<b>タスク名：<%= task.getTaskName()%></b><br><br>
	<%=commentId %>&nbsp;&nbsp;&nbsp;&nbsp;
	投稿者：<%=user.getUserName() %>&nbsp;&nbsp;&nbsp;&nbsp;
	投稿日時:<%=updateDate.format(formatter) %><br>
	<div style="width: 560px; height: 50px; padding: 10px; margin-bottom: 10px; border: 1px solid #333333;">
    <%=comment.getComment() %>
	</div>
	
	<table>
		<tr>
			<td>
				<form action="comment-delete-servlet" method="POST">
					<input type="hidden" name="commentId" value="<%=commentId%>">
					<input type="submit" value="削除">
					&nbsp;&nbsp;&nbsp;&nbsp;
				</form>
			</td>
			<td>
				<form action="task-datail.jsp" method="POST">
					<input type="submit" value="タスク詳細画面へ">
				</form>
			</td>
		</tr>
	</table>	
	
</body>
</html>