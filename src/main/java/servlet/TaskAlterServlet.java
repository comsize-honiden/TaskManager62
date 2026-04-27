package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.TaskDAO;
import model.entity.CategoryBean;
import model.entity.StatusBean;
import model.entity.TaskBean;
import model.entity.UserBean;

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

		this.doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF=8");
		
		HttpSession session = request.getSession();
		
		List<TaskBean> taskList = (List<TaskBean>)session.getAttribute("taskList");
		List<CategoryBean> categoryList = (List<CategoryBean>)session.getAttribute("categoryList");
		List<UserBean> userList = (List<UserBean>)session.getAttribute("userList");
		List<StatusBean> statusList = (List<StatusBean>)session.getAttribute("statusList");
		
		//変更前のタスクを取得
		TaskBean task = new TaskBean();
		
		task = (TaskBean)session.getAttribute("task");
		
		int taskId = 0;
		
		try {
			
			taskId = (int)(session.getAttribute("taskId"));
			
			System.out.println("try:" + taskId); 

		
		}catch (NullPointerException e) {
			
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
			
			return;
			
		}
		
/*		for (TaskBean newTask : taskList) {
			
			if(newTask.getTaskId() == taskId) {
				
				task = newTask;
				
			}
			
		}*/
		
		
		//変更後のタスクオブジェクトを用意
		TaskBean updateTask = new TaskBean();
		updateTask.setTaskId(taskId);
		updateTask.setCreateDatetime(task.getCreateDatetime());
		updateTask.setUpdateDatetime(task.getUpdateDatetime());
		 
		
	//変更後の値を取得しオブジェクトの中身を更新する
		//タスク名
			String taskName = request.getParameter("taskName");
			//Name属性を変更された場合
			if (taskName == null) {
				//変更前の値をセット
				updateTask.setTaskName(task.getTaskName());
				
			} else {
				//正常な更新
				updateTask.setTaskName(taskName);
				
			}
			
		//カテゴリーID
			//Name属性を変更された場合
			try {
				
				int categoryId = Integer.parseInt(request.getParameter("categoryId"));
			//valueの数値を変更された場合	
				if (categoryList.size() >= categoryId) {
				//リストサイズ内ならそのまま登録	
					updateTask.setCategoryId(categoryId);
					
				} else {
				//リストサイズ外なら初期値を登録	
					updateTask.setCategoryId(task.getCategoryId());
					
				}
				
			} catch (NumberFormatException e) {
			//正常な更新	
				updateTask.setCategoryId(taskId);
				
			}
			
		//期限
		boolean dateBollean = false;
		
		try {
			
			LocalDate limitDate = LocalDate.parse(request.getParameter("limitdate"));
			updateTask.setLimitDate(limitDate);
			
			//期限が過去のものになっていないかチェック
			LocalDate nowDate = LocalDate.now();
		
			if (limitDate.isAfter(nowDate)) {
				
				dateBollean = true;
				
			}
		
		}catch (DateTimeParseException | NullPointerException e) {
			
			updateTask.setLimitDate(task.getLimitDate());
			
		}
		
		//ユーザーID
		String userId = request.getParameter("userId");
			//Name属性を変更された場合	
		if (userId == null) {
			//変更前の値をセット
			updateTask.setUserId(task.getUserId());
			
		}else {
		
			for (UserBean ub : userList) {
				
				if (ub.getUserId().equals(userId)) {
					//変更後の値をセット
					updateTask.setUserId(userId);
					
				} else {
					
					//変更前の値をセット
					updateTask.setUserId(task.getUserId());
					
				}
				
			}
			
		}
		
		//ステータスコード
		String statusCode = request.getParameter("statusCode");
			//Name属性を変更された場合
		if (statusCode == null) {
			
			//変更前の値をセット
			updateTask.setStatusCode(task.getStatusCode());
				
		}else {
			
			for (StatusBean sb : statusList) {
				
				if (sb.getStatusCode().equals(statusCode)) {
					
					//正常な更新
					updateTask.setStatusCode(statusCode);
					
				} else {
					
					//変更前の値をセット
					updateTask.setStatusCode(task.getStatusCode());
					
				}
				
			}
			
			
		}
		
		//メモ
		updateTask.setMemo(request.getParameter("memo"));
		
		session.setAttribute("updateTask", updateTask);
		
		TaskDAO taskDao = new TaskDAO();
		
		String url = "task-alter-failure.jsp";
		
		if (task.equals(updateTask)) {
			//成功処理に遷移するコード
			System.out.println("変更有");
			
			try {
				
				//文字数チェック、未入力チェック
				if (updateTask.getTaskName().length() <= 50 && updateTask.getMemo().length() <= 100
						&& updateTask.getTaskName() != ""&& dateBollean){
					
					//DBアップデート
					int res = taskDao.updateTask(updateTask);
					
					if (res == 1) {
						System.out.println("変更成功");
					url = "task-alter-success.jsp";
					//TaskBeanList更新
					taskList.get(taskId - 1).setTaskId(updateTask.getTaskId());
					taskList.get(taskId - 1).setTaskName(updateTask.getTaskName());
					taskList.get(taskId - 1).setCategoryId(updateTask.getCategoryId());
					taskList.get(taskId - 1).setLimitDate(updateTask.getLimitDate());
					taskList.get(taskId - 1).setUserId(updateTask.getUserId());
					taskList.get(taskId - 1).setStatusCode(updateTask.getStatusCode());
					taskList.get(taskId - 1).setMemo(updateTask.getMemo());
					
					
					} else {
						
						System.out.println("変更失敗");
						url = "task-list-servlet";
						
					}
	
			
				} else {
					
					url = "task-alter-failure.jsp";
					
				}
			
			} catch (SQLException | ClassNotFoundException | NullPointerException e) {
			
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
