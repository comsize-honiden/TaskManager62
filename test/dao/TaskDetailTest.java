package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import model.dao.TaskDAO;
import model.entity.TaskBean;

public class TaskDetailTest {
	
	@Test
	void task_detail_成功() {
		TaskDAO dao = new TaskDAO();
		TaskBean task = null;
		try {
			int taskId = 1;
			task = dao.getTaskDetail(taskId);
		}catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		assertEquals(task.getCategoryId(),1);
		assertEquals(task.getStatusCode(),"00");
		assertEquals(task.getTaskName(),"チームメンバー面談");
		assertEquals(task.getUserId(),"i-sato");
		
	}
	
	void task_detail_失敗() throws ClassNotFoundException, SQLException {
		TaskDAO dao = new TaskDAO();
		TaskBean task = null;
		
		int taskId = 99;
		task = dao.getTaskDetail(taskId);
		
		assertNull(task.getLimitDate());
		assertNull(task.getCategoryId());
		
	}
}
