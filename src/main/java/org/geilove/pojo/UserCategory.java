package org.geilove.pojo;

import java.util.Date;

public class UserCategory {
    private Long usercategoryid;

    private String usercategoryuuid;

    private String useruuid;

    private String accountuuid;

    private String categorytype;

    private Date linkdate;

    private Date joindate;

    private Date effectivedate;

    private Date stopdate;

    private String nowstate;

    private Long remainfee;

    private Integer historyfeetimes;

    private Long historytotalfee;

    public Long getUsercategoryid() {
        return usercategoryid;
    }

    public void setUsercategoryid(Long usercategoryid) {
        this.usercategoryid = usercategoryid;
    }

    public String getUsercategoryuuid() {
        return usercategoryuuid;
    }

    public void setUsercategoryuuid(String usercategoryuuid) {
        this.usercategoryuuid = usercategoryuuid == null ? null : usercategoryuuid.trim();
    }

    public String getUseruuid() {
        return useruuid;
    }

    public void setUseruuid(String useruuid) {
        this.useruuid = useruuid == null ? null : useruuid.trim();
    }

    public String getAccountuuid() {
        return accountuuid;
    }

    public void setAccountuuid(String accountuuid) {
        this.accountuuid = accountuuid == null ? null : accountuuid.trim();
    }

    public String getCategorytype() {
        return categorytype;
    }

    public void setCategorytype(String categorytype) {
        this.categorytype = categorytype == null ? null : categorytype.trim();
    }

    public Date getLinkdate() {
        return linkdate;
    }

    public void setLinkdate(Date linkdate) {
        this.linkdate = linkdate;
    }

    public Date getJoindate() {
        return joindate;
    }

    public void setJoindate(Date joindate) {
        this.joindate = joindate;
    }

    public Date getEffectivedate() {
        return effectivedate;
    }

    public void setEffectivedate(Date effectivedate) {
        this.effectivedate = effectivedate;
    }

    public Date getStopdate() {
        return stopdate;
    }

    public void setStopdate(Date stopdate) {
        this.stopdate = stopdate;
    }

    public String getNowstate() {
        return nowstate;
    }

    public void setNowstate(String nowstate) {
        this.nowstate = nowstate == null ? null : nowstate.trim();
    }

    public Long getRemainfee() {
        return remainfee;
    }

    public void setRemainfee(Long remainfee) {
        this.remainfee = remainfee;
    }

    public Integer getHistoryfeetimes() {
        return historyfeetimes;
    }

    public void setHistoryfeetimes(Integer historyfeetimes) {
        this.historyfeetimes = historyfeetimes;
    }

    public Long getHistorytotalfee() {
        return historytotalfee;
    }

    public void setHistorytotalfee(Long historytotalfee) {
        this.historytotalfee = historytotalfee;
    }
}