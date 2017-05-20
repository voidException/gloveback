
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<html>
<head>

    <title>首页</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no" />
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/mobileMainPage.css">
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/index.css">
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue-resource.min.js"></script>
</head>
<body style="background-color: #E1E5E7">

<div id="mobileMainDiv" >
    <div>
        <%--2选择1--%>
        <div class="userPart" style="display: none">
            <div class="logoWrapper">
                <img src="<%=contextPath%>/resources/image/xiong.png"  style="width: 60px;height: 60px;border-radius: 30px"/>
            </div>
            <div class="loginReg">
                <a class="login"  href="#" >注册</a>
                <a class="login"  href="#">登录</a>
            </div>
        </div>

        <div style="margin: 10px;background-color: #FFFFFF;border-radius: 4px">
            <div class="userPhoto">
                <img src="<%=contextPath%>/resources/image/xiong.png"  style="width: 50px;height: 50px;border-radius: 25px"/>
                <div style="font-weight: bold;font-family: STHeiti Light ;margin-left: 5px">昵称是什么</div>
            </div>
            <div class="helpcommon">
                <div style="margin-left: 5px">我帮助</div>
                <div  style="font-weight: bold;font-family: STHeiti Light ;">200人</div>
                <div  style="font-weight: bold;font-family: STHeiti Light ;">400元</div>
            </div>
            <div class="helpcommon">
                <div style="margin-left: 5px">帮助我</div>
                <div style="font-weight: bold;font-family: STHeiti Light ;">800人</div>
                <div style="font-weight: bold;font-family: STHeiti Light ;">500元</div>
            </div>
            <div class="IpublishWrapper">
                <img src="<%=contextPath%>/resources/image/iPublish.png"  style="width: 24px;height: 24px;border-radius: 12px"/>
                <div>我的求助</div>
            </div>

        </div>
    </div>

    <div class="publishWrapper">
        <img src="<%=contextPath%>/resources/image/publish.png"  style="width: 24px;height: 24px;border-radius: 12px"/>
        <div>发布项目</div>
    </div>
    <div class="zizhu">
        <div class="donate">赞助我们</div>
        <div class="donatelove">资助爱心社</div>
    </div>
    <div class="aboutus">
        <img src="<%=contextPath%>/resources/image/publish.png"  style="width: 24px;height: 24px;border-radius: 12px"/>
        <div>关于我们</div>
    </div>
    <div class="publishWrapper">
        <img src="<%=contextPath%>/resources/image/publish.png"  style="width: 24px;height: 24px;border-radius: 12px"/>
        <div>常见问题</div>
    </div>
</div>

<script type="text/javascript" src="<%=contextPath%>/resources/javaScript/mobileMainPage.js"></script>
</body>
</html>

















