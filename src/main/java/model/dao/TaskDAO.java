package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import model.entity.TaskBean;

public class TaskDAO {
	
	public int insertTask(TaskBean task)
		throws SQLException, ClassNotFoundException {
		
		int insertCount;
		
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement
					("INSERT INTO t_task (task_id, task_name, category_id, limit_date, user_id, status_code, memo) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
			
			ResultSet res = pstmt.executeQuery();
			
			int taskId = task.getTaskId();
			String taskName = task.getTaskName();
			int categoryId = task.getCategoryId();
			LocalDate limitDate = task.getLimitDate();
			String userId = task.getUserId();
			String statusCode = task.getStatusCode();
			String memo = task.getMemo();
			
			Date sqlLimitDate = Date.valueOf(limitDate);
			
			if (taskName.length() <= 50
				&& userId.length() <= 24
				&& memo.length() <= 100) {
			
				pstmt.setInt(1, taskId);
				pstmt.setString(2, taskName);
				pstmt.setInt(3, categoryId);
				pstmt.setDate(4, sqlLimitDate);
				pstmt.setString(5, userId);
				pstmt.setString(6, statusCode);
				pstmt.setString(7, memo);
				insertCount = pstmt.executeUpdate();
			
			} else {
				insertCount = 0;
			}
		}
		
		return insertCount;
	}
}
