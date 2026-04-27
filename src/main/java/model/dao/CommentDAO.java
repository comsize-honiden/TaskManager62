package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.entity.CommentBean;

public class CommentDAO {
	
// コメント一覧を取得するメソッド
	public List<CommentBean> getCommentList(int taskId) throws ClassNotFoundException, SQLException{
		
		String sql ="SELECT * FROM t_comment WHERE task_id = ?";
		List<CommentBean> result = new ArrayList<CommentBean>();
		
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, taskId);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int commentId = rs.getInt("comment_id");
				String userId = rs.getString("user_id");
				String commentText = rs.getString("comment");
				LocalDateTime updateTime = rs.getTimestamp("update_datetime").toLocalDateTime();
				
				CommentBean comment = new CommentBean();
				
				comment.setCommentId(commentId);
				comment.setUserId(userId);
				comment.setCommentText(commentText);
				comment.setUpdateDateTime(updateTime);
				
				result.add(comment);
			}
		}
		
		return result;
	}

	// コメント登録メソッド
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
		}
		
		return count;
	}
}

