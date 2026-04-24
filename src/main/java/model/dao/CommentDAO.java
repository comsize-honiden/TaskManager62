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
				String comment = rs.getString("comment");
				LocalDateTime updateTime = rs.getTimestamp("update_datetime").toLocalDateTime();
				
				CommentBean commentBean = new CommentBean();
				
				commentBean.setCommentId(commentId);
				commentBean.setUserId(userId);
				commentBean.setComment(comment);
				commentBean.setUpdateDateTime(updateTime);
				
				result.add(commentBean);
			}
		}
		return result;
	}
}
