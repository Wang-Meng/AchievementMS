<%@page import="com.mysql.jdbc.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询</title>
<script type="text/javascript" src="../js/search.js"></script>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript">
	var collageSel = document.getElementById('collageSelect');
	function change() {
		alert("changed");
	}
</script>
</head>
<body>
	<h1>serach</h1>
	<table>
		<tr>
			<td>学院：<select id="cleeageSelect" onchange="change(this.value)">
					<optgroup label="请选择学院">
						<option>
						<option>
					</optgroup>
			</select>
			</td>
			<td>班级：<select id="majorSelect">
					<optgroup label="请选择班级">
						<option>
						<option>
					</optgroup>
			</select>
			</td>
			<td>课程：<select>
					<optgroup label="请选择课程">

					</optgroup>
			</select>
			</td>
		</tr>
		<tr>
			<td>请输入学号：<input type="text"></td>
		</tr>
	</table>
</body>
</html>