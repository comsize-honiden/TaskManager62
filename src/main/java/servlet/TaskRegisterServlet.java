package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TaskDAO;
import model.entity.TaskBean;

/**
 * Servlet implementation class TaskRegisterServlet
 */
@WebServlet("/task-register-servlet")
public class TaskRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskRegisterServlet() {
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
		
		String url = null;
		
		try {
			int taskId = request.getParameter("taskId");
			String taskName = request.getParameter("taskName");
			int categoryId = request.getParameter("categoryId");
			LocalDate limitDate =request.getParameter("limitDate");
			String userId = request.getParameter("userId");
			String statusCode = request.getParameter("statusCode");
			String memo = request.getParameter("memo");
			
			TaskBean task = new TaskBean();
			
			task.setTaskId(taskId);
			task.setTaskName(taskName);
			task.setCategoryId(categoryId);
			task.setLimitDate(limitDate);
			task.setUserId(userId);
			task.setStatusCode(statusCode);
			task.setMemo(memo);
			
			TaskDAO taskDao = new TaskDAO();
			
			if (taskDao.insertTask(task) == 1) {
				url = "register-success.jsp";
			} else {
				throw new NullPointerException();
			}
			
		} catch (IllegalArgumentException | 
				NullPointerException | 
				SQLException | 
				ClassNotFoundException e) {
			
			url = "register-failure.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
