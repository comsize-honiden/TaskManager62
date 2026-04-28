package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.entity.CommentBean;

public class CommentDAOTest {
	
	@Test
	void get_comment_list_成功() throws ClassNotFoundException, SQLException {
		CommentDAO dao = new CommentDAO();
		List<CommentBean> comment = dao.getCommentList(1);
		
		assertNotNull(comment);
		assertEquals(comment.get(0).getCommentId(),1);
		assertEquals(comment.get(0).getCommentText(),"よろしくお願いします");
		assertEquals(comment.get(0).getUserId(),"h-suzuki");
				
	}
		
}
