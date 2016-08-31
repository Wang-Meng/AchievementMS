<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="main.com.jike131.dao.*" language="java"%>
<%@ page import="main.com.jike131.util.*" language="java"%>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
		ArrayList<PersonalScore> list = PersonalScoreDao.queryByNum((String) session.getAttribute("stuNum"));
	%>
	
	<div style="margin-bottom: 10px;">
	    		<span>当前位置:我的成绩</span>
	 </div>
	<table class="stu_info_table" id = "stu_info_table" cellspacing="0px" align="left">
		<tr class="table_header">
			<th>所学课程</th>
			<th>任课教师</th>
			<th>分数</th>
		</tr>
		<%
			for (PersonalScore ps : list) {
		%>
		<tr  style="border-bottom: 1px solid red;">
			<td><%=ps.getcourseName()%></td>
			<td><%=ps.getTeacher()%></td>
			<td><%=ps.getScore()%></td>
		</tr>
		<%
			}
		%>
	</table>
	    </div>
  	</div>
</body>
</html>