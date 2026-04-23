package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.CommentDAO;
import model.dao.TaskDAO;
import model.entity.CommentBean;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		try {
			request.setCharacterEncoding("UTF-8");
			int taskId = (int) Integer.parseInt(request.getParameter("taskId"));
			TaskDAO taskDao = new TaskDAO();
			CommentDAO commentDao = new CommentDAO();
			
			TaskBean task = taskDao.getTaskDetail(taskId);
			List<CommentBean> commentList = commentDao.getCommentList(taskId);
			
			HttpSession session = request.getSession();
			session.setAttribute("task", task);
			session.setAttribute("commentList", commentList);
			
			RequestDispatcher rd = request.getRequestDispatcher("task-detail.jsp");
			rd.forward(request, response);
			
		} catch (UnsupportedEncodingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("一覧を表示できません。");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("一覧を表示できませんでした。");
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		
	}
}
