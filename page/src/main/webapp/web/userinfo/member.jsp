<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<%@ include file="../head/head.jsp"%>
		<title>个人中心-会员入驻</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>web/layui/css/layui.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>web/css/common.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>web/css/user_center.css"/>
		<script src="<%=basePath%>web/layui/layui.js" type="text/javascript" charset="utf-8"></script>

        <script src="<%=basePath%>web/userinfo/member.js" type="text/javascript" charset="utf-8"></script>
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
						<a href="<%=basePath%>web/userinfo/my_buy.jsp"><li >我的求购</li></a>
						<a href="javascript:;"><li class="this">会员入驻</li></a>
						<a href="<%=basePath%>web/userinfo/account_safety.jsp"><li>账户安全</li></a>
					</ul>
				</div>
				<div class="right_content">
					<h4>会员入驻</h4>

					<div class="upload" id="a1" style="display:none;">
						<div class="left">
							<blockquote class="layui-elem-quote">正在审核，请耐心等待</blockquote>
						</div>
					</div>

					<div class="upload" id="a2" style="display:none;">
						<div class="left">
							<blockquote class="layui-elem-quote">会员已认证</blockquote>
						</div>
					</div>

					<div class="upload" id="a8" style="display:none;">
						<div class="left">
							<blockquote class="layui-elem-quote" style="color: red">认证未通过请重新提交资料</blockquote>
						</div>
					</div>

					<%--<div class="upload" id="a3" style="display:none;">
						<div class="left">
							<blockquote class="layui-elem-quote">营业执照</blockquote>
							<div class="layui-upload-drag" id="business_license">
							  <img class="layui-upload-img" id="business_license_img">
                                <input type="hidden" id="img1" name="img1" value=""/>
							  <i class="layui-icon"></i>
							  <p>点击上传，或将文件拖拽到此处</p>
							</div>
						</div>
						<div class="right">
							<blockquote class="layui-elem-quote">食品经营许可证/食品生产许可证</blockquote>
							<div class="layui-upload-drag" id="licence">
							  <img class="layui-upload-img" id="licence_img">
                                <input type="hidden" id="img2" name="img2" value=""/>
							  <i class="layui-icon"></i>
							  <p>点击上传，或将文件拖拽到此处</p>
							</div>
						</div>
						&lt;%&ndash;<button  name="" id="img_submit" value="" />&ndash;%&gt;
					</div>
					<div class="upload" id="a4" style="display:none;">
						<p><a class="layui-btn layui-btn-normal" id="is_img">确认上传</a></p>
					</div>--%>

					<form class="layui-form" id="a3" style="display:none;">

						<div class="layui-form-item">
							<label class="layui-form-label">公司名称</label>
							<div class="layui-input-block">
								<input type="text" name="company" lay-verify="required" placeholder="请输入公司名称" autocomplete="off" class="layui-input">
							</div>
						</div>

						<div class="layui-form-item">
							<label class="layui-form-label">公司电话</label>
							<div class="layui-input-block">
								<input type="tel" name="company_tel" lay-verify="required" placeholder="请输入公司电话" autocomplete="off" class="layui-input">
							</div>
						</div>

						<div class="layui-form-item">
							<label class="layui-form-label">公司法人姓名</label>
							<div class="layui-input-block">
								<input type="text" name="company_owner" lay-verify="required" placeholder="公司法人姓名" autocomplete="off" class="layui-input">
							</div>
						</div>

						<div class="layui-form-item">
							<label class="layui-form-label">营业执照</label>
							<div class="layui-input-block">
								<div class="layui-upload">
									<div class="layui-upload-list">
										<img class="layui-upload-img" id="business_license">
										<input type="hidden" id="img1" name="img1" value=""/>
										<p id="business_license_upload"></p>
									</div>
								</div>
							</div>
						</div>

						<div class="layui-form-item">
							<label class="layui-form-label">食品许可证<br />(2选1)上传</label>
							<div class="layui-input-block">

								<input type="radio" name="licence" value="licence_jy" title="食品经营许可证" checked>
								<input type="radio" name="licence" value="licence_sc" title="食品生产许可证">

							</div><br />
							<div class="layui-input-block">
								<div class="layui-upload">
									<div class="layui-upload-list">
										<img class="layui-upload-img" id="licence">
										<input type="hidden" id="img2" name="img2" value=""/>
										<p id="license_upload"></p>
									</div>
								</div>
							</div>
						</div>


						<%--<div class="layui-form-item">
							<label class="layui-form-label">身份证号码</label>
							<div class="layui-input-block">
								<input type="text" name="identity" lay-verify="identity" placeholder="请输入身份证号" autocomplete="off" class="layui-input">
							</div>
						</div>


						<div class="layui-form-item">
							<label class="layui-form-label">身份证正面</label>
							<div class="layui-input-block">
								<div class="layui-upload">
									<div class="layui-upload-list">
										<img class="layui-upload-img" id="identity_front">
										<input type="hidden" id="img3" name="img3" value=""/>
										<p id="identity_front_upload"></p>
									</div>
								</div>
							</div>
						</div>

						<div class="layui-form-item">
							<label class="layui-form-label">身份证反面</label>
							<div class="layui-input-block">
								<div class="layui-upload">
									<div class="layui-upload-list">
										<img class="layui-upload-img" id="identity_reverse">
										<input type="hidden" id="img4" name="img4" value=""/>
										<p id="identity_reverse_upload"></p>
									</div>
								</div>
							</div>
						</div>--%>

						<div class="layui-form-item">
							<div class="layui-input-block">
								<button class="layui-btn layui-btn-normal" lay-submit lay-filter="member">提交审核</button>
								<!--<button type="reset" class="layui-btn layui-btn-primary">重置</button>-->
							</div>
						</div>

					</form>
				</div>
			</div>
			<div class="clear"></div>
		</div>

		<%@include file ="../head/down.jsp" %>
	</body>
	<script type="text/javascript">

	</script>
</html>
