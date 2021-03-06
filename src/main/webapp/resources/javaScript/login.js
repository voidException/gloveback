
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
                //console.log(response.body);

                //存储或者改变相应的值
                if (response.body.retcode==2000){

                    localStorage.setItem("userToken",response.body.data.backupfour);
                    localStorage.setItem("loginTag","logined");  //登录标志

                    localStorage.setItem("userid",response.body.data.userid);
                    localStorage.setItem("usernickname",response.body.data.usernickname);
                    localStorage.setItem("useremail",response.body.data.useremail);
                    localStorage.setItem("usertype",response.body.data.usertype);
                    localStorage.setItem("certificatetype",response.body.data.certificatetype);
                    localStorage.setItem("realname",response.body.data.realname);
                    localStorage.setItem("selfintroduce",response.body.data.selfintroduce);
                    localStorage.setItem("usertag",response.body.data.usertag);
                    localStorage.setItem("address",response.body.data.address);
                    localStorage.setItem("msgpubcount",response.body.data.msgpubcount);
                    localStorage.setItem("fanscount",response.body.data.fanscount);
                    localStorage.setItem("followcount",response.body.data.followcount);
                    localStorage.setItem("notsay",response.body.data.notsay);
                    localStorage.setItem("userphoto",response.body.data.userphoto);
                    localStorage.setItem("backupone",response.body.data.backupone);
                    localStorage.setItem("backuptwo",response.body.data.backuptwo);
                    localStorage.setItem("backupthree",response.body.data.backupthree);
                    localStorage.setItem("backupfour",response.body.data.backupfour);
                    localStorage.setItem("backupfive",response.body.data.backupfive);
                    localStorage.setItem("backupsix",response.body.data.backupsix);
                    localStorage.setItem("userhelpsman",response.body.data.userhelpsman);
                    localStorage.setItem("userdonate",response.body.data.userdonate);
                    localStorage.setItem("amountaccept",response.body.data.amountaccept);
                    localStorage.setItem("acceptmoney",response.body.data.acceptmoney);
                    localStorage.setItem("backupten",response.body.data.backupten);
                    localStorage.setItem("userhelpsman",response.body.data.userhelpsman);
                    localStorage.setItem("userUUID",response.body.data.useruuid)
                    window.location.href="http://localhost:8080/glove/path/pages/mobileWo.do"
                }

            }, err => {
                //this.showDialog("登录出现异常");
                return alert(err);
            });


        }

    },

});


