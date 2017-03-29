/**
 * Created by aihaitao on 29/3/2017.
 */
new Vue({
    el: '#productDetail',
    data: {
        "productDetail": {

        }
    },
    mounted: function () {

        let url='http://localhost:8080/glove/productInfo/info/1_98398dnjsfnkfkjsfn';
        this.$http.get(url).then(response => {
            console.log(response.body);
            this.productDetail=response.body.productInfo;
        }, err => {
            console.log(err);
        });
    },
});

