package model.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import model.entity.TaskBean;

class TaskDAOTest {

	@Test
	void testInsertTask_正常なタスクを登録する場合_データベースに登録される() {
		
		// Arrange
		TaskBean task = new TaskBean();
		TaskDAO taskDao = new TaskDAO();
		int insertCount = 0;
		
		task.setTaskName("a");
		task.setCategoryId(1);
		task.setLimitDate(LocalDate.of(2000, 1, 1));
		task.setUserId("i-sato");
		task.setStatusCode("00");
		task.setMemo("");
		
		// Act
		try {
			insertCount = taskDao.insertTask(task);
		} catch (ClassNotFoundException | SQLException e) {
		}
		
		// Assert
		assertEquals(1, insertCount);
	}

	@Test
	void testInsertTask_タスク名にnullを設定する場合_データベースの登録に失敗する() {
		
		// Arrange
		TaskBean task = new TaskBean();
		TaskDAO taskDao = new TaskDAO();
		int insertCount = 0;
		
		task.setTaskName(null);
		task.setCategoryId(1);
		task.setLimitDate(LocalDate.of(2000, 1, 1));
		task.setUserId("i-sato");
		task.setStatusCode("00");
		task.setMemo("");
		
		// Act
		try {
			insertCount = taskDao.insertTask(task);
		} catch (ClassNotFoundException | SQLException e) {
		}
		
		// Assert
		assertEquals(0, insertCount);
	}
	
	@Test
	void testInsertTask_存在しないユーザIDを設定する場合_データベースの登録に失敗する() {
		
		// Arrange
		TaskBean task = new TaskBean();
		TaskDAO taskDao = new TaskDAO();
		int insertCount = 0;
		
		task.setTaskName("a");
		task.setCategoryId(1);
		task.setLimitDate(LocalDate.of(2000, 1, 1));
		task.setUserId("a");
		task.setStatusCode("00");
		task.setMemo("");
		
		// Act
		try {
			insertCount = taskDao.insertTask(task);
		} catch (ClassNotFoundException | SQLException e) {
		}
		
		// Assert
		assertEquals(0, insertCount);
	}

	@Test
	void testInsertTask_存在しないカテゴリIDを設定する場合_データベースの登録に失敗する() {
		
		// Arrange
		TaskBean task = new TaskBean();
		TaskDAO taskDao = new TaskDAO();
		int insertCount = 0;
		
		task.setTaskName("a");
		task.setCategoryId(10);
		task.setLimitDate(LocalDate.of(2000, 1, 1));
		task.setUserId("i-sato");
		task.setStatusCode("00");
		task.setMemo("");
		
		// Act
		try {
			insertCount = taskDao.insertTask(task);
		} catch (ClassNotFoundException | SQLException e) {
		}
		
		// Assert
		assertEquals(0, insertCount);
	}
	
	@Test
	void testInsertTask_存在しないステータスコードを設定する場合_データベースの登録に失敗する() {
		
		// Arrange
		TaskBean task = new TaskBean();
		TaskDAO taskDao = new TaskDAO();
		int insertCount = 0;
		
		task.setTaskName("a");
		task.setCategoryId(1);
		task.setLimitDate(LocalDate.of(2000, 1, 1));
		task.setUserId("i-sato");
		task.setStatusCode("01");
		task.setMemo("");
		
		// Act
		try {
			insertCount = taskDao.insertTask(task);
		} catch (ClassNotFoundException | SQLException e) {
		}
		
		// Assert
		assertEquals(0, insertCount);
	}
	
	@Test
	void testInsertTask_50文字のタスク名を設定する場合_データベースに登録される() {
		
		// Arrange
		TaskBean task = new TaskBean();
		TaskDAO taskDao = new TaskDAO();
		int insertCount = 0;
		
		task.setTaskName("12345678901234567890123456789012345678901234567890");
		task.setCategoryId(1);
		task.setLimitDate(LocalDate.of(2000, 1, 1));
		task.setUserId("i-sato");
		task.setStatusCode("00");
		task.setMemo("");
		
		// Act
		try {
			insertCount = taskDao.insertTask(task);
		} catch (ClassNotFoundException | SQLException e) {
		}
		
		// Assert
		assertEquals(1, insertCount);
	}
	
	@Test
	void testInsertTask_51文字のタスク名を設定する場合_データベースの登録に失敗する() {
		
		// Arrange
		TaskBean task = new TaskBean();
		TaskDAO taskDao = new TaskDAO();
		int insertCount = 0;
		
		task.setTaskName("123456789012345678901234567890123456789012345678901");
		task.setCategoryId(1);
		task.setLimitDate(LocalDate.of(2000, 1, 1));
		task.setUserId("i-sato");
		task.setStatusCode("00");
		task.setMemo("");
		
		// Act
		try {
			insertCount = taskDao.insertTask(task);
		} catch (ClassNotFoundException | SQLException e) {
		}
		
		// Assert
		assertEquals(0, insertCount);
	}
	
	@Test
	void testInsertTask_100文字のメモを設定する場合_データベースに登録される() {
		
		// Arrange
		TaskBean task = new TaskBean();
		TaskDAO taskDao = new TaskDAO();
		int insertCount = 0;
		
		task.setTaskName("a");
		task.setCategoryId(1);
		task.setLimitDate(LocalDate.of(2000, 1, 1));
		task.setUserId("i-sato");
		task.setStatusCode("00");
		task.setMemo("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
		
		// Act
		try {
			insertCount = taskDao.insertTask(task);
		} catch (ClassNotFoundException | SQLException e) {
		}
		
		// Assert
		assertEquals(1, insertCount);
	}
	
	@Test
	void testInsertTask_101文字のメモを設定する場合_データベースの登録に失敗する() {
		
		// Arrange
		TaskBean task = new TaskBean();
		TaskDAO taskDao = new TaskDAO();
		int insertCount = 0;
		
		task.setTaskName("a");
		task.setCategoryId(1);
		task.setLimitDate(LocalDate.of(2000, 1, 1));
		task.setUserId("i-sato");
		task.setStatusCode("00");
		task.setMemo("12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901");
		
		// Act
		try {
			insertCount = taskDao.insertTask(task);
		} catch (ClassNotFoundException | SQLException e) {
		}
		
		// Assert
		assertEquals(0, insertCount);
	}
	
	@Test
	void testInsertTask_期限が設定されていない場合_SQLのDATEもnullになる() {
		
		// Arrange
		TaskBean task = new TaskBean();
		TaskDAO taskDao = new TaskDAO();
		int insertCount = 0;
		
		task.setTaskName("a");
		task.setCategoryId(1);
		task.setLimitDate(null);
		task.setUserId("i-sato");
		task.setStatusCode("00");
		task.setMemo("");
		
		// Act
		try {
			insertCount = taskDao.insertTask(task);
		} catch (ClassNotFoundException | SQLException e) {
		}
		
		// Assert
		assertEquals(1, insertCount);
	}
}
