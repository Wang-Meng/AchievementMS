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
	<link rel="stylesheet" type="text/css" href="../css/student_info.css"/>
	<link rel="stylesheet" type="text/css" href="/AchievementMS/view/public/css/bootstrap.min.css"/>
	<script type="text/javascript" language="javascript" src="/AchievementMS/view/public/js/jquery.js"></script>
	<script type="text/javascript" language="javascript" src="/AchievementMS/view/public/js/bootstrap.min.js"></script>
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
				
		function getCourse () {
			getData('/AchievementMS/GetDataServlet', {
				param_name: 'school_id',
				param_val: 1,
				class_name: '111',
				method: 'asdsadsad'
			}, function (re) {
				console.log(re);
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
	</script>
</head>
<body>
	<jsp:include page="../../public/jsp/admin_index_header.jsp"></jsp:include>
  	<div style="width: 100%;">
	    <jsp:include page="../../public/jsp/admin_index_left.jsp"></jsp:include>
	    <div style="width:84%;float:right;margin-top:0px; ">
	    	
	    	<div>
	    		<span>当前位置:学生信息</span>
	    	</div>
	    	<div style="margin-bottom: 10px;">
	    		<center>
	    			<form action="student_info.jsp" method="post">
	    				<span style="margin-right: 20px;">班级:</span>
	    				<select class="form-control classSelected" id="classSelected" style="width:100px;height:27px;display:inline" onchange="search()">
	    					<option value = "1">全部</option>
	    					<option value = "2">计科131</option>
	    					<option value = "3">通信131</option>
	    					<option value = "4">教育131</option>
	    					<option value = "5">商务英语131</option>
	    					<option value = "6">英语教育131</option>
	    				</select>
	    				<input type="hidden" id = "classNameTest" name="classNameTest" value="">
	    				<input value="确定" type="submit" >
	    			</form>
	    		</center>
	    	</div>
			<table class="stu_info_table" id = "stu_info_table" cellspacing="0px" align="left">
				<tr class="table_header">
					<th>学号</th>
					<th>姓名</th>
					<th>院系</th>
					<th>班级</th>
					<th>性别</th>
					<!-- <th>出生日期</th> -->
				</tr>
				
				
				<%
					String currPage = request.getParameter("currPage");
					int iCurrPage = 1;	//当前所在页的页数
					int pages = 1;	//页数
					int totalRecordCount = 0;	//总记录数目
					int perPageCount = 5;		//每一页的数目
					
					String a = request.getParameter("classNameTest");
					int classNum = 0;
					try
					{
						classNum = Integer.valueOf(a);
					}catch(NumberFormatException e){
						e.printStackTrace();
					}
						
					totalRecordCount = StudentInfoDao.findStudentCount(classNum);
					
					pages = (totalRecordCount-1)/perPageCount+1;
					
					if(pages == 0){
						pages = 1;
					}
					
					if(currPage != null && !currPage.equalsIgnoreCase("")){
						iCurrPage = Integer.parseInt(currPage);
					}
					
					
					ArrayList<Student> list = StudentInfoDao.findStudentInfo(classNum, (iCurrPage-1)*perPageCount, perPageCount);
					if(list.isEmpty()){
						out.println("暂无消息");
					}
				%>
				<%
					for (Student s : list) {
				%>
				<tr>
					<td><%=s.getStu_num()%></td>
					<td><%=s.getStu_name() %></td>
					<td><%=s.getFaculty_name()%></td>
					<td><%=s.getClass_name()%></td>
					<td><%=s.getStu_sex()%></td>
					<%-- <td><%=s.getStu_birthday()%></td> --%>
				</tr>
				<%
					}
				%>
				
			</table>
			<%
				if(perPageCount<totalRecordCount){
					String href = "&nbsp;&nbsp;<a href='student_info.jsp?currPage=";
					StringBuffer stringBuffer = new StringBuffer();
					if(iCurrPage >1){
						stringBuffer.append(href+(iCurrPage-1)+"'>上一页</a>");
					}
					for(int i = 1;i<=pages;i++){
						if(i==iCurrPage){
							stringBuffer.append(href+i+"'>["+i+"]</a>");
						}else{
							stringBuffer.append(href+i+"'>"+i+"</a>");
						}
					}
					if(iCurrPage < pages){
						stringBuffer.append(href+(iCurrPage+1)+"'>下一页</a>");
					}
					%>
					<div class="paging">
						<%out.print("当前页数为:["+iCurrPage+"/"+pages+"]"); %>
						<%=stringBuffer.toString() %>
					</div>
					
					<%
				}
			%>
	    </div>
  	</div>
	
</body>
</html>