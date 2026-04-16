package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.SampleDAO;
import model.entity.CategoryBean;
import model.entity.StatusBean;
import model.entity.UserBean;

/**
 * Servlet implementation class SampleSevlet
 */
@WebServlet("/sample-servlet")
public class SampleSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SampleSevlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF=8");
		
		//サンプルデータ生成
		UserBean user = new UserBean();
		List<UserBean> userBaenList = new ArrayList<>();
		List<CategoryBean> categoryBeanList = new ArrayList<>();
		List<StatusBean> statusBeanList = new ArrayList<>();
		
		
		SampleDAO sampleDao = new SampleDAO();
		
		try {
			userBaenList = sampleDao.getUserBeanList();
			categoryBeanList = sampleDao.getCategoryBeanList();
			
			
		}catch (SQLException | ClassNotFoundException e) {
			
		}
		
	}

}
