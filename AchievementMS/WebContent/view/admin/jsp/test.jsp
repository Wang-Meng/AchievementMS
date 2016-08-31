<%@page import="main.com.jike131.util.Student"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="main.com.jike131.dao.JDBCtools"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>info</h1>
	<%
	
		Connection con = JDBCtools.getConnection();
		con.setAutoCommit(false);
		//select * from student,teacher,project where student.id=teacher.sid and student.id=project.sid;
		String search = "select * from stu_info s,faculty f,class c where s.stu_to_faculty=f.faculty_id and s.stu_to_class=c.class_num;";
		PreparedStatement ps = con.prepareStatement(search);
		ResultSet result = ps.executeQuery();
		con.commit();
		JDBCtools.release(result, ps, con);
		while (result.next()) {
			System.out.println(result.getString("stu_num"));
			System.out.println(result.getString("stu_name"));
			System.out.println("-----------------------------------");
		}
	%>

</body>
</html>