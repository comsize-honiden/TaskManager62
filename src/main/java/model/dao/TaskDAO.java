package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entity.TaskBean;

public class TaskDAO {
	public List<TaskBean> getTaskList() throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM t_task";
		List<TaskBean> result = new ArrayList<TaskBean>();

		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while(rs.next()) {
				String taskName = rs.getString("task_name");
				int categoryId = rs.getInt("category_id");
				Date limitDate = rs.getDate("limit_date");
				String userId = rs.getString("user_id");
				String statusCode = rs.getString("status_code");
				String memo = rs.getString("memo");
				
				TaskBean task = new TaskBean();
				
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
}
