
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
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/jquery/vue-resource.min.js"></script>
</head>

<body style="font-size: 14px;overflow: scroll;flex:1; font-family:  'Microsoft YaHei' ">
<div id="uppart">
    <!--这个是头部-->
    <div class="header">
        <div class="headerChild">
            <div  style=" display:table-cell;vertical-align: middle;">
                <img style="float: left;height: 40px;width: 32px" src="<%=contextPath%>/resources/image/back.png">
                <a class="headerChilda" href="#">首页</a>
            </div>
        </div>
        <div class="headerChild">
            <div style="display:table-cell;text-align:center; vertical-align:middle;">
                <div style="display: flex;justify-content: center;align-items: center;color: #ffffff">
                    <div>互助</div>
                    <img src="<%=contextPath%>/resources/image/love.png">
                    <div>管家</div>
                </div>
            </div>
        </div>
        <div class="headerChild" style="display:table;float:left;background:#2FAC4C;height:46px;width: 34%;">
            <div style="display:table-cell;float:right;vertical-align: middle">
                <a class="headerChildReport" href="#">举报</a>
            </div>
        </div>
    </div>
    <!--头像,昵称等部分-->
    <div style="height: 50px"></div>
    <div class="photo">
        <div class="photoLeft">
            <img src="<%=contextPath%>/resources/image/25.jpg" style="float:left;width: 34px;height: 34px;border-radius: 17px;margin-top: 10px;margin-left: 10px;">
            <div style="float: left;font-size: 14px; height:34px;line-height: 34px;padding-top: 10px;padding-left: 10px;">{{nickName}}</div>
        </div>
        <div class="photoRight">
            <div style="float: right;font-size:12px;color:#AAA;height:34px;line-height: 34px;padding-top: 10px;padding-right: 10px;">
                共计<span style="font-size:12px;color:red">{{totalDays}}</span>天
            </div>
        </div>
    </div>

    <!--标题进度结束时间资金流向说明-->
    <div style="margin-left: 10px;margin-right: 10px;">
        <div class="title"  style="margin-top: 20px;margin-bottom: 20px;text-align: center;color:#555D5F;font-size: 18px;font-weight:bold">
            {{title}}
        </div>
        <div style="height: 3px;background: red; width: 100%;border-radius: 1.5px">
            <div style="height:3px;background: #2FAC4C; width: 100px"></div>
        </div>
        <div>
            <div style="margin-top: 10px;color: #AAA;float: left ">
                结束日期<span style="font-size: 13px;font-weight: bold;color:black">{{endDate}}</span>
            </div>
            <a style="color: #222;float: right;margin-top: 10px">资金流向说明>></a>
        </div>
    </div>
    <div style="clear: both;height: 10px"></div>
    <!--目标金额,已筹金额,次数-->
    <div  class="target" >
        <div class="targetMoneyWrapper">
            <div class="targetMoney"><strong>{{targetMoney}}</strong><span class="targetMoneyAttr">元</span></div>
            <div class="targetDesp"> 目标金额</div>
        </div>
        <div style="height: 24px;border-right: #AAA solid 0.5px;"></div>
        <div class="targetMoneyWrapper" >
            <div class="targetMoney"><strong>{{realMoney}}</strong><span class="targetMoneyAttr">元</span></div>
            <div class="targetDesp"> 已筹钱数</div>
        </div>
        <div style="height: 24px;border-right: #AAA solid 0.5px;"></div>
        <div class="targetMoneyWrapper">
            <div class="targetMoney"><strong>{{helpTimes}}</strong><span class="targetMoneyAttr">次</span></div>
            <div class="targetDesp"> 捐助次数</div>
        </div>
        <!--奇怪,不这用,黑线会跑到上面-->
    </div>
    <div style="height: 10px;background: #F5F6F9"><!--一段灰暗区--></div>
    <div style="color:#aaa;font-size: 15px;font-weight: 800; height: 50px; line-height: 50px;border-bottom:1px solid rgba(0,0,0,.15);padding-left: 10px">
        项目详情
    </div>
    <div style="padding-left: 10px;padding-right:10px;margin-top: 10px;overflow: visible; max-height: none;font-size: 16px;word-wrap:break-word;line-height:1.7">
        {{tweet}}
    </div>
    <div style="display:block;margin-top:-20px;height:20px;line-height: 20px;text-align: center;color: #43AC43;font-size: 15px;font-family:  'Microsoft YaHei'">
        展示全文
    </div>
    <!--这里是图片展示,css3布局-->
    <div style="display: flex;flex-direction: row;justify-content: flex-start;flex-wrap: wrap">
        <img style="height: 96px;width: 23%;padding: 2px" :src="imgOneUrl" >
        <img style="height: 96px;width: 23%;padding: 2px" :src="imgTwoUrl">
        <img style="height: 96px;width: 23%;padding: 2px" :src="imgThreeUrl">
        <img style="height: 96px;width: 23%;padding: 2px" :src="imgFourUrl">
        <img style="height: 96px;width: 23%;padding: 2px" :src="imgFiveUrl">
        <img style="height: 96px;width: 23%;padding: 2px" :src="imgSixUrl">
    </div>
    <div style="padding-left: 10px;padding-right: 10px; border-bottom:1px solid rgba(0,0,0,.15);border-top:1px solid rgba(0,0,0,.15);">
        <div class="zhengmingHeader">
            <div style="font-weight: bold;font-size: 15px;color: #666">资料证明</div>
            <div style="display: flex;flex-direction: row;justify-content: flex-end;align-items: center">
                <div style="color: #43AC43;font-size: 15px">责任声明</div>
                <img style="width: 26px;height: 26px" src="<%=contextPath%>/resources/image/more.png" />
            </div>
        </div>
        <div style="margin-top: 14px">
            <div style="font-size: 15px">认证机构</div>
            <div style="display: flex;flex-direction: row;align-items: center;justify-content: flex-start;padding-top: 6px">
                <img style="width: 12px;height: 12px" src="<%=contextPath%>/resources/image/selected.png">
                <div style="color: #2FAC4C;font-size: 13px;margin-top: 3px;margin-left: 2px">{{authority}}</div>
            </div>
        </div>
        <div style="margin-top: 14px">
            <div style="font-size: 15px">监督小组</div>
            <div style="display: flex;flex-direction: row;align-items: center;justify-content: flex-start;padding-top: 6px">
                <img style="width: 12px;height: 12px" src="<%=contextPath%>/resources/image/selected.png">
                <div style="color: #2FAC4C;font-size: 13px;margin-top: 3px;margin-left: 2px">{{monitoringGroup}}</div>
            </div>
        </div>
        <div style="margin-top: 14px;">
            <div style="font-size: 15px;">具体负责人</div>
            <div style="display: flex;flex-direction: row;align-items: center;justify-content: flex-start;padding-top: 6px">
                <img style="width: 12px;height: 12px" src="<%=contextPath%>/resources/image/selected.png">
                <div style="color: #2FAC4C;font-size: 13px;margin-top: 3px;margin-left: 2px">{{concreteStudent}}</div>
            </div>
        </div>
        <div style="margin-top: 14px;">
            <div style="font-size: 15px;">收款人</div>
            <div style="display: flex;flex-direction: row;align-items: center;justify-content: flex-start;">
                <div style="display: flex;flex-direction: row;align-items: center;justify-content: flex-start;padding-top: 6px">
                    <img style="width: 12px;height: 12px" src="<%=contextPath%>/resources/image/selected.png">
                    <div style="color: #2FAC4C;font-size: 13px;margin-top: 3px;margin-left: 2px">{{receiveMoneyMan}}</div>
                </div>
                <div style="display: flex;flex-direction: row;align-items: center;justify-content: flex-start;padding-top: 6px;margin-left: 20px">
                    <img style="width: 12px;height: 12px" src="<%=contextPath%>/resources/image/selected.png">
                    <div style="color: #2FAC4C;font-size: 13px;margin-top: 3px;margin-left: 2px">关系证明已提交</div>
                </div>
            </div>
        </div>
        <div style="margin-top: 14px;">
            <div style="font-size: 15px;">受助人</div>
            <div style="display: flex;flex-direction: row;align-items: center;justify-content: flex-start;padding-top: 6px">
                <img style="width: 12px;height: 12px" src="<%=contextPath%>/resources/image/selected.png">
                <div style="color: #2FAC4C;font-size: 13px;margin-top: 3px;margin-left: 2px">{{needHelpMan}}</div>
            </div>
            <div style="display: flex;flex-direction: row;align-items: center;justify-content: flex-start;padding-top: 6px">
                <img style="width: 12px;height: 12px" src="<%=contextPath%>/resources/image/selected.png">
                <div style="color: #2FAC4C;font-size: 13px;margin-top: 3px;margin-left: 2px">身份证明已提交</div>
            </div>
            <div style="display: flex;flex-direction: row;align-items: center;justify-content: flex-start;padding-top: 6px">
                <img style="width: 12px;height: 12px" src="<%=contextPath%>/resources/image/selected.png">
                <div style="color: #2FAC4C;font-size: 13px;margin-top: 3px;margin-left: 2px">医院证明已提交</div>
            </div>
            <div style="display: flex;flex-direction: row;align-items: center;justify-content: flex-start;padding-top: 6px">
                <img style="width: 12px;height: 12px" src="<%=contextPath%>/resources/image/selected.png">
                <div style="color: #2FAC4C;font-size: 13px;margin-top: 3px;margin-left: 2px">居委会证明已提交</div>
            </div>
        </div>
        <div style="height: 10px;background: #F5F6F9"></div>
        <!--实名证实-->
        <div style="display: flex;justify-content: space-between;align-items: center;height: 46px;border-bottom:1px solid rgba(0,0,0,.15);">
            <div>有<span>{{affirmPeopleCount}}</span>人实名为Ta证实</div>
            <a href="#">我也来证实</a>
        </div>
        <div style="display: flex;align-items:center;justify-content: space-between;height: 60px">
            <div style="display: flex;justify-content: flex-start">
                <template v-for="url in affirmImgUrls">
                    <img :src="url"  style=" width: 38px;height: 38px;border-radius: 19px; ">
                </template>
            </div>
            <div>
                <img src="<%=contextPath%>/resources/image/more.png">
            </div>
        </div>
        <div style="display:flex;align-items:center;height: 30px;margin-top: 0px;margin-bottom: 10px;background: #F5F5F5">
            他是我们那里的人,这个是真实的
        </div>
        <%--这个是受助人承诺--%>
        <div>
            <div>受助人承诺诺</div>
            <div>承诺诺类型（A）</div>
            <div>承诺20年内帮助100人</div>
        </div>

    </div>
</div>

<!--进度更新-->
<div id="progressUpdate">
    <div style="display: flex;align-items: center;color: #43AC43;border-bottom: rgba(0,0,0,.15) solid 1px">
        <div style="display: flex;height: 40px;margin-left:10px;width: 50%;justify-content: flex-start;align-items:center;">
            进度更新(33)
        </div>
        <div style="display: flex;height: 40px;width: 50%;justify-content: center;align-items:center;">
            <span></span>
        </div>
    </div>
    <template v-for="item in lp">
        <div  :key="item.itemProgress.itemprogressid"  style="border-bottom: 1px solid rgba(0,0,0,.15);margin-top: 2px">
            <div style="display: flex;justify-content: space-between;align-items: flex-end;padding: 10px;">
                <div style="display: flex">
                    <img :src="item.itemProgress.userphoto" style="height: 36px;width: 36px;border-radius: 18px">
                    <div style="margin-left: 10px">
                        <div style="display: flex;align-items: flex-start">
                            <div>{{item.itemProgress.usernickname}}</div>
                            <div style="margin-left: 10px">{{item.itemProgress.backupone}}</div>
                        </div>
                        <div style="margin-top: 5px">{{item.itemProgress.updatetime}}</div>
                    </div>
                </div>
                <img src="<%=contextPath%>/resources/image/pinglun2.png" style="height: 18px;width: 18px;cursor: pointer;">
            </div>
            <div style="padding-left: 30px">
                <template v-for="comment in item.lmp">
                    <div style="display: flex;background: #F5F5F5;padding-top: 5px;padding-bottom: 5px;margin-right: 10px;margin-left: 20px">
                        <div  style="color: #4284B6;">
                            <span  v-bind:data-reciver="comment.useridsender"  v-on:click="test" style="cursor: pointer;">{{comment.sendernickname}}</span><span>:</span>
                            <span v-if="comment.refer==1 " >
                                <span style="color: #0f0f0f">回复</span><span  v-bind:data-reciver="comment.itempgcommentid" v-on:click="test"  style="color: #4284B6;cursor: pointer;">{{comment.recivernickname}}</span><span>:</span>
                            </span>
                            <span style="color: #666">{{comment.content}}</span>
                        </div>

                    </div>
                </template>
            </div>
        </div>
    </template>
