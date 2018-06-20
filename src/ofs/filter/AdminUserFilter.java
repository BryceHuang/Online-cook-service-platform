package ofs.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ofs.javabean.AdminUser;

public class AdminUserFilter implements Filter {
	private static final String KEYWORDS = "Login";
	private static final String PATH = "/admin";
	private static final String LOGINPAGE = "Login.jsp";

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();

		// 请求URL
		String url = req.getRequestURI();

		String str = url.substring(url.indexOf(PATH));
		// 如果不是Login.jsp和loginAction
		if (str.indexOf(KEYWORDS) == -1 && str.indexOf("loginAction") == -1) {
			// 从session中获取user
			AdminUser admin = (AdminUser) session.getAttribute("admin");
			
			// 如果未登录
			if (admin == null) {
				// 保存目标页地址
				session.setAttribute("loginUrl", str);

				// 跳转到登录页
				String path = req.getContextPath() + "/user" + "/" + LOGINPAGE;
				res.sendRedirect(path);
				return;
			}
		}

		// 放过请求
		chain.doFilter(req, res);
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
