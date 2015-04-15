<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>statics/styles/common.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>statics/styles/login.css">
	<script type="text/javascript" src="<%=basePath%>statics/javascript/jquery/jquery-1.11.2.js"></script>
	<script type="text/javascript" src="<%=basePath%>statics/javascript/common.js"></script>
	<script type="text/javascript" src="<%=basePath%>statics/javascript/login.js"></script>
    <title>登录</title>
</head>
<body>

	<div id="headerNav"></div>
	
	<!-- 登录部分 -->
	<div id="login">
		<div class="fast-login">
			<h3>
				合作账户一键登录
			</h3>
			<div>
				<ul>
					<li><span class="qq_bg">&nbsp;</span><a href="javascript:;">QQ登录</a></li>
					<li><span class="wx_bg">&nbsp;</span><a href="javascript:;">微信登录</a></li>
					<li><span class="xl_bg">&nbsp;</span><a href="javascript:;">新浪登录</a></li>
				</ul>
			</div>
		</div>
		<div class="author-login">
			<h3>
				帐号注册
			</h3>
			<form id="loginForm" action="" method="post">
				<div class="login-error">
					<span id="loginError">${error}</span>
				</div>
				<div class="login-input">
					<span class="username_icon"></span>
					<label for="username">用户名</label>
					<input id="username" name="username" type="text">
				</div>
				<div class="login-input">
					<span class="password_icon"></span>
					<label for="password" style="position: absolute;">密码，6-20位字符，建议有字母、数字和符号(.,_)组成</label>
					<input id="password" name="password" type="password">
				</div>
				<div class="login-rember">
					<input type="checkbox" id="rememberMe" name="rememberMe" checked="checked">
					<span>下次自动登录</span>
					<a>忘记密码？</a>
				</div>
				<div>
					<input id="login_btn" type="submit" value="登录">
				</div>
			</form>
		</div>
	</div>
	
	
	<!-- 底部 -->
	<div id="footer">
		<div id="footer_content"><span>Copyright © 2014-2015</span></div>
	</div>

</body>
</html>