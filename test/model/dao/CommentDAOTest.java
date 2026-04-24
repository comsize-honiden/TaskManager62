package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class CommentDAOTest {

	@Test
	void コメント削除_成功(){
	
		//Arrange
		CommentDAO dao = new CommentDAO();
		int commentId = 1;
		int res = 0;
		
		//Act
		try {
			
			res = dao.deleteComment(commentId);
		
		} catch (ClassNotFoundException | SQLException e) {
		
			e.printStackTrace();
		}
		
		//Assert
	assertEquals(res, 1, "戻り値が期待値と一致しません");
	
	}

	@Test
	void コメント削除_失敗(){
	
		//Arrange
		CommentDAO dao = new CommentDAO();
		int commentId = 100;
		int res = 0;
		
		//Act
		try {
			
			res = dao.deleteComment(commentId);
		
		} catch (ClassNotFoundException | SQLException e) {
		
			e.printStackTrace();
		}
		
		//Assert
	assertEquals(res, 0, "戻り値が期待値と一致しません");
	
	}

}


