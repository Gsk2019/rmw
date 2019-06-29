<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="init.jsp"%>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta name="renderer" content="webkit">
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<link rel="shortcut icon" href="../favicon.ico" />
	<c:set var="path" value="${pageContext.request.contextPath}" />
	<%
	String path = request.getContextPath();
	// 获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%>
	<!-- 全局js -->
	<script src="<%=basePath%>res/js/jquery.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>res/js/bootstrap.min.js" type="text/javascript"></script>
	 <script src="<%=basePath%>res/js/plugins/validate/jquery.validate.min.js" type="text/javascript"></script>
	 <script src="<%=basePath%>res/js/plugins/validate/messages_zh.min.js" type="text/javascript" ></script>
	<script src="<%=basePath%>res/js/plugins/layer/layer.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>res/js/plugins/layer/laydate/laydate.js" type="text/javascript"></script>
	 <script src="<%=basePath%>res/js/jquery.form.js" type="text/javascript"></script>
	 <script src="<%=basePath%>res/js/common.js" type="text/javascript"></script>
	<link rel="shortcut icon" href="<%=basePath%>res/favicon.ico">
	<link href="<%=basePath%>res/css/bootstrap.min.css" rel="stylesheet">
	<link href="<%=basePath%>res/css/animate.css" rel="stylesheet">
	<link href="<%=basePath%>res/css/style.css" rel="stylesheet">
	<link href="<%=basePath%>res/css/font-awesome.css" rel="stylesheet">
