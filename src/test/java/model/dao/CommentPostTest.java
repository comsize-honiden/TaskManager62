package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.dao.CommentDAO;
import model.entity.CommentBean;


public class CommentPostTest {
	private CommentDAO commentDao;
	private CommentBean comment;
	private int count;
	
	@Test
	void get_comment_list_成功() throws ClassNotFoundException, SQLException {
		CommentDAO dao = new CommentDAO();
		List<CommentBean> comment = dao.getCommentList(1);
		
		//Arrange
		comment.setTaskId(1);
		comment.setUserId("i-sato");
		comment.setCommentText("よろしく");
		
		//Act
		try {
			count = commentDao.commentPost(comment);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		//Assert
		assertEquals(1, count);
		
		//Arrange
		comment.setTaskId(0);
		comment.setUserId("i-sato");
		comment.setCommentText("よろしく");
		
		//Act
		try {
			count = commentDao.commentPost(comment);
			
		} catch (ClassNotFoundException | SQLException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		//Assert
		assertEquals(0, count);
	}
	@Test
	void コメント登録_存在しないユーザID_失敗() {
		
		//Arrange
		comment.setTaskId(1);
		comment.setUserId("a");
		comment.setCommentText("よろしく");
		
		//Act
		try {
			count = commentDao.commentPost(comment);
			
		} catch (ClassNotFoundException | SQLException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		//Assert
		assertEquals(0, count);
	}
	@Test
	void コメント登録_コメントnull_失敗() {
		
		//Arrange
		comment.setTaskId(1);
		comment.setUserId("i-sato");
		comment.setCommentText(null);
		
		//Act
		try {
			count = commentDao.commentPost(comment);
			
		} catch (ClassNotFoundException | SQLException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		//Assert
		assertEquals(0, count);
	}
	@Test
	void コメント登録_コメント最大文字数_成功() {

		//Arrange
		comment.setTaskId(1);
		comment.setUserId("i-sato");
		
		//コメントの最大文字数である100文字の文字列をBeanに詰める
		comment.setCommentText("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
		
		//Act
		try {
			count = commentDao.commentPost(comment);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		//Assert
		assertEquals(1, count);
	}
	@Test
	void コメント登録_コメント最大文字数以上_失敗() {
		
		//Arrange
		comment.setTaskId(1);
		comment.setUserId("i-sato");
		
		//コメントの最大文字数である100文字以上の文字列をBeanに詰める
		comment.setCommentText("12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901");
		
		//Act
		try {
			count = commentDao.commentPost(comment);
			
		} catch (ClassNotFoundException | SQLException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		//Assert
		assertEquals(0, count);
	}
}
