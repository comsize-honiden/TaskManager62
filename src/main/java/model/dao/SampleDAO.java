package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.entity.CategoryBean;
import model.entity.StatusBean;
import model.entity.TaskBean;
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
			
			System.out.println("1");
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
			
			System.out.println("2");
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
			
			System.out.println("3");
			return statusBeanList;
			
		}
		
	}
	
	public List<TaskBean> getTaskBeanList() throws SQLException, ClassNotFoundException {
		
		List<TaskBean> taskBeanList = new ArrayList<>();
		
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement
					("SELECT * FROM t_task")) {
			
			ResultSet res = pstmt.executeQuery();
			
			while (res.next()) {
			
				TaskBean task = new TaskBean();
				
				int taskId = res.getInt("task_id");
				String taskName = res.getNString("task_name");
				int categoryId = res.getInt("category_id");
				LocalDate limitDate = res.getObject("limit_date", LocalDate.class);
				String userId = res.getString("user_id");
				String statusCode = res.getString("status_code");
				String memo = res.getString("memo");
				LocalDateTime createDatetime = res.getObject("create_datetime", LocalDateTime.class);
				LocalDateTime updateDatetime = res.getObject("update_datetime", LocalDateTime.class);
				
				task.setTaskId(taskId);
				task.setTaskName(taskName);
				task.setCategoryId(categoryId);
				task.setLimitDate(limitDate);
				task.setUserId(userId);
				task.setStatusCode(statusCode);
				task.setMemo(memo);
				task.setCreateDatetime(createDatetime);
				task.setUpdateDatetime(updateDatetime); 
				
				
				taskBeanList.add(task);
				
			}
			
			System.out.println("4");
			return taskBeanList;
			
		}
		
	}
	
}
