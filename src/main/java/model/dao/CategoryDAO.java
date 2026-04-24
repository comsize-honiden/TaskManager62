package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entity.CategoryBean;

public class CategoryDAO {
	public List<CategoryBean> getCategoryList() throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM m_category";
		List<CategoryBean> result = new ArrayList<CategoryBean>();

		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				int CategoryId = rs.getInt("category_id");
				String CategoryName = rs.getString("category_name");

				CategoryBean category = new CategoryBean();

				category.setCategoryId(CategoryId);
				category.setCategoryName(CategoryName);

				result.add(category);
			}
		}
		return result;
	}

	public CategoryBean getCategoryDetail(int categoryId) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM m_category WHERE CategoryId = ?";
		CategoryBean category = new CategoryBean();
		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				String categoryName = rs.getString("category_name");

				category.setCategoryName(categoryName);
			}
		}
		return category;
	}
}

