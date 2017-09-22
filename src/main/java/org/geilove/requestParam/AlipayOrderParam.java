package org.geilove.requestParam;

/**
 * Created by aihaitao on 8/9/2017.
 */
public class AlipayOrderParam {

    private  String  amount;  //钱数，单位元
    private  String  body;
    private  String  outTradeNo;
    private  String  accountUUID; // 也即身份证号


    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getAccountUUID() {
        return accountUUID;
    }

    public void setAccountUUID(String accountUUID) {
        this.accountUUID = accountUUID;
    }
}
