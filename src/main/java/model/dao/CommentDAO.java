package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.entity.CommentBean;

public class CommentDAO {
	//コメント登録メソッド
	public int commentPost(CommentBean comment) throws ClassNotFoundException, SQLException {
		
		int count;
		String sql = "INSERT INTO t_comment (task_id, user_id, comment) VALUES (?,?,?)";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			int taskId = comment.getTaskId();
			String UserId = comment.getUserId();
			String commentText = comment.getCommentText();
			
			pstmt.setInt(1, taskId);
			pstmt.setString(2, UserId);
			pstmt.setString(3, commentText);

			count = pstmt.executeUpdate();
			return count;
		}
	}
}