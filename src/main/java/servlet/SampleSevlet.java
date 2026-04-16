package servlet;
 
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.SampleNoDAO;
import model.entity.CategoryBean;
import model.entity.StatusBean;
import model.entity.TaskBean;
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
		
		List<UserBean> userBaenList = new ArrayList<>();
		List<CategoryBean> categoryBeanList = new ArrayList<>();
		List<StatusBean> statusBeanList = new ArrayList<>();
		List<TaskBean> taskBeanList = new ArrayList<>();
		
		
		SampleNoDAO sampleDao = new SampleNoDAO();
		
		try {
			
			userBaenList = sampleDao.getUserBeanList();
			categoryBeanList = sampleDao.getCategoryBeanList();
			statusBeanList = sampleDao.getStatusBeanList();
			taskBeanList = sampleDao.getTaskBeanList();
			
			//テスト用
			System.out.println("各リストの長さ");
			System.out.println("userBaenList:" + userBaenList.size());
			System.out.println("categoryBeanList:" + categoryBeanList.size());
			System.out.println("statusBeanList:" + statusBeanList.size());
			System.out.println("taskBeanList:" + taskBeanList.size());
			
			
		}catch (SQLException | ClassNotFoundException e) {
			 System.out.println("error");
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("userBaenList",userBaenList );
		session.setAttribute("categoryBeanList", categoryBeanList);
		session.setAttribute("statusBeanList", statusBeanList);
		session.setAttribute("taskBeanList", taskBeanList);
		
		//転送先を代入
		String forwadUrl = "";
		
		RequestDispatcher rd = request.getRequestDispatcher(forwadUrl);
		
		rd.forward(request, response); 
		
	}

}
