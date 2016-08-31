<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<meta charset="utf-8" content="text/html">
    <title></title>
    <link type="text/css" rel="stylesheet" href="/AchievementMS/view/public/css/admin_index_left.css"/>

    <script type="text/javascript" src="/AchievementMS/view/public/js/jquery-2.1.4.min.js"></script>
    <script type="text/javascript">
        $(function(){
            $(".subNav").click(function(){
                $(this).toggleClass("currentDd").siblings(".subNav").removeClass("currentDd")
                $(this).toggleClass("currentDt").siblings(".subNav").removeClass("currentDt")
                $(this).next(".navContent").slideToggle(300).siblings(".navContent").slideUp(500)
            })
        })
    </script>
</head>
<body>
  <div class="left">
      <div class="subNavBox">
          <div class="subNav currentDd currentDt">基本信息</div>
          <ul class="navContent " style="display:block">
              <li><a href="/AchievementMS/view/admin/jsp/student_info.jsp">学生信息</a></li>

          </ul>
          <div class="subNav">学生成绩管理</div>
          <ul class="navContent">
              <li><a href="/AchievementMS/view/admin/jsp/add_stu.jsp">成绩添加</a></li>
              <li><a href="/AchievementMS/view/admin/jsp/add_stu_score.jsp">成绩维护</a></li>
              <li><a href="/AchievementMS/view/admin/jsp/StuClassCount.jsp">成绩统计</a></li>
              <li><a href="/AchievementMS/view/admin/jsp/StuAny.jsp">不及格统计</a></li>
          </ul>

     <!--      <div class="subNav">学生成绩分析</div>
          <ul class="navContent">
              <li><a href="/AchievementMS/view/admin/jsp/student_info.jsp">成绩段人数分析</a></li>
              <li><a href="/AchievementMS/view/admin/jsp/student_info.jsp">年段分数段分析</a></li>
              <li><a href="/AchievementMS/view/admin/jsp/student_info.jsp">年段平均分统计</a></li>
          </ul> -->

          <div class="subNav">系统帮助</div>
          <ul class="navContent">
              <li><a href="#">系统简介</a></li>
          </ul>
      </div>
  </div>
</body>
</html>
