<%@page import="main.com.jike131.util.ClassTotalScore"%>
<%@page import="main.com.jike131.dao.PersonalScoreDao"%>
<%@page import="main.com.jike131.dao.ClassScoreDao"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>班级成员成绩</h1>
	<table>
		<tr>
			<th>学号</th>
			<th>姓名</th>
			<th>总分</th>
			<th>平均分</th>
		</tr>
		<%

			ArrayList<ClassTotalScore> list = ClassScoreDao.queryScoreByClass("1");
		%>
		<%
			for (ClassTotalScore cts : list) {
		%>
		<tr>
			<td><%=cts.getStuNum() %></td>
			<td><%=cts.getStuName() %></td>
			<td><%=cts.getTotal() %></td>
			<td><%=cts.getAverage() %></td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>