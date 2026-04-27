package model.entity;

import java.time.LocalDateTime;

public class CommentBean {
	private int commentId;
	private int taskId;
	private String userId;
<<<<<<< HEAD
	private String comment;
=======
	private String commentText;
>>>>>>> feature/comment-post
	private LocalDateTime updateDateTime;

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

<<<<<<< HEAD
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
=======
	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
>>>>>>> feature/comment-post
	}

	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

}
