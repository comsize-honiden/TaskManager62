package model.entity;

import java.sql.Timestamp;
import java.util.Date;

public class TaskBean {
	private int taskId;
	private String taskName;
	private int categoryId;
	private Date limitDate;
	private String userId;
	private String statusCode;
	private String memo;
	private Timestamp createDatetime;
	private Timestamp updateDatetime;

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

	public Date getLimitDate() {
		return limitDate;
	}

	public void setLimitDate(Date limitDate) {
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

	public Timestamp getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Timestamp createDatetime) {
		this.createDatetime = createDatetime;
	}

	public Timestamp getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(Timestamp updateDatetime) {
		this.updateDatetime = updateDatetime;
	}
	
	@Override
	public boolean equals(Object updateTaskObj) {
		
		TaskBean updateTask = (TaskBean)updateTaskObj;
		
		if (
			this.taskName.equals(updateTask.getTaskName()) &&
			this.categoryId == updateTask.getCategoryId() &&
			this.limitDate.compareTo(updateTask.getLimitDate()) == 0 &&
			this.statusCode.equals(updateTask.getStatusCode()) &&
			this.memo.equals(updateTask.equals(updateTask.getMemo()))
			
			
				) 
		{
			
			
		}
		
	}

}
