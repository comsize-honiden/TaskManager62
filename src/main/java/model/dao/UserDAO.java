package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entity.UserBean;

public class UserDAO {
	//ログイン認証メソッド
	public UserBean getUser(String id, String pass) throws SQLException, ClassNotFoundException {
		
		String sql = "SELECT user_name FROM m_user WHERE user_id = ? AND password = ?";
		
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			
			ResultSet res = pstmt.executeQuery();
			UserBean user = new UserBean();
			
			while (res.next()) {
				String userName = res.getString("user_name");
				
				user.setUserId(id);
				user.setPassword(pass);
				user.setUserName(userName);
			}
			return user;
		}
	}
	//全ユーザー情報リストを生成するメソッド
	public List<UserBean> getUserList() throws SQLException, ClassNotFoundException {
		
		List<UserBean> userList = new ArrayList<>();
		
		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement();
				ResultSet res = stmt.executeQuery("SELECT * FROM m_user")) {
			
			while (res.next()) {
				String userId = res.getString("user_id");
				String password = res.getString("password");
				String userName = res.getString("user_name");
				
				UserBean user = new UserBean();
				user.setUserId(userId);
				user.setPassword(password);
				user.setUserName(userName);

				userList.add(user);
			}
			return userList;
		}
	}
}
