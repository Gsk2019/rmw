<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<%@ include file="head/head.jsp"%>
		<title>登录</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>web/layui/css/layui.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>web/css/common.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>web/css/login.css"/>
		<script src="<%=basePath%>web/layui/layui.js" type="text/javascript" charset="utf-8"></script>

		<script src="<%=basePath%>web/login.js" type="text/javascript" charset="utf-8"></script>

	</head>
	<body>
		
		<div class="nav_bg">
			<div class="nav">
				<div class="logo">
					<img src="<%=basePath%>web/img/logo1.png" onclick="window.location.href='<%=basePath%>web/index.jsp'" style="cursor:pointer;">
				</div>
				<!--<div class="nav_list">
					<ul>
						<li><a href="">首页</a></li>
						<li><a class="active" href="">搜现货</a></li>
						<li><a href="">求供应</a></li>
						<li><a href="">找物流</a></li>
						<a class="layui-btn layui-btn-normal" href="">登录</a>
						<a class="layui-btn layui-btn-primary" href="">注册</a>
					</ul>
				</div>-->
				
				<div class="clear"></div>
				
			</div>
		</div>
		
		<div class="login_form_bg">
			<div class="login_form">
				
				<form class="layui-form layui-form-pane" action="">

				<h2>登录我的乳买网</h2>
				
				  <div class="layui-form-item">
				    <label class="layui-form-label"><i class="layui-icon layui-icon-username"></i></label>
				    <div class="layui-input-inline">
				      <input type="text" id="phone" name="title" lay-verify="required" placeholder="请输入手机号" autocomplete="off" class="layui-input">
				    </div>
				  </div>
				  <div class="layui-form-item">
				    <label class="layui-form-label"><i class="layui-icon layui-icon-password"></i></label>
				    <div class="layui-input-inline">
				      <input type="password" id="password" name="password" lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
				    </div>
				  </div>
				  
				  <p class="retrieve_the_password"><a href="<%=basePath %>web/userinfo/password_recovery.jsp">找回密码</a></p>
				  
				  <div class="layui-form-item">
				    <div class="layui-input-inline">
				      <button id="login" class="layui-btn layui-btn-normal layui-btn-fluid" lay-submit lay-filter="login">立即登录</button>
				    </div>
				  </div>
				  
				  <p><span class="register"><a href="<%=basePath%>web/user/register.jsp">还没有账户？<i>快速注册</i></a></span></p>
				<%--<span class="quick">快捷登录：<a href=""><i class=" layui-icon layui-icon-login-wechat"></i></a></span>--%>
				</form>

			</div>
		</div>

		<div class="copy_bg">
			<div class="copy">
				<p>
					<%--<a href="javascript:;">首页</a>丨
                    <a href="javascript:;">关于我们</a>丨
                    <a href="javascript:;">法律申明</a>丨
                    <a href="javascript:;">招贤纳士</a>丨
                    <a href="javascript:;">产品入驻</a>丨
                    <a href="javascript:;">公告咨询</a>丨
                    <a href="javascript:;">联系我们</a>--%>
					<a href="<%=basePath%>web/index.jsp">首页</a>丨
					<a href="<%=basePath%>web/help_center.jsp?page=9">关于我们</a>丨
					<a href="<%=basePath%>web/help_center.jsp?page=4">会员入驻</a>丨
					<!--<a href="news.html">公告咨询</a>丨-->
					<a href="<%=basePath%>web/help_center.jsp?page=10">联系我们</a>
				</p>
				<p>&copy;2018 乳买网 rumaiwang.com 版权所以 豫ICP备18030548号</p>
			</div>
		</div>
		
		<script>

		</script>
	</body>
</html>
