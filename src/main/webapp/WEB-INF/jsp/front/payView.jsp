
<%  String contextPath = request.getContextPath(); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>

<html>
<head>
   <title>包含有openId的payView页面</title>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
   <meta name="apple-mobile-web-app-capable" content="yes">
   <meta name="apple-mobile-web-app-status-bar-style" content="black">
   <meta name="format-detection" content="telephone=no" />
   <link rel="stylesheet" href="<%=contextPath%>/resources/css/payView.css">
   <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
   <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue-resource.min.js"></script>

</head>
<body>
<%--4种方式获取ModelAndView 传输来的数据--%>
<%--<div><%=request.getAttribute("orderId")%></div>--%>
<%--<div>${requestScope.openId}</div>--%>
<%--<div><c:out value="${orderId}" /></div>--%>
<div id="payView">
   <div style="display: block">
      <div id="orderId">${orderId}</div>
      <div id="openId">${openId}</div>
   </div>
   <div>

      <div class="backupMoney">
         <div>支持金额</div>
         <input  class="moneyInput" />
      </div>
      <div id="resdata" style="height: 100px">改变我</div>
      <div class="clickPay">
         <div>合计：0.00元</div>
         <div class="doClick" v-on:click="getPrepayiD">点击支付</div>
      </div>
   </div>
</div>
<script type="text/javascript" src="<%=contextPath%>/resources/javaScript/payView1.js"></script>
</body>
</html>















