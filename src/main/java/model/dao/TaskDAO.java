package model.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.entity.TaskBean;

public class TaskDAO {
	public List<TaskBean> getTaskList() throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM t_task ";
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
}
