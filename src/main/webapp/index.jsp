<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
    String contextPath = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue-resource.min.js"></script>
	<%--<script src="https://unpkg.com/vue-router/dist/vue-router.js"></script>--%>
	<title>首页用来测试</title>
</head>
<body id="root">
	<a href="http://geilove.org/glove/path/pages/aboutus.do?code=021zmCGf2HGaCC0BpKFf26SKGf2zmCGo&state=20170415">关于我们</a><br/>
	<a href="http://geilove.org/glove/path/pages/faq.do">常见问题</a><br/>

	<a href="http://localhost:8080/glove/pages/feedback.do">意见反馈</a><br/>

	<a href="http://localhost:8080/glove/path/pages/helpApp">资助我们</a><br/>
	<a href="http://localhost:8080/glove/path/pages/helpAixinshe">赞助爱心社</a><br/>
	<a href="http://localhost:8080/glove/path/pages/shareTotimeline/1000?tweetiD=28&useriD=2&cashiD=10">分享到朋友圈</a><br/>
	<a href="http://localhost:8080/glove/path/pages/loginRegister">登录PC</a><br/>
	<a href="http://localhost:8080/glove/path/pages/login.do">公众号登录</a><br/>
	<a href="http://localhost:8080/glove/path/pages/register.do">公众号注册</a><br/>

	<a href="http://localhost:8080/glove/publishInfo/upload/addman.do">发布求助信息</a><br/>

	<a href="http://localhost:8080/glove/backPages/checkProfileList">后台管理系统</a><br/>
	<a href="http://geilove.org/glove/wechatpay/toPay/20170415/code.do">点我去支付</a><br/>
	<a href="http://localhost:8080/glove/wechatpay/toPay/20170415/code.do">点我去支付localhost</a><br/>
	<a href="http://localhost:8080/glove/path/pages/userSelfHelp.do">我的求助</a><br/>
	<a href="http://localhost:8080/glove/path/pages/helpTweetList.do">求助列表</a><br/>
	<a href="http://localhost:8080/glove/path/pages/mobileMainPage.do">公众号首页</a><br/>
	<a href="http://localhost:8080/glove/photo/setting.do">设置</a><br/>
	<a href="http://localhost:8080/glove/profile/goAddProfile.do">完善资料</a><br/>

	<a href="http://localhost:8080/glove/user/resetPassword.do">修改密码</a><br/>

	<div id="app">
		<h1>Hello App!</h1>
		<%--<p>--%>
			<%--<!-- 使用 router-link 组件来导航. -->--%>
			<%--<!-- 通过传入 `to` 属性指定链接. -->--%>
			<%--<!-- <router-link> 默认会被渲染成一个 `<a>` 标签 -->--%>
			<%--<router-link to="/foo">Go to Foo</router-link>--%>
			<%--<router-link to="/bar">Go to Bar</router-link>--%>
			<%--<router-link to="/user/foo">/user/foo</router-link>--%>
			<%--<router-link to="/user/bar">/user/bar</router-link>--%>
		<%--</p>--%>
		<!-- 路由出口 -->
		<!-- 路由匹配到的组件将渲染在这里 -->
		<router-view></router-view>
	</div>
	<script type="text/javascript" src="<%=contextPath%>/resources/javaScript/vueroute.js"></script>
</body> 
</html>