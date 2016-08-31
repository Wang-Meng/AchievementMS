package main.com.jike131.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import main.com.jike131.dao.AlterCourseScore;
import main.com.jike131.dao.GetClassByFaculty;
import main.com.jike131.dao.GetCourseByClass;
import main.com.jike131.util.Facultys;

/**
 * Servlet implementation class GetDataServlet
 */
public class GetDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetDataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		response.setContentType("Content-Type:application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        JSONObject json = new JSONObject();
        JSONArray jsonMembers = new JSONArray();
        
		String param = request.getParameter("param");
		String classname = request.getParameter("class_name");
		String type = request.getParameter("type");
		List<Facultys> a = null;
		switch (type) {
		case "1":
			try {
				a = GetClassByFaculty.getCLass(Integer.valueOf(param));
				for (Facultys f: a) {
					JSONObject temp = new JSONObject();
					temp.put("id", f.getId());
					temp.put("name", f.getName());
					jsonMembers.put(temp);
				}
				json.put("data", jsonMembers);
		        out.println(new String(json.toString().getBytes(),"UTF-8"));
			} catch (NumberFormatException | SQLException |JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "2":
			try {
				a = GetCourseByClass.getCourse(Integer.valueOf(param));
				for (Facultys f: a) {
					JSONObject temp = new JSONObject();
					temp.put("id", f.getId());
					temp.put("name", f.getName());
					jsonMembers.put(temp);
				}
				json.put("data", jsonMembers);
		        out.println(new String(json.toString().getBytes(),"UTF-8"));
			} catch (NumberFormatException | SQLException |JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		default:
			break;
		}
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		String stuNum = request.getParameter("user");
		String score = request.getParameter("score");
		String courseId = request.getParameter("course");
		JSONObject json = new JSONObject();
		try {
			AlterCourseScore.alterScore(score, stuNum, courseId);
			json.put("status", 0);
		} catch (SQLException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        response.setContentType("Content-Type:application/json; charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        out.println(json.toString());
        out.close();
	}

}
