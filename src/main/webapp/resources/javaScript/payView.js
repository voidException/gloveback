/**
 * Created by aihaitao on 19/4/2017.
 */

/**
 *
 *分享到朋友圈的登录模态弹出框
 * 2017-04-10
 */
new Vue({
    el: '#loginEmail',
    data: {

    },
    methods: {
        getPrepayiD: function () { //获取

            let orderIdValue=document.getElementById("orderId").value;
            let openIdValue=document.getElementById("openId").value;

            let unifiedPartParam={
                orderId:orderIdValue,
                openId:openIdValue
            };
            //显示loading动画，避免被多次操作
            this.$http.post('http://gelove.org/glove/authorize/user/weixinJSBridge/invoke.do',unifiedPartParam).then(response => {
                console.log(response.body);

                //存储或者改变相应的值
                if (response.body.retcode==2000){
                       //组装参数，调用WeixinJSBridge.invoke
                }

            }, err => {
                this.showDialog("出现异常");
            });
        },


    },

});



