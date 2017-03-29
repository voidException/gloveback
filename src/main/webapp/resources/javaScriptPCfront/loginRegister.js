/**
 * Created by aihaitao on 29/3/2017.
 */

new Vue({
    el: '#loginRegister',
    data: {
        "userProfile": {},
    },
    methods: {
        dologin:function (event) {
            var loginEmailInputValue=document.getElementById("loginEmailInput").value; //登录的email值
            var loginPasswdInputValue=document.getElementById("loginPasswdInput").value; //登录的密码值

            //接下来发送登录请求
            let param={
                userEmail:loginEmailInputValue,
                userPassword:loginPasswdInputValue,
            };
            this.$http.post('http://localhost:8080/glove/user/login',param).then(response => {
                console.log(response.body);
                this.userProfile=response.body.data; //存入数据库
                window.location.href="http://localhost:8080/glove/";
            }, err => {
                console.log(err);
            });

        },
        doregister:function(event) {
            var registerEmailInput=document.getElementById("registerEmailInput").value;   //邮箱
            var registerPasswdInput=document.getElementById("registerPasswdInput").value;   //密码
            var registerNickNameInput=document.getElementById("registerNickNameInput").value; //昵称
            var registerCityInput=document.getElementById("registerCityInput").value;   //城市

            //接下来发送注册请求
            let param={
                userNickName:registerNickNameInput,
                userEmail:registerEmailInput,
                userPassword:registerPasswdInput,
                cityName:registerCityInput,
            };
            this.$http.post('http://localhost:8080/glove/user/register',param).then(response => {
                console.log(response.body);
                this.userProfile=response.body.data;
                alert("注册成功，请登录")

            }, err => {
                console.log(err);
                alert("注册失败")
            });


        },
    },

})
