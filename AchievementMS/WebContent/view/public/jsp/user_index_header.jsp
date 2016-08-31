<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8" content="text/html">
<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="/AchievementMS/view/public/css/user_index_header.css"/>
	<script type="text/javascript" language="javascript">
         function doLogout(){
        	 
             //访问LogoutServlet注销当前登录的用户
<%-- 				alert("退出");
				alert(<%
						HttpSession sess = request.getSession();
					sess.getAttribute("isLogin");%>);

				alert(<%session.getAttribute("isLogin");%>); --%>
				window.location.href="http://localhost:8080/AchievementMS/"+"view/public/jsp/login.jsp";  
         }
     </script>
</head>
<body>
	<div class="header">
		<span class="systemTitle" >学生成绩管理系统</span>
		<span class="exit" onclick="doLogout()">退出</span>
	</div>
</body>
</html>