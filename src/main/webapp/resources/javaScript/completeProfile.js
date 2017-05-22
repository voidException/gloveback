/**
 * Created by aihaitao on 20/5/2017.
 */

new Vue({
    el: '#completeProfile',
    data: {
        "userProfile": {},
    },
    methods: {
        fullFillProfile: function () {
            let token=sessionStorage.getItem("token"); //待统一

            let realName=document.getElementById("realName").value;
            let identity=document.getElementById("identity").value;
            let address=document.getElementById("address").value;
            let college=document.getElementById("college").value;
            let mobilePhone=document.getElementById("mobilePhone").value;
            let selfIntroduce=document.getElementById("selfIntroduce").value;
            if (token==null){
               // return  alert("尚未登录");
            }
            if(realName==null && identity==null  && address==null && college==null && mobilePhone==null){
                return alert("请属于有效更新资料")
            }

            let param={
                token:token,
                realName:realName,
                identity:identity,
                address:address,
                college:college,
                mobilePhone:mobilePhone,
                selfIntroduce:selfIntroduce
            };
            this.$http.post('http://localhost:8080/glove/profile/completeProfileJSP.do',param).then(response => {
                console.log(response.body);

                if(response.body.retcode==2000){
                    alert("更新成功");
                    window.location.href="www.geilove.org/mobile"
                }else {
                    alert("更新失败");
                }

            }, err => {
                console.log(err);
                alert("更新出现异常")
            });

        }
    }
});