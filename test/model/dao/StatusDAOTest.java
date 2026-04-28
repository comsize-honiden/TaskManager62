package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.entity.StatusBean;

public class StatusDAOTest {

	@Test
	void get_status_成功() throws ClassNotFoundException, SQLException {
		StatusDAO dao = new StatusDAO();
		List<StatusBean> status = new ArrayList<>();
		
		status = dao.getStatusList();
		
		assertNotNull(status);
		assertEquals(status.get(0).getStatusCode(),"00");
		assertEquals(status.get(0).getStatusName(),"未着手");
	}
}
