package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.entity.UserBean;

public class UserDAO {
	//ログイン認証メソッド
	public UserBean getUser(String id, String pass) throws SQLException, ClassNotFoundException {
		
		String sql = "SELECT * FROM m_user WHERE user_id = ? AND password = ?";
		
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			
			ResultSet res = pstmt.executeQuery();
			
			if (res.next()) {
				String userId = res.getString("user_id");
				String password = res.getString("password");
				String userName = res.getString("user_name");
				
				UserBean user = new UserBean();
				user.setUserId(userId);
				user.setPassword(password);
				user.setUserName(userName);
				
				return user;
			} else {
				return null;
			}
		}
	}
}
