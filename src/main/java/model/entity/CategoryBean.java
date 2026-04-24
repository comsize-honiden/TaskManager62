package model.entity;

<<<<<<< HEAD

import java.time.LocalDateTime;

public class CategoryBean { 
	private int categoryId;
	private String categoryName;
	private LocalDateTime updateDatetime;
=======
public class CategoryBean {
	private int categoryId;
	private String categoryName;
>>>>>>> origin/feature/task-list

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
