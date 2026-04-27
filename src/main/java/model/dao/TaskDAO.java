package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.entity.TaskBean;

public class TaskDAO {
	
	public int insertTask(TaskBean task)
		throws SQLException, ClassNotFoundException {
		
		int insertCount;
		
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement
				("INSERT INTO t_task (task_name, category_id, limit_date, user_id, status_code, memo) VALUES (?, ?, ?, ?, ?, ?)")) {
			
			String taskName = task.getTaskName();
			int categoryId = task.getCategoryId();
			LocalDate limitDate = task.getLimitDate();
			String userId = task.getUserId();
			String statusCode = task.getStatusCode();
			String memo = task.getMemo();
			
			Date sqlLimitDate;
			
			if (limitDate == null) {
				sqlLimitDate = null;
			} else {
				sqlLimitDate = Date.valueOf(limitDate);
			}
			
			pstmt.setString(1, taskName);
			pstmt.setInt(2, categoryId);
			pstmt.setDate(3, sqlLimitDate);
			pstmt.setString(4, userId);
			pstmt.setString(5, statusCode);
			pstmt.setString(6, memo);
				
			insertCount = pstmt.executeUpdate();
		}
		
		return insertCount;
	}

	public List<TaskBean> getTaskList() throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM t_task";
		List<TaskBean> result = new ArrayList<TaskBean>();

		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				int taskId = rs.getInt("task_id");
				String taskName = rs.getString("task_name");
				int categoryId = rs.getInt("category_id");
				LocalDate limitDate = rs.getObject("limit_date", LocalDate.class);
				String userId = rs.getString("user_id");
				String statusCode = rs.getString("status_code");
				String memo = rs.getString("memo");

				TaskBean task = new TaskBean();

				task.setTaskId(taskId);
				task.setTaskName(taskName);
				task.setCategoryId(categoryId);
				task.setLimitDate(limitDate);
				task.setUserId(userId);
				task.setStatusCode(statusCode);
				task.setMemo(memo);

				result.add(task);
			}
		}
		return result;
	}

	public TaskBean getTaskDetail(int taskId) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM t_task WHERE task_id = ?";
		TaskBean result = new TaskBean();
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, taskId);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String taskName = rs.getString("task_name");
				int categoryId = rs.getInt("category_id");
				LocalDate limitDate = rs.getObject("limit_date", LocalDate.class);
				String userId = rs.getString("user_id");
				String statusCode = rs.getString("status_code");
				String memo = rs.getString("memo");

				result.setTaskId(taskId);
				result.setTaskName(taskName);
				result.setCategoryId(categoryId);
				result.setLimitDate(limitDate);
				result.setUserId(userId);
				result.setStatusCode(statusCode);
				result.setMemo(memo);
			}
		}
		return result;
	}

	public int deleteTask(int taskId) throws ClassNotFoundException, SQLException {
		String taskSql = "DELETE FROM t_task WHERE task_id = ?";
		String commentSql = "DELETE FROM t_comment WHERE task_id = ?";
		int count = 0;
		try (Connection con = ConnectionManager.getConnection()){
//			con.setAutoCommit(false);
				try(PreparedStatement commentPstmt = con.prepareStatement(commentSql)){
					commentPstmt.setInt(1, taskId);
					commentPstmt.executeUpdate();
				}
				try(PreparedStatement taskPstmt = con.prepareStatement(taskSql)){
					taskPstmt.setInt(1, taskId);
					count = taskPstmt.executeUpdate();
				}
//			con.commit();
		}
		return count;
	}
}
