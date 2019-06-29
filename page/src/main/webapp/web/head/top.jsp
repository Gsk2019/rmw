<%@ page import="java.net.URLDecoder" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="<%=basePath%>web/head/top.js" type="text/javascript" charset="utf-8"></script>
<%--<%@ taglib uri="/eltag" prefix="el" %>--%>
<div class="top_bg">
	<div class="top">
		<div class="left">
			<ul>
				<c:if test="${cookie['token'].value eq '' || cookie['token'].value eq null}">
					<li>您好，欢迎来到乳买网！</li>
					<li><a href="<%=basePath%>web/login.jsp">登录</a></li>
					<li><a href="<%=basePath%>web/user/register.jsp">注册</a></li>
				</c:if>
				<c:if test="${cookie['token'].value ne '' && cookie['token'].value ne null}">
					<li id="uanme1">
							<%--<%!String username = "not user"; %>
                            <%${cookie['uname'].value}
                                Cookie[] cookies = request.getCookies();
                                for(int i = 0 ; i < cookies.length ; i++){
                                    if(cookies[i].getName().equals("uname")){
                                        username = cookies[i].getValue();break;
                                    }
                                }
                            %>
                            <%=URLDecoder.decode(username) %>--%></li>
					<li><a href="<%=basePath%>web/userinfo/user_center.jsp">个人中心</a></li>
				</c:if>
			</ul>
		</div>
		<div class="right">
			<ul>
				<li><a href="<%=basePath%>web/help_center.jsp">帮助中心</a></li>
				<li><a href="<%=basePath%>web/help_center.jsp?page=9">关于我们</a></li>
				<c:if test="${cookie['token'].value ne '' && cookie['token'].value ne null}">
					<li><a href="javascript:exitUser();">退出登录</a></li>
				</c:if>
				<li>客服热线：<span>13821101272</span></li>
			</ul>
		</div>
		<div class="clear"></div>
	</div>
</div>