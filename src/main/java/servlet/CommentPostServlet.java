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

import model.dao.CommentDAO;
import model.entity.CommentBean;
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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String task = request.getParameter("taskId");
		int taskId = Integer.parseInt(task);
		
		HttpSession session = request.getSession();
		
		//本番用のログインユーザー取得のための記述
		//UserBean user = (UserBean) session.getAttribute("user");
		
		//テスト用のログインユーザー取得のための記述
		List<UserBean> userList = (List<UserBean>) session.getAttribute("userBeanList");
		String userId = null;
		for (UserBean user : userList) {
			userId = user.getUserId();
		}
		
		String commentText = request.getParameter("comment");
		
		CommentBean comment = new CommentBean();
		CommentDAO commentDao = new CommentDAO();
		
		//コメント登録メソッドを呼び出し、登録件数から登録成功か否かを判別して画面遷移させる
		int count = 0;
		try {
			comment.setTaskId(taskId);
			
			//本番用のログインユーザー取得のための記述
			//comment.setUserId(user.getUserId());
			
			//テスト用のログインユーザー取得のための記述
			comment.setUserId(userId);
			
			comment.setCommentText(commentText);
			
			count = commentDao.insertComment(comment);
			
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (count > 0) {
			RequestDispatcher rd = request.getRequestDispatcher("comment-post-success.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("comment-post-failure.jsp");
			rd.forward(request, response);
		}
	}
}
