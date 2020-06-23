<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>教师首页</title>
	<meta charset="utf-8">
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.css">
    
    <link rel="stylesheet" type="text/css" href="stylesheets/theme.css">
    <link rel="stylesheet" href="lib/font-awesome/css/font-awesome.css">

    <script src="lib/jquery-1.7.2.min.js" type="text/javascript"></script>

    <!-- Demo page code -->

    <style type="text/css">
        #line-chart {
            height:300px;
            width:800px;
            margin: 0px auto;
            margin-top: 1em;
        }
        .brand { font-family: georgia, serif; }
        .brand .first {
            color: #ccc;
            font-style: italic;
        }
        .brand .second {
            color: #fff;
            font-weight: bold;
        }
    </style>

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="../assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
</head>
<body class="">
<div class="navbar">
        <div class="navbar-inner">
                <ul class="nav pull-right">
                    
                    <li id="fat-menu" class="dropdown">
                        <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="icon-user"></i> 
                            ${Tname }
                            <i class="icon-caret-down"></i>
                        </a>

                        <ul class="dropdown-menu">
                            <li><a tabindex="-1" class="visible-phone" href="#">Settings</a></li>
                            <li class="divider visible-phone"></li>
                            <li><a tabindex="-1" href="QuitServlet">退出</a></li>
                        </ul>
                    </li>
                    
                </ul>
                <a class="brand" ><span class="first">计算机组成原理</span> <span class="second">教学管理网站</span></a>
        </div>
    </div>
    
    <div class="sidebar-nav">
      
        <a href="#accounts-menu" class="nav-header" data-toggle="collapse"><i class="icon-briefcase"></i>首页<i class="icon-chevron-up"></i></a>
        <ul id="accounts-menu" class="nav nav-list collapse">
            <li ><a href="readSyllabusServlet" target="resume_right">教学大纲</a></li>
            <li ><a href="ShowClassesServlet" target="resume_right">班课列表</a></li>
            <li ><a href="showCla.jsp" target="resume_right">班级信息</a></li> 
        </ul>

        <a href="#error-menu" class="nav-header collapsed" data-toggle="collapse"><i class="icon-comment"></i>发布上传 <i class="icon-chevron-up"></i></a>
        <ul id="error-menu" class="nav nav-list collapse">
            <li ><a href="ShowResourceServlet" target="resume_right">资料</a></li>
            <li ><a href="ShowProblemServlet" target="resume_right">题目</a></li>
            <li ><a href="textServlet" target="resume_right">试卷</a></li>
        </ul>
        <a href="#dashboard-menu" class="nav-header" data-toggle="collapse"><i class="icon-dashboard"></i>论坛 <i class="icon-chevron-up"></i></a>
        <ul id="dashboard-menu" class="nav nav-list collapse in">
            <li><a href="AnswerServlet" target="resume_right">答疑</a></li>
            <li ><a href="qingjiaServlet" target="resume_right">请假</a></li>
        </ul>
        <a href="FeedBackServlet" class="nav-header" target="resume_right""><i class="icon-legal"></i>题目反馈 <i class="icon-chevron-up"></i></a>
		<a href="resourceSearch.jsp" class="nav-header" target="resume_right"><i class="icon-legal"></i>资料查找 <i class="icon-chevron-up"></i></a>
        <!-- <ul id="grade-menu" class="nav nav-list collapse">
            <li ><a href="GradeServlet" target="resume_right">会员等级分类</a></li>
            <li ><a href="ShowAllGradeServlet" target="resume_right">会员信息</a></li>
        </ul> -->
        <a href="takeNotesServlet" class="nav-header" target="resume_right"><i class="icon-legal"></i>记录<i class="icon-chevron-up"></i></a>
        
        </ul>
    </div>
    
    <div class="content"> 
        
	<div class="copyrights"></div>
   
   <iframe name="resume_right" src="index.jsp" width="100%" height="950px" frameborder="no" scrolling="no" ></iframe>
    
</div>
                    
     <hr>
		<div align="center" id="foot">
			<font face="微软雅黑" size="2" color="gray">
				<br>
				版权所有：数据结构课程教学网站 技术支持：xxxxxxxxxxxxx
				最佳效果: 1024*768 或1440*900 或 1360*768
				备案号：xxxxx
				<br><br>
			</font>
		</div>

    <script src="lib/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
    </script>
</body>
</html>