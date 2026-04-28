package model.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.entity.UserBean;

public class UserDAOTest {
	private UserDAO userDao;
	private UserBean user;
	private List<UserBean> userList;
	private String userId;
	private String password;
	private String userName;
	
	@BeforeEach
	void setUp() {
		userDao = new UserDAO();
		user = new UserBean();
		userList = new ArrayList<>();
	}
	@Test
	void login_test_成功() {
		//Arrange
		String userId = "t-yamada";
		String pass = "789";
		//Act
		try {
			user = userDao.getUser(userId, pass);
					
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		//Assert
		assertNotNull(user);
				
		userName = user.getUserName();
		assertEquals("山田太郎",userName);
	}
	@Test
	void login_test_失敗() {
		//Act
		try {
			user = userDao.getUser("", "");
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		//Assert
		userId = user.getUserId();
		password = user.getPassword();
		userName = user.getUserName();
		
		assertNull(userId);
		assertNull(password);
		assertNull(userName);
	}
	@Test
	void userList生成_成功() {
		//Act
		try {
			userList = userDao.getUserList();
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		//Assert
		assertNotNull(userList);
		assertEquals(3,userList.size());
	}
	@Test
	void userList生成_失敗() {
		//Act
		try {
			userList = userDao.getUserList();
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		//Assert
		for (UserBean user : userList) {
			userId = user.getUserId();
			password = user.getPassword();
			userName = user.getUserName();
		}
		assertNull(userId);
		assertNull(password);
		assertNull(userName);
	}
}
