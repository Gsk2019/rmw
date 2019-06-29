<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<%@ include file="../head/head.jsp"%>
	<title>找物流</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>web/layui/css/layui.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>web/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>web/css/logistics.css"/>
	<script src="<%=basePath%>web/layui/layui.js" type="text/javascript" charset="utf-8"></script>

	<script src="<%=basePath%>web/lagistics/logistics.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=basePath%>res/dist/js/city-picker.data.js"></script>
	<script src="<%=basePath%>res/dist/js/city-picker.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>res/dist/css/city-picker.css"/>
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
				<div class="layui-tab-item layui-show"><form class="layui-form" action=""><input type="" name="goods_in_stock_search" id="a" value="" placeholder="请输入名称/品种/型号进行搜索" /><button class="layui-btn layui-btn-normal" lay-submit lay-filter="goods_in_stock_search">搜索</button></form></div>
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
			<li ><a href="<%=basePath%>web/supply/supply.jsp">求供应</a></li>
			<li class="active"><a href="javascript:;">找物流</a></li>
			<!--<li><a href="javascript:;">金融</a></li>-->
			<li><a href="<%=basePath%>web/news/news.jsp">资讯</a></li>
			<li><a href="<%=basePath%>web/trend/price_trend.jsp">价格走势</a></li>
		</ul>
	</div>
</div>

<div class="wrapper">


	<div class="logistics_banner">
		<img src="<%=basePath%>web/img/logistics_banner.jpg"/>
		<div class="find_car">
			<h4>————&emsp;询价找车&emsp;————</h4>
			<form class="layui-form layui-form-pane" action="">

				<div class="layui-form-item">
					<label class="layui-form-label">起始地</label>
					<div class="layui-input-block">
						<input type="text" id="startPoint" name="startPoint" value="${param.startplace}"<%--lay-verify="required"--%> placeholder="请输入起始地" autocomplete="off" class="layui-input">
					</div>
				</div>

				<div class="layui-form-item">
					<label class="layui-form-label">目的地</label>
					<div class="layui-input-block">
						<input type="text" id="endPoint" name="endPoint" value="${param.endplace}"<%--lay-verify="required"--%> placeholder="请输入目的地" autocomplete="off" class="layui-input">
					</div>
				</div>

				<div class="layui-form-item">
					<button class="layui-btn layui-btn-danger layui-btn-fluid" lay-submit="" lay-filter="find_car">查找</button>
				</div>

			</form>
		</div>
	</div>

	<div class="release_logistics">
		<div class="left">
			<div>
				<h2>免费物流撮合</h2>
				<em>欢迎物流企业，发布物流运力资源</em>
				<%--<p><a class="layui-btn layui-btn-warm" id="open_issuing_logistics_quotes">发布物流报价</a></p>--%>
				<p>联系我们：13821101272</p>
			</div>
			<img src="<%=basePath%>web/img/release_logistics.jpg"/>
		</div>
		<div class="right">
			<h2>物流报价</h2>
			<div class="topRec_List">
				<dl>
					<dd>发布时间</dd>
					<dd>起点</dd>
					<dd>卸点</dd>
					<dd>运输价格</dd>
					<dd>运输吨位</dd>
					<dd>联系方式</dd>
				</dl>
				<div class="maquee" id="logistics1">
					<ul id="ul1">
						<%--<li>
							<div>2018-09-06 11:01</div>
							<div>北京1</div>
							<div>广州</div>
							<div>3000</div>
							<div>5000</div>
							<div>13345678910</div>
						</li>--%>

					</ul>
				</div>
			</div>


		</div>
	</div>

	<div class="logistics_entrustment">
		<div class="left">
			<div>
				<h2>物流服务</h2>
				<em>乳买网整合多家物流机构，为您提供综合的物流服务</em>
				<p><a class="layui-btn layui-btn-warm" id="open_issuing_logistics_commission">发布物流委托</a></p>
				<p>联系我们：13821101272</p>
			</div>
			<img src="<%=basePath%>web/img/logistics-entrustment.jpg"/>
		</div>
		<div class="right">
			<h2>物流委托</h2>
			<div class="topRec_List">
				<dl>
					<dd>发布时间</dd>
					<dd>起点</dd>
					<dd>卸点</dd>
					<dd>委托货物</dd>
					<dd>运输吨位</dd>
					<dd>联系方式</dd>
				</dl>
				<div class="maquee" id = "logistics2">
					<ul id="ul2">
						<%--<li>
							<div>2018-09-06 11:01</div>
							<div>北京</div>
							<div>广州</div>
							<div>全脂奶粉-速溶</div>
							<div>5000</div>
							<div>13345678910</div>
						</li>--%>

					</ul>
				</div>
			</div>

		</div>
	</div>

</div>

	<%@include file ="../head/down.jsp" %>

<div class="issuing_logistics_quotes">

	<form class="layui-form layui-form-pane">

		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">起始地</label>
				<div class="layui-input-inline">
					<input type="text" name="startPoint" lay-verify="required" placeholder="请输入起始地" autocomplete="off" class="layui-input">
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">目的地</label>
				<div class="layui-input-inline">
					<input type="text" name="endPoint" lay-verify="required" placeholder="请输入目的地" autocomplete="off" class="layui-input">
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">运输价格</label>
				<div class="layui-input-inline">
					<input type="number" name="price" lay-verify="required" placeholder="请输入运输价格" autocomplete="off" class="layui-input">
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">运输吨位</label>
				<div class="layui-input-inline">
					<input type="number" name="tonnage" lay-verify="required" placeholder="请输入运输吨位" autocomplete="off" class="layui-input">
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">联系方式</label>
				<div class="layui-input-inline">
					<input type="tel" name="phone" lay-verify="required" placeholder="请输入联系方式" autocomplete="off" class="layui-input">
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">有效期</label>
				<div class="layui-input-inline">
					<input type="text" name="expiryDate" id="expiryDate" lay-verify="required" placeholder="请选择有效期" autocomplete="off" class="layui-input">
				</div>
			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="issuing_logistics_quotes">立即发布</button>
				<button type="reset" class="layui-btn layui-btn-primary close">取消</button>
			</div>
		</div>

	</form>

</div>

<div class="issuing_logistics_commission">

	<form class="layui-form layui-form-pane">

		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">起始地</label>
				<div class="layui-input-inline">
					<input type="text" name="startPoint" readonly lay-verify="required" data-toggle="city-picker" placeholder="请输入起始地" >
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">目的地</label>
				<div class="layui-input-inline">
					<input type="text" name="endPoint" lay-verify="required" data-toggle="city-picker" placeholder="请输入目的地" autocomplete="off" >
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">委托货物</label>
				<div class="layui-input-inline">
					<input type="text" name="consignment" lay-verify="required" placeholder="请输入委托货物" autocomplete="off" class="layui-input">
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">运输吨位</label>
				<div class="layui-input-inline">
					<input type="number" name="tonnage" lay-verify="required" placeholder="请输入运输吨位" autocomplete="off" class="layui-input">
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">联系方式</label>
				<div class="layui-input-inline">
					<input type="tel" name="phone" lay-verify="phone" placeholder="请输入联系方式" autocomplete="off" class="layui-input">
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">有效期</label>
				<div class="layui-input-inline">
					<input type="text" name="expiryDate" id="expiryDate2" lay-verify="required" placeholder="请选择有效期" autocomplete="off" class="layui-input">
				</div>
			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="issuing_logistics_commission">立即发布</button>
				<button type="reset" class="layui-btn layui-btn-primary close">取消</button>
			</div>
		</div>

	</form>

</div>

</body>

</html>
