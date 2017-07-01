package org.geilove.pojo;

public class PayMoney {
    private Long paymoneyid;

    private String paymoneyuuid;

    private String accountuuid;

    private String useruuid;

    private String categorytype;

    private Long wechatpay;

    public Long getPaymoneyid() {
        return paymoneyid;
    }

    public void setPaymoneyid(Long paymoneyid) {
        this.paymoneyid = paymoneyid;
    }

    public String getPaymoneyuuid() {
        return paymoneyuuid;
    }

    public void setPaymoneyuuid(String paymoneyuuid) {
        this.paymoneyuuid = paymoneyuuid == null ? null : paymoneyuuid.trim();
    }

    public String getAccountuuid() {
        return accountuuid;
    }

    public void setAccountuuid(String accountuuid) {
        this.accountuuid = accountuuid == null ? null : accountuuid.trim();
    }

    public String getUseruuid() {
        return useruuid;
    }

    public void setUseruuid(String useruuid) {
        this.useruuid = useruuid == null ? null : useruuid.trim();
    }

    public String getCategorytype() {
        return categorytype;
    }

    public void setCategorytype(String categorytype) {
        this.categorytype = categorytype == null ? null : categorytype.trim();
    }

    public Long getWechatpay() {
        return wechatpay;
    }

    public void setWechatpay(Long wechatpay) {
        this.wechatpay = wechatpay;
    }
}