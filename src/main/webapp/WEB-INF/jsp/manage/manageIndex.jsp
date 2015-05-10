<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=basePath%>statics/styles/common.css">
<script type="text/javascript" src="<%=basePath%>statics/javascript/jquery/jquery-1.11.2.js"></script>
	<script type="text/javascript" src="<%=basePath%>statics/javascript/common.js"></script>
<title>后台管理</title>
</head>
<body>

	<div id="headerNav"></div>
	
	
	<!-- 底部 -->
	<div id="footer">
		<div id="footer_content"><span>Copyright © 2014-2015</span></div>
	</div>

</body>
</html>