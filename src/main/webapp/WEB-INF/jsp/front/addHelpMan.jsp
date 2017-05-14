
<%--<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
%>
<html>
<head>

    <title>发布项目</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no" />
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/publishHelpInfo.css">
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue-resource.min.js"></script>

</head>
<body id="root" style="margin: 0px">
<!--发布求助信息-->
<form   action="wapmultiUpload.do"  method="post" enctype="multipart/form-data" class="form-horizontal" role="form" target="hidden_frame">
    <div id="publishInfo" >
        <!--以下要用js从本地获取具体值后，然后改变默认值-->
        <input name="token" type="text" style="display: none" value="e10adc3949ba59abbe56e057f20f883e1">
        <input name="useruuid" type="text" style="display: none" value="94111BD33D3F474590C535C0BE24905B">
        <input name="userName" type="text" style="display: none" value="张三">
        <input name="selfintroduce" type="text" style="display: none" value="我是一个乐观开朗的人">
        <input name="photoUrl" type="text" style="display: none" value="http://7xihgc.com1.z0.glb.clouddn.com/14.jpg">


        <div class="ZhengMing">
            <div class="proveHeader">证明信息</div>
            <div class="checkTeam">
                <div class="checkTeamTxt">所在城市</div>
                <input class="checkTeamInput"   name="cityName" type="text" value="" placeholder="重要，例如临沂市"/>
            </div>
            <div class="checkTeam">
                <div class="checkTeamTxt">受助人姓名</div>
                <input class="checkTeamInput"   name="shouZhurenName" type="text" value="" placeholder="身份证上姓名"/>
            </div>
            <div class="checkTeam">
                <div class="checkTeamTxt">受助人ID</div>
                <input class="checkTeamInput" name="shouZhureniDentityNo"  type="text" value="" placeholder="填写身份证号"/>
            </div>
            <div class="checkTeam">
                <div class="checkTeamTxt">收款人姓名</div>
                <input class="checkTeamInput"  name="acceptMoneyName"  type="text" value="" placeholder="填写收款人姓名"/>
            </div>
            <div class="checkTeam">
                <div class="checkTeamTxt">收款人电话</div>
                <input class="checkTeamInput"   name="acceptMoneyPhone"  type="text" value="" placeholder="填写手机号"/>
            </div>

            <div class="ShiFo">
                <div class="ShiFoTxt">是否有身份证明</div>
                <input class="ShiFoInput"   name="prove"  type="checkbox" value="11" />
            </div>
            <div class="ShiFo">
                <div class="ShiFoTxt">是否有居委会证明</div>
                <input class="ShiFoInput"  name="prove"  type="checkbox" value="22" />
            </div>
            <div class="ShiFo">
                <div class="ShiFoTxt">是否有医院证明</div>
                <input class="ShiFoInput"   name="prove"  type="checkbox" value="33" />
            </div>
            <div class="ShiFo">
                <div class="ShiFoTxt">是否有贫困证明</div>
                <input class="ShiFoInput" name="prove" type="checkbox" value="44" />
            </div>
            <div class="ShiFo">
                <div class="ShiFoTxt">是否有收款人关系证明</div>
                <input class="ShiFoInput"  name="prove"  type="checkbox" value="55" />
            </div>

        </div>
        <%--以下是承诺--%>
        <div class="proveHeader">受助人承诺</div>
        <div style="padding-left: 10px">承诺类型</div>
        <div class="promiseBody" style="display:flex;flex-direction: row;align-items: center;justify-content: space-around;">
            <div class="ChengNuoItem">
                <div>A</div>
                <input  type="checkbox"  name="chengnuoType" value="1"   />
            </div>
            <div class="ChengNuoItem">
                <div>B</div>
                <input  type="checkbox"  name="chengnuoType" value="2" />
            </div>
            <div class="ChengNuoItem">
                <div>C</div>
                <input  type="checkbox"  name="chengnuoType" value="3" />
            </div>
        </div>
        <div class="PromiseTxT">
            <div class="checkTeamTxt">我的承诺</div>
            <textarea class="promiseTextarea"  name="chengnuoContent"  rows="10" cols="10" value="" placeholder="写下承诺"></textarea>
        </div>
        <%--捐款信息--%>
        <div class="proveHeader">捐款信息</div>
        <div class="donateDate">
            <div>开始日期:</div>
            <div style="margin-left: 20px">今天</div>
        </div>
        <div class="checkTeam">
            <div class="checkTeamTxt">截止日期</div>
            <input class="checkTeamInput"  name="endDate"  type="date" value="" />
        </div>
        <div class="checkTeam">
            <div class="checkTeamTxt">募捐金额</div>
            <input class="checkTeamInput"  name="targetMoney"  type="number" value="" placeholder="务必输入整数，单位元"/>
            <div>元</div>
        </div>
        <div class="checkTeam">
            <div class="checkTeamTxt">筹款标题</div>
            <input class="checkTeamInput"  name="moneyTitle"  type="text" value="" placeholder="16字以内"/>
        </div>
        <div class="PromiseTxT">
            <div class="checkTeamTxt">描述详情</div>
            <textarea class="promiseTextarea" name="content"  rows="30" cols="10" value="" placeholder="800字以内" style="font-size: 15px"></textarea>
        </div>
    </div>
    <div>
        <div class="proveHeader">上传相应证明图片</div>
        <div class="selectImg">
            <span>1.</span> <input  name="fileone"  type="file" value="浏览" filename=" " />
        </div>
        <div class="selectImg">
            <span>2.</span> <input  name="filetwo"  type="file" value="浏览" />
        </div>
        <div  class="selectImg">
            <span>3.</span> <input  name="filethree"  type="file" value="浏览" />
        </div>
        <div class="selectImg">
            <span>4.</span> <input  name="filefour"  type="file" value="浏览" />
        </div>
        <div  class="selectImg">
            <span>5.</span> <input   name="filefive"  type="file"  value="浏览" />
        </div>
        <div  class="selectImg">
            <span>6.</span> <input   name="filesix"  type="file" value="浏览" />
        </div>
        <div  class="selectImg" style="margin-bottom: 0px">
            <span>7.</span> <input   name="fileseven"  type="file" value="浏览" />
        </div>
        <div style="display: flex;justify-content: center;align-content: center;height: 40px">
            <button type="submit" style="width: 96%;height: 40px;
            line-height: 40px;border: none;font-size: 16px; -webkit-appearance: none; background-color: #2FAC4C;color: #FFFFFF">
                提交
            </button>
        </div>
    </div>
</form>
<div style="height: 50px"></div>
<script type="text/javascript" src="<%=contextPath%>/resources/javaScript/publishHelpInfo.js"></script>
</body>
</html>



























