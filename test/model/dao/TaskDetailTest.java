package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import model.entity.TaskBean;

public class TaskDetailTest {

	@Test
	void task_detail_成功() throws ClassNotFoundException, SQLException {
		TaskDAO dao = new TaskDAO();
		TaskBean task = null;

		int taskId = 1;
		task = dao.getTaskDetail(taskId);

		assertEquals(task.getCategoryId(), 1);
		assertEquals(task.getStatusCode(), "00");
		assertEquals(task.getTaskName(), "チームメンバー面談");
		assertEquals(task.getUserId(), "h-suzuki");

	}
}
