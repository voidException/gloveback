

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
                    // console.log("hahha");
                    // console.log(this.lp);
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

