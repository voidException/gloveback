
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no" />
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue-resource.min.js"></script>
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/navigation.css">
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/publishItemPC.css">
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/footer.css">
    <title>发起项目</title>
</head>
<body style="color: #6f7a7e">
<!--这里是头部部分-->
<div class="header">
    <div class="headerLeft">
        <div class="logo" >
            <img src="<%=contextPath%>/resources/image/logo.png" style="width: 40px;height: 40px;border-radius: 20px">
            <div style="color:#00BB3B;">首页</div>
        </div>
        <div class="logo  appHelp" >
            <span>App下载</span>
        </div>
        <div class="logo appHelp">
            <span>帮助中心</span>
        </div>
    </div>
</div>
<!--发布项目-->
<div style="height: 40px;text-align: center;line-height: 40px;font-size: 18px;font-weight: 700;font-family: STHeiTi">
    发布项目
</div>
<div class="bodyOuter">
    <!--项目基本信息-->
    <div class="basicInfo">
        <div class="basiInfoTop">项目基本信息</div>
        <div class="basicInfoBody">
            <div class="basicInfoBodyItem">
                <div class="basicInfoBodyItemDesp">筹款金额(元)</div>
                <input  placeholder="请输入筹钱的数额" style="height: 24px;width: 200px"/>
            </div>
            <div class="basicInfoBodyItem">
                <div class="basicInfoBodyItemDesp">截止日期</div>
                <input  type="datetime-local" style="height: 24px;width: 100px"/>
            </div>
            <div class="basicInfoBodyItem">
                <div class="basicInfoBodyItemDesp">项目标题</div>
                <input  placeholder="请输入项目的标题" style="height: 24px;width: 200px"/>
            </div>
            <div class="basicInfoBodyItem">
                <div class="basicInfoBodyItemDesp">售后电话</div>
                <input  placeholder="请输入售后电话等联系方式" style="height: 24px;width: 200px"/>
            </div>
        </div>
    </div>
    <!--支持与回报-->
    <div class="basicInfo">
        <div class="basiInfoTop">支持与回报</div>
        <div class="basicInfoBody">
            <!--支持1-->
            <div>
                <div class="basicInfoBodyItem">
                    <div class="basicInfoBodyItemDesp">支持1（元）</div>
                    <input  class="backupInput"/>
                </div>
                <div class="basicInfoBodyItem">
                    <div class="basicInfoBodyItemDesp">回报1</div>
                    <textarea  class="feedBackTextArea"></textarea>
                </div>
            </div>
            <!--支持2-->
            <div>
                <div class="basicInfoBodyItem">
                    <div class="basicInfoBodyItemDesp">支持2（元）</div>
                    <input  class="backupInput"/>
                </div>
                <div class="basicInfoBodyItem">
                    <div class="basicInfoBodyItemDesp">回报2</div>
                    <textarea  class="feedBackTextArea"></textarea>
                </div>
            </div>
            <!--支持3-->
            <div>
                <div class="basicInfoBodyItem">
                    <div class="basicInfoBodyItemDesp">支持3（元）</div>
                    <input  class="backupInput"/>
                </div>
                <div class="basicInfoBodyItem">
                    <div class="basicInfoBodyItemDesp">回报3</div>
                    <textarea  class="feedBackTextArea"></textarea>
                </div>
            </div>
            <!--支持4-->
            <div>
                <div class="basicInfoBodyItem">
                    <div class="basicInfoBodyItemDesp">支持4（元）</div>
                    <input class="backupInput" />
                </div>
                <div class="basicInfoBodyItem">
                    <div class="basicInfoBodyItemDesp">回报4</div>
                    <textarea  class="feedBackTextArea"></textarea>
                </div>
            </div>
            <!--支持5-->
            <div>
                <div class="basicInfoBodyItem">
                    <div class="basicInfoBodyItemDesp">支持5（元）</div>
                    <input  class="backupInput"/>
                </div>
                <div class="basicInfoBodyItem">
                    <div class="basicInfoBodyItemDesp">回报5</div>
                    <textarea  class="feedBackTextArea"></textarea>
                </div>
            </div>
        </div>
    </div>
    <!--文字描述与图片实物信息-->

    <div class="basicInfo">
        <div class="basiInfoTop">上传图片和描述</div>
        <form>
            <div class="uploadWrapper">
                <div>描述1</div>
                <textarea class="uploadTextArea"></textarea><br/>
                <input type="file">
            </div>
            <div class="uploadWrapper">
                <div>描述2</div>
                <textarea class="uploadTextArea"></textarea><br/>
                <input type="file">
            </div>
            <div class="uploadWrapper">
                <div>描述3</div>
                <textarea class="uploadTextArea"></textarea><br/>
                <input type="file">
            </div>
            <div class="uploadWrapper">
                <div>描述4</div>
                <textarea class="uploadTextArea"></textarea><br/>
                <input type="file">
            </div>
            <div class="uploadWrapper">
                <div>描述5</div>
                <textarea class="uploadTextArea"></textarea><br/>
                <input type="file">
            </div>
            <div class="uploadWrapper">
                <div>描述6</div>
                <textarea class="uploadTextArea"></textarea><br/>
                <input type="file">
            </div>
            <div class="uploadWrapper">
                <div>描述7</div>
                <textarea class="uploadTextArea"></textarea><br/>
                <input type="file">
            </div>
            <div class="uploadWrapper">
                <div>描述8</div>
                <textarea class="uploadTextArea"></textarea><br/>
                <input type="file">
            </div>
        </form>
    </div>
    <!--发布按钮-->
    <div class="publish">
        <div class="doPublish">发布项目</div>
    </div>
</div>

<footer class="footer">
    <div>
        <img src="<%=contextPath%>/resources/image/logo.png" style="width: 100px;width: 100px;border-radius: 50px">
    </div>
    <div class="footerItem">
        <div>关于我们</div>
        <div>帮助中心</div>
        <div>加入我们</div>
    </div>
    <div class="footerItem">
        <div>联系方式</div>
        <div>Phone:010-888888</div>
        <div>Mail:noexception@126.com</div>
    </div>
    <div>
        <img src="<%=contextPath%>/resources/image/erweima.png" style="height: 100px;width: 100px">
    </div>
</footer>
<div class="bottom">
    © 2017 给爱众筹 - 北京给爱科技有限公司  京公网安备 11010102002425号  京ICP证160503号   京ICP备14052685号-1
</div>
</body>
</html>
























