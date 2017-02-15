
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
  System.out.print(basePath);
%>

<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="https://unpkg.com/vue/dist/vue.js"></script>
</head>
<body id="root" style="background-color: #019875">
	<div id="app">
      {{ message }}
     </div>
	<script>
	  var app = new Vue({
		  el: '#app',
		  data: {
		    message: 'Hello Vue!',number: 1
		  },
          beforeCreate: function () {
              console.log('beforeCreate 钩子执行...');
              console.log(this.number)
          },
          cteated: function () {
              console.log('cteated 钩子执行...');
              console.log(this.number)
          },
          beforeMount: function () {
              console.log('beforeMount 钩子执行...');
              console.log(this.number)
          },
          mounted: function () {
              console.log('mounted 钩子执行...');
              console.log(this.number)
          },
          beforeUpdate: function () {
              console.log('beforeUpdate 钩子执行...');
              console.log(this.number)
          },
          updated: function () {
              console.log('updated 钩子执行...');
              console.log(this.number)
          },
          beforeDestroy: function () {
              console.log('beforeDestroy 钩子执行...');
              console.log(this.number)
          },
          destroyed: function () {
              console.log('destroyed 钩子执行...');
              console.log(this.number)
          },
		})
	</script>
</body>
</html>



























