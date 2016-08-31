<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<meta charset="utf-8" content="text/html">
    <title></title>
    <link type="text/css" rel="stylesheet" href="/AchievementMS/view/public/css/user_index_left.css"/>

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
              <li><a href="/AchievementMS/view/user/jsp/user_index.jsp">学生个人信息</a></li>

          </ul>
          <div class="subNav">学生成绩管理</div>
          <ul class="navContent">
              <li><a href="/AchievementMS/view/user/jsp/user_score_manage.jsp">我的成绩</a></li>
          </ul>
      </div>
  </div>
</body>
</html>
