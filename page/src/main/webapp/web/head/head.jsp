<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="init.jsp"%>
	<meta name="description" content="乳买网_买卖乳制品原料，上乳买网" />
	<meta name="keywords" content="乳买网,全脂奶粉,NZMP全脂奶粉,脱脂奶粉,无水奶油,无盐黄油,乳清粉,乳糖,乳钙,恒天然全脂奶粉,新西兰奶粉,安佳大黄油" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta name="renderer" content="webkit">
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<%--<link rel="shortcut icon" href="../favicon.ico" />--%>
	<c:set var="path" value="${pageContext.request.contextPath}" />
	<%
	String path = request.getContextPath();
	// 获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%>
	<!-- 全局js -->

	<link rel="shortcut icon" href="<%=basePath%>res/favicon.ico">
    <script src="<%=basePath%>web/js/jquery.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>web/js/jquery.cookie.js" type="text/javascript"></script>
    <script src="<%=basePath%>web/head/init.js" type="text/javascript"></script>
	<script src="<%=basePath%>res/js/common.js" type="text/javascript"></script>

	<link rel="stylesheet" type="text/css" href="<%=basePath%>web/layui/css/layui.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>web/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>web/css/goods_in_stock.css"/>
	<script src="<%=basePath%>web/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script>
    var _hmt = _hmt || [];
    (function() {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?eb4c83e410b6ea2af2b152552c4639b2";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();
</script>
