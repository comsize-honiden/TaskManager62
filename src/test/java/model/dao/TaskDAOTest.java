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
	void testInsertTask_期限が設定されていない場合_SQLのDATEもnullになる() {
		
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
}
