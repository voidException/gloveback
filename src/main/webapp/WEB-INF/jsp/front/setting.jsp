<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<html>
<head>
    <title>设置</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no" />
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/setting.css">
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/index.css">
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue-resource.min.js"></script>
</head>
<body style="background-color: #E1E5E7">
    <form class="photo"   method="post" action="jspupload.do"  enctype="multipart/form-data" class="form-horizontal" role="form">
        <input  id="token"  name="token" type="text" style="display: none" value="e10adc3949ba59abbe56e057f20f883e1">
        <div  class="upload">
            <div style="margin-left: 10px">
                <div style="margin-bottom: 3px">上传头像</div>
                <input type="file"  name="photo" value="浏览"/>
            </div>
        </div>
        <div style="width: 70px">
            <button  class="button" type="submit">提交</button>
        </div>
    </form>
    <div class="publishWrapper">
        <img src="<%=contextPath%>/resources/image/publish.png" style="width: 24px;height: 24px;border-radius: 12px"/>
        <div>完善资料</div>
    </div>
    <div class="publishWrapper">
        <img src="<%=contextPath%>/resources/image/publish.png" style="width: 24px;height: 24px;border-radius: 12px"/>
        <div>修改密码</div>
    </div>
    <div class="loginout">
        <div class="dologinOut">退出</div>
    </div>
</body>
<script type="text/javascript" src="<%=contextPath%>/resources/javaScript/setting.js"></script>

</html>
