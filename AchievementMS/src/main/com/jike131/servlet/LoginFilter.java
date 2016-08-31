package main.com.jike131.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class LoginFilter implements Filter {
	static String LOGIN_PAGE = ".../public/jsp/login.jsp";
	protected FilterConfig filterConfig;

	public void doFilter(final ServletRequest req, final ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		String isLog = (String) request.getSession().getAttribute("isLog");
		if ((isLog != null) && ((isLog.equals("true")) || (isLog == "true")))
		{
			chain.doFilter(req, res);
			return;
		} else {
			request.getRequestDispatcher(LOGIN_PAGE).forward(req, res);
		}

	}

	public void destroy() {
		this.filterConfig = null;
	}

	public void init(FilterConfig config) {
		this.filterConfig = config;
	}

	public void setFilterConfig(final FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}
}