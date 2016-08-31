<%@page import="main.com.jike131.dao.FailedByClassAndCourseDao"%>
<%@page import="main.com.jike131.util.FailedByClassAndCourse"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<%
		ArrayList<FailedByClassAndCourse> list = FailedByClassAndCourseDao.classCourseFailed(1, 1);
	%>
	<h1>班级成员成绩</h1>
	<h3>
		不及格人数：<%=list.size()%></h3>
	<table>
		<tr>
			<th>学号</th>
			<th>姓名</th>
			<th>分数</th>
		</tr>

		<%
			for (FailedByClassAndCourse fcc : list) {
		%>
		<tr>
			<td><%=fcc.getStuNum()%></td>
			<td><%=fcc.getName()%></td>
			<td><%=fcc.getScore()%></td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>