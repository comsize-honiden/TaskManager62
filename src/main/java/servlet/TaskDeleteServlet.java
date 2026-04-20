package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.TaskDAO;
import model.entity.TaskBean;

/**
 * Servlet implementation class TaskDeleteServlet
 */
@WebServlet("/task-delete-servlet")
public class TaskDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskDeleteServlet() {
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
		HttpSession session = request.getSession();
		TaskBean task = (TaskBean) session.getAttribute("task");
		
		TaskDAO dao = new TaskDAO();
		String url = "";
		try {
			int count = dao.deleteTask(task.getTaskId());
			System.out.println(task.getTaskId());
			if(count >= 1) {
				url = "task-delete-success.jsp";
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("削除に失敗しました。");
			e.printStackTrace();
			url = "task-delete-failure.jsp";
			
		} catch (SQLException e) {
			System.out.println("削除に失敗");
			e.printStackTrace();
			url = "task-delete-failure.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}
}
