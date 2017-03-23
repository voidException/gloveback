
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
//  String path = request.getContextPath();
//  String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
//  System.out.print(basePath);
	String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>

	<title>用户登录页</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="format-detection" content="telephone=no" />
	<link rel="stylesheet" href="<%=contextPath%>/resources/css/index.css">
	<link rel="stylesheet" href="<%=contextPath%>/resources/css/nickname.css">
	<link rel="stylesheet" href="<%=contextPath%>/resources/css/zhengming.css">
	<script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue-resource.min.js"></script>
</head>
<body id="root" style="background-color: #019875">
	<div id="app">
		<div>
			<input  placeholder="邮箱"/>
			<input  placeholder="密码"/>
		</div>
     </div>

	<script>

	</script>
</body>
</html>



























