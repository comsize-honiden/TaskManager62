package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.entity.TaskBean;

public class TaskDAO {

	//タスクの変更を行うメソッド
	public int updateTask(TaskBean task) throws SQLException, ClassNotFoundException {
		
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement
					("UPDATE t_task SET"
						+ "task_name = ?"
						+ "categoryId = ?"
						+ "limitDate = ?"
						+ "userId = ?"
						+ "statusCode = ?"
						+ "memo = ?"
						+ "createDatetime = ?"
						+ "updateDatetime = ?"
						+ " WHERE task_id = ?")) {
			
			
		}
		
	}

}
