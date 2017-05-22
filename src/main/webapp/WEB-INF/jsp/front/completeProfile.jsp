<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<html>
<head>
    <title>完善资料</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no" />
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/completeProfile.css">
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue-resource.min.js"></script>
</head>
<body style="background-color: #E1E5E7">

<div id="completeProfile"  class="ZhengMing">

    <div class="checkTeam">
        <div class="checkTeamTxt">真实姓名</div>
        <input  id="realName"   class="checkTeamInput"  name="realName"  type="text" value="" placeholder="用户真实姓名"/>
    </div>
    <div class="checkTeam">
        <div class="checkTeamTxt">身份证号</div>
        <input  id="identity"  class="checkTeamInput"   name="identity"  type="text" value="" placeholder="填写身份证号"/>
    </div>
    <div class="checkTeam">
        <div class="checkTeamTxt">家庭住址</div>
        <input  id="address"  class="checkTeamInput"   name="address"  type="text" value="" placeholder="家庭住址"/>
    </div>
    <div class="checkTeam">
        <div class="checkTeamTxt">毕业大学</div>
        <input id="college"    class="checkTeamInput"    name="college" type="text" value="" placeholder="填写毕业院校"/>
    </div>
    <div class="checkTeam">
        <div class="checkTeamTxt">手机号码</div>
        <input id="mobilePhone"    class="checkTeamInput"    name="mobilePhone" type="text" value="" placeholder="手机号码"/>
    </div>
    <div>
        <div class="checkTeamTxt">自我介绍</div>
        <textarea id="selfIntroduce"   name="selfIntroduce"  rows="10" cols="10" value="" placeholder="100以内介绍自己"></textarea>
    </div>

    <div class="commitWrapper">
        <div class="commit" v-on:click="fullFillProfile">提交</div>
    </div>
</div>
</body>
<script type="text/javascript" src="<%=contextPath%>/resources/javaScript/completeProfile.js"></script>

</html>