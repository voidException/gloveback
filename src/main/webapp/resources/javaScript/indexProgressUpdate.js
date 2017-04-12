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

        commentToUpdate:function(event){//这里是对直接对更新的评论
            /* 1. 让弹出框先弹出来*/
            let  modal=document.getElementById("modal");
            let  tips=document.getElementById("tips");
            modal.style.display="block";
            tips.style.display="block";

            /* 2. 告诉弹出框这个是进度更新的还是支持了列表的*/
            let  replyDiv = document.getElementById ("reply");
            replyDiv.setAttribute("data-updateORdynamic","progressUpdate"); //progressUpdate代表是进度更新，dynamic代表的是支持动态
            replyDiv.setAttribute("data-refer","0");  //0代表回复进度更新人，或者是回复直接回复捐钱人

            /* 3.把 弹出框需要的reciverNickname，userUUIDReciver等信息放置到弹出框，供modalPingLun.js 使用 */
            let  itemprogressid=event.target.getAttribute("data-itemprogressid");
            let  useridreciver=event.target.getAttribute("data-useridreciver");
            let  useruuidreciver=event.target.getAttribute("data-useruuidreciver");
            let  recivernickname=event.target.getAttribute("data-recivernickname");
            let  senderphoto=event.target.getAttribute("data-senderphoto");
            // 相比于"支持了"列表，这个是超集
            replyDiv.setAttribute("data-itemprogressid",itemprogressid);
            replyDiv.setAttribute("data-useridreciver",useridreciver);
            replyDiv.setAttribute("data-useruuidreciver",useruuidreciver);
            replyDiv.setAttribute("data-recivernickname",recivernickname);
            replyDiv.setAttribute("data-senderphoto",senderphoto);

        },
        commentReplyToUpdate:function(event){//这里是对更新下面评论的回复
            /* 1. 让弹出框先弹出来*/
            let  modal=document.getElementById("modal");
            let  tips=document.getElementById("tips");
            modal.style.display="block";
            tips.style.display="block";
            /* 2. 告诉弹出框这个是进度更新的还是支持了列表的*/
            let  replyDiv = document.getElementById ("reply");
            replyDiv.setAttribute("data-updateORdynamic","progressUpdate"); //progressUpdate代表是进度更新，dynamic代表的是支持动态
            replyDiv.setAttribute("data-refer","1");  //1代表对评论的回复

            /* 3.把 弹出框需要的reciverNickname，userUUIDReciver等信息放置到弹出框，供modalPingLun.js 使用 */
            let  itemprogressid=event.target.getAttribute("data-itemprogressid");
            let  useridreciver=event.target.getAttribute("data-useridreciver");
            let  useruuidreciver=event.target.getAttribute("data-useruuidreciver");
            let  recivernickname=event.target.getAttribute("data-recivernickname");
            let  reciverphoto=event.target.getAttribute("data-reciverphoto");
            // 相比于"支持了"列表，这个是超集
            replyDiv.setAttribute("data-itemprogressid",itemprogressid);
            replyDiv.setAttribute("data-useridreciver",useridreciver);
            replyDiv.setAttribute("data-useruuidreciver",useruuidreciver);
            replyDiv.setAttribute("data-recivernickname",recivernickname);
            replyDiv.setAttribute("data-reciverphoto",reciverphoto);

            // let  dataUpdateORdynamic = replyDiv.getAttribute ("data-updateORdynamic");
            // console.log(dataUpdateORdynamic);
            // let  dataRefer = replyDiv.getAttribute ("data-refer");
            // console.log(dataRefer);

        },

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
                         console.log("点击我了");
                        // console.log(this.lp);
                    }, err => {
                        console.log(err);
                    });

        },
        alertComment: function (){
            //这里先检查用户是否登录了
            //如果用户登录了，存储userToken等等数据，localStorage存储
            if(localStorage.getItem('userToken')==null){
                //弹出登录模态框

                return; //return了，后续代码不执行

            }
            //弹出进度更新模态框
            let  modal=document.getElementById("modal");
            let  tips=document.getElementById("tips");
            if (modal.style.display=="none" ||modal.style.display==""){
                modal.style.display="block";
                tips.style.display="block";
            }
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
