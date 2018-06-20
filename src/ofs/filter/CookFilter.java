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


import ofs.javabean.Cook;

public class CookFilter implements Filter {
	private static final String KEYWORDS = "Login";
	private static final String PATH = "/cook";
	private static final String LOGINPAGE = "Login.jsp";

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();	
		String url = req.getRequestURI();
		String str = url.substring(url.indexOf(PATH));
		if (str.indexOf(KEYWORDS) == -1 && str.indexOf("loginAction") == -1	
				&& str.indexOf("Registration") == -1 && str.indexOf("registrationAction") == -1) {
			Cook cook=(Cook)session.getAttribute("cook");
			if (cook == null) {				
				session.setAttribute("loginUrl", str);
				String path = req.getContextPath() + "/user" + "/" + LOGINPAGE;
				res.sendRedirect(path);
				return;
			}
		}

		chain.doFilter(req, res);
	}
	public void init(FilterConfig arg0) throws ServletException {

	}

}
