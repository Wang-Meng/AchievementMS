<%@page import="main.com.jike131.dao.PersonalFailedClassesDao"%>
<%@page import="main.com.jike131.util.PersonalFailedClasses"%>
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
		ArrayList<PersonalFailedClasses> list = PersonalFailedClassesDao.failedPass("201401");
	%>
	<h1>班级成员信息</h1>
	<h3>不及格门数：<%=list.size() %></h3>
	<table>
		<tr>
			<th>姓名</th>
			<th>不及格课程</th>
			<th>分数</th>
		</tr>
		<%
			for (PersonalFailedClasses p : list) {
		%>
		<tr>
			<td><%=p.getName()%></td>
			<td><%=p.getClassName()%></td>
			<td><%=p.getFailedScore()%></td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>