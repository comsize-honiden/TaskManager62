package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import model.entity.TaskBean;

public class TaskDAO {

	//タスクの変更を行うメソッド
	public int taskAlter(TaskBean task) throws SQLException, ClassNotFoundException {
		
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement
					("UPDATE t_task SET task_name = ?, category_id = ?, limit_date = ?,user_id = ?, status_code = ?, memo = ? WHERE task_id = ?;")) {
			
			int taskId = task.getTaskId();
			String taskName = task.getTaskName();
			int categoryId = task.getTaskId();
			LocalDate limitDate = task.getLimitDate();
			String limitDateStr = limitDate.toString();
			String userId = task.getUserId();
			String statusCode = task.getStatusCode();
			String memo = task.getMemo();
			
			pstmt.setString(1, taskName);
			pstmt.setInt(2, categoryId);
			pstmt.setString(3, limitDateStr);
			pstmt.setString(4, userId);
			pstmt.setString(5, statusCode);
			pstmt.setString(6, memo);
			pstmt.setInt(7, taskId);

			
			int res = pstmt.executeUpdate();
			
			return res;
				
		}
		
	}

}
