package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginCheckFilter
 */
@WebFilter("/*") //全リクエストを対象
public class LoginCheckFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public LoginCheckFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		//キャッシュ無効化
		res.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
		res.setHeader("Pragma", "no-cache");
		res.setHeader("Expires", "0");
		
		HttpSession session = req.getSession(false);
		
		//アクセスされたURLを取得
		String requestURI = req.getRequestURI();
		System.out.println(requestURI);
		
		//ログインページとログイン済みのページはチェック対象外にする
		boolean isLoginPage = requestURI.contains("login.jsp");
		boolean isLoginServlet = requestURI.contains("login-servlet");
		boolean isLoggedIn = (session != null && session.getAttribute("user") != null);
		
		System.out.println(isLoginPage);
		System.out.println(isLoginServlet);
		System.out.println(isLoggedIn);
		
		if (isLoginPage || isLoginServlet || isLoggedIn) {
			//ログインページ、またはログイン済みのページならそのまま通す
			chain.doFilter(request, response);
		} else {
			//未ログインならログイン画面へ飛ばす
			res.sendRedirect(req.getContextPath() + "/login.jsp");
		}	
	}
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
}
