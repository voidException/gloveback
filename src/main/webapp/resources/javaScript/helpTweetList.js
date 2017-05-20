/**
 * Created by aihaitao on 19/5/2017.
 */


new Vue({
    el: '#userSelfHelp',
    data: {
        lp:[],
    },
    mounted: function () {

       // let url='http://localhost:8080/glove/weibos/helpTweetlist.do?lastUpdate="2018-09-02"'
        let baseurl='http://localhost:8080/glove/weibos/helpTweetlist.do'
        let update="2018-09-02";
        let urlfinal=baseurl+'?'+"lastUpdate="+update;
        console.log(urlfinal)
        var that=this;
        this.$http.get(urlfinal).then(response => {
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


