<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传资料</title>
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
<body>
        
<div class="header">
    <h1 class="page-title">上传资料</h1>
</div>

<ul class="breadcrumb">
	<li><a href="index.jsp">主页</a> <span class="divider">/</span></li>
	<li class="active">上传资料</li>
</ul>

<div class="well">
 
    <div id="myTabContent" class="tab-content">
      <div class="tab-pane active in" id="home">
     ${uploadmsg}
    <form id="tab" action="UploadResourceServlet" method="post" enctype="multipart/form-data">
        <label>选择文件：</label>
        <input type="file" name="resource" value="" class="input-xlarge">
        <label>上传班级：</label>
        <select name="rclass" class="input-xlarge">
        	<c:forEach var="classes" items="${clist1}">
        		<option value="${classes.getClasses() }">${classes.getClasses() }</option>
        	</c:forEach>
        </select>
        <!-- <input type="text" name="rclass" value="" class="input-xlarge"> -->
        <div class="btn-toolbar">
    	<button class="btn btn-primary"><i class="icon-save"></i>上传</button>
  		<div class="btn-group">
  		</div>
		</div>
    </form>
      </div>
  </div>