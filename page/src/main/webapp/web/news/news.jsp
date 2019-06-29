<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<%@ include file="../head/head.jsp"%>
		<title>资讯</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>web/layui/css/layui.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>web/css/common.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>web/css/news.css"/>
		<%--<script src="<%=basePath%>web/layui/layui.js" type="text/javascript" charset="utf-8"></script>--%>
		<script src="<%=basePath%>web/news/news.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>

		<%@include file ="../head/top.jsp" %>

		<jsp:include page="../head/search.jsp" ></jsp:include>
		
		<div class="nav_menu_bg">
			<div class="nav_menu">
				<ul>
					<li><a href="<%=basePath%>web/index.jsp">首页</a></li>
					<li><a href="<%=basePath%>web/gstock/gstock.jsp">搜现货</a></li>
					<li ><a href="<%=basePath%>web/supply/supply.jsp">求供应</a></li>
					<li><a href="<%=basePath%>web/lagistics/logistics.jsp">找物流</a></li>
					<!--<li><a href="javascript:;">金融</a></li>-->
					<li class="active"><a href="javascript:;">资讯</a></li>
					<li><a href="<%=basePath%>web/trend/price_trend.jsp">价格走势</a></li>
				</ul>
			</div>
		</div>
		
		<div class="wrapper">
			
			<div class="news">
				
			<div class="left">
				
				<div class="layui-carousel" id="news_banner">
				  <div carousel-item id="div1">
				    <%--<div><a href="news_content.jsp"><img src="<%=basePath%>web/img/news_banner.jpg"/><p>中国乳制品工业协会第24次年会在西安隆重召开</p></a></div>--%>
				  </div>
				</div>
				
				 <div class="news_list">
					<ul>
						
						<%--<li>
						<a href="">
						<img src="<%=basePath%>web/img/news_list_img.jpg"/>
						<p class="title">中国乳制品工业协会第24次年会在西安隆重召开</p>
						<p class="describe">携手创新开启新时代，开放合作共谱新华章。8月25日，由陕西省人民政府、西安市人民政府支持，中国乳制品工业协会与国际乳品联合会(IDF)中国国家委员会主办，西安银桥乳业集团与西安百跃羊乳集团协办的中国乳制品工业协会第二十四次年会在西安曲江国际会议中心召开，中国乳业领军企业齐聚西安，共谋乳业发展良策，共商乳业振兴大计。</p>
						<p class="time">2018-08-26</p>
						</a>
						</li>--%>
						
					</ul>
					<p class="more"><a id="a1" class="layui-btn layui-btn-primary layui-btn-radius" href="javascript:;">加载更多</a></p>
				</div>
				
			</div>
			<div class="right">
				<div class="industry" id="industry1">
					<h2>行业动态</h2>
					<img src="<%=basePath%>web/img/industry.jpg"/>
					<ul>
						<%--<li><a href="">中国乳制品工业协会第24次年会在西安隆重召开</a></li>--%>
					</ul>
					<p><a id="a2" class="layui-btn layui-btn-radius layui-btn-primary layui-btn-sm" href="javascript:;">加载更多</a></p>
				</div>
				<div class="industry" id="industry2">
					<h2>价格行情</h2>
					<img src="<%=basePath%>web/img/quotation.jpg"/>
					<ul>
						<%--<li><a href="">中国乳制品工业协会第24次年会在西安隆重召开</a></li>--%>
					</ul>
					<p><a id="a3" class="layui-btn layui-btn-radius layui-btn-primary layui-btn-sm" href="javascript:;">加载更多</a></p>
				</div>
			</div>
			<div class="clear"></div>
			</div>
			
		</div>

		<%@include file ="../head/down.jsp" %>
	</body>

</html>
