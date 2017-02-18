<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
    String contextPath = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%--<script type="text/javascript" src="<%=contextPath%>/resources/jquery/jquery-1.11.1.js"></script>--%>
	<%--<script src="https://unpkg.com/babel-standalone@6/babel.min.js"></script>--%>
	<%--<script src="https://unpkg.com/react@15/dist/react.min.js"></script>--%>
	<%--<script src="https://unpkg.com/react-dom@15/dist/react-dom.min.js"></script>--%>
	<%--<script src="https://unpkg.com/vue/dist/vue.js"></script>--%>

    <title>首页用来测试</title>
</head>
<body id="root">
	<a href="http://localhost:8080/glove/pages/aboutus">关于我们</a><br/>
	<a href="http://localhost:8080/glove/pages/faq">常见问题</a><br/>
	<a href="http://localhost:8080/glove/pages/feedback">意见反馈</a><br/>
	<a href="http://localhost:8080/glove/pages/helpApp">资助我们</a><br/>
	<a href="http://localhost:8080/glove/pages/helpAixinshe">赞助爱心社</a><br/>
	<a href="http://localhost:8080/glove/pages/shareTotimeline/1000?tweetiD=28&useriD=2&cashiD=10">
		分享到朋友圈
	</a><br/>

	<%--<script type="text/babel">--%>

       <%--class Bottom extends React.Component{--%>
            <%--constructor(props){--%>
                <%--super(props);--%>
            <%--}--%>

            <%--render() {--%>
                <%--return (--%>
						<%--<footer>--%>
							<%--<div>这里是底部</div>--%>
						<%--</footer>--%>
                <%--);--%>
            <%--}--%>
        <%--}--%>
        <%--ReactDOM.render(--%>
            <%--<div>--%>
				<%--<a  href="http://localhost:8080/glove/pages/aboutus">关于我们</a><br/>--%>
				<%--<a  href="http://localhost:8080/glove/pages/faq">常见问题</a><br/>--%>
				<%--<a  href="http://localhost:8080/glove/pages/feedback">意见反馈</a><br/>--%>
				<%--<a href="http://localhost:8080/glove/pages/helpApp">资助我们</a><br/>--%>
				<%--<a href="http://localhost:8080/glove/pages/helpAixinshe">赞助爱心社</a><br/>--%>
				<%--<a href="http://localhost:8080/glove/pages/shareTotimeline/1000?tweetiD=28&useriD=2&cashiD=10">--%>
					<%--分享到朋友圈--%>
				<%--</a><br/>--%>
				<%--<h1>Hello, world!</h1>--%>
			   <%--<Bottom/>--%>
			<%--</div>,--%>
            <%--document.getElementById('root')--%>
        <%--);--%>
	<%--</script>--%>
</body> 
</html>