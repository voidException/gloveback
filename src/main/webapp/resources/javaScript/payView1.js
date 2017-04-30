/**
 * Created by aihaitao on 19/4/2017.
 */

/**
 *
 *分享到朋友圈的登录模态弹出框
 * 2017-04-10
 */
new Vue({
    el: '#payView',
    data: {

    },
    methods: {
        getPrepayiD: function () { //获取

            let orderIdValue=document.getElementById("orderId").value;
            let openIdValue=document.getElementById("openId").value;

            // let unifiedPartParam={
            //     orderId:orderIdValue,
            //     openId: openIdValue,
            //     money:10
            // };
            let unifiedPartParam={
                orderId:"alooge126com",
                openId:"otQXfwdHziiO1pkZptC7l1NKdRT0",
                money:10
            };

            //alert(openIdValue);
            //alert(orderIdValue);
            //显示loading动画，避免被多次操作
            this.$http.post('http://geilove.org/glove/authorize/weixinJSBridge/invoke.do',unifiedPartParam).then(response => {
                console.log(response.body.prepay_id);
                alert(response.body.prepay_id)
                document.getElementById("resdata").innerHTML=response.body.prepay_id;
                //存储或者改变相应的值
                if (response.body.retcode==2000){
                       //组装参数，调用WeixinJSBridge.invoke
                    //document.getElementById("resdata").value=response.body
                }

            }, err => {
                //this.showDialog("出现异常");
                console.log(err)
                alert(err);
            });
        },


    },

});



