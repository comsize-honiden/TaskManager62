package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
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
		
		HttpSession session = request.getSession(false);
		
		if (session != null) {
			doPost(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("login-servlet.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		request.setCharacterEncoding("UTF-8");
		
		String url = null;
		
		//フォームから送信されたデータを受け取る
		String taskName = request.getParameter("taskName");
		String categoryIdStr = request.getParameter("categoryId");
		String limitDateStr = request.getParameter("limitDate");
		String assigneeId = request.getParameter("assigneeId");
		String statusCode = request.getParameter("statusCode");
		String memo = request.getParameter("memo");
		
		try {
			// 未入力チェック
			if (taskName == null || taskName.isEmpty()) {
				throw new NullPointerException();
			}
			
			if (categoryIdStr == null || categoryIdStr.isEmpty()) {
				throw new NullPointerException();
			}
			
			if (limitDateStr == null) {
				throw new NullPointerException();
			}
			
			if (assigneeId == null || assigneeId.isEmpty()) {
				throw new NullPointerException();
			}
			
			if (statusCode == null || statusCode.isEmpty()) {
				throw new NullPointerException();
			}
			
			if (memo == null) {
				throw new NullPointerException();
			}
			
			// 文字数チェック
			if (taskName.length() > 50) {
				throw new IllegalArgumentException();
			}
			
			if (memo.length() > 100) {
				throw new IllegalArgumentException();
			}
			
			// カテゴリIDをString型からint型に変換する
			int categoryId = Integer.parseInt(categoryIdStr);
			
			// 妥当性チェック用のリストを取得
			
			CategoryDAO categoryDao = new CategoryDAO();
			UserDAO userDao = new UserDAO();
			StatusDAO statusDao = new StatusDAO();
			
			List<CategoryBean> categoryList = categoryDao.getCategoryList();
			List<UserBean> userList = userDao.getUserList();
			List<StatusBean> statusList = statusDao.getStatusList();
			
			request.setAttribute("categoryList", categoryList);
			request.setAttribute("userList", userList);
			request.setAttribute("statusList", statusList);
			
			// 妥当性チェック用のboolean変数
			boolean categoryIdExist = false;
			boolean userIdExist = false;
			boolean statusCodeExist = false;
			
			// リクエストパラメータのカテゴリIDのチェック
			for (CategoryBean category : categoryList) {
				int validCategoryId = category.getCategoryId();
				
				if (categoryId == validCategoryId) {
					categoryIdExist = true;
					break;
				}
			}
			
			if (!categoryIdExist) {
				throw new IllegalArgumentException();
			}
			
			// リクエストパラメータのユーザIDのチェック
			for (UserBean user : userList) {
				String ValidUserId = user.getUserId(); 
				
			 	if (assigneeId.equals(ValidUserId)) {
			 		userIdExist = true;
			 		break;
			 	}
			}
			
			if (!userIdExist) {
				throw new IllegalArgumentException();
			}
			
			// リクエストパラメータのステータスIDのチェック
			for (StatusBean user : statusList) {
				String ValidstatusCode = user.getStatusCode(); 
				
			 	if (statusCode.equals(ValidstatusCode)) {
			 		statusCodeExist = true;
			 		break;
			 	}
			}
			
			if (!statusCodeExist) {
				throw new IllegalArgumentException();
			}
			
			// 期限をString型からLocalDate型に変換する
			LocalDate limitDate;
			
			if (limitDateStr.equals("")) {
				limitDate = null;
			} else {
				limitDate = LocalDate.parse(limitDateStr);
				
				// 日付の妥当性チェック
				if (limitDate.isBefore(LocalDate.now())) {
					throw new DateTimeException("過去の日付は選択できません");
				}
			}
			
			// Beanに代入
>>>>>>> feature/task-register
			TaskBean task = new TaskBean();
			
			task.setTaskName(taskName);
			task.setCategoryId(categoryId);
			task.setLimitDate(limitDate);
			task.setUserId(assigneeId);
			task.setStatusCode(statusCode);
			task.setMemo(memo);
			
			TaskDAO taskDao = new TaskDAO();
			
			// DAOの利用
			if (taskDao.insertTask(task) == 1) {
				response.sendRedirect("task-register-success.jsp");
			} else {
				throw new NullPointerException();
			}
			
		} catch (IllegalArgumentException | 
				NullPointerException | 
				SQLException | 
				ClassNotFoundException |
				DateTimeException e) {
			
			e.printStackTrace();
			
			RequestDispatcher rd = request.getRequestDispatcher("task-register-failure.jsp");
			rd.forward(request, response);
		}
	}

}
