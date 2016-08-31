<%@page import="main.com.jike131.dao.FailedPersonByCourseDao"%>
<%@page import="main.com.jike131.util.FailedPerosnByCourse"%>
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
	<h1>班级成员成绩</h1>
	<table>
		<tr>
			<th>学号</th>
			<th>姓名</th>
			<th>班级</th>
			<th>分数</th>
		</tr>
		<%
			ArrayList<FailedPerosnByCourse> list = FailedPersonByCourseDao.personsByCourse(1);
		%>
		<%
			for (FailedPerosnByCourse fpc : list) {
		%>
		<tr>
			<td><%=fpc.getStuNum()%></td>
			<td><%=fpc.getName()%></td>
			<td><%=fpc.getClassName()%></td>
			<td><%=fpc.getScore()%></td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>