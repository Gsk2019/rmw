<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<%@ include file="head/head.jsp"%>
		<title>帮助中心</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>web/css/help_center.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>web/layui/css/layui.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>web/css/common.css"/>
		<script src="<%=basePath%>web/layui/layui.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
	<%@include file ="head/top.jsp" %>

	<jsp:include page="head/search.jsp" ></jsp:include>
		<%--<div class="top_bg">
			<div class="top">
				<div class="left">
					<ul>
						<li>您好，欢迎来到乳买网！</li>
						<li><a href="login.html">登录</a></li>
						<li><a href="register.html">注册</a></li>
					</ul>
				</div>
				<div class="right">
					<ul>
						<li><a href="help_center.jsp?page=0">帮助中心</a></li>
						<li><a href="help_center.jsp?page=9">关于我们</a></li>
						<li>客服热线：<span>400-123-456</span></li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
		</div>
		
		<div class="nav_bg">
			<div class="nav">
				<div class="logo">
					<img src="img/logo1.png" >
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
		
		<%--<div class="nav_menu_bg">
			<div class="nav_menu">
				<ul>
					<li><a href="index.html">首页</a></li>
					<li><a href="goods_in_stock.html">搜现货</a></li>
					<li><a href="supply.html">求供应</a></li>
					<li><a href="javascript:;">找物流</a></li>
					<!--<li><a href="javascript:;">金融</a></li>-->
					<li><a href="news.html">资讯</a></li>
					<li><a href="price_trend.html">价格走势</a></li>
				</ul>
			</div>
		</div>--%>

		<div class="wrapper">
			
			<div class="help_center">
				<p class="current_location">当前位置：<a href="<%=basePath%>web/index.jsp">首页</a>&gt;帮助中心</p>
				<div class="left">
					
					<div class="left_menu">
						<ul>
							<li class="this">如何找货</li>
							<li>现货搜索</li>
							<li>委托人工帮您找货</li>
							<li>发布求购</li>
							<li>成为供应商</li>
							<li>发布现货信息</li>
							<li>如何注册账户</li>
							<li>如何登录</li>
							<li>如何找回密码</li>
							<li>公司简介</li>
							<li>联系我们</li>
						</ul>
					</div>
					
				</div>
				<div class="right">
					<ul>
						<li style="display: block;">
							<h2>如何找货</h2>
							<p>在乳买网，您可以通过以下三种方式进行找货：现货搜索，委托人工帮您找货，发布求购。</p>
							<p>一、现货搜索</p>
							<p><img src="../web/img/help_1.png"/></p>
							<p>二、委托人工帮您找货</p>
							<p><img src="../web/img/help_2.png"/></p>
							<p>三、发布求购</p>
							<p><img src="../web/img/help_3.png"/></p>
						</li>
						<li>
							<h2>现货搜索</h2>
							<p>一、您可以直接在乳买网首页【搜现货】进行搜索，输入您想要查询的现货信息条件，搜索查看并委托。乳买网交易员会尽快联系您为您提供免费撮合服务。</p>
							<p><img src="../web/img/help_4.png"/></p>
							<p>二、 2、您可以通过【搜现货】页面通过点击筛选条件找到您需要的现货信息，查看现货信息并委托。乳买网交易员会尽快联系您为您提供免费撮合服务。</p>
							<p><img src="../web/img/help_1.png"/></p>
						</li>
						<li>
							<h2>委托人工帮您找货</h2>
							<p>一、网页发布找货需求：</p>
							<p>乳买网【搜现货】页面下方【找货助手】文件框内直接填写您的详细采购需求,点击【帮我找】后即可将您的采购信息提交给乳买网。乳买网交易员会联系您确定您的采购意并尽快帮您撮合找货。</p>
							<p><img src="../web/img/help_2.png"/></p>
							<p>二、您可以直接拨打客服热线：13821101272，提交您的采购需求。</p>
						</li>
						<li>
							<h2>发布求购</h2>
							<p>乳买网【求供应】页面点击【发布求购信息】；在【发布求购信息】弹出页面直接填写您的采购需求即可。</p>
							<p><img src="../web/img/help_5.png"/></p>
							<p><img src="../web/img/help_3.png"/></p>
						</li>
						<li>
							<h2>成为供应商</h2>
							<p>注册账户成功后，选择会员入驻进入会员入驻页面，填写信息进行提交审核。审核完成后即可成功供应商，进行现货发布。</p>
							<p><img src="../web/img/help_6.png"/></p>
							<p><img src="../web/img/help_7.png"/></p>
						</li>
						<li>
							<h2>发布现货信息</h2>
							<p>您可以直接在【搜现货】页面点击【发布供货信息】，输入您要发布的现货产品信息，乳买网审核通过后会在网站平台发布显示您的现货信息供平台客户查看委托。</p>
							<p><img src="../web/img/help_1.png"/></p>
							<p><img src="../web/img/help_8.png"/></p>
						</li>
						<li>
							<h2>如何注册账户</h2>
							<p>打开乳买网网页，点击左上方【免费注册】，填入正确信息，提交完成账号注册。</p>
							<p><img src="../web/img/help_9.png"/></p>
						</li>
						<li>
							<h2>如何登录</h2>
							<p>打开乳买网网页，点击左上方【登录】，输入手机号和密码进行登录。</p>
							<p><img src="../web/img/help_10.png"/></p>
						</li>
						<li>
							<h2>如何找回密码</h2>
							<p>如果您忘记了登录密码，可以进入登录页面，点击忘记密码，按照要求填写即可重置密码。</p>
							<p>进入登录页面，点击【忘记密码】，输入注册的手机号接收短信验证码来重置密码。</p>
							<p><img src="../web/img/help_11.png"/></p>
						</li>
						<li>
							<h2>公司简介</h2>
							<p>乳买网（www.rumaiwang.com）是由上海乳缘信息科技有限公司打造的国内首家服务于乳制品原料产业链的专业电商平台。</p>
						</li>
						<li>
							<h2>联系我们</h2>
							
							<p>客服热线：13821101272</p>

							<p>客服QQ：286222235</p>
							
							<p>官方网址：www.rimaiwang.com</p>
							
							<p>微信公众号：rumaiwang</p>
							
							<p><img style="width: 200px!important;" src="img/qrcode.png"/></p>
						
						</li>
					</ul>
				</div>
				<div class="clear"></div>
			
			</div>
			
		</div>

	<%@include file ="head/down.jsp" %>
		
		<%--<div class="serve_model">
			<p>
			<span><img src="../web/img/matchmaking.png"/>免费撮合</span>
			<span><img src="../web/img/high_quality.png"/>优质货源</span>
			<span><img src="../web/img/service.png"/>全方位服务</span>
			<span><img src="../web/img/rich.png"/>品类丰富</span>
			<span><img src="../web/img/quality_goods.png"/>正品保障</span>
			</p>
		</div>--%>
		
		<%--<div class="footer">
			<ul class="nav">
				<li>我要买货
					<ul>
						<li><a href="help_center.jsp?page=0">如何找货</a></li>
						<li><a href="help_center.jsp?page=1">现货搜索</a></li>
						<li><a href="help_center.jsp?page=2">委托人工帮您找货</a></li>
						<li><a href="help_center.jsp?page=3">发布求购</a></li>
					</ul>
				</li>
				<li>我要卖货
					<ul>
						<li><a href="help_center.jsp?page=4">成为供应商</a></li>
						<li><a href="help_center.jsp?page=5">发布现货信息</a></li>
					</ul>
				</li>
				<li>常见问题
					<ul>
						<li><a href="help_center.jsp?page=6">如何注册账户</a></li>
						<li><a href="help_center.jsp?page=7">如何登陆</a></li>
						<li><a href="help_center.jsp?page=8">如何找回密码</a></li>
					</ul>
				</li>
				<li>关于我们
					<ul>
						<li><a href="help_center.jsp?page=9">公司简介</a></li>
						<li><a href="help_center.jsp?page=10">联系我们</a></li>
					</ul>
				</li>
				<li>关注微信服务号
					<img class="qr" src="img/qrcode.png"/>
				</li>--%>
				<!--<li>
					<em class="tel" ><img src="img/tel.png"/>400-1234-5678</em>
					<ul>
						<li>工作时间：<span>周一至周六9:00-18:00</span></li>
						<li>售后服务：<span>kefu@rumaiwang.com</span></li>
						<li>产品入驻：<span>yunying@rumaiwang.com</span></li>
						<li>建议投诉：<span>ceo@rumaiwang.com</span></li>
					</ul>
				</li>-->
			<%--</ul>--%>
			
			<!--<div class="link">
				友情链接：<a href="javasrcipt:;">乳买网</a>
				<a href="javasrcipt:;">乳买网</a>
				<a href="javasrcipt:;">乳买网</a>
				<a href="javasrcipt:;">乳买网</a>
				<a href="javasrcipt:;">乳买网</a>
				<a href="javasrcipt:;">乳买网</a>
				<a href="javasrcipt:;">乳买网</a>
				<a href="javasrcipt:;">乳买网</a>
				<a href="javasrcipt:;">乳买网</a>
				<a href="javasrcipt:;">乳买网</a>
				<a href="javasrcipt:;">乳买网</a>
				<a href="javasrcipt:;">乳买网</a>
				<a href="javasrcipt:;">乳买网</a>
				<a href="javasrcipt:;">乳买网</a>
				<a href="javasrcipt:;">乳买网</a>
				<a href="javasrcipt:;">乳买网</a>
				<a href="javasrcipt:;">乳买网</a>
				<a href="javasrcipt:;">乳买网</a>
				<a href="javasrcipt:;">乳买网</a>
				<a href="javasrcipt:;">乳买网</a>
				<a href="javasrcipt:;">乳买网</a>
			</div>-->
			
		<%--</div>--%>
		
		<%--<div class="copy_bg">
			<div class="copy">
				<p>
					<a href="index.html">首页</a>丨
					<a href="help_center.jsp?page=9">关于我们</a>丨
					<a href="help_center.jsp?page=4">会员入驻</a>丨
					<!--<a href="news.html">公告咨询</a>丨-->
					<a href="help_center.jsp?page=10">联系我们</a>
				</p>
				<p>&copy;2018 乳买网 rumaiwang.com 版权所以 豫ICP备18030548号</p>
			</div>
		</div>
		
		<div class="right_menu">
			<ul>
				<li>
					<div>
						<p class="mb-20"><img width="20" src="img/qq.png" alt="" /><a href="#">12345678910</a></p>
						<p>工作时间：<br />周一至周五 9：00-18：00</p>
					</div>
					<span><img src="img/qq_icon.png"/></span><em>QQ<br />客服</em>
				</li>
				<li>
					<div>
						<p class="center"><img width="170" src="img/qrcode.png"/><b>扫二维码联系客服</b></p>
					</div>
					<span><img src="img/wechat_icon.png"/></span><em>微信<br />客服</em>
				</li>
				<li>
					<div>
						<p class="mb-20">客服电话：<b>400-1234-5678</b></p>
						<p>工作时间：<br />周一至周五 9：00-18：00</p>
					</div>
					<span><img src="img/customer_service_icon.png"/></span><em>在线<br />客服</em>
				</li>
				<li>
					<div>
						<form class="layui-form" action="">
							<textarea rows="" cols="" name="feedback" class="layui-textarea"  lay-verify="required" placeholder="欢迎提出意见和建议，我们会认真对待您的反馈，感谢您的关注和支持。"></textarea>
							<button class="layui-btn layui-btn-normal btn-sm layui-btn-fluid" lay-submit="" lay-filter="feedback">提交反馈</button>
						</form>
					</div>
					<span><img src="img/feedback.png"/></span><em>意见<br />反馈</em>
				</li>
				
			</ul>
		</div>--%>
	</body>
	<script type="text/javascript">
		layui.use(['layer','element','form','laydate','table','carousel'], function(){ 
			
			 var $ = layui.$ //由于layer弹层依赖jQuery，所以可以直接得到
			,layer = layui.layer
			,element = layui.element
			,form = layui.form
			,laydate = layui.laydate
			,table = layui.table
			,carousel = layui.carousel;
			
			//右侧固定客服
			$('.right_menu ul li').hover(function(){
				$(this).find('span').hide()
				$(this).find('em,div').css('display','block')
			},function(){
				$(this).find('em,div').hide()
				$(this).find('span').show()
				layer.close(layer.index);
			})
			
			//现货搜索
			  form.on('submit(goods_in_stock_search)', function(data){
			    layer.msg(JSON.stringify(data.field));
			    return false;
			  });
			  
  			//求购搜索	
				form.on('submit(want_to_buy_search)', function(data){
			    layer.msg(JSON.stringify(data.field));
			    return false;
			 });
			 
			 //导航点击样式和内容切换
  			$('.left_menu ul li').click(function(){
  				$('.left_menu ul li').removeClass('this')
  				$(this).addClass('this')
  				
  				var index = $(this).index()
  				$('.help_center .right ul li').hide().eq(index).show()
  			})
  			
  			//获取url参数
  			function GetQueryString(name)
			{
			     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
			     var r = window.location.search.substr(1).match(reg);
			     if(r!=null)return  unescape(r[2]);return null;
			}
			//显示指定的内容
			var page = GetQueryString('page')
			$('.left_menu ul li').eq(page).click()
  			
  			 //意见反馈	
			form.on('submit(feedback)', function(data){
			    layer.msg(JSON.stringify(data.field));
			    return false;
			 });
			 
		});
		
		</script>
	
</html>
