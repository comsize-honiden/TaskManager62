package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entity.StatusBean;

public class StatusDAO {
	public List<StatusBean> getStatusList() throws ClassNotFoundException, SQLException{
		
		String sql = "SELECT * FROM m_status";
		
		List<StatusBean> result = new ArrayList<StatusBean>();
		
		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql)){
			
			while(rs.next()) {
				String statusCode = rs.getString("status_code");
				String statusName = rs.getString("status_name");
				StatusBean status = new StatusBean();
				status.setStatusCode(statusCode);
				status.setStatusName(statusName);
				
				result.add(status);
			}
		}
		
		return result;
	}
}

