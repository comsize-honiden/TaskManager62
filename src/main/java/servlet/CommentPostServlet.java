package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.CommentDAO;
import model.entity.CommentBean;
import model.entity.TaskBean;
import model.entity.UserBean;

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
		
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String commentText = request.getParameter("comment");
		
		// 対象タスクの詳細とログインユーザーをセッションから取得する
		HttpSession session = request.getSession();
		TaskBean task = (TaskBean) session.getAttribute("task");
		UserBean user = (UserBean) session.getAttribute("user");
		
		try {
			//コメントが未入力の場合は例外を投げる
			if (commentText == "") {
				//コメントが未入力の場合は投稿失敗画面を表示し、例外を投げる
				RequestDispatcher rd = request.getRequestDispatcher("comment-post-failure.jsp");
				rd.forward(request, response);
				throw new IllegalArgumentException();
			
			} else if (commentText.length() > 100) {
				//コメントが規定の文字数以上の場合は投稿失敗画面を表示し、例外を投げる
				RequestDispatcher rd = request.getRequestDispatcher("comment-post-failure.jsp");
				rd.forward(request, response);
				throw new SQLException();
				
			} else if (task.getTaskId() == 0 && task.getTaskName() == null 
						&& task.getCategoryId() == 0 && task.getUserId() == null
						&& task.getStatusCode() == null) {
				//対象タスクが別ブラウザで削除された場合は投稿失敗画面を表示し、例外を投げる
				RequestDispatcher rd = request.getRequestDispatcher("comment-post-failure.jsp");
				rd.forward(request, response);
				throw new SQLIntegrityConstraintViolationException();
				
			} else {
				CommentBean comment = new CommentBean();
				CommentDAO commentDao = new CommentDAO();
				
				comment.setTaskId(task.getTaskId());
				comment.setUserId(user.getUserId());
				comment.setCommentText(commentText);

				//コメント登録メソッドを呼び出し、登録件数から登録成功か否かを判別して画面遷移させる
				int count = 0;
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
