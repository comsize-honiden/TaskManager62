package model.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	void testInsertTask_カラム長制限を超過しないタスク名を設定する場合_データベースに登録される() {
		
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
	void testInsertTask_カラム長制限を超過するタスク名を設定する場合_データベースの登録に失敗する() {
		
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
	void testInsertTask_カラム長制限を超過しないメモを設定する場合_データベースに登録される() {
		
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
	void testInsertTask_カラム長制限を超過するメモを設定する場合_データベースの登録に失敗する() {
		
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
	void testInsertTask_期限が設定されていないタスクを登録する場合_期限のカラムにnullが登録される() {
		
		// Arrange
		TaskBean task = new TaskBean();
		TaskDAO taskDao = new TaskDAO();
		Date limitDate = null;
		
		task.setTaskName("b");
		task.setCategoryId(1);
		task.setLimitDate(null);
		task.setUserId("i-sato");
		task.setStatusCode("00");
		task.setMemo("");
		
		// Act
		try {
			taskDao.insertTask(task);
			
			Connection con = ConnectionManager.getConnection();
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery("SELECT limit_date FROM t_task WHERE task_name = 'b'");
			
			while (res.next()) {
				limitDate = res.getDate("limit_date");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
		}
		
		// Assert
		assertNull(limitDate);
	}
	
	@Test
	void testInsertTask_期限が設定されているタスクを登録する場合_設定した期限が登録される() {
		
		// Arrange
		TaskBean task = new TaskBean();
		TaskDAO taskDao = new TaskDAO();
		Date actualLimitDate = new Date(0);
		
		task.setTaskName("c");
		task.setCategoryId(1);
		task.setLimitDate(LocalDate.of(2000, 1, 1));
		task.setUserId("i-sato");
		task.setStatusCode("00");
		task.setMemo("");
		
		// Act
		try {
			taskDao.insertTask(task);
			
			Connection con = ConnectionManager.getConnection();
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery("SELECT limit_date FROM t_task WHERE task_name = 'c'");
			
			while (res.next()) {
				actualLimitDate = res.getDate("limit_date");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
		}
		
		// Assert
		assertNotNull(actualLimitDate);
		assertEquals(LocalDate.of(2000, 1, 1), actualLimitDate.toLocalDate());
	}
}
