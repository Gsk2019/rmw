<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../head/head.jsp"%>
<script src="<%=basePath%>web/head/search.js" type="text/javascript" charset="utf-8"></script>

<div class="nav_bg">
	<div class="nav">
		<div class="logo">
			<img src="<%=basePath%>web/img/logo1.png" onclick="window.location.href='<%=basePath%>web/index.jsp'" style="cursor:pointer;" >
		</div>

		<div class="layui-tab layui-tab-brief nav_search">
			<ul class="layui-tab-title">
				<li class="layui-this">现货</li>
				<li>求购</li>
			</ul>
			<div class="layui-tab-content">
				<div class="layui-tab-item layui-show"><form class="layui-form" action=""><input type="" name="goods_in_stock_search" value="" placeholder="请输入品类进行搜索" /><button class="layui-btn layui-btn-normal" lay-submit lay-filter="goods_in_stock_search">搜索</button></form></div>
				<div class="layui-tab-item"><form class="layui-form" action=""><input type="" name="want_to_buy_search" value="" placeholder="请输入品类进行搜索" /><button class="layui-btn layui-btn-normal" lay-submit lay-filter="want_to_buy_search">搜索</button></form></div>
			</div>
		</div>

		<div class="clear"></div>
	</div>
</div>