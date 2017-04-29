package org.geilove.response;

/**
 * 这里返回调起H5支付的全部参数值
 */
public class UnifiedorderRsp {

    private  String   msg;
    private  Integer  retcode;

    private  String   orderId;
    private  String   openId;
    private  String   prepay_id;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getRetcode() {
        return retcode;
    }

    public void setRetcode(Integer retcode) {
        this.retcode = retcode;
    }
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }
}
