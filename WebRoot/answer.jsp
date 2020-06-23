<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>答疑</title>
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
    
    <h1 class="page-title">答疑</h1>
</div>

        <ul class="breadcrumb">
    <li><a href="index.jsp">论坛</a> <span class="divider">/</span></li>
    <li class="active">答疑</li>
</ul>
                    
<div class="well">
	<form action="SelectRP?op=a" method="post">
		按班级查找：
		<select name="select">
			<c:forEach var="classes" items="${clist1}">
				<option value="${classes.getClasses() }">${classes.getClasses() }</option>
			</c:forEach>
		</select>
		<input type="submit" value="查找">
	</form> 
	<table class="table">
		<thead>
        <tr>
          <th>班级</th>
          <th>提疑</th>
          <th>答疑</th>
          <th style="width: 26px;"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="ch" items="${chlist}">       
            <tr>
            	<td>${ch.getChcid()}</td>
                <td>${ch.getRaise()}</td>
                <td>
                	<c:if test="${empty ch.getAnswer()}">              	
                		<form action="InsertAnswerServlet" method="post">
                			<input type="text" name="answer" value="">
                			<input type="hidden" name="chid" value="${ch.getChid()}">
                			<input type="submit" value="回复"/>
                		</form>
                	</c:if>
                	<c:if test="${not empty ch.getAnswer()}">
                	${ch.getAnswer()}
                	</c:if>
                </td>
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