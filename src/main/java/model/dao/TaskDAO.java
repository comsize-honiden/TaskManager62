package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

public class TaskDAO {
	public <List>TaskBean getTaskList(TaskBean task) {
		String sql = "SELECT * FROM t_task t1 INNER JOIN m_category t2 ON t1.category_id = t2.category_id";
		<List>TaskBean result = new <List>TaskBean();

		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while(rs.next()) {
				String taskName = rs.getString("task_name");
				String categoryName = rs.getString("category_name");
				Date limitDate = rs.getDate("limit_date");
				String userId = rs.getString("user_id");
				String statusName = rs.getString("status_name");
				
			}
			
		}
		return result;
	}
}
