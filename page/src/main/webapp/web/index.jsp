<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<%@ include file="head/head.jsp"%>
	<title>乳买网_买卖乳制品原料，上乳买网</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>web/layui/css/layui.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>web/css/common.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>web/css/index.css"/>
	<script src="<%=basePath%>web/layui/layui.js" type="text/javascript" charset="utf-8"></script>

</head>
<body>
<%@include file ="head/top.jsp" %>
<%--<div class="top_bg">
    <div class="top">
        <div class="left">
            <ul>
                <li>您好，欢迎来到乳买网！</li>
                <li><a href="<%=basePath%>web/login.html">登录</a></li>
                <li><a href="<%=basePath%>web/register.html">注册</a></li>
            </ul>
        </div>
        <div class="right">
            <ul>
                <li><a href="">帮助中心</a></li>
                <li><a href="">关于我们</a></li>
                <li>客服热线：<span>13821101272</span></li>
            </ul>
        </div>
        <div class="clear"></div>
    </div>
</div>--%>

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
				<div class="layui-tab-item layui-show"><form class="layui-form" action=""><input type="" name="goods_in_stock_search" id="name1" value="" placeholder="请输入名称/品种/型号进行搜索" /><button class="layui-btn layui-btn-normal" lay-submit lay-filter="goods_in_stock_search">搜索</button></form></div>
				<div class="layui-tab-item"><form class="layui-form" action=""><input type="" name="want_to_buy_search" id="name2" value="" placeholder="请输入名称/品种/型号进行搜索" /><button class="layui-btn layui-btn-normal" lay-submit lay-filter="want_to_buy_search">搜索</button></form></div>
			</div>
		</div>

		<div class="clear"></div>
	</div>
</div>--%>
<jsp:include page="./head/search.jsp" ></jsp:include>

<div class="nav_menu_bg">
	<div class="nav_menu">
		<ul>
			<li class="active"><a href="javascript:;">首页</a></li>
			<li><a href="<%=basePath%>web/gstock/gstock.jsp">搜现货</a></li>
			<li><a href="<%=basePath%>web/supply/supply.jsp">求供应</a></li>
			<li><a href="<%=basePath%>web/lagistics/logistics.jsp">找物流</a></li>
			<!--<li><a href="javascript:;">金融</a></li>-->
			<li><a href="<%=basePath%>web/news/news.jsp">资讯</a></li>
			<li><a href="<%=basePath%>web/trend/price_trend.jsp">价格走势</a></li>
		</ul>
	</div>
</div>