</div>

<!--筹款动态-->
<div id="dynamic">
    <div style="display: flex;align-items: center;color: #43AC43;border-bottom: rgba(0,0,0,.15) solid 1px">
        <div style="display: flex;height: 40px;margin-left:10px;width: 50%;justify-content: flex-start;align-items:center;">
            筹款动态(100)
        </div>
        <div style="display: flex;height: 40px;width: 50%;justify-content: center;align-items:center;">
            <span></span>
        </div>
    </div>

    <template v-for="item in lp">
        <div v-show="updateORprogress" :key="item.moneySource.moneysourceid"  style="border-bottom: 1px solid rgba(0,0,0,.15);margin-top: 2px">
            <div style="display: flex;justify-content: space-between;align-items: flex-end;padding: 10px;">
                <div style="display: flex">
                    <img :src="item.moneySource.backuptwo" style="height: 36px;width: 36px;border-radius: 18px">
                    <div style="margin-left: 10px">
                        <div style="display: flex;align-items: flex-start">
                            <div>{{item.moneySource.backupone}}</div>
                            <div style="margin-left: 10px">支持了</div>
                            <div style="margin-left: 10px">{{item.moneySource.moneynum}}</div>
                        </div>
                        <div style="margin-top: 5px">{{item.moneySource.helptime}}</div>
                    </div>
                </div>
                <img src="<%=contextPath%>/resources/image/pinglun2.png" style="height: 18px;width: 18px;cursor: pointer;">
            </div>
            <div style="padding-left: 30px">
                <template v-for="comment in item.lmp">
                    <div style="display: flex;background: #F5F5F5;padding-top: 5px;padding-bottom: 5px;margin-right: 10px;margin-left: 20px">
                        <div  style="color: #4284B6;">
                            <span  v-bind:data-reciver="comment.useruuidsender"  v-on:click="test" style="cursor: pointer;">{{comment.sendernickname}}</span><span>:</span>
                            <span v-if="comment.refer==1 " >
                                <span style="color: #0f0f0f">回复</span><span  v-bind:data-reciver="comment.moneysrcpinglunid" v-on:click="test"  style="color: #4284B6;cursor: pointer;">{{comment.recivernickname}}</span><span>:</span>
                            </span>
                            <span style="color: #666">{{comment.pingluntext}}</span>
                        </div>

                    </div>
                </template>
            </div>
        </div>
    </template>
</div>


<div id="foo" style="height: 50px"></div>
<div class="footer">
    <div class="zhuafa" style="width: 46%;display: flex;align-items: center;justify-content: center">
        <span>转发</span>
        <img  style="height: 30px;width: 30px" src="<%=contextPath%>/resources/image/zhuanfa.png">
    </div>
    <div class="donate"  style="width: 46%;height: 40px;border-radius:20px;display: flex;align-items: center;justify-content: center;background-color: #2FAC4C">
        <span style="color: #ffffff">捐助Ta</span>
    </div>
</div>
<script type="text/javascript" src="<%=contextPath%>/resources/javaScript/indexDynamic.js"></script>
<script type="text/javascript" src="<%=contextPath%>/resources/javaScript/indexUpPart.js"></script>
<script type="text/javascript" src="<%=contextPath%>/resources/javaScript/indexProgressUpdate.js"></script>
</body>
</html>

