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
import model.entity.CategoryBean;
import model.entity.CommentBean;
import model.entity.StatusBean;
import model.entity.TaskBean;
import model.entity.UserBean;

/**
 * Servlet implementation class SampleSevlet
 */
@WebServlet("/sample-servlet")
public class SampleSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SampleSevlet() { 
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
		response.setContentType("text/html; charset=UTF=8");
		
		//サンプルデータ生成
		
		List<UserBean> userBeanList = new ArrayList<>();
		List<CategoryBean> categoryBeanList = new ArrayList<>();
		List<StatusBean> statusBeanList = new ArrayList<>();
		List<TaskBean> taskBeanList = new ArrayList<>();
		List<CommentBean> commentBeanList = new ArrayList<>();
		
		
		SampleDAO sampleDao = new SampleDAO();
		
		try {
			
			userBeanList = sampleDao.getUserBeanList();
			categoryBeanList = sampleDao.getCategoryBeanList();
			statusBeanList = sampleDao.getStatusBeanList();
			taskBeanList = sampleDao.getTaskBeanList();
			commentBeanList = sampleDao.getCommentBeanList();
			
			//テスト用
			System.out.println("各リストの長さ");
			System.out.println("userBaenList:" + userBeanList.size());
			System.out.println("categoryBeanList:" + categoryBeanList.size());
			System.out.println("statusBeanList:" + statusBeanList.size());
			System.out.println("taskBeanList:" + taskBeanList.size());
			
			
		}catch (SQLException | ClassNotFoundException e) {
			 System.out.println("error");
		}
		
		HttpSession session = request.getSession();
		
		//仮の受け取るタスク番号taskId = 1
		int taskId = 1;
		session.setAttribute("taskId", taskId);
		//仮の受け取るコメント番号commentId = 1
		int commentId = 1;
		session.setAttribute("commentId", commentId);
		
		session.setAttribute("userBeanList", userBeanList);
		session.setAttribute("categoryBeanList", categoryBeanList);
		session.setAttribute("statusBeanList", statusBeanList);
		session.setAttribute("taskBeanList", taskBeanList);
		session.setAttribute("commentBeanList", commentBeanList);
		
		System.out.println(userBeanList.get(1).getUserName());
		//転送先を代入
		String forwadUrl = "sample-delete.jsp";
		
		RequestDispatcher rd = request.getRequestDispatcher(forwadUrl);
		
		rd.forward(request, response); 
		
	}

}
