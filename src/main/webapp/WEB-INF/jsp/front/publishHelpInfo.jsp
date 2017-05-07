
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<% String contextPath = request.getContextPath(); %>

<!DOCTYPE html>
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
<form   action="jspmultiUpload"  method="post" enctype="multipart/form-data" class="form-horizontal" role="form" target="hidden_frame">
    <div id="publishInfo" >
        <div class="ZhengMing">
            <div class="proveHeader">证明信息</div>

            <div class="checkTeam">
                <div class="checkTeamTxt">所在城市</div>
                <input class="checkTeamInput" type="text" value="" placeholder="重要，例如临沂市"/>
            </div>
            <div class="checkTeam">
                <div class="checkTeamTxt">受助人姓名</div>
                <input class="checkTeamInput" type="text" value="" placeholder="身份证上姓名"/>
            </div>
            <div class="checkTeam">
                <div class="checkTeamTxt">受助人ID</div>
                <input class="checkTeamInput" type="text" value="" placeholder="填写身份证号"/>
            </div>
            <div class="checkTeam">
                <div class="checkTeamTxt">收款人姓名</div>
                <input class="checkTeamInput" type="text" value="" placeholder="填写收款人姓名"/>
            </div>
            <div class="checkTeam">
                <div class="checkTeamTxt">收款人电话</div>
                <input class="checkTeamInput" type="text" value="" placeholder="填写手机号"/>
            </div>

            <div class="ShiFo">
                <div class="ShiFoTxt">是否有身份证明</div>
                <input class="ShiFoInput" type="checkbox" value="" />
            </div>
            <div class="ShiFo">
                <div class="ShiFoTxt">是否有居委会证明</div>
                <input class="ShiFoInput" type="checkbox" value="" />
            </div>
            <div class="ShiFo">
                <div class="ShiFoTxt">是否有医院证明</div>
                <input class="ShiFoInput" type="checkbox" value="" />
            </div>
            <div class="ShiFo">
                <div class="ShiFoTxt">是否有贫困证明</div>
                <input class="ShiFoInput" type="checkbox" value="" />
            </div>
            <div class="ShiFo">
                <div class="ShiFoTxt">是否有收款人关系证明</div>
                <input class="ShiFoInput" type="checkbox" value="" />
            </div>
        </div>
        <%--以下是承诺--%>
        <div class="proveHeader">受助人承诺</div>
        <div style="padding-left: 10px">承诺类型</div>
        <div class="promiseBody" style="display:flex;flex-direction: row;align-items: center;justify-content: space-around;">
            <div class="ChengNuoItem">
                <div>A</div>
                <input  type="checkbox" value=""   filename="imgone"/>
            </div>
            <div class="ChengNuoItem">
                <div>B</div>
                <input  type="checkbox" value="" />
            </div>
            <div class="ChengNuoItem">
                <div>C</div>
                <input  type="checkbox" value="" />
            </div>
        </div>
        <div class="PromiseTxT">
            <div class="checkTeamTxt">我的承诺</div>
            <textarea class="promiseTextarea" rows="10" cols="10" value="" placeholder="写下承诺"></textarea>
        </div>
        <%--捐款信息--%>
        <div class="proveHeader">捐款信息</div>
        <div class="donateDate">
            <div>开始日期:</div>
            <div style="margin-left: 20px">今天</div>
        </div>
        <div class="checkTeam">
            <div class="checkTeamTxt">截止日期</div>
            <input class="checkTeamInput" type="datetime-local" value="" />
        </div>
        <div class="checkTeam">
            <div class="checkTeamTxt">募捐金额</div>
            <input class="checkTeamInput" type="number" value="" placeholder="务必输入整数，单位元"/>
            <div>元</div>
        </div>
        <div class="checkTeam">
            <div class="checkTeamTxt">筹款标题</div>
            <input class="checkTeamInput" type="text" value="" placeholder="16字以内"/>
        </div>
        <div class="PromiseTxT">
            <div class="checkTeamTxt">描述详情</div>
            <textarea class="promiseTextarea" rows="30" cols="10" value="" placeholder="800字以内" style="font-size: 15px"></textarea>
        </div>
    </div>
    <div>
        <div class="proveHeader">上传相应证明图片</div>
        <div class="selectImg">
            <span> 1.</span> <input type="file" value="浏览" filename=" " />
        </div>
        <div class="selectImg">
            <span>2.</span> <input type="file" value="浏览" />
        </div>
        <div  class="selectImg">
            <span>3.</span> <input type="file" value="浏览" />
        </div>
        <div class="selectImg">
            <span>4.</span> <input type="file" value="浏览" />
        </div>
        <div  class="selectImg">
            <span>5.</span> <input type="file"  value="浏览" />
        </div>
        <div  class="selectImg">
            <span>6.</span> <input type="file" value="浏览" />
        </div>
        <div  class="selectImg" style="margin-bottom: 0px">
            <span>7.</span> <input type="file" value="浏览" />
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



























