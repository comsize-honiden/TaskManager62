package model.entity;

import java.time.LocalDate;
<<<<<<< HEAD

public class TaskBean {
=======
import java.time.LocalDateTime;

public class TaskBean { 
>>>>>>> feature/task-alter
	private int taskId;
	private String taskName;
	private int categoryId;
	private LocalDate limitDate;
	private String userId;
	private String statusCode;
	private String memo;
<<<<<<< HEAD

=======
	private LocalDateTime createDatetime;
	private LocalDateTime updateDatetime;
 
>>>>>>> feature/task-alter
	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public LocalDate getLimitDate() {
		return limitDate;
	}

	public void setLimitDate(LocalDate limitDate) {
		this.limitDate = limitDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

<<<<<<< HEAD
=======
	public LocalDateTime getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(LocalDateTime createDatetime) {
		this.createDatetime = createDatetime;
	}

	public LocalDateTime getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(LocalDateTime updateDatetime) {
		this.updateDatetime = updateDatetime;
	}
	
	@Override
	public boolean equals(Object updateTaskObj) {
		
		TaskBean updateTask = (TaskBean)updateTaskObj;
		
		if (
			this.taskName.equals(updateTask.getTaskName()) &&
			this.categoryId == updateTask.getCategoryId() &&
			this.limitDate.compareTo(updateTask.getLimitDate()) == 0 &&
			this.userId.equals(updateTask.getUserId())&&
			this.statusCode.equals(updateTask.getStatusCode()) &&
			this.memo.equals(updateTask.getMemo())){
				
			return false;
			
		} else {
			
			return true;
		
		}
		
		
	}

>>>>>>> feature/task-alter
}
