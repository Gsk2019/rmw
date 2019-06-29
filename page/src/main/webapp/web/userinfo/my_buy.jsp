<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<%@ include file="../head/head.jsp"%>
		<title>个人中心-我的求购</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>web/layui/css/layui.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>web/css/common.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>web/css/user_center.css"/>
		<script src="<%=basePath%>web/layui/layui.js" type="text/javascript" charset="utf-8"></script>

		<script src="<%=basePath%>web/userinfo/my_buy.js" type="text/javascript" charset="utf-8"></script>
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
						<a href="javascript:;"><li class="this">我的求购</li></a>
						<a href="<%=basePath%>web/userinfo/member.jsp"><li>会员入驻</li></a>
						<a href="<%=basePath%>web/userinfo/account_safety.jsp"><li>账户安全</li></a>
					</ul>
				</div>
				<div class="right_content">
					<h4>我的求购<a class="layui-btn layui-btn-sm layui-btn-warm" href="<%=basePath%>web/supply/publish_purchase_message.jsp">发布求购信息</a></h4>
					
					<table class="table">
						<thead>
							<tr>
								<th>细分品种</th>
								<th>价格</th>
								<th>数量</th>
								<th>发布日期</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<%--<tr>--%>
								<%--<td>全脂奶粉-速溶</td>--%>
								<%--<td class="red">9999-9999元</td>--%>
								<%--<td>999</td>--%>
								<%--<td>2018-08-17 18:04</td>--%>
								<%--<td>展示中</td>--%>
								<%--<td><a class="layui-btn layui-btn-xs layui-btn-normal" href="">编辑</a><a class="layui-btn layui-btn-xs layui-btn-danger" href="">删除</a></td>--%>
							<%--</tr>--%>
							<%--<tr>--%>
								<%--<td>全脂奶粉-速溶</td>--%>
								<%--<td class="red">9999-9999元</td>--%>
								<%--<td>999</td>--%>
								<%--<td>2018-08-17 18:04</td>--%>
								<%--<td>已过期</td>--%>
								<%--<td><a class="layui-btn layui-btn-xs layui-btn-normal" href="">编辑</a><a class="layui-btn layui-btn-xs layui-btn-danger" href="">删除</a></td>--%>
							<%--</tr>--%>

						</tbody>
					</table>
					<div id="page"></div>
				</div>
			</div>
			
		</div>

		<%@include file ="../head/down.jsp" %>
	</body>
</html>
