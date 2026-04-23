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

import model.dao.SampleDAO;
import model.entity.CategoryBean;
import model.entity.StatusBean;
import model.entity.TaskBean;
import model.entity.UserBean;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/test-servlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() { 
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF=8");
		
		//サンプルデータ生成
		
		List<UserBean> userList = new ArrayList<>();
		List<CategoryBean> categoryList = new ArrayList<>();
		List<StatusBean> statusList = new ArrayList<>();
		List<TaskBean> taskList = new ArrayList<>();
		
		
		SampleDAO sampleDao = new SampleDAO();
		
		try {
			
			userList = sampleDao.getUserList();
			categoryList = sampleDao.getCategoryList();
			statusList = sampleDao.getStatusList();
			taskList = sampleDao.getTaskList();
			
			//テスト用
			System.out.println("各リストの長さ");
			System.out.println("userList:" + userList.size());
			System.out.println("categoryList:" + categoryList.size());
			System.out.println("statusList:" + statusList.size());
			System.out.println("taskList:" + taskList.size());
			
			
		}catch (SQLException | ClassNotFoundException e) {
			 System.out.println("error");
		}
		
		HttpSession session = request.getSession();
		
		//仮の受け取るタスク番号taskId = 1
		int taskId = 1;
		session.setAttribute("taskId", taskId);
		
		session.setAttribute("userList", userList);
		session.setAttribute("categoryList", categoryList);
		session.setAttribute("statusList", statusList);
		session.setAttribute("taskList", taskList);
		
		System.out.println(userList.get(1).getUserName());
		//転送先を代入
		String forwadUrl = "task-register.jsp";
		
		RequestDispatcher rd = request.getRequestDispatcher(forwadUrl);
		
		rd.forward(request, response); 
		
	}

}
