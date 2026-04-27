package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.entity.CommentBean;

public class CommentDAOTest {
	private CommentDAO commentDao;
	private CommentBean comment;
	
	@BeforeEach
	void setUp() {
		commentDao = new CommentDAO();
		comment = new CommentBean();
	}
	@Test
	void コメント登録_正常な入力_成功() {
		
		//Arrange
		comment.setTaskId(8);
		comment.setUserId("i-sato");
		comment.setCommentText("よろしく");
		int count = 0;
		
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
	void コメント登録_存在しないタスクID_失敗() {
		
		//Arrange
		comment.setTaskId(0);
		comment.setUserId("i-sato");
		comment.setCommentText("よろしく");
		int count = 0;
		
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
		comment.setTaskId(8);
		comment.setUserId("a");
		comment.setCommentText("よろしく");
		int count = 0;
		
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
		comment.setTaskId(8);
		comment.setUserId("i-sato");
		comment.setCommentText(null);
		int count = 0;
		
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
		comment.setTaskId(8);
		comment.setUserId("i-sato");
		
		//コメントの最大文字数である100文字の文字列をBeanに詰める
		comment.setCommentText("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
		int count = 0;
		
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
		comment.setTaskId(8);
		comment.setUserId("i-sato");
		
		//コメントの最大文字数である100文字以上の文字列をBeanに詰める
		comment.setCommentText("12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901");
		int count = 0;
		
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
