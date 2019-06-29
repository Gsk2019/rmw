<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<%@ include file="../head/head.jsp"%>
	<title>发布现货信息</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>web/layui/css/layui.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>web/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>web/css/publish_purchase_message.css"/>
	<script src="<%=basePath%>web/layui/layui.js" type="text/javascript" charset="utf-8"></script>

	<script src="<%=basePath%>web/userinfo/publishing_user1.js" type="text/javascript" charset="utf-8"></script>

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
				<div class="layui-tab-item layui-show"><form class="layui-form" action=""><input type="" name="goods_in_stock_search" id="A1" value="" placeholder="请输入名称/品种/型号进行搜索" /><button class="layui-btn layui-btn-normal" lay-submit lay-filter="goods_in_stock_search">搜索</button></form></div>
				<div class="layui-tab-item"><form class="layui-form" action=""><input type="" name="want_to_buy_search" id="B1" value="" placeholder="请输入名称/品种/型号进行搜索" /><button class="layui-btn layui-btn-normal" lay-submit lay-filter="want_to_buy_search">搜索</button></form></div>
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

<div class="publish_purchase_message_bg">
	<div class="publish_purchase_message">
		<div class="title">
			<p>——————&emsp;&emsp;<span>编辑现货信息</span>&emsp;&emsp;——————</p>
		</div>
		<div class="form">
			<p class="path">当前位置：<a href="goods_in_stock.html">搜现货</a>&gt;<span>编辑现货信息</span></p>
			<form class="layui-form layui-form-pane" action="">

				<input type="hidden" value="" name="id" id="id"/>
				<table class="layui-table">
					<colgroup>
						<col width="110">
						<col>
						<col width="110">
						<col width="220">
					</colgroup>
					<tr>
						<th>品种<i>*</i></th>
						<td>
							<div class="layui-input-inline">
								<select id="categoryTopId" name="categoryTopId" lay-verify="required" lay-filter="varieties">
									<option value="">请选择品种</option>
									<%--<option value="全脂奶粉">全脂奶粉</option>
									<option value="脱脂奶粉">脱脂奶粉</option>
									<option value="黄油">黄油</option>
									<option value="无水奶油">无水奶油</option>
									<option value="淡奶油">淡奶油</option>
									<option value="乳清蛋白粉">乳清蛋白粉</option>
									<option value="乳糖">乳糖</option>
									<option value="乳清粉">乳清粉</option>
									<option value="液态奶">液态奶</option>--%>
								</select>
								<input id="categoryTopName" name="categoryTopName" type="hidden">
								<input id="categoryName" name="categoryName" type="hidden">
							</div>
							<div class="layui-input-inline varieties_1">

							</div>
						</td>
						<th>品牌<i>*</i></th>
						<td>
							<div class="layui-input-inline">
								<input type="text" name="brand" id="brand" lay-verify="required" placeholder="请输入品牌" autocomplete="off" class="layui-input">
							</div>
						</td>
					</tr>
					<tr>
						<th>产地<i>*</i></th>
						<td>
							<div class="layui-input-inline">
								<select name="productTopAreaId" id="productTopAreaId" lay-verify="required" lay-filter="place_of_origin">
									<option value="">请选择产地</option>
									<%--<option value="中国">中国</option>
									<option value="澳洲">澳洲</option>
									<option value="欧洲">欧洲</option>
									<option value="北美洲">北美洲</option>
									<option value="南美洲">南美洲</option>--%>
								</select>
								<input id="productTopArea" name="productTopArea" type="hidden">
								<input id="productArea" name="productArea" type="hidden">
							</div>
							<div class="layui-input-inline place_of_origin_1">

							</div>
						</td>
						<th>仓库交割地<i>*</i></th>
						<td>
							<div class="layui-input-inline">
								<select name="repertoryId" id="repertoryId" lay-verify="required">
									<option value="">请选择交割仓库地</option>
									<%--<option value="不限">不限</option>
									<option value="北京">北京</option>
									<option value="上海">上海</option>
									<option value="广州">广州</option>
									<option value="厦门">厦门</option>
									<option value="天津">天津</option>--%>
								</select>
								<input id="repertory" name="repertory" type="hidden">
							</div>
						</td>
					</tr>
					<tr>
						<th>生产日期<i>*</i></th>
						<td>
							<div class="layui-input-inline">
								<input type="text" name="productDate" lay-verify="required" id="productDate" lay-verify="date_of_manufacture" placeholder="请选择生产日期" autocomplete="off" class="layui-input" lay-key="1">
							</div>
						</td>
						<th>可供数量<i>*</i></th>
						<td>
							<div class="layui-input-inline">
								<input type="number" name="count" id="count" placeholder="请输入数量" lay-verify="required" autocomplete="off" class="layui-input">
							</div>
						</td>
					</tr>
					<tr>
						<th>价格<i>*</i></th>
						<td>
							<div class="layui-input-inline">
								<input type="number" name="price" id="price" lay-verify="required" placeholder="￥" autocomplete="off" class="layui-input">
							</div>
						</td>
						<th>有效期<i>*</i></th>
						<td>
							<div class="layui-input-inline">
								<select id="valday" name="valday" lay-verify="required">
									<option value="">请选择有效期</option>
									<option value="3">3天</option>
									<option value="7">7天</option>
									<option value="15">15天</option>
									<option value="30">30天</option>
								</select>
							</div>
						</td>
					</tr>
					<tr>
						<th>单位<i>*</i></th>
						<td>
							<div class="layui-input-inline">
								<input type="text" name="unit" id="unit" lay-verify="required" placeholder="请输入货品单位" autocomplete="off" class="layui-input">
							</div>
						</td>
						<th><i>*</i></th>
						<td>

						</td>
					</tr>
					<tr>
						<th>联系人<i>*</i></th>
						<td>
							<div class="layui-input-inline">
								<input type="text" name="contactName" id="contactName" lay-verify="required" placeholder="请输入联系人" autocomplete="off" class="layui-input">
							</div>
						</td>
						<th>电话<i>*</i></th>
						<td>
							<div class="layui-input-inline">
								<input type="tel" name="contactTel" id="contactTel" lay-verify="required|phone" placeholder="请输入电话" autocomplete="off" class="layui-input">
							</div>
						</td>
					</tr>
				</table>


				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn layui-btn-normal" lay-submit lay-filter="release_spot">立即发布</button>
						<button type="reset" class="layui-btn layui-btn-primary" id="reset">重置</button>
					</div>
				</div>

			</form>
		</div>
	</div>
</div>

<%@include file ="../head/down.jsp" %>
</body>

</html>
