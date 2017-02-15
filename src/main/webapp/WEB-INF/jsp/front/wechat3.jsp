
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
//    String path = request.getContextPath();
//    String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";

%>
<!DOCTYPE html>
<html>
<head>
    <title>分享到微信圈</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no" />

    <link rel="stylesheet" href="<%=contextPath%>/resources/css/index.css">
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/nickname.css">
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/zhengming.css">
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/jquery-1.11.1.js"></script>
    <%--<script src="https://unpkg.com/vue/dist/vue.js"></script>--%>
    <%--<script src="https://cdn.jsdelivr.net/vue.resource/1.2.0/vue-resource.min.js"></script>--%>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue-resource.min.js"></script>
</head>
<body>

<div id="app">
    <span>这个是微信圈传播界面</span>
    {{ message }}
</div>






<script type="text/javascript">

    function getQueryString(name)
    {
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if(r!=null)
            return  decodeURIComponent(r[2]);
        return null;
    }
    console.log(getQueryString("tweetiD"));
    console.log(getQueryString("useriD"));
    console.log(getQueryString("cashiD"));
    new Vue({
        el: '#app',
        data: {
            message: 'Hello Vue!',
            lp:""
        },
        mounted: function () {
            console.log('mounted 钩子执行...');

            this.$http.get('http://localhost:8080/glove/timelinetweet/info/1').then(response => {
                console.log(response.body);
                this.message=response.body.lp.cash.backupfive;
            }, err => {
                console.log(err);
            });
        },

    })

</script>
</body>
</html>