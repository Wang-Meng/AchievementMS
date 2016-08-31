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
		
		function add_score (self) {
			self = $(self);
			updateData('/AchievementMS/UpdateScoreServlet', {
				user: self.parent().parent().children('.stu_num').attr('value'),
				score: self.prev().val(),
				course: courseSelect.val()
			}, function (re) {
				console.log(re);
				self.parent().trigger('reset');
			});
		}
		
		$(function () {
			window.courseSelect = $('#courseSelect');
			window.school = $('#school');
			window.classSelect = $('#classSelected');
			$('.add_score').on('dblclick', function () {
				var score = $(this).text();
				$(this).html('<input class = "score_text" value = "'+score+'" type = "text" /><input onclick = "add_score(this)" type = "button" value = "提交"/>');
			});
			
			$('.add_score').on('reset', function () {
				console.log('sadsadsad');
				var score = $(this).children('.score_text').val();
				$(this).html(score);
			});
		});
	</script>
</head>
<body>
	<jsp:include page="../../public/jsp/admin_index_header.jsp"></jsp:include>
	<div style="width: 100%;">
		<jsp:include page="../../public/jsp/admin_index_left.jsp"></jsp:include>
		<div style="width: 84%; float: right; margin-top: 0px;">

			<div>
				<span>当前位置:成绩维护</span>
			</div>
			<div style="margin-bottom: 10px;">
				<center>
					<form action="add_stu_score.jsp" method="post">
						<span style="margin-right: 20px;">学院:</span> 
						<select name = "school"
							class="form-control classSelected" id="school"
							style="width: 100px; height: 27px; display: inline" onchange = "getClass()">
							<%
								List<Facultys> facultyList = GetFacultys.getFacultys();
								int school = facultyList.get(0).getId();
								if (request.getParameter("school") != null) {
									school = Integer.valueOf(request.getParameter("school"));
								}
								
								for(Facultys falculty:facultyList){
							%>
							<option <% if (school == falculty.getId()) { %> selected <% } %> value="<%=falculty.getId() %>"><%=falculty.getName() %></option>
							<% } %>
						</select> <span style="margin-right: 20px;">班级:</span> <select
							class="form-control classSelected" id="classSelected" name = "class"
							style="width: 100px; height: 27px; display: inline" onchange = "getCourse()"
							>
							<%
								List<Facultys> classList = GetClassByFaculty.getCLass(school);
								int cl = classList.get(0).getId();
								
								if (request.getParameter("class") != null) {
									cl = Integer.valueOf(request.getParameter("class"));
								}
								for(Facultys falculty:classList){
							%>
							<option <% if (cl == falculty.getId()) { %> selected <% } %> value="<%=falculty.getId() %>"><%=falculty.getName() %></option>
							<% } %>
						</select> <span style="margin-right: 20px;">课程:</span> <select
							class="form-control classSelected" id="courseSelect"
							style="width: 100px; height: 27px; display: inline" name = "course"
							>
							<%
								List<Facultys> courseList = GetCourseByClass.getCourse(cl);
								int course = courseList.get(0).getId();
								if (request.getParameter("course") != null) {
									course = Integer.valueOf(request.getParameter("course"));
								}	
								for(Facultys falculty:courseList){
							%>
							<option <% if (course ==  falculty.getId()) { %> selected <% } %> value="<%=falculty.getId() %>"><%=falculty.getName() %></option>
							<% } %>
						</select> <input type="hidden" id="classNameTest" name="classNameTest"
							value=""> <input value="确定" type="submit">
					</form>
				</center>
			</div>
			<table class="stu_info_table" id="stu_info_table" cellspacing="0px"
				align="left">
				<tr class="table_header">
					<th>学号</th>
					<th>姓名</th>
					<th>任课教师</th>
					<th>成绩</th>
				</tr>

				<%
					String currPage = request.getParameter("currPage");
					int iCurrPage = 1; //当前所在页的页数
					int pages = 1; //页数
					int totalRecordCount = 0; //总记录数目
					int perPageCount = 5; //每一页的数目

					String a = request.getParameter("classNameTest");
					int classNum = 0;
					try {
						classNum = Integer.valueOf(a);
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}

					totalRecordCount = StudentInfoDao.findStudentCount(classNum);

					pages = (totalRecordCount - 1) / perPageCount + 1;

					if (pages == 0) {
						pages = 1;
					}

					if (currPage != null && !currPage.equalsIgnoreCase("")) {
						iCurrPage = Integer.parseInt(currPage);
					}
					ArrayList<FailedByClassAndCourse> stuList = ScoreByClassAndCourse.classCourseFailed(classList.get(0).getId()+"", courseList.get(0).getId()+"");
					if (stuList.isEmpty()) {
						out.println("暂无消息");
					}
				
					for (FailedByClassAndCourse s : stuList) {
				%>
				<tr>
					<td class = "stu_num" value = "<%=s.getStuNum()%>"><%=s.getStuNum()%></td>
					<td><%=s.getName()%></td>
					<td><%=s.getTeacher()%></td>
					<td class = "add_score"><%=s.getScore()%></td>
				</tr>
				<% } %>
			</table>
		</div>
	</div>
</body>
</html>