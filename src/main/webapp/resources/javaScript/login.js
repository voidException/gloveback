
/**
 *
 * 这个适用于公众号内登录
 * 登录成功，要将数据存储在本地，然后显示相应内容
 */
new Vue({
    el: '#wechatLogin',
    data: {

    },
    methods: {
        verify(){ //此方法用于校验邮箱和密码是否合法，与移动端等同
            //console.log("verify")
            let email= document.getElementById("emailInput").value;
            let password =document.getElementById("passwordInput").value;

            let regx=/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
            let regP=/^[0-9|a-z|A-Z]\w{5,17}$/; //6-18w位数字和字母组成的密码
            //let testEm='@567890qwertyui';
            //console.log(regP.test(testEm));

            if(email===null ||password===null ||email.length<10 || email.length>30 || password.length<6 ||password.length>18|| !regx.test(email) || !regP.test(password)){
                //控制'您输入的邮箱或密码有误'  errorTips
                return false;
            }
            return true;

        },
        loginIn:function () { //执行登录的方法，先获取邮箱和密码，如果验证不通过，在模态框提示错误；登录后的结果通过dialog显示
            //先校验
            if(!this.verify()){
                return   alert("您的邮箱或密码有误") ;
            }

            let email= document.getElementById("emailInput").value;
            let password =document.getElementById("passwordInput").value;
            let userAccount={
                userEmail:email,
                userPassword:password
            };
            //发送请求前先，隐藏弹出框，避免多次点击
            //发送网络请求
            this.$http.post('http://localhost:8080/glove/user/login.do',userAccount).then(response => {
                console.log(response.body);

                //存储或者改变相应的值
                if (response.body.retcode==2000){
                    sessionStorage.setItem("userToken",response.body.data.backupfour);
                    sessionStorage.setItem("usernickname",response.body.data.usernickname);
                    sessionStorage.setItem("userphoto",response.body.data.userphoto);
                    sessionStorage.setItem("userid",response.body.data.userid);
                    sessionStorage.setItem("useruuid",response.body.data.backupten);

                    sessionStorage.setItem("loginTag","logined");  //登录标志
                    //console.log(sessionStorage.getItem("usernickname"));
                    window.location.href="http://localhost:8080/glove"
                }

            }, err => {
                //this.showDialog("登录出现异常");
                return alert(err);
            });


        }

    },

});


