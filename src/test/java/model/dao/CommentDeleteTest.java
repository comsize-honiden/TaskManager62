package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class CommentDeleteTest {
////
	@Test
	void コメント削除_正常() {
		
		//Arrange
		CommentDAO dao = new CommentDAO();
		int res = 0;
		
		//Act
		try {
			
			res = dao.deleteComment(1);
		
		} catch (SQLException | ClassNotFoundException e) {
			
			e.printStackTrace();
			
		}
		
		//Assert
		assertEquals(1, res,"戻り値が期待値と一致しません");
	
	}

	@Test
	void コメント削除_失敗() {
		
		//Arrange
		CommentDAO dao = new CommentDAO();
		int res = 1;
		
		//Act
		try {
			
			res = dao.deleteComment(0);
		
		} catch (SQLException | ClassNotFoundException e) {
			
			e.printStackTrace();
			
		}
		
		//Assert
		assertEquals(0, res,"戻り値が期待値と一致しません");
	
	}
	
	
}
