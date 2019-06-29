<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<%@ include file="../head/head.jsp"%>
		<title>注册</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>web/layui/css/layui.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>web/css/common.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>web/css/register.css"/>
		<script src="<%=basePath%>web/layui/layui.js" type="text/javascript" charset="utf-8"></script>
		<script src="register.js" type="text/javascript" charset="utf-8"></script>

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
		
		<div class="register_bg">
			<div class="register">
				<h2>注册账户</h2>
				<div class="left">
					<form class="layui-form" action="">
						
						<div class="layui-form-item">
					      <label class="layui-form-label">姓名</label>
					      <div class="layui-input-inline">
					        <input type="text" id="name" name="name" lay-verify="required" placeholder="请输入姓名" autocomplete="off" class="layui-input">
					      </div>
					    </div>
					    
						<div class="layui-form-item">
					      <label class="layui-form-label">手机号</label>
					      <div class="layui-input-inline">
					        <input type="tel" id="phone" name="phone" lay-verify="required|phone" placeholder="请输入手机号" autocomplete="off" class="layui-input">
					      </div>
					    </div>
					    
					    <div class="layui-form-item">
					      <label class="layui-form-label">验证码</label>
					      <div class="layui-input-inline" style="width: 134px;">
					        <input type="text" id="code" name="verification_code" lay-verify="required" placeholder="请输入验证码" autocomplete="off" class="layui-input">
					      </div>
					      <a class="layui-btn layui-btn-danger"  href="javascript:void(0);" id="sendCode">获取验证码</a>
					    </div>
											
						  <div class="layui-form-item">
						    <label class="layui-form-label">密码</label>
						    <div class="layui-input-inline">
						      <input type="password" id="password" name="password" lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
						    </div>
						  </div>

						  <div class="layui-form-item">
						  	<label class="layui-form-label"></label>
						    <div class="layui-input-inline">
						      <button id="register" class="layui-btn layui-btn-normal layui-btn-fluid" lay-submit lay-filter="register">立即注册</button>
						    </div>
						  </div>
						  

					      <div class="layui-form-item">
						    <label class="layui-form-label"></label>
						    <div class="layui-input-inline agree">
						      <input type="checkbox" name="agree" lay-skin="primary" title="" checked="">我已阅读并同意《<a href="<%=basePath%>web/userinfo/user_service_agreement.jsp">用户服务协定</a>》
						    </div>
						  </div>

						  
					</form>
				</div>
				<div class="right">
					<h2>已有乳买网账户？<br /><br /><span>成为乳买网用户，可以免费发布需求，查看行业采购需求，更可免费快速帮您找性价比更高的货。</span><br /><br /><a class="layui-btn layui-btn-normal" href="<%=basePath%>web/login.jsp">立即登录</a></h2>
					<h5>乳买网客服热线：<span>13821101272</span></h5>
				</div>
			</div>
		</div>
		
<%--		<div class="copy_bg">
			<div class="copy">
				<p>
					<a href="javascript:void(0);">首页</a>丨
					<a href="javascript:void(0);">关于我们</a>丨
					<a href="javascript:void(0);">法律申明</a>丨
					<a href="javascript:void(0);">招贤纳士</a>丨
					<a href="javascript:void(0);">产品入驻</a>丨
					<a href="javascript:void(0);">公告咨询</a>丨
					<a href="javascript:void(0);">联系我们</a>
				</p>
				<p>&copy;2018 乳买网 rumaiwang.com 版权所以 豫ICP备18030548号</p>
			</div>
		</div>--%>

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

		<div class="register_success">
			<p><img src="<%=basePath%>web/img/success.png"/></p>
			<h2>恭喜您注册成功！</h2>
			<p><a class="layui-btn-primary layui-btn" href="<%=basePath%>web/index.jsp">回到首页</a><a class="layui-btn layui-btn-danger" href="<%=basePath%>web/userinfo/member.jsp">会员入驻</a></p>
		</div>
		<script>

		</script>
	</body>
</html>
