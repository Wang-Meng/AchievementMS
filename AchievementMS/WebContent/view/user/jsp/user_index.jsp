<%@page import="main.com.jike131.util.Student"%>
<%@page import="main.com.jike131.dao.StudentDAO"%>
<%@page import="main.com.jike131.util.PersonalScore"%>
<%@page import="main.com.jike131.dao.PersonalScoreDao"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.If"%>
<%@page import="org.apache.catalina.connector.Request"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>个人成绩</title>
	<style type="text/css" rel="stylesheet">
		.stu_info_table{
	width: 100%;
	border-width: 1px;
	border-style: solid;
	border-color: #a2a1ac;
}
.stu_info_table tr{
	border:1px solid #a2a1ac;
	text-align:left;
}


.table_header{
	background-color: rgb(160,188,224);
}
	</style>
</head>
<body>
	<jsp:include page="../../public/jsp/user_index_header.jsp"></jsp:include>
  	<div style="width: 100%;">
	    <jsp:include page="../../public/jsp/user_index_left.jsp"></jsp:include>
	    <div style="width:84%;float:right;margin-top:0px; ">
	    	<%
		/* ResultSet resultSet = ShowScoreDao.queryByNum((String) session.getAttribute("stuNum")); */
		ArrayList<Student> list = StudentDAO.query((String) session.getAttribute("stuNum"));
	%>
	
	<div style="margin-bottom: 10px;">
	    		<span>当前位置:学生个人信息</span>
	 </div>
	<table class="stu_info_table" id = "stu_info_table" cellspacing="0px" align="left">
		<tr  class="table_header">
			<th>学号</th>
			<th>姓名</th>
			<th>学院</th>
			<th>班级</th>
			<th>性别</th>
			<!-- <th>出生日期</th> -->
			
		</tr>

		<%
			for (Student ps : list) {
		%>
		<tr>
			<td><%=ps.getStu_num() %></td>
			<td><%=ps.getStu_name() %></td>
			<td><%=ps.getFaculty_name() %></td>
			<td><%=ps.getClass_name() %></td>
			<td><%=ps.getStu_sex() %></td>
			<%-- <td><%=ps.getStu_birthday() %></td> --%>
		</tr>
		<%
			}
		%>
	</table>
	    </div>
  	</div>
	
</body>
</html>