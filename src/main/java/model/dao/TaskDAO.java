package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

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
}
