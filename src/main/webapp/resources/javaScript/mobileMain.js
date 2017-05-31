
/**
 *
 * 这个适用于公众号内登录
 * 登录成功，要将数据存储在本地，然后显示相应内容
 */
new Vue({
    el: '#woWechat',
    data: {
        myhelp:0,
        myDonate:0,
        helpMe:0,
        donateMe:0
    },
    mounted: function(){
        let openId=document.getElementById("openId").innerText;
        let email=localStorage.getItem("useremail");
        let token=localStorage.getItem("backupfour");

        let params={
            email:email,
            token:token,
            openId:openId
        };
        this.$http.post('http://localhost:8080/glove/user/openidemail.do',params).then(response => {

            if (response.body.retcode==2000){
                //
                 this.myhelp=response.body.data.userhelpsman,
                 this.myDonate=response.body.data.userdonate,
                 this.helpMe=response.body.data.amountaccept,
                 this.donateMe=response.body.data.amountaccept
            }
        },err=>{

        })
    },

});



