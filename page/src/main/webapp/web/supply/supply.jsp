<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<%@ include file="../head/head.jsp"%>
		<title>求供应</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>web/layui/css/layui.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>web/css/common.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>web/css/supply.css"/>
		<%--<script src="<%=basePath%>web/layui/layui.js" type="text/javascript" charset="utf-8"></script>--%>
		<script src="<%=basePath%>web/supply/supply.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		<%@include file ="../head/top.jsp" %>
		
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
				
				<div class="layui-tab layui-tab-brief nav_search">
				  <ul class="layui-tab-title">
				    <li ><a href="<%=basePath%>web/gstock/gstock.jsp">现货</a></li>
				    <li class="layui-this">求购</li>
				  </ul>
				  <div class="layui-tab-content">
				    <div class="layui-tab-item "><form class="layui-form" action=""><input type="" name="goods_in_stock_search" id="a1" value="" placeholder="请输入名称/品种/型号进行搜索" /><button class="layui-btn layui-btn-normal" lay-submit lay-filter="goods_in_stock_search">搜索</button></form></div>
				    <div class="layui-tab-item layui-show"><form class="layui-form" action=""><input type="" name="want_to_buy_search" id="want_to_buy_search" value="${param.keyvalue}" placeholder="请输入名称/品种/型号进行搜索" /><button class="layui-btn layui-btn-normal" lay-submit lay-filter="want_to_buy_search">搜索</button></form></div>
				  </div>
				</div>

				<div class="clear"></div>
			</div>
		</div>
		
		<div class="nav_menu_bg">
			<div class="nav_menu">
				<ul>
					<li><a href="<%=basePath%>web/index.jsp">首页</a></li>
					<li><a href="<%=basePath%>web/gstock/gstock.jsp">搜现货</a></li>
					<li class="active"><a href="javascript:;">求供应</a></li>
					<li><a href="<%=basePath%>web/lagistics/logistics.jsp">找物流</a></li>
					<!--<li><a href="javascript:;">金融</a></li>-->
					<li><a href="<%=basePath%>web/news/news.jsp">资讯</a></li>
					<li><a href="<%=basePath%>web/trend/price_trend.jsp">价格走势</a></li>
				</ul>
			</div>
		</div>

		<div class="supply">
			<div class="form">
				<form class="layui-form" action="">
					<textarea class="" name="content" id="content" rows="" cols="" placeholder="写下您的真实需求，包括品种、品牌等，收到后我们会立即给您回电确认，剩下的就交给我们吧。"></textarea><br />
					<button class="layui-btn layui-btn-danger" lay-submit lay-filter="supply">帮我找</button>
				</form>
			</div>
		</div>

		<div class="select_list">
			<h2>
				<p>
					<!--<span><i>↑</i>发布日期</span>|<span><i></i>数量</span>|<span><i></i>价格</span>-->
				<form class="layui-form" action="">
					品种：<input class="layui-input" id="category" name="category" type="text" />品牌：<input class="layui-input" name="brand" id="brand" type="text" /><button class="layui-btn layui-btn-normal" lay-submit lay-filter="select_search">搜索</button><button type="reset" class="layui-btn layui-btn-primary">清空</button>
				</form>
				</p>
				<a class="layui-btn layui-btn-warm" href="javascript:clickSupply();">发布求购信息</a><div class="clear"></div></h2>

			<div class="supply_list">
				<ul>
					<%--<li>
						<div class="left">
							<p class="tit"><span class="varieties">全脂奶粉-速溶</span><span class="price"><i>9999</i>-<i>9999</i>元/吨</span></p>
							<p>求购数量：<span>9999吨</span>产地：<span>中国-北京</span>仓库交割地：<span>北京</span>品牌：<span>品牌</span></p>
							<p>生产日期：<span>2018-08-28 15:58</span>交易员：<span>交易员</span></p>
							<p>备注：<span></span></p>
						</div>
						<div class="right">
							<p class="release_time"><span>发布时间：<em>2018-08-28 15:58</em></span><span class="time">距结束：<em></em></span></p>
							<p class="supply_of_material">买家比价中<a class="layui-btn layui-btn-sm layui-btn-normal" href="">我要供货</a></p>
						</div>
					</li>--%>
				</ul>
			</div>

			<div id="page"></div>
		</div>


		<%@include file ="../head/down.jsp" %>
	</body>

</html>
