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
import model.dao.UserDAO;
import model.entity.CategoryBean;
import model.entity.StatusBean;
import model.entity.UserBean;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login-servlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		String userId = request.getParameter("userId");
		String pass = request.getParameter("pass");
		
		UserBean user = new UserBean();
		UserDAO userDao = new UserDAO();
		
		//ログイン認証メソッドを呼び出し、認証された場合はセッションに詰める
		try {
 			user = userDao.getUser(userId,pass);
 			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (user.getUserName() != null) {
			
			HttpSession session = request.getSession();
			session.setAttribute("user",user);
			
			//全ユーザーリストを生成し、セッションに詰める
			List<UserBean> userList = new ArrayList<>();
			try {
				userList = userDao.getUserList();
				
			} catch(SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			session.setAttribute("userList",userList);
			
			//カテゴリーリストを生成し、セッションに詰める
			SampleDAO sampleDao = new SampleDAO();
			List<CategoryBean> categoryList = new ArrayList<>();
			try {
				categoryList = sampleDao.getCategoryBeanList();
				
			} catch(SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			session.setAttribute("categoryList",categoryList);
			
			//ステータスリストを生成し、セッションに詰める
			List<StatusBean> statusList = new ArrayList<>();
			try {
				statusList = sampleDao.getStatusBeanList();
				
			} catch(SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			session.setAttribute("statusList",statusList);
			
			RequestDispatcher rd = request.getRequestDispatcher("menu.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("login-failure.jsp");
			rd.forward(request, response);
		}
	}
}
