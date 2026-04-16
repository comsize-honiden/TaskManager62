package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.CategoryBean;
import model.entity.StatusBean;
import model.entity.UserBean;

public class SampleDAO {

	public List<UserBean> getUserBeanList() throws SQLException, ClassNotFoundException {
		
		List<UserBean> userBeanList = new ArrayList<>();
		
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement
					("SELECT * FROM m_user")) {
			
			ResultSet res = pstmt.executeQuery();
			
			while (res.next()) {
			
				UserBean user = new UserBean();
				
				String userId = res.getString("user_id");
				String pass = res.getString("password");
				String userName = res.getString("user_name");
				
				user.setUserId(userId);
				user.setPassword(pass);
				user.setUserName(userName);
				
				userBeanList.add(user);
				
			}
			
			//System.out.println(userBeanList.size());
			return userBeanList;
			
		}
		
	}
	
	public List<CategoryBean> getCategoryBeanList() throws SQLException, ClassNotFoundException {
		
		List<CategoryBean> categoryBeanList = new ArrayList<>();
		
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement
					("SELECT * FROM m_category")) {
			
			ResultSet res = pstmt.executeQuery();
			
			while (res.next()) {
			
				CategoryBean category = new CategoryBean();
				
				int categoryId = res.getInt("category_id");
				String categoryName = res.getString("category_name");
				
				category.setCategoryId(categoryId);
				category.setCategoryName(categoryName);
				
				categoryBeanList.add(category);
				
			}
			
			System.out.println(categoryBeanList.size());
			return categoryBeanList;
			
		}
		
	}
	
	public List<StatusBean> getStatusBeanList() throws SQLException, ClassNotFoundException {
		
		List<StatusBean> statusBeanList = new ArrayList<>();
		
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement
					("SELECT * FROM m_status")) {
			
			ResultSet res = pstmt.executeQuery();
			
			while (res.next()) {
			
				StatusBean status = new StatusBean();
				
				String statusCode = res.getString("status_code");
				String statusName = res.getString("status_name");
				
				status.setStatusCode(statusCode);
				status.setStatusName(statusName);
				
				
				statusBeanList.add(status);
				
			}
			
			System.out.println(statusBeanList.size());
			return statusBeanList;
			
		}
		
	}
	
}
