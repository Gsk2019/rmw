<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<%@ include file="../head/head.jsp"%>
		<title>价格走势</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>web/layui/css/layui.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>web/css/common.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>web/css/price_trend.css"/>
		<script src="<%=basePath%>web/layui/layui.js" type="text/javascript" charset="utf-8"></script>
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
					<li class="active"><a href="javascript:;">价格走势</a></li>
				</ul>
			</div>
		</div>
		
		<div class="wrapper">
			
			<div class="trend_chart">
				<div class="left">
					<h2>参考价简介</h2>
					<p>乳买网成交参考价是乳买网对采集到的乳制品市场成交数据，经过严格审核、计算，然后进行发布的价格参考体系。该参考价格反映的是乳制品的平均成交价格，旨在为广大乳制品企业和终端企业提供客观、及时的市场价格信息，为乳制品产业链企业提供决策参考依据。</p>
				</div>
				<div class="right layui-tab">
					<ul class="layui-tab-title" id="c-ul">
						<%--<li class="layui-this">全脂奶粉</li>
                        <li>脱脂奶粉</li>--%>
					</ul>
					<h2>乳买网参考价走势图
						<div class="layui-btn-group" id="group1">
							<button class="layui-btn layui-btn-sm layui-btn-primary layui-btn-normal" val="1">一月</button>
							<button class="layui-btn layui-btn-sm layui-btn-primary" val="2">三月</button>
							<button class="layui-btn layui-btn-sm layui-btn-primary" val="3">半年</button>
						</div>
					</h2>
					<div class="" id="container" style="height:250px;"></div>
				</div>
			</div>
			
			<div class="cycle">
				<div class="left">
					<h2>发布周期</h2>
					<h4>发布时间：</h4>
					<p>每个工作日上午 09:00 发布</p>
					<h4>发布渠道：</h4>
					<p>1.乳买网官方网站：</p>
					<p>www.rumaiwang.com</p>
					<p>2.乳买网官方公众号</p>
					<p><img width="150" src="<%=basePath%>web/img/qrcode.png"/></p>
				</div>
				<div class="right">
					<h2>乳买网市场成交参考价</h2>
					<table class="table">
						<thead>
							<tr><th>细分品种</th><th>本期价格</th><th>本期时间</th></tr>
						</thead>
						<tbody>
							<%--<tr><td>全脂奶粉-速溶</td><td class="bold">9999</td><td class="bold">9989</td><td class="red bold">+10</td><td class="red bold">+0.1%</td></tr>--%>
						</tbody>
					</table>
				</div>
				<div class="clear"></div>
			</div>
			
		</div>


		<%@include file ="../head/down.jsp" %>
	</body>

	<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>
	<script src="<%=basePath%>web/trend/price_trend.js" type="text/javascript" charset="utf-8"></script>
</html>
