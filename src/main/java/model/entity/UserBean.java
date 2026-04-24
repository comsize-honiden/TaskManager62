package model.entity;

<<<<<<< HEAD
import java.time.LocalDateTime;

public class UserBean { 
	private String userId;
	private String password;
	private String userName;
	private LocalDateTime updateDatetime;
=======
public class UserBean {
	private String userId;
	private String password;
	private String userName;
>>>>>>> origin/feature/task-list

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
<<<<<<< HEAD

	public LocalDateTime getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetimet(LocalDateTime updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

=======
>>>>>>> origin/feature/task-list
}
