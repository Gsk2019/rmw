<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<%@ include file="../head/head.jsp"%>
		<title></title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>web/layui/css/layui.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>web/css/common.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>web/css/news.css"/>
		<script src="<%=basePath%>web/layui/layui.js" type="text/javascript" charset="utf-8"></script>

        <script src="<%=basePath%>web/news/news_content.js" type="text/javascript" charset="utf-8"></script>
	</head>
<style type="text/css">

	.content img{
		max-width: 100%;
	}
</style>
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
					<li class="active" ><a href="<%=basePath%>web/news/news.jsp">资讯</a></li>
					<li><a href="<%=basePath%>web/trend/price_trend.jsp">价格走势</a></li>
				</ul>
			</div>
		</div>
		
		<div class="wrapper">
			
			<div class="news">
				<p class="current_location">当前位置：<a href="news.jsp">资讯</a>&gt;资讯详情</p>
			<div class="left">
				 <div class="news_content">
					<h1 class="title"></h1>
					<h6 class="time"><span id="time1"></span><span id="time2"></span> <span id="time3"></span>

						<input type="hidden" id="id">

						<div class="bdsharebuttonbox" data-tag="share_1">
							<em>分享到：</em>
							<a class="bds_more" data-cmd="more"></a>
							<a class="bds_qzone" data-cmd="qzone" href="#"></a>
							<a class="bds_tsina" data-cmd="tsina"></a>
							<a class="bds_tqq" data-cmd="tqq"></a>
							<a class="bds_renren" data-cmd="renren"></a>
							<a class="bds_weixin" data-cmd="weixin"></a>
						</div>
					</h6>
					<div class="content">

						<%--<p style="margin-top: 0px; margin-bottom: 12px; padding: 0px; white-space: normal; line-height: 30px; letter-spacing: 1px; color: rgb(61, 61, 61); font-family: 微软雅黑;">
						    携手创新开启新时代，开放合作共谱新华章。8月25日，由陕西省人民政府、西安市人民政府支持，中国<a href="http://www.rupin.org/" target="_blank" style="text-decoration-line: none;"><span style="color: rgb(98, 98, 98);">乳制品</span></a>工业协会与国际乳品联合会(IDF)中国国家委员会主办，西安银桥乳业集团与西安百跃羊乳集团协办的中国<a href="http://www.rupin.org/" target="_blank" style="text-decoration-line: none;"><span style="color: rgb(98, 98, 98);">乳制品</span></a>工业协会第二十四次年会在西安曲江国际会议中心召开，中国乳业领军企业齐聚西安，共谋乳业发展良策，共商乳业振兴大计。
						</p>
						<p>
						    &nbsp;
						</p>
						<p style="text-align: center;">
						    <img alt=" " src="http://www.rupin.org/uploads/allimg/180826/213AQ910-0.jpg" title=" "/>
						</p>
						<p style="margin-top: 0px; margin-bottom: 12px; padding: 0px; white-space: normal; line-height: 30px; letter-spacing: 1px; color: rgb(61, 61, 61); font-family: 微软雅黑;">
						    　　陕西省副省长魏增军，西安市委常委、副市长高杲，国家市场监督管理总局稽查专员毕玉安、工业和信息化部消费品司副司长汪敏燕、中国<a href="http://www.rupin.org/" target="_blank" style="text-decoration-line: none;"><span style="color: rgb(98, 98, 98);">乳制品</span></a>工业协会理事长吴秋林、国际乳品联合会(IDF)中国国家委员会主席宋昆冈等领导以及澳大利亚、新西兰、法国等乳业发达国家驻华使馆官员以及来自全球的乳业精英、专家、科技工作者参加了大会开幕式。
						</p>
						<p>
						    &nbsp;
						</p>
						<p style="text-align: center;">
						    <img alt=" " src="http://www.rupin.org/uploads/allimg/180826/213AT137-1.jpg" title=" "/>
						</p>
						<p style="margin-top: 0px; margin-bottom: 12px; padding: 0px; white-space: normal; line-height: 30px; letter-spacing: 1px; color: rgb(61, 61, 61); font-family: 微软雅黑;">
						    　　中国<a href="http://www.rupin.org/" target="_blank" style="text-decoration-line: none;"><span style="color: rgb(98, 98, 98);">乳制品</span></a>工业协会理事长吴秋林致大会开幕词，陕西省人民政府副省长魏增军、西安银桥乳业(集团)有限公司董事长刘华国，西安百跃羊乳集团有限公司执行董事、总经理孟江涛分别代表东道主致欢迎词，协会副理事长兼秘书长刘美菊主持大会开幕式。
						</p>
						<p style="margin-top: 0px; margin-bottom: 12px; padding: 0px; white-space: normal; line-height: 30px; letter-spacing: 1px; color: rgb(61, 61, 61); font-family: 微软雅黑;">
						    　　作为大会协办方，西安银桥乳业(集团)有限公司董事长刘华国在致辞中表示，这次会议是在中国改革开放40周年，中国特色社会主义进入了新时代，乳品企业按照党中央国务院的部署，加快民族<a href="http://www.rupin.org/" target="_blank" style="text-decoration-line: none;"><span style="color: rgb(98, 98, 98);">奶业</span></a>振兴，按照高质量发展的要求，为决胜全面建成小康社会开创未来之际召开的一次盛会，具有重大的历史意义。
						</p>
						<p>
						    &nbsp;
						</p>
						<p style="text-align: center;">
						    <img alt=" " src="http://www.rupin.org/uploads/allimg/180826/213AW000-2.jpg" title=" "/>
						</p>
						<p style="margin-top: 0px; margin-bottom: 12px; padding: 0px; white-space: normal; line-height: 30px; letter-spacing: 1px; color: rgb(61, 61, 61); font-family: 微软雅黑;">
						    　　刘华国说，中国实行改革开放40年以来，经济社会快速发展，综合国力不断提高，人民生活发生了翻天覆地的变化。中国人民实现了向全面小康迈进的历史跨越。中国<a href="http://www.rupin.org/" target="_blank" style="text-decoration-line: none;"><span style="color: rgb(98, 98, 98);">乳制品</span></a>行业也取得了举世瞩目的成绩，诞生了一批具有国际化的大型民族企业，中国的乳品企业也逐步迈出国门走向世界，在国际舞台上崭露头角。与中国改革开放同步，银桥乳业也经历了40年的发展，从一个小手工作坊，发展成为今天的国际乳品联合会IDF成员、中国<a href="http://www.rupin.org/htm/hangye/" target="_blank" style="text-decoration-line: none;"><span style="color: rgb(98, 98, 98);">乳品行业</span></a>领军品牌，这些都归功于改革开放的伟大实践。
						</p>
						<p>
						    &nbsp;
						</p>
						<p style="text-align: center;">
						    <img alt=" " src="http://www.rupin.org/uploads/allimg/180826/213AT622-3.jpg" title=" "/>
						</p>
						<p style="margin-top: 0px; margin-bottom: 12px; padding: 0px; white-space: normal; line-height: 30px; letter-spacing: 1px; color: rgb(61, 61, 61); font-family: 微软雅黑;">
						    　　成立于1978年的西安银桥乳业集团，是中国西北地区产销量最大的<a href="http://www.rupin.org/" target="_blank" style="text-decoration-line: none;"><span style="color: rgb(98, 98, 98);">乳制品</span></a>专业生产企业，40年来秉承“质量求发展，诚信铸品牌”和“奶品就是人品，质量就是生命”的理念，已发展成为农业产业化国家重点龙头企业、中国学生饮用奶定点生产企业，并跨入了中国乳品十强企业前列。集团旗下主导产品“秦俑牌”、“阳光宝宝牌”、“艾宝瑞牌”系列奶粉和“银桥牌”系列<a href="http://www.rupin.org/" target="_blank" style="text-decoration-line: none;"><span style="color: rgb(98, 98, 98);">液态奶</span></a>以过硬的质量、优质的服务和良好的信誉，深受广大消费者的喜爱和信赖。银桥产品连续七届蝉联欧亚经济论坛官方指定专用<a href="http://www.rupin.org/" target="_blank" style="text-decoration-line: none;"><span style="color: rgb(98, 98, 98);">乳制品</span></a>。
						</p>
						<p style="margin-top: 0px; margin-bottom: 12px; padding: 0px; white-space: normal; line-height: 30px; letter-spacing: 1px; color: rgb(61, 61, 61); font-family: 微软雅黑;">
						    　　刘华国在致辞中说，在习近平新时代中国特色社会主义思想指引下，坚持改革开放，不断适应把握经济发展新常态，有效提升中国乳业的发展质量和效益。贯彻落实国务院《关于推进<a href="http://www.rupin.org/" target="_blank" style="text-decoration-line: none;"><span style="color: rgb(98, 98, 98);">奶业</span></a>振兴保障乳品质量安全的意见》，加快推进民族<a href="http://www.rupin.org/" target="_blank" style="text-decoration-line: none;"><span style="color: rgb(98, 98, 98);">奶业</span></a>振兴。希望以本次大会为契机，进一步增强创新发展理念，加强与科技前沿对接，积极参与“一带一路”建设，深化多领域交流与合作，努力实现互利共赢，协同发展，为振兴中国乳业贡献力量。
						</p>
						<p>
						    &nbsp;
						</p>
						<p style="text-align: center;">
						    <img alt=" " src="http://www.rupin.org/uploads/allimg/180826/213AR1S-4.jpg" title=" "/>
						</p>
						<p style="margin-top: 0px; margin-bottom: 12px; padding: 0px; white-space: normal; line-height: 30px; letter-spacing: 1px; color: rgb(61, 61, 61); font-family: 微软雅黑;">
						    　　会上，国家食品药品监督管理总局毕玉安司长在大会上就目前<a href="http://www.rupin.org/" target="_blank" style="text-decoration-line: none;"><span style="color: rgb(98, 98, 98);">乳制品</span></a>的监管工作，<a href="http://www.rupin.org/" target="_blank" style="text-decoration-line: none;"><span style="color: rgb(98, 98, 98);">乳制品</span></a>质量状况做了重要报告。国际乳品联合会(IDF)中国国家委员会主席宋昆冈就《中国乳业十年变迁》作主题报告。
						</p>
						<p style="margin-top: 0px; margin-bottom: 12px; padding: 0px; white-space: normal; line-height: 30px; letter-spacing: 1px; color: rgb(61, 61, 61); font-family: 微软雅黑;">
						    　　澳大利亚驻华大使安思捷女士、新西兰驻华使馆大使傅恩莱女士、美国驻华使馆公使齐伯平先生、法国驻华公使孟森先生、国际乳品联合会(IDF)Judith Bryans主席、IDF韩国国家委员会Chang-buhm Lee 主席和美国乳品出口协会Tom Vilsack 总裁应邀出席了大会并发言。
						</p>
						<p>
						    &nbsp;
						</p>
						<p style="text-align: center;">
						    <img alt=" " src="http://www.rupin.org/uploads/allimg/180826/213ATP8-5.jpg" title=" "/>
						</p>
						<p style="margin-top: 0px; margin-bottom: 12px; padding: 0px; white-space: normal; line-height: 30px; letter-spacing: 1px; color: rgb(61, 61, 61); font-family: 微软雅黑;">
						    　　大会同期举办“中国全球乳业(CGD)2018合作与发展论坛”，共同探讨国际乳业发展趋势和中国乳业未来发展方向，交流了企业在国际合作中的经验、成果。本次年会还组织了“国际乳业发展合作论坛”、“液体乳创新发展论坛”、“婴幼儿及功能配方乳粉创新发展论坛”、“乳品科技创新论坛”、“干酪技术创新发展论坛”、“羊乳发展创新论坛”、“<a href="http://www.rupin.org/" target="_blank" style="text-decoration-line: none;"><span style="color: rgb(98, 98, 98);">乳制品</span></a>生产供应链新技术、新产品、新配料发布专场”七个专题论坛，计有85位报告人做主题报告。
						</p>
						<p style="margin-top: 0px; margin-bottom: 12px; padding: 0px; white-space: normal; line-height: 30px; letter-spacing: 1px; color: rgb(61, 61, 61); font-family: 微软雅黑;">
						    　　8月25日晚，还将举办2018中国乳业科技系列奖项颁奖盛典。
						</p>
						<p>
						    <br/>
						</p>--%>

						
					</div>
					<div class="more"  id="dm1">
						<%--<p>上一篇：<a href="<%=basePath%>web/news/news_content.jsp?id">中国乳制品工业协会第24次年会在西安隆重召开</a></p>
						<p>下一篇：<a href="">中国乳制品工业协会第24次年会在西安隆重召开</a></p>--%>
					</div>
					<!--<div class="">评论</div>-->
				</div>

				<div class="comment">
					<div class="comment_title">
						<h2>评论</h2>
						<div class="border"></div>
					</div>
					<div class="comment_reply">
						<form class="layui-form">
							<textarea class="layui-textarea" lay-verify="required" name="desc" rows="" cols="" placeholder="来说两句吧"></textarea>
							<button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="desc"><i class="layui-icon layui-icon-survey"></i><br />快速评论</button>
							<p>评论仅供其表达个人看法，不代表乳买网立场</p>
						</form>
					</div>
					<div class="comment_list">
						<ul id="ncul">
							<%--<li>
								<p><span class="name">乳买网</span><span class="time">2周前</span></p>
								<p class="desc">乳买网很不错</p>
							</li>
							<li>
								<p><span class="name">乳买网</span><span class="time">2周前</span></p>
								<p class="desc">乳买网很不错</p>
							</li>
							<li>
								<p><span class="name">乳买网</span><span class="time">2周前</span></p>
								<p class="desc">乳买网很不错</p>
							</li>--%>
						</ul>
						<p class="more"><button class="layui-btn layui-btn-sm layui-btn-primary" id="bm1">查看更多</button></p>
					</div>
				</div>

			</div>
			<div class="right">
				<div class="industry" id="industry1">
					<h2>行业动态</h2>
					<img src="<%=basePath%>web/img/industry.jpg"/>
					<ul>
						<%--<li><a href="">中国乳制品工业协会第24次年会在西安隆重召开</a></li>
						--%>
					</ul>
					<p><a id="a1" class="layui-btn layui-btn-radius layui-btn-primary layui-btn-sm" href="javascript:;">加载更多</a></p>
				</div>
				<div class="industry" id="industry2">
					<h2>价格行情</h2>
					<img src="<%=basePath%>web/img/quotation.jpg"/>
					<ul>
						<%--<li><a href="">中国乳制品工业协会第24次年会在西安隆重召开</a></li>--%>
					</ul>
					<p><a id="a2" class="layui-btn layui-btn-radius layui-btn-primary layui-btn-sm" href="javascript:;">加载更多</a></p>
				</div>
			</div>
			<div class="clear"></div>
			</div>
			
		</div>

		<%@include file ="../head/down.jsp" %>
	</body>

</html>
