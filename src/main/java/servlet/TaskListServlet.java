package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.CategoryDAO;
import model.dao.StatusDAO;
import model.dao.TaskDAO;
import model.dao.UserDAO;
import model.entity.CategoryBean;
import model.entity.StatusBean;
import model.entity.TaskBean;
import model.entity.UserBean;

/**
 * Servlet implementation class TaskListServlet
 */
@WebServlet("/task-list-servlet")
public class TaskListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TaskListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		TaskDAO taskDao = new TaskDAO();
		CategoryDAO categoryDao = new CategoryDAO();
		StatusDAO statusDao = new StatusDAO();
		UserDAO userDao = new UserDAO();
		UserBean user = new UserBean();
		
		try {
			List<TaskBean> taskList = taskDao.getTaskList();
			List<CategoryBean> categoryList = categoryDao.getCategoryList();
			List<StatusBean> statusList = statusDao.getStatusList();
			List<UserBean> userList = userDao.getUserList();
			
			HttpSession session = request.getSession();
<<<<<<< HEAD
			session.setAttribute("taskList", taskList);
			session.setAttribute("categoryList", categoryList);
			session.setAttribute("statusList", statusList);
			session.setAttribute("userList", userList);
			//session.setAttribute("user", user);
=======
	//		session.setAttribute("user", user);	山村コメントアウト
			session.setAttribute("taskList", taskList); //山村追記
>>>>>>> e6f6d54 (修正　タスク編集)
			
			RequestDispatcher rd = request.getRequestDispatcher("task-list.jsp");
			rd.forward(request, response);
			
		} catch (ClassNotFoundException e) {
			System.out.println("一覧を表示できません。");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("一覧を表示できませんでした。");
			e.printStackTrace();
		}
	}
}
