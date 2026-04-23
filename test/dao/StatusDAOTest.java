package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.dao.StatusDAO;
import model.entity.StatusBean;

public class StatusDAOTest {

	@Test
	void get_status_成功() {
		StatusDAO dao = new StatusDAO();
		List<StatusBean> status = new ArrayList<>();
		
		try {
			status = dao.getStatusList();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		assertNotNull(status);
		assertEquals(status.get(0).getStatusCode(),"00");
		assertEquals(status.get(0).getStatusName(),"未着手");
	}
	
	void get_status_失敗() {
		StatusDAO dao = new StatusDAO();
		List<StatusBean> status = new ArrayList<>();
		
		try {
			status = dao.getStatusList();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		assertNull(status);
		StatusBean bean = new StatusBean();
		bean.setStatusCode("99");
		status.add(bean);
		assertNotNull(status.get(77).getStatusName());
	}
}