<div class="layui-carousel" id="banner">
	<div class="suspension">
		<div class="left">
			<ul class="menu">
				<li><p><i class="layui-icon layui-icon-component"></i>搜现货<i class="layui-icon layui-icon-right"></i></p></li>
				<li><p><i class="layui-icon layui-icon-cart"></i>求供应<i class="layui-icon layui-icon-right"></i></p></li>
				<li><p><i class="layui-icon layui-icon-release"></i>找物流<i class="layui-icon layui-icon-right"></i></p></li>
				<li><p><i class="layui-icon layui-icon-search"></i>帮我找<i class="layui-icon layui-icon-right"></i></p></li>
			</ul>
			<div class="menu_content">
				<div class="goods_in_stock_search">
					<form class="layui-form" action=""><input type="" name="goods_in_stock_search" id="" value="" placeholder="请输入品类进行搜索"><button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="goods_in_stock_search">搜现货</button></form>
					<h2>热门搜索</h2>
					<ul id="pcate1">
						<%--<li><a href="">全脂奶粉</a></li>
						<li><a href="">脱脂奶粉</a></li>
						<li><a href="">黄油</a></li>
						<li><a href="">无水奶油</a></li>
						<li><a href="">淡奶油</a></li>
						<li><a href="">乳清蛋白粉</a></li>
						<li><a href="">乳糖</a></li>
						<li><a href="">乳清粉</a></li>
						<li><a href="">液态奶</a></li>--%>
					</ul>
					<%--<h2>热门品牌</h2>
					<ul>
						<li><a href="">蒙牛</a></li>
						<li><a href="">伊利</a></li>
						<li><a href="">蒙牛</a></li>
						<li><a href="">伊利</a></li>
						<li><a href="">蒙牛</a></li>
						<li><a href="">伊利</a></li>
					</ul>--%>
					<h6><a class="layui-btn layui-btn-radius layui-btn-normal" href="<%=basePath%>web/gstock/gstock.jsp">查看更多</a></h6>
				</div>
				<div class="want_to_buy_search">
					<form class="layui-form" action=""><input type="" name="want_to_buy_search" id="111" value="" placeholder="请输入品类进行搜索"><button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="goods_in_stock_search">求供应</button></form>
					<h2>最新供应</h2>
					<ul id="fsup" >
						<%--<li>
							<p class="name">全脂奶粉-速溶<span>9999-9999元/吨</span></p>
							<p class="price">9999吨<span>半小时前</span></p>
						</li>--%>
					</ul>
					<h6><a class="layui-btn layui-btn-radius layui-btn-normal" href="<%=basePath%>web/supply/supply.jsp">更多供应</a></h6>
				</div>
				<div class="find_car">
					<h4>————&emsp;询价找车&emsp;————</h4>
					<form class="layui-form layui-form-pane" action="">

						<div class="layui-form-item">
							<label class="layui-form-label">起始地</label>
							<div class="layui-input-block">
								<input type="text" name="starting_place" lay-verify="required" placeholder="请输入起始地" autocomplete="off" class="layui-input">
							</div>
						</div>

						<div class="layui-form-item">
							<label class="layui-form-label">目的地</label>
							<div class="layui-input-block">
								<input type="text" name="destination" lay-verify="required" placeholder="请输入目的地" autocomplete="off" class="layui-input">
							</div>
						</div>

						<div class="layui-form-item">
							<button class="layui-btn layui-btn-danger layui-btn-fluid" lay-submit="" lay-filter="find_car">查找</button>
						</div>

					</form>
					<ul>
						<li><img src="<%=basePath%>web/img/time_saving.png"/>省时<br />当天下单 当天派车</li>
						<li><img src="<%=basePath%>web/img/labor_saving.png"/>省力<br />乳买专车 说走就走</li>
						<li><img src="<%=basePath%>web/img/save_worry.png"/>省心<br />全程跟踪 短信通知</li>
						<li><img src="<%=basePath%>web/img/money_saving.png"/>省钱<br />多方报价 尽俭尽盈</li>
					</ul>

				</div>
				<div class="find_goods">
					<h2>找货助手-帮我找</h2>
					<form class="layui-form" action="">
						<div class="layui-form-item">
							<textarea class="layui-textarea" lay-verify="required" placeholder="写下您的真实需求，包括品种、品牌等，收到后我们会立即给您回电确认，剩下的就交给我们吧。" name="help_find" rows="" cols=""></textarea>
						</div>

						<div class="layui-form-item">
							<button class="layui-btn layui-btn-danger layui-btn-fluid" lay-submit="" lay-filter="help_find">帮我找</button>
						</div>
					</form>

				</div>
			</div>
		</div>
		<div class="right">
			<p class="user_info"><span><img src="<%=basePath%>web/img/user_head.png"/></span><i id="ui1"></i><br/>欢迎来到乳买网</p>
			<p id="ui2" style="display:none;" class="landing_registration"><a href="<%=basePath%>web/login.jsp" style="margin: 0 18px;" class="layui-btn layui-btn-primary layui-btn-sm layui-btn-radius">立即登录</a><a style="margin: 0 18px;" href="<%=basePath%>web/user/register.jsp" class="layui-btn layui-btn-primary layui-btn-sm layui-btn-radius">立即注册</a></p>
			<p id="ui3" style="display:none;" class="landing_registration"><a href="<%=basePath%>web/userinfo/user_center.jsp" style="margin: 0 18px;" class="layui-btn layui-btn-primary layui-btn-sm layui-btn-radius">个人中心</a><a style="margin: 0 18px;" href="javascript:exitUser();" class="layui-btn layui-btn-primary layui-btn-sm layui-btn-radius">退出登录</a></p>
			<h4>乳买网动态</h4>
			<ul id="new5">

			</ul>
		</div>
	</div>
	<div carousel-item>
		<div class="item"><a href="<%=basePath%>web/gstock/gstock.jsp"><img src="" id="img1" alt="" /></a></div>
		<div class="item"><a href="<%=basePath%>web/gstock/gstock.jsp"><img src="" id="img2" alt="" /></a></div>
		<div class="item"><a href="<%=basePath%>web/gstock/gstock.jsp"><img src="" id="img3" alt="" /></a></div>
	</div>
</div>



<%--<div class="layui-carousel" id="banner">
	<div carousel-item id="banner1">
		<div><img src="<%=basePath%>web/img/banner.jpg" alt="" /></div>
		<div><img src="<%=basePath%>web/img/banner.jpg" alt="" /></div>
	</div>
</div>--%>

