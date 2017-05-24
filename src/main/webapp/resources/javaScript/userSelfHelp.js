//如果用户没有发布求助信息，就隐藏这一块

new Vue({
    el: '#userSelfHelp',
    data: {
        lp:[],
    },
    mounted: function () {
            let url='http://localhost:8080/glove/weibos/94111BD33D3F474590C535C0BE24905B/helpselflist.do'
            var that=this;
            this.$http.get(url).then(response => {
                console.log(response.body);
                //存储或者改变相应的值
                if (response.body.retcode==2000){
                    that.$nextTick(function(){
                        this.lp=response.body.lp.concat(this.lp);
                    });
                    let  noData=document.getElementById("noData");
                    noData.style.display="none";

                    let  userSelfHelp=document.getElementById("userSelfHelp");
                    userSelfHelp.style.display="block";
                    // console.log("hahha");
                    // console.log(this.lp);
                }else{
                    //这里处理没有数据的表现
                    let  noData=document.getElementById("noData");
                    noData.style.display="flex";

                    let  userSelfHelp=document.getElementById("userSelfHelp");
                    userSelfHelp.style.display="none";

                }

            }, err => {
                //this.showDialog("登录出现异常");
                return alert(err);
            });
        },
    methods:{
        goTimeline:function () {
            window.location.href="http://localhost:8080/glove/path/pages/shareTotimeline/1000?tweetiD=28&useriD=2&cashiD=10"
        }
    }

})

