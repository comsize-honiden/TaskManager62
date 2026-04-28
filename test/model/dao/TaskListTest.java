package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.entity.TaskBean;

public class TaskListTest {
	
	@Test
	void task_list_成功() {
		TaskDAO dao = new TaskDAO();
		List<TaskBean> taskList = null;
		try {
			taskList = dao.getTaskList();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		assertNotNull(taskList);
		assertEquals(taskList.size(),3);
		assertEquals(taskList.get(0).getStatusCode(),"00");
		
	}
	
	@Test
	void task_list_失敗() {
		TaskDAO dao = new TaskDAO();
		List<TaskBean> taskList = null;
		try {
			taskList = dao.getTaskList();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
