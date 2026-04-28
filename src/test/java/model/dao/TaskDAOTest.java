package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import model.entity.TaskBean;

class TaskDAOTest {
//
	@Test
	void タスク編集_正常() {

		//Arrange
		TaskDAO dao = new TaskDAO();
		TaskBean task = new TaskBean();
		
		int taskId= 1;
		String taskName = "編集テスト";
		int categoryId = 2;
		LocalDate limitDate = LocalDate.of(2026, 05, 30);
		String userId = "t-yamada";
		String statusCode = "99";
		String memo = "編集テスト";
		
		task.setTaskId(taskId);
		task.setTaskName(taskName);
		task.setCategoryId(categoryId);
		task.setLimitDate(limitDate);
		task.setUserId(userId);
		task.setStatusCode(statusCode);
		task.setMemo(memo);
		
		//Act
		try {
			
			dao.updateTask(task);
		
		} catch (ClassNotFoundException | SQLException e) {
		
			e.printStackTrace();
		
		}
		
		//Asert
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement
					("SELECT * FROM t_task")) {
			
			ResultSet res = pstmt.executeQuery();
			
			if (res.next()) {
				
				assertEquals(res.getInt("task_id"), taskId, "タスクIDで、DB上のデータと期待値が一致しません");
				assertEquals(res.getString("task_name"), taskName, "タスク名で、DB上のデータと期待値が一致しません");
				assertEquals(res.getInt("category_id"), categoryId, "カテゴリーIDで、DB上のデータと期待値が一致しません");
				assertEquals(res.getDate("limit_date").toLocalDate(), limitDate, "期限で、DB上のデータと期待値が一致しません");				
				assertEquals(res.getString("user_id"), userId, "ユーザーIDで、DB上のデータと期待値が一致しません");
				assertEquals(res.getString("status_code"), statusCode, "ステータスコードで、DB上のデータと期待値が一致しません");
				assertEquals(res.getString("memo"), memo, "メモで、DB上のデータと期待値が一致しません");
				
			}
			
		} catch (SQLException e) {
			
		} catch (ClassNotFoundException e1) {
			
			e1.printStackTrace();
		
		}
		
	}

	@Test
	void タスク編集_異常タスク名null() {
		
		//Arrange
				TaskDAO dao = new TaskDAO();
				TaskBean task = new TaskBean();
				
				int taskId= 1;
				String taskName = null;
				int categoryId = 2;
				LocalDate limitDate = LocalDate.of(2026, 05, 30);
				String userId = "t-yamada";
				String statusCode = "99";
				String memo = "編集テスト";
				
				task.setTaskId(taskId);
				task.setTaskName(taskName);
				task.setCategoryId(categoryId);
				task.setLimitDate(limitDate);
				task.setUserId(userId);
				task.setStatusCode(statusCode);
				task.setMemo(memo);
				
				//Act
			assertThrows(SQLException.class,() -> {
				
				dao.updateTask(task);
				
			});
		
	}

	@Test
	void タスク編集_異常ユーザーIDa() {
		
		//Arrange
				TaskDAO dao = new TaskDAO();
				TaskBean task = new TaskBean();
				
				int taskId= 1;
				String taskName = "編集テスト";
				int categoryId = 2;
				LocalDate limitDate = LocalDate.of(2026, 05, 30);
				String userId = "a";
				String statusCode = "99";
				String memo = "編集テスト";
				
				task.setTaskId(taskId);
				task.setTaskName(taskName);
				task.setCategoryId(categoryId);
				task.setLimitDate(limitDate);
				task.setUserId(userId);
				task.setStatusCode(statusCode);
				task.setMemo(memo);
				
				//Act
			assertThrows(SQLException.class,() -> {
				
				dao.updateTask(task);
				
			});
		
	}
	
	@Test
	void タスク編集_異常カテゴリID10() {
		
		//Arrange
				TaskDAO dao = new TaskDAO();
				TaskBean task = new TaskBean();
				
				int taskId= 1;
				String taskName = "編集テスト";
				int categoryId = 10;
				LocalDate limitDate = LocalDate.of(2026, 05, 30);
				String userId = "t-yamada";
				String statusCode = "99";
				String memo = "編集テスト";
				
				task.setTaskId(taskId);
				task.setTaskName(taskName);
				task.setCategoryId(categoryId);
				task.setLimitDate(limitDate);
				task.setUserId(userId);
				task.setStatusCode(statusCode);
				task.setMemo(memo);
				
				//Act
			assertThrows(SQLException.class,() -> {
				
				dao.updateTask(task);
				
			});
		
	}
	
	@Test
	void タスク編集_異常ステータスコード01() {
		
		//Arrange
				TaskDAO dao = new TaskDAO();
				TaskBean task = new TaskBean();
				
				int taskId= 1;
				String taskName = "編集テスト";
				int categoryId = 2;
				LocalDate limitDate = LocalDate.of(2026, 05, 30);
				String userId = "t-yamada";
				String statusCode = "01";
				String memo = "編集テスト";
				
				task.setTaskId(taskId);
				task.setTaskName(taskName);
				task.setCategoryId(categoryId);
				task.setLimitDate(limitDate);
				task.setUserId(userId);
				task.setStatusCode(statusCode);
				task.setMemo(memo);
				
				//Act
			assertThrows(SQLIntegrityConstraintViolationException.class,() -> {
				
				dao.updateTask(task);
				
			});
		
	}
	
	@Test
	void タスク編集_正常タスク名カラム長() {
		
		//Arrange
				TaskDAO dao = new TaskDAO();
				TaskBean task = new TaskBean();
				
				int taskId= 1;
				String taskName = "12345678901234567890123456789012345678901234567890";
				int categoryId = 2;
				LocalDate limitDate = LocalDate.of(2026, 05, 30);
				String userId = "t-yamada";
				String statusCode = "99";
				String memo = "編集テスト";
				
				task.setTaskId(taskId);
				task.setTaskName(taskName);
				task.setCategoryId(categoryId);
				task.setLimitDate(limitDate);
				task.setUserId(userId);
				task.setStatusCode(statusCode);
				task.setMemo(memo);
				
				//Act
				try {
					
					dao.updateTask(task);
				
				} catch (ClassNotFoundException | SQLException e) {
				
					e.printStackTrace();
				
				}
		
				//Asert
				try (Connection con = ConnectionManager.getConnection();
						PreparedStatement pstmt = con.prepareStatement
							("SELECT * FROM t_task")) {
					
					ResultSet res = pstmt.executeQuery();
					
					if (res.next()) {
						
						assertEquals(res.getString("task_name"), taskName, "タスク名で、DB上のデータと期待値が一致しません");
			
						
					}
					
				} catch (SQLException e) {
					
				} catch (ClassNotFoundException e1) {
					
					e1.printStackTrace();
				
				}
		
	}
	
	@Test
	void タスク編集_異常タスク名カラム長超過() {
		
		//Arrange
				TaskDAO dao = new TaskDAO();
				TaskBean task = new TaskBean();
				
				int taskId= 1;
				String taskName = "123456789012345678901234567890123456789012345678901";
				int categoryId = 2;
				LocalDate limitDate = LocalDate.of(2026, 05, 30);
				String userId = "t-yamada";
				String statusCode = "99";
				String memo = "編集テスト";
				
				task.setTaskId(taskId);
				task.setTaskName(taskName);
				task.setCategoryId(categoryId);
				task.setLimitDate(limitDate);
				task.setUserId(userId);
				task.setStatusCode(statusCode);
				task.setMemo(memo);
				
				//Act
				try {
					
					dao.updateTask(task);
				
				} catch (ClassNotFoundException | SQLException e) {
				
					e.printStackTrace();
				
				}
		
				//Asert
				try (Connection con = ConnectionManager.getConnection();
						PreparedStatement pstmt = con.prepareStatement
							("SELECT * FROM t_task")) {
					
					ResultSet res = pstmt.executeQuery();
					
					if (res.next()) {
						
						assertFalse(res.getString("task_name").equals(taskName), "タスク名で、DB上のデータと期待値が一致しました");
			
						
					}
					
				} catch (SQLException e) {
					
				} catch (ClassNotFoundException e1) {
					
					e1.printStackTrace();
				
				}
		
	}
	
	@Test
	void タスク編集_正常メモカラム長() {
		
		//Arrange
				TaskDAO dao = new TaskDAO();
				TaskBean task = new TaskBean();
				
				int taskId= 1;
				String taskName = "編集テスト";
				int categoryId = 2;
				LocalDate limitDate = LocalDate.of(2026, 05, 30);
				String userId = "t-yamada";
				String statusCode = "99";
				String memo = "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";
				
				task.setTaskId(taskId);
				task.setTaskName(taskName);
				task.setCategoryId(categoryId);
				task.setLimitDate(limitDate);
				task.setUserId(userId);
				task.setStatusCode(statusCode);
				task.setMemo(memo);
				
				//Act
				try {
					
					dao.updateTask(task);
				
				} catch (ClassNotFoundException | SQLException e) {
				
					e.printStackTrace();
				
				}
		
				//Asert
				try (Connection con = ConnectionManager.getConnection();
						PreparedStatement pstmt = con.prepareStatement
							("SELECT * FROM t_task")) {
					
					ResultSet res = pstmt.executeQuery();
					
					if (res.next()) {
						
						assertEquals(res.getString("memo"), memo, "メモで、DB上のデータと期待値が一致しません");
			
					}
					
				} catch (SQLException e) {
					
				} catch (ClassNotFoundException e1) {
					
					e1.printStackTrace();
				
				}
		
	}
	
	@Test
	void タスク編集_異常メモカラム長超過() {
		
		//Arrange
				TaskDAO dao = new TaskDAO();
				TaskBean task = new TaskBean();
				
				int taskId= 1;
				String taskName = "編集テスト";
				int categoryId = 2;
				LocalDate limitDate = LocalDate.of(2026, 05, 30);
				String userId = "t-yamada";
				String statusCode = "99";
				String memo = "12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901";
				
				task.setTaskId(taskId);
				task.setTaskName(taskName);
				task.setCategoryId(categoryId);
				task.setLimitDate(limitDate);
				task.setUserId(userId);
				task.setStatusCode(statusCode);
				task.setMemo(memo);
				
				//Act
				try {
					
					dao.updateTask(task);
				
				} catch (ClassNotFoundException | SQLException e) {
				
					e.printStackTrace();
				
				}
		
				//Asert
				try (Connection con = ConnectionManager.getConnection();
						PreparedStatement pstmt = con.prepareStatement
							("SELECT * FROM t_task")) {
					
					ResultSet res = pstmt.executeQuery();
					
					if (res.next()) {
						
						assertFalse(res.getString("memo").equals(memo), "メモで、DB上のデータと期待値が一致しました");
			
						
					}
					
				} catch (SQLException e) {
					
				} catch (ClassNotFoundException e1) {
					
					e1.printStackTrace();
				
				}
	
	}

}
