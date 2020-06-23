<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.File"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>记录</title>
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
    
    <h1 class="page-title">记录</h1>
</div>

        <ul class="breadcrumb">
    <li>首页 <span class="divider">/</span></li>
    <li class="active">记录</li>
</ul>
                    
<div class="well">
	<!-- <form action="SyllabusServlet" method="post" enctype="multipart/form-data">
		<input type="text" name="syllabus" value="" class="input-xlarge"/>
		<button class="btn btn-primary"><i class="icon-save"></i>上传</button>
	</form> -->
<%-- 	<%
    //此处写你的txt文件e79fa5e98193e78988e69d8331333337613136的绝对路径
    FileReader reader = new FileReader("D:\Workspaces\MyEclipse 2017 CI\.metadata\.me_tcat85\webapps\BookShopMS\takeNotes\3.txt");
    BufferedReader bufferedReader = new BufferedReader(reader);
    StringBuffer txt = new StringBuffer();
    String temp = null;
    while((temp = bufferedReader.readLine()) != null) {
        txt.append(temp);
    }
    InputStreamReader in = new InputStreamReader(System.in);
	BufferedReader br = new BufferedReader(in);
	String inputStr = null;
%> --%>
	<form action="addNoteServlet" method="post">
		<textarea name="takeNotes" rows="2" style="width:600px;height:40px;" value=""></textarea>
		<input type="submit" value="记录">
	</form>
	<%-- ${fileContent} --%>
	<c:forEach var="string" items="${fileContent}">
         ${string}<br/>
    </c:forEach>&nbsp;
	<br/>
</div>
<!-- <script>var textarea=document.getElementById('textarea');
	//设置高度
	textarea.style.height = textarea.scrollHeight + 'px';
</script> -->
    <script src="lib/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
    </script>
</body>
</html>