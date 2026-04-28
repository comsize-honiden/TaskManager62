package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.entity.CategoryBean;

public class CategoryDAOTest {

	@Test
	void get_category_dao_成功() throws ClassNotFoundException, SQLException {
		CategoryDAO dao = new CategoryDAO();
		List<CategoryBean> categoryList = dao.getCategoryList();

		assertNotNull(categoryList);
		assertEquals(categoryList.get(0).getCategoryId(), 1);
		assertEquals(categoryList.get(0).getCategoryName(), "新商品A：開発プロジェクト");
	}
}
