
new Vue({
    el: '#resetPasswd',
    data: {
        stopTag:0, // 防止用户多次点击的标志位
    },
    methods: {
        resetPasswd: function () {
             if(this.stopTag==1){
                 console.log("stopTag")
                 return;
             }
             this.stopTag=1;
            //let token=sessionStorage.getItem("token"); //待统一
            let token="fcea920f7412b5da7be0cf42b8c937591"
            let originPass=document.getElementById("originPass").value;
            let newPass=document.getElementById("newPass").value;
            let againPass=document.getElementById("againPass").value;
            if (token==null){
                this.stopTag=0;
                 return  alert("尚未登录");
            }
            if(originPass==null || newPass==null || againPass==null){
                this.stopTag=0;
                return alert("不得为空")
            }
            if (newPass!=againPass){
                this.stopTag=0;
                return alert("两次密码不一致")
            }

            let param={
                token:token,
                originPass:originPass,
                newPass:newPass,
                againPass:againPass
            };
            this.$http.post('http://localhost:8080/glove/user/resetpass.do',param,"application/json").then(response => {

                if(response.body.retcode==2000){
                    this.stopTag=0;
                    //接下来，应该清除本地缓存，让用户重新登录，待完成

                    alert("重置密码成功");
                    setTimeout(function(){
                        window.location.href="www.geilove.org/mobile" //跳转到首页
                    },100);

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