/**
 * Created by mfhj-dz-001-424 on 17/2/15.
 */
function getQueryString(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)
        return  decodeURIComponent(r[2]);
    return null;
}
console.log(getQueryString("tweetiD"));
console.log(getQueryString("useriD"));
console.log(getQueryString("cashiD"));
new Vue({
    el: '#app',
    data: {
        message: 'Hello Vue!',
        lp:""
    },
    mounted: function () {
        console.log('mounted 钩子执行...');

        this.$http.get('http://localhost:8080/glove/timelinetweet/info/1').then(response => {
            console.log(response.body);
            this.message=response.body.lp.cash.backupfive;
        }, err => {
            console.log(err);
        });
    },

})
