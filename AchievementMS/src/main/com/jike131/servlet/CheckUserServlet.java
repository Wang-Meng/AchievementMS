package main.com.jike131.servlet;

import java.io.IOException;
import java.sql.PseudoColumnUsage;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.com.jike131.dao.JDBCtools;
import main.com.jike131.dao.LoginDao;
import main.com.jike131.util.LoginInfo;

/**
 * Servlet implementation class for Servlet: CcheckUserServlet
 * 
 */
public class CheckUserServlet extends HttpServlet implements javax.servlet.Servlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4061025680582998159L;

	public CheckUserServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(userName + "---user+password--" + password);
		LoginInfo info;
		int type;
		try {
			info = LoginDao.login(userName, password);
			if (info != null) {
				type = info.getType();
				HttpSession session = request.getSession();
				session.setAttribute("isLogin", "true");
				session.setAttribute("type", type);
				session.setAttribute("stuNum", info.getStuNum());
				if (type == 0) {
					request.getRequestDispatcher("view/user/jsp/user_index.jsp").forward(request, response);
				} else if (type == 1) {
					request.getRequestDispatcher("view/admin/jsp/admin_index.jsp").forward(request, response);
				}

			} else {
				request.getRequestDispatcher("view/public/jsp/login.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}