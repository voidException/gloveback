/**
 * Created by mfhj-dz-001-424 on 17/2/16.
 */
// /**
//  * Created by mfhj-dz-001-424 on 17/2/15.
//  */
// function getQueryString(name)
// {
//     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
//     var r = window.location.search.substr(1).match(reg);
//     if(r!=null)
//         return  decodeURIComponent(r[2]);
//     return null;
// }
// console.log(getQueryString("tweetiD"));
// console.log(getQueryString("useriD"));
// console.log(getQueryString("cashiD"));
new Vue({
    el: '#progressUpdate',
    data: {
        msg: 'nihao',
        retcode:'',
        lp:[],
        updateORprogress:true,
        updateORprogress2:false,
    },
    methods: {
        test: function (event) {
            //console.log(event.target.attributes[0])
                    var requestParam={
                        "userIDBehelped":1,
                        "tweetid":1,
                        "cashid":1,
                        "page":0,
                        "pageSize":5,
                        "timeStamp":"2017-09-04 00:00:00"
                    };
                    this.$http.post('http://localhost:8080/glove/itemprogress/updatelist',requestParam).then(response => {
                        // console.log(response.body);
                        this.$nextTick(function(){
                            this.lp=response.body.lp.concat(this.lp);
                        });
                        // console.log("hahha");
                        // console.log(this.lp);
                    }, err => {
                        console.log(err);
                    });

        }
    },

    mounted: function () {
        //console.log('mounted 钩子执行...');
        //1.这里监听滚动条事件
        // var d = document.getElementById("part1").offsetHeight

        //2.这里应该从页面获取参数，
        var param={
            "userIDBehelped":1,
            "tweetid":1,
            "cashid":1,
            "page":0,
            "pageSize":5,
            "timeStamp":"2017-09-04 00:00:00"
        };

        this.$http.post('http://localhost:8080/glove/itemprogress/updatelist',param).then(response => {
            console.log(response.body);
            this.lp=response.body.lp;
        }, err => {
            console.log(err);
        });
    },

})
