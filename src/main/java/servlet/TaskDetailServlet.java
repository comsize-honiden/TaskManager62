package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CategoryDAO;
import model.dao.TaskDAO;
import model.entity.TaskBean;

/**
 * Servlet implementation class TaskDetailServlet
 */
@WebServlet("/task-detail-servlet")
public class TaskDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TaskDetailServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		taskBean task = (taskBean) request.getAttribute("taskBean");
		TaskDAO taskDao = new TaskDAO();
		CategoryDAO categoryDao = new CategoryDAO();
		StatusDAO StatusDao = new StatusDAO();
		try {
			TaskBean task = taskDao.getTaskDetail(task);
			CategoryBean category = categoryDao.getCategoryDetail()
			request.setAttribute("task", task);
			RequestDispatcher rd = request.getRequestDispatcher("task-detail.jsp");
			rd.forward(request, response);
		}
	}
}
