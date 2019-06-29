<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<%@ include file="../head/head.jsp"%>
		<title>个人中心-账户安全</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>web/layui/css/layui.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>web/css/common.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>web/css/user_center.css"/>
		<script src="<%=basePath%>web/layui/layui.js" type="text/javascript" charset="utf-8"></script>

        <script src="<%=basePath%>web/userinfo/account_safety.js" type="text/javascript" charset="utf-8"></script>

	</head>
	<body>
		<%@include file ="../head/top.jsp" %>
		
		<%--<div class="nav_bg">
			<div class="nav">
				<div class="logo">
					<img src="<%=basePath%>web/img/logo1.png" >
				</div>
				
				<div class="layui-tab layui-tab-brief nav_search">
				  <ul class="layui-tab-title">
				    <li class="layui-this">现货</li>
				    <li>求购</li>
				  </ul>
				  <div class="layui-tab-content">
				    <div class="layui-tab-item layui-show"><form class="layui-form" action=""><input type="" name="goods_in_stock_search" id="" value="" placeholder="请输入名称/品种/型号进行搜索" /><button class="layui-btn layui-btn-normal" lay-submit lay-filter="goods_in_stock_search">搜索</button></form></div>
				    <div class="layui-tab-item"><form class="layui-form" action=""><input type="" name="want_to_buy_search" id="" value="" placeholder="请输入名称/品种/型号进行搜索" /><button class="layui-btn layui-btn-normal" lay-submit lay-filter="want_to_buy_search">搜索</button></form></div>
				  </div>
				</div>

				<div class="clear"></div>
			</div>
		</div>--%>
		<jsp:include page="../head/search.jsp" ></jsp:include>
		
		<div class="nav_menu_bg">
			<div class="nav_menu">
				<ul>
					<li><a href="<%=basePath%>web/index.jsp">首页</a></li>
					<li><a href="<%=basePath%>web/gstock/gstock.jsp">搜现货</a></li>
					<li><a href="<%=basePath%>web/supply/supply.jsp">求供应</a></li>
					<li><a href="<%=basePath%>web/lagistics/logistics.jsp">找物流</a></li>
					<!--<li><a href="javascript:;">金融</a></li>-->
					<li><a href="<%=basePath%>web/news/news.jsp">资讯</a></li>
					<li><a href="<%=basePath%>web/trend/price_trend.jsp">价格走势</a></li>
				</ul>
			</div>
		</div>
		
		<div class="wrapper">
			
			<div class="content">
				<h2>当前位置：<a href="">首页</a>&gt;个人中心</h2>
				<div class="left_menu">
					<ul>
						<a href="<%=basePath%>web/userinfo/user_center.jsp"><li>我的发布</li></a>
						<a href="<%=basePath%>web/userinfo/my_buy.jsp"><li>我的求购</li></a>
						<a href="<%=basePath%>web/userinfo/member.jsp"><li>会员入驻</li></a>
						<a href="javascript:;"><li class="this">账户安全</li></a>
					</ul>
				</div>
				<div class="right_content">
					<h4>账户安全</h4>
					<div class="security">
						<p><i class="layui-icon layui-icon-auz"></i>定期修改密码有利于您的账户安全</p>
						<ul>
							<li><span>绑定手机号</span><em id="uphone"></em><i>已绑定</i></li>
							<li><span>登陆密码</span><em>********</em><i>已设置</i><a class="layui-btn layui-btn-normal" id="change_password">修改</a></li>
						</ul>
					</div>
				</div>
			</div>
			
		</div>

		<%@include file ="../head/down.jsp" %>
		
		<div class="change_password_content">
			<form class="layui-form">
				
				<div class="layui-form-item">
		          <label class="layui-form-label">旧密码</label>
		          <div class="layui-input-inline">
		            <input type="password" name="old_password" lay-verify="required" placeholder="请输入旧密码" autocomplete="off" class="layui-input">
		          </div>
		        </div>
		        <div class="layui-form-item">
		          <label class="layui-form-label">新密码</label>
		          <div class="layui-input-inline">
		            <input type="password" name="new_password" lay-verify="required" placeholder="请输入新密码" autocomplete="off" class="layui-input">
		          </div>
		        </div>
		        <div class="layui-form-item">
		          <label class="layui-form-label">再次输入</label>
		          <div class="layui-input-inline">
		            <input type="password" name="new_password1" lay-verify="required" placeholder="请再次输入新密码" autocomplete="off" class="layui-input">
		          </div>
		        </div>
		        <div class="layui-form-item">
		          <div class="layui-input-block">
		            <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="change_password">确认修改</button>
		            <button type="reset" class="layui-btn layui-btn-primary" id="close_change_password">取消</button>
		          </div>
		        </div>
			</form>
		</div>
		
	</body>
	<script type="text/javascript">

	</script>
</html>
