<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学生登录</title>
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
                    
                </ul>
                <a class="brand"><span class="first">计算机组成原理</span> <span class="second">教学管理网站</span></a>
        </div>
    </div>
    <br><br><br>
    <div align="center"> <font color="red">${msg}</font></div>
    <div class="row-fluid">
    <div class="dialog">
        <div class="block">
            <p class="block-heading">学生登录</p>
           
            <div class="block-body">
                <form action="LoginStudentServlet" method="post">
                 	${LSmsg}
                    <label>用户名：</label>
                    <input type="text" class="span12" name="Sunum"/>
                    <label>学&nbsp;&nbsp;校：</label>
                    <input type="text" class="span12" name="Suschool"/>
                    <label>密&nbsp;&nbsp;码：</label>
                    <input type="password" class="span12" name="Supass">
                    <p>用户名为学号</p>
                    <input type="submit" value="登录" class="btn btn-primary pull-right">
                   <!--  <label class="remember-me">
                   		<input type="radio" name="job" value="m">店主&nbsp;&nbsp;&nbsp;&nbsp;
                    	<input type="radio" name="job" value="s" checked>店员
                    </label> -->
                    <div class="clearfix"></div>
                </form>
            </div>
        </div>
    </div>
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