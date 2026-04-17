package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

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
 * Servlet implementation class TaskAlterServlet
 */
@WebServlet("/task-alter-servlet")
public class TaskAlterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskAlterServlet() {
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
		
		HttpSession session = request.getSession();
		
		List<TaskBean> taskBeanList = (List<TaskBean>)session.getAttribute("taskBeanList");
		
		//変更前のタスクを取得
		TaskBean task = new TaskBean();
		
		int taskId = Integer.parseInt(request.getParameter("taskId"));
		
		for (TaskBean newTask : taskBeanList) {
			
			if(newTask.getTaskId() == taskId) {
				
				task = newTask;
				
			}
			
		}
		
		//変更後のタスクオブジェクトを用意
		TaskBean updateTask = new TaskBean();
		
		updateTask.setTaskId(task.getTaskId());
		updateTask.setTaskName(task.getTaskName());
		updateTask.setCategoryId(taskId);
		updateTask.setLimitDate(task.getLimitDate());
		updateTask.setUserId(task.getUserId());
		updateTask.setStatusCode(task.getStatusCode());
		updateTask.setMemo(task.getMemo());
		updateTask.setCreateDatetime(task.getCreateDatetime());
		updateTask.setUpdateDatetime(task.getUpdateDatetime());
		 
		
		//変更後の値を取得
		String taskName = request.getParameter("taskName");
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		LocalDate limitDate = LocalDate.parse(request.getParameter("limitdate"));
		String userId = request.getParameter("userId");
		String statusCode = request.getParameter("statusCode");
		String memo = request.getParameter("memo");
		
		//変更後の値でオブジェクトの中身を更新
		updateTask.setTaskName(taskName);
		updateTask.setCategoryId(categoryId);
		updateTask.setLimitDate(limitDate);
		updateTask.setUserId(userId);
		updateTask.setStatusCode(statusCode);
		updateTask.setMemo(memo);
		
		session.setAttribute("updateTask", updateTask);
		
		TaskDAO taskDao = new TaskDAO();
		
		String url = null;
		
		if (task.equals(updateTask)) {
			//成功処理に遷移するコード
			System.out.println("変更有");
			try {
				
				int res = taskDao.updateTask(updateTask);
				System.out.println("res:" + res);
				
				if (res == 1) {
					
					url = "task-alter-success.jsp";
			
				} else {
					
					url = "task-alter-failure.jsp";
					
				}
			
			} catch (SQLException | ClassNotFoundException e) {
			
				e.printStackTrace();
		
			}
			
			
		} else {
			//失敗画面に遷移するコード
			System.out.println("変更なし");
			url = "task-alter-failure.jsp";
		}
		
		System.out.println(url);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
