package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommentDAO {

	//コメントを削除するメソッド
	public int deleteComment(int commentId) throws SQLException, ClassNotFoundException {
		
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement
					("DELETE FROM t_comment WHERE comment_id = ?")) {
			
			pstmt.setInt(1, commentId);
			
			int res = pstmt.executeUpdate();
			
			System.out.println("削除テスト：" + res);
			
			return res;
			
		}
	}
	
}