<div class="wrapper">
	<%--<div class="statistical_data">
		<p><span class="release_data"><img src="<%=basePath%>web/img/rekease1.png"/><em>累计发布量<i>10000,00</i></em></span><span class="deal_data"><img src="<%=basePath%>web/img/deal1.png"/><em>累积成交量<i>10000,00</i></em></span><span class="amount_of_money_data"><img src="<%=basePath%>web/img/amount_of_money1.png"/><em>累计成交金额<i>10000,00</i></em></span></p>
	</div>--%>

	<div class="trend">
		<h2><img src="<%=basePath%>web/img/new_trend.png"/>最新现货<a href="<%=basePath%>web/gstock/gstock.jsp">查看全部&gt;&gt;</a></h2>
		<div class="left">

			<button id="right" type="button" class=""><i class="layui-icon layui-icon-left"></i></button>
			<button id="left" type="button" class=""><i class="layui-icon layui-icon-right"></i></button>

			<div class="layui-tab" id="stockdiv1">
				<ul class="layui-tab-title" id="c-ul">
					<%--<li class="layui-this">全脂奶粉</li>
					<li>脱脂奶粉</li>--%>
				</ul>
				<%--<div class="layui-tab-content" id="stockdiv1">
					<div class="layui-tab-item layui-show">
						<table class="table">
							<thead>
							<tr>
								<th>细分品种</th>
								<th>品牌</th>
								<th>价格</th>
								<th>产地</th>
								<th>交割仓库地</th>
								<th>可供数量</th>
								<th>供应商</th>
								<th>交易员</th>
							</tr>
							</thead>
							<tbody>
							&lt;%&ndash;<tr>
								<td>全脂奶粉-速溶</td>
								<td>品牌</td>
								<td class="red"><a class="log_show" href="javascript:;">登录可见</a></td>
								<td>中国-北京</td>
								<td>北京</td>
								<td>999</td>
								<td>供应商</td>
								<td>交易员</td>
							</tr>
							<tr>
								<td>全脂奶粉-速溶</td>
								<td>品牌</td>
								<td class="red">￥9999</td>
								<td>中国-北京</td>
								<td>北京</td>
								<td>999</td>
								<td>供应商</td>
								<td>交易员</td>
							</tr>
							<tr>
								<td>全脂奶粉-速溶</td>
								<td>品牌</td>
								<td class="red">￥9999</td>
								<td>中国-北京</td>
								<td>北京</td>
								<td>999</td>
								<td>供应商</td>
								<td>交易员</td>
							</tr>
							<tr>
								<td>全脂奶粉-速溶</td>
								<td>品牌</td>
								<td class="red">￥9999</td>
								<td>中国-北京</td>
								<td>北京</td>
								<td>999</td>
								<td>供应商</td>
								<td>交易员</td>
							</tr>
							<tr>
								<td>全脂奶粉-速溶</td>
								<td>品牌</td>
								<td class="red">￥9999</td>
								<td>中国-北京</td>
								<td>北京</td>
								<td>999</td>
								<td>供应商</td>
								<td>交易员</td>
							</tr>
							<tr>
								<td>全脂奶粉-速溶</td>
								<td>品牌</td>
								<td class="red">￥9999</td>
								<td>中国-北京</td>
								<td>北京</td>
								<td>999</td>
								<td>供应商</td>
								<td>交易员</td>
							</tr>&ndash;%&gt;

							</tbody>
						</table>
					</div>
					&lt;%&ndash;<div class="layui-tab-item">内容2</div>
					<div class="layui-tab-item">内容3</div>
					<div class="layui-tab-item">内容4</div>
					<div class="layui-tab-item">内容5</div>&ndash;%&gt;
				</div>--%>
			</div>

		</div>
		<div class="right" id="container">

		</div>
	</div>


	<div class="supply">
		<h2><img src="<%=basePath%>web/img/supply_icon.png"/>求购<a href="<%=basePath%>web/supply/supply.jsp">更多求购&gt;&gt;</a></h2>

		<div class="supply_list">
			<ul>
				<%--<li>
					<p class="tit">全脂奶粉-速溶<span>买家比价中</span></p>
					<p class="supplier">供应商：<span>供应商</span></p>
					<p class="number">求购数量：<span>9999</span>吨</p>
					<p class="time">距结束：<span></span></p>
					<span class="price"><em>9999</em>&nbsp;-&nbsp;<em>9999</em><i>元/吨</i></span>
					<a class="layui-btn layui-btn-radius layui-btn-normal" href="">我要供货</a>
				</li>--%>


			</ul>
		</div>
		<div class="right">
			<form class="layui-form layui-inline" action="">
				<textarea class="layui-textarea" lay-verify="required" placeholder="写下您的真实需求，包括品种、品牌等，收到后我们会立即给您回电确认，剩下的就交给我们吧。" name="help_find" rows="" cols=""></textarea>
				<button class="layui-btn layui-btn-danger" lay-submit lay-filter="help_find">帮我找</button>
			</form>
		</div>
		<div class="clear"></div>
	</div>

	<div class="logistics">
		<h2><img src="<%=basePath%>web/img/logistics_icon.png"/>物流服务</h2>
		<p class="logistics_service">
			<a href="<%=basePath%>web/lagistics/logistics.jsp"><img src="<%=basePath%>web/img/logistics_entrustment.jpg"/></a>
			<a href="<%=basePath%>web/lagistics/logistics.jsp"><img src="<%=basePath%>web/img/logistics_quotation.jpg"/></a>
		</p>
		<div class="logistics_search">
			<h4>————&emsp;询价找车&emsp;————</h4>
			<form class="layui-form layui-form-pane" action="">

				<div class="layui-form-item">
					<label class="layui-form-label">起始地</label>
					<div class="layui-input-block">
						<input type="text" name="starting_place" lay-verify="required" placeholder="请输入起始地" autocomplete="off" class="layui-input">
					</div>
				</div>

				<div class="layui-form-item">
					<label class="layui-form-label">目的地</label>
					<div class="layui-input-block">
						<input type="text" name="destination" lay-verify="required" placeholder="请输入目的地" autocomplete="off" class="layui-input">
					</div>
				</div>

				<%--<div class="layui-form-item">
					<label class="layui-form-label">货物</label>
					<div class="layui-input-block">
						<input type="text" name="goods" autocomplete="off" class="layui-input">
					</div>
				</div>--%>

				<div class="layui-form-item">
					<button class="layui-btn layui-btn-danger layui-btn-fluid" lay-submit lay-filter="find_car">查找</button>
				</div>

			</form>
		</div>
	</div>

	<div class="news_cooperation">
		<div class="left">

			<h2><img src="<%=basePath%>web/img/news_icon.png"/>行业资讯</h2>

			<div class="industry">
				<h5>行业动态<a href="<%=basePath%>web/news/news.jsp">查看更多&gt;&gt;</a></h5>
				<img src="<%=basePath%>web/img/industry.jpg"/>
				<ul>
					<%--<li><a href="">中国乳制品工业协会第二十四次年会在西安召开</a><span>2018-08-26</span></li>--%>
					<%--<li><a href="">中国乳制品工业协会第二十四次年会在西安召开</a><span>2018-08-26</span></li>
					<li><a href="">中国乳制品工业协会第二十四次年会在西安召开</a><span>2018-08-26</span></li>
					<li><a href="">中国乳制品工业协会第二十四次年会在西安召开</a><span>2018-08-26</span></li>
					<li><a href="">中国乳制品工业协会第二十四次年会在西安召开</a><span>2018-08-26</span></li>--%>
				</ul>
			</div>
			<div class="quotation">
				<h5>价格行情<a href="<%=basePath%>web/news/news.jsp">查看更多&gt;&gt;</a></h5>
				<img src="<%=basePath%>web/img/quotation.jpg"/>
				<ul>
					<%--<li><a href="">中国乳制品工业协会第二十四次年会在西安召开</a><span>2018-08-26</span></li>--%>
					<%--<li><a href="">中国乳制品工业协会第二十四次年会在西安召开</a><span>2018-08-26</span></li>
					<li><a href="">中国乳制品工业协会第二十四次年会在西安召开</a><span>2018-08-26</span></li>
					<li><a href="">中国乳制品工业协会第二十四次年会在西安召开</a><span>2018-08-26</span></li>
					<li><a href="">中国乳制品工业协会第二十四次年会在西安召开</a><span>2018-08-26</span></li>--%>
				</ul>
			</div>
		</div>

		<%--<div class="right">
			<h2><img src="<%=basePath%>web/img/cooperation_icon.png"/>合作企业</h2>
			<div class="cooperation">
				<ul>
					<li><img src="<%=basePath%>web/img/cooperation1.jpg"/></li>
					<li><img src="<%=basePath%>web/img/cooperation2.jpg"/></li>
					<li><img src="<%=basePath%>web/img/cooperation1.jpg"/></li>
					<li><img src="<%=basePath%>web/img/cooperation2.jpg"/></li>
					<li><img src="<%=basePath%>web/img/cooperation1.jpg"/></li>
					<li><img src="<%=basePath%>web/img/cooperation2.jpg"/></li>
					<li><img src="<%=basePath%>web/img/cooperation1.jpg"/></li>
					<li><img src="<%=basePath%>web/img/cooperation2.jpg"/></li>
					<li><img src="<%=basePath%>web/img/cooperation1.jpg"/></li>
					<li><img src="<%=basePath%>web/img/cooperation2.jpg"/></li>
				</ul>
			</div>
		</div>--%>
		<div class="clear"></div>

	</div>

</div>

<%@include file ="head/down.jsp" %>
</body>
<script type="text/javascript">

</script>

<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>

<script src="<%=basePath%>web/index.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">

</script>

</html>
