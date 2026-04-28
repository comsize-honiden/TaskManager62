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
	private String pass;
	private String userName;
	
	@BeforeEach
	void setUp() {
		userDao = new UserDAO();
		user = new UserBean();
		userList = new ArrayList<>();
	}
	@Test
	void login_正常値を設定_成功() {
		//Arrange
		userId = "i-sato";
		pass = "123";
		//Act
		try {
			user = userDao.getUser(userId, pass);
					
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		//Assert
		assertNotNull(user);
				
		userName = user.getUserName();
		assertEquals("佐藤一郎",userName);
	}
	@Test
	void login_存在しないユーザIDを設定_失敗() {
		//Arrange
		userId = "a-aoi";
		pass = "123";
		//Act
		try {
			user = userDao.getUser(userId, pass);
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		//Assert
		userId = user.getUserId();
		pass = user.getPassword();
		userName = user.getUserName();
		
		assertNull(userId);
		assertNull(pass);
		assertNull(userName);
	}
	@Test
	void login_存在しないパスワードを設定_失敗() {
		//Arrange
		userId = "i-sato";
		pass = "321";
		//Act
		try {
			user = userDao.getUser(userId, pass);
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		//Assert
		userId = user.getUserId();
		pass = user.getPassword();
		userName = user.getUserName();
		
		assertNull(userId);
		assertNull(pass);
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
/*	@Test
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
			
			assertNull(userId);
			assertNull(password);
			assertNull(userName);
		}
	}*/
}
