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

import model.dao.CategoryDAO;
import model.dao.StatusDAO;
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
		doPost(request,response);
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
		
		try {
			//入力値が未入力の場合,例外を投げる
			if (userId == null || pass == null) {
				throw  new NullPointerException();
				
			//入力値が既定の文字数以上の場合,例外を投げる
			} else if (userId.length() > 24 || pass.length() > 32) {
				throw new IllegalArgumentException();
				
			//ログイン認証メソッドを呼び出し、認証した場合はセッションに詰める
			} else {
				user = userDao.getUser(userId,pass);
 			
	 			if (user.getUserName() != null) {
	 				
	 				HttpSession session = request.getSession();
	 				session.setAttribute("user",user);
	 				
	 				//全ユーザーリストを生成し、セッションに詰める
	 				List<UserBean> userList = new ArrayList<>();
	 				userList = userDao.getUserList();
	 				
	 				session.setAttribute("userList",userList);
	 				
	 				//カテゴリーリストを生成し、セッションに詰める
	 				CategoryDAO categoryDao = new CategoryDAO();
	 				List<CategoryBean> categoryList = new ArrayList<>();
	 				categoryList = categoryDao.getCategoryList();
	 				
	 				session.setAttribute("categoryList",categoryList);
	 				
	 				//ステータスリストを生成し、セッションに詰める
	 				StatusDAO statusDao = new StatusDAO();
	 				List<StatusBean> statusList = new ArrayList<>();
	 				statusList = statusDao.getStatusList();
	 				
	 				session.setAttribute("statusList",statusList);
	 				
	 				RequestDispatcher rd = request.getRequestDispatcher("menu.jsp");
	 				rd.forward(request, response);
	 			} else {
	 				RequestDispatcher rd = request.getRequestDispatcher("login-failure.jsp");
	 				rd.forward(request, response);
	 			}
			}
		} catch (SQLException | ClassNotFoundException 
					| IllegalArgumentException | NullPointerException e) {
			e.printStackTrace();
			
			RequestDispatcher rd = request.getRequestDispatcher("login-failure.jsp");
			rd.forward(request, response);
		}
	}
}
