<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>题目</title>
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
        
<div class="header">
    
    <h1 class="page-title">题目</h1>
</div>

        <ul class="breadcrumb">
    <li>发布上传 <span class="divider">/</span></li>
    <li class="active">题目</li>
</ul>
                    
<div class="well">
	<a href="insertProblem.jsp">添加题目</a>
	<%-- <form action="SelectRP?op=p" method="post">
		按班级查找：
		<select name="select?op=r">
			<c:forEach var="classes" items="${clist1}">
				<option value="${classes.getClasses() }">${classes.getClasses() }</option>
			</c:forEach>
		</select>
		<input type="submit" value="查找">
	</form> --%>
	<table class="table">
		<thead>
        <tr>
        	<th>题目编号</th>
          <th>题目</th>
          <th>选项1</th>
          <th>选项2</th>
          <th>选项3</th>
          <th>选项4</th>
          <th>正确答案</th>
          <th>解析</th>  
         <!--  <th>班级</th>  -->    
          <th style="width: 26px;"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="problem" items="${plist}">
            <tr>
                <td>${problem.getPid()}</td>
                <td>${problem.getPoblem()}</td>
                <td>${problem.getOptionA()}</td>
                <td>${problem.getOptionB()}</td>
                <td>${problem.getOptionC()}</td>
                <td>${problem.getOptionD()}</td>
                <td>${problem.getSolution()}</td>
                <td>${problem.getAnalyzing()}</td>
                <%-- <td>${problem.getPclass()}</td> --%>
                <!-- <td>
                	<a href="#">修改</a>
                	<a href="#">删除</a>
                </td> -->
            </tr>
        </c:forEach>
      </tbody>
	</table>	
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