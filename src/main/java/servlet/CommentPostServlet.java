package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CommentDAO;
import model.dao.TaskDAO;
import model.entity.CommentBean;
import model.entity.TaskBean;

/**
 * Servlet implementation class CommentPostServlet
 */
@WebServlet("/comment-post-servlet")
public class CommentPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentPostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("sample.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String commentText = request.getParameter("comment");
		
		/* 本番用のタスクIDとログインユーザー取得のための記述
		HttpSession session = request.getSession();
		TaskBean task = (TaskBean) session.getAttribute("task");
		UserBean user = (UserBean) session.getAttribute("user"); */
		
		//テスト用のタスクIDとログインユーザー取得のための記述
		String userId = "t-yamada";
		TaskDAO taskDao = new TaskDAO();
		TaskBean task = null;
		try {
			task = taskDao.getTaskDetail(4);
		
			//コメントが未入力の場合は例外を投げる
			if (commentText == "") {
				RequestDispatcher rd = request.getRequestDispatcher("comment-post-failure.jsp");
				rd.forward(request, response);
				throw new IllegalArgumentException();
			} else {
				CommentBean comment = new CommentBean();
				CommentDAO commentDao = new CommentDAO();
				
				//コメント登録メソッドを呼び出し、登録件数から登録成功か否かを判別して画面遷移させる
				int count = 0;
				comment.setTaskId(task.getTaskId());
					
				//本番用のログインユーザーをBeanに詰める記述
				//comment.setUserId(user.getUserId());
					
				//テスト用のログインユーザーをBeanに詰める記述
				comment.setUserId(userId);
					
				comment.setCommentText(commentText);
					
				count = commentDao.commentPost(comment);
					
				if (count > 0) {
					String url = "comment-post-success.jsp";
					response.sendRedirect(url);
				} else {
					RequestDispatcher rd = request.getRequestDispatcher("comment-post-failure.jsp");
					rd.forward(request, response);
				}
			}
		} catch(SQLException | ClassNotFoundException | IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
}
