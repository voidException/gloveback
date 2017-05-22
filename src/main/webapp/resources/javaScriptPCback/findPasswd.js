/**
 * Created by aihaitao on 20/5/2017.
 * 这里把加密后的邮箱，2 次输入的密码通过ajax发送到后台
 *
 */



new Vue({
    el: '#findPasswd',
    data: {
        stopTag:0, // 防止用户多次点击的标志位
    },
    methods: {
        findtPasswd: function () {
            if(this.stopTag==1){
                console.log("stopTag")
                return;
            }
            this.stopTag=1;
            let encryptEmail="H0Am8jdfLzK3ChuW78NnaQ==";
            //let encryptEmail=document.getElementById("encryptEmail").value;
            let originPass=document.getElementById("originPass").value;
            let againPass=document.getElementById("againPass").value;


            if(encryptEmail==null || originPass==null || againPass==null){
                this.stopTag=0;
                return alert("参数不得为空")
            }
            //检查邮箱格式

            if (originPass!=againPass){
                this.stopTag=0;
                return alert("两次密码不一致")
            }

            let param={
                encryptEmail:encryptEmail,
                originPass:originPass,
                againPass:againPass
            };
            this.$http.post('http://localhost:8080/glove/user/changePasswd.do',param,"application/json").then(response => {

                if(response.body.retcode==2000){
                    this.stopTag=0;
                    //接下来，应该清除本地缓存，让用户重新登录，待完成
                    alert("重置密码成功");

                }else {
                    this.stopTag=0;
                    alert("重置密码失败");
                }
            }, err => {
                this.stopTag=0;
                alert("重置密码出现异常")
            });

        }
    }
});