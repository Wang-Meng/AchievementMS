<%@page import="main.com.jike131.util.PersonalFailedClasses"%>
<%@page import="main.com.jike131.dao.PersonalFailedClassesDao"%>
<%@page import="main.com.jike131.dao.PersonalScoreDao"%>
<%@page import="main.com.jike131.util.PersonalScore"%>
<%@page import="main.com.jike131.util.ClassTotalScore"%>
<%@page import="main.com.jike131.dao.ClassScoreDao"%>
<%@page import="main.com.jike131.dao.AddStuScore"%>
<%@page import="main.com.jike131.util.FailedByClassAndCourse"%>
<%@page import="main.com.jike131.dao.ScoreByClassAndCourse"%>
<%@page import="main.com.jike131.dao.GetCourseByClass"%>
<%@page import="main.com.jike131.dao.GetClassByFaculty"%>
<%@page import="main.com.jike131.util.Facultys"%>
<%@page import="java.util.List"%>
<%@page import="main.com.jike131.dao.GetFacultys"%>
<%@page import="main.com.jike131.dao.StudentInfoDao"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="main.com.jike131.util.Student"%>
<%@page import="java.util.ArrayList"%>
<%@page import="main.com.jike131.dao.StudentDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/student_info.css" />
<link rel="stylesheet" type="text/css"
	href="/AchievementMS/view/public/css/bootstrap.min.css" />
<script type="text/javascript" language="javascript"
	src="/AchievementMS/view/public/js/jquery.js"></script>
<script type="text/javascript" language="javascript"
	src="/AchievementMS/view/public/js/bootstrap.min.js"></script>
<script type="text/javascript" language="javascript">
		function search() {
			var title = document.getElementById("classSelected");
			
			if ($(".classSelected").val()=="全部") {
				
				document.forms[0].classNameTest.value = 0;

			}else{
				if($(".classSelected").val()=="计科131"){
					document.forms[0].classNameTest.value = 1;
				}
				if($(".classSelected").val()=="通信131"){
					document.forms[0].classNameTest.value = 2;
				}
				if($(".classSelected").val()=="教育131"){
					document.forms[0].classNameTest.value = 3;
				}
				if($(".classSelected").val()=="商务英语131"){
					document.forms[0].classNameTest.value = 4;
				}
				if($(".classSelected").val()=="英语教育131"){
					document.forms[0].classNameTest.value = 5;
				}
	
			}
			
		}
		
		function getClass () {
			getData('/AchievementMS/GetDataServlet', {
				param_name: 'school_id',
				param: school.val(),
				class_name: 'GetClassByFaculty',
				method: 'getCLass',
				type: 1
			}, function (re) {
				var data = re.data;
				classSelect.html('');
				var str = null;
				for (var item in data) {
					str = '<option value = "'+data[item].id+'" >'+data[item].name+'</option>';
					classSelect.append(str);
				}
				classSelect.trigger('change');
			});
		}
		
		function getCourse () {
			getData('/AchievementMS/GetDataServlet', {
				param_name: 'school_id',
				param: classSelect.val(),
				class_name: 'GetClassByFaculty',
				method: 'getCLass',
				type: 2
			}, function (re) {
				var data = re.data;
				courseSelect.html('');
				var str = null;
				for (var item in data) {
					str = '<option value = "'+data[item].id+'" >'+data[item].name+'</option>';
					courseSelect.append(str);
				}
			});
		}
		
		function getData (url, data, fn) {
			$.ajax({
				url: url,
				data: data,
				type: 'GET',
				success: function (result) {
					fn(result);
				}
			});
		}
		
		function updateData (url, data, fn) {
			$.ajax({
				url: url,
				data: data,
				type: 'POST',
				success: function (result) {
					fn(result);
				}
			});
		}
		
		$(function () {
			window.courseSelect = $('#courseSelect');
			window.school = $('#school');
			window.classSelect = $('#classSelected');
		});
	</script>
</head>
<body>
	<jsp:include page="../../public/jsp/admin_index_header.jsp"></jsp:include>
	<div style="width: 100%;">
		<jsp:include page="../../public/jsp/admin_index_left.jsp"></jsp:include>
		<div style="width: 84%; float: right; margin-top: 0px;">

			<div>
				<span>当前位置:学生信息</span>
			</div>
			<div style="margin-bottom: 10px;">
				<center>
					
				</center>
			</div>
			<table class="stu_info_table" id="stu_info_table" cellspacing="0px"
				align="left">
				<tr class="table_header">
					<th>姓名</th>
					<th>课程</th>
					<th>成绩</th>
				</tr>

				<%
					ArrayList<PersonalFailedClasses> stuList = PersonalFailedClassesDao.failedPass(request.getParameter("stu_num"));
					if (stuList.isEmpty()) {
						out.println("暂无消息");
					}
				
					for (PersonalFailedClasses ss : stuList) {
				%>
				<tr>
					
						<td><%=ss.getName()%></td>
						<td><%=ss.getClassName()%></td>
						<td><%=ss.getFailedScore() %></td>
					
				</tr>
				<% } %>
			</table>
		</div>
	</div>
</body>
</html>