<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<%@ include file="../head/head.jsp"%>
		<title>搜现货</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>web/layui/css/layui.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>web/css/common.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>web/css/goods_in_stock.css"/>
		<script src="<%=basePath%>web/layui/layui.js" type="text/javascript" charset="utf-8"></script>

		<%--<script src="<%=basePath%>web/gstock/gstock.js" type="text/javascript" charset="utf-8"></script>--%>
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
				    <div class="layui-tab-item layui-show"><form class="layui-form" action=""><input type="" name="goods_in_stock_search" id="a1" value="" placeholder="请输入名称/品种/型号进行搜索" /><button class="layui-btn layui-btn-normal" lay-submit lay-filter="goods_in_stock_search">搜索</button></form></div>
				    <div class="layui-tab-item"><form class="layui-form" action=""><input type="" name="want_to_buy_search" id="a2" value="" placeholder="请输入名称/品种/型号进行搜索" /><button class="layui-btn layui-btn-normal" lay-submit lay-filter="want_to_buy_search">搜索</button></form></div>
				  </div>
				</div>

				<div class="clear"></div>
			</div>
		</div>
		
		<div class="nav_menu_bg">
			<div class="nav_menu">
				<ul>
					<li><a href="<%=basePath%>web/index.jsp">首页</a></li>
					<li class="active"><a href="javascript:;">搜现货</a></li>
					<li><a href="<%=basePath%>web/supply/supply.jsp">求供应</a></li>
					<li><a href="javascript:;">找物流</a></li>
					<!--<li><a href="javascript:;">金融</a></li>-->
					<li><a href="javascript:;">资讯</a></li>
					<li><a href="javascript:;">价格走势</a></li>
				</ul>
			</div>
		</div>
		
		<div class="select_search_bg">
			<div class="select_search">
				<ul>
					<li class="border-bottom"><span>品种</span>
						<ul class="variety">

						</ul>
					</li>
					<li class="border-bottom"><span>产地</span>
						<ul class="place">

						</ul>
					</li>
					<li><span>交割仓库地址</span>
						<ul class="warehouse">

						</ul>
					</li>
				</ul>
			</div>
		</div>
		
		<div class="select_list">
			<h2><p><span><i>↑</i>发布日期</span>|<span><i></i>数量</span>|<span><i></i>价格</span></p><a class="layui-btn layui-btn-warm" href="<%=basePath%>web/gstock/publishing_spot_information.jsp">发布现货信息</a><div class="clear"></div></h2>
			<table class="table">
			  <!--<colgroup>
			    <col width="150">
			    <col width="200">
			    <col>
			  </colgroup>-->
			  <thead>
			    <tr>
			      <th>细分品种</th>
			      <th>品牌</th>
			      <th>生产日期</th>
			      <th>价格</th>
			      <th>产地</th>
			      <th>交割仓库地</th>
			      <th>可供数量</th>
			      <th>供应商</th>
			      <th>交易员</th>
			      <th>发布日期</th>
			      <th></th>
			    </tr> 
			  </thead>
			  <tbody>

			  </tbody>
			</table>
			<div id="page"></div>
		</div>
			
		<div class="help_find">
			<img src="<%=basePath%>web/img/seach.png"/>
			<form class="layui-form layui-inline" action="">
			<textarea class="layui-textarea" placeholder="写下您的真实需求，包括品种、品牌等，收到后我们会立即给您回电确认，剩下的就交给我们吧。" name="help_find" rows="" cols=""></textarea>
			<button class="layui-btn layui-btn-danger" lay-submit lay-filter="help_find">帮我找</button>
			</form>
		</div>--%>

		<jsp:include page="gstock_in.jsp" ></jsp:include>
		<%@include file ="../head/down.jsp" %>
	</body>

</html>
