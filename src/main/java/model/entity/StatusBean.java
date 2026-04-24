package model.entity;

<<<<<<< HEAD
import java.time.LocalDateTime;

public class StatusBean { 
	private String statusCode;
	private String statusName;
	private LocalDateTime updateDatetime;
=======
public class StatusBean {
	private String statusCode;
	private String statusName;
>>>>>>> origin/feature/task-list

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
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
