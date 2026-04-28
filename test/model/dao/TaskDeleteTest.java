package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import model.entity.TaskBean;

public class TaskDeleteTest {
	
	@Test
	void task_delete_成功() throws ClassNotFoundException, SQLException {
		TaskDAO dao = new TaskDAO();
		TaskBean task = new TaskBean();
		
		task.setTaskId(1);
		task.setCategoryId(0);
		task.setMemo("aaa");
		task.setStatusCode("00");
		task.setTaskName("TaskManager");
		
		int count = dao.deleteTask(task.getTaskId());
		assertTrue(count == 1);
	}
}
