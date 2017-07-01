package org.geilove.pojo;

import java.util.Date;

public class UserAccount {
    private Long useraccountid;

    private String useruuid;

    private String accountuuid;

    private String breakif;

    private Date buildrelationdate;

    private Date breakrelationdate;

    private String buildrelationdescription;

    private String breakrelationdescription;

    private Integer paytimes;

    private Long paytotalmoney;

    private byte[] useraccountuuid;

    public Long getUseraccountid() {
        return useraccountid;
    }

    public void setUseraccountid(Long useraccountid) {
        this.useraccountid = useraccountid;
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

    public String getBreakif() {
        return breakif;
    }

    public void setBreakif(String breakif) {
        this.breakif = breakif == null ? null : breakif.trim();
    }

    public Date getBuildrelationdate() {
        return buildrelationdate;
    }

    public void setBuildrelationdate(Date buildrelationdate) {
        this.buildrelationdate = buildrelationdate;
    }

    public Date getBreakrelationdate() {
        return breakrelationdate;
    }

    public void setBreakrelationdate(Date breakrelationdate) {
        this.breakrelationdate = breakrelationdate;
    }

    public String getBuildrelationdescription() {
        return buildrelationdescription;
    }

    public void setBuildrelationdescription(String buildrelationdescription) {
        this.buildrelationdescription = buildrelationdescription == null ? null : buildrelationdescription.trim();
    }

    public String getBreakrelationdescription() {
        return breakrelationdescription;
    }

    public void setBreakrelationdescription(String breakrelationdescription) {
        this.breakrelationdescription = breakrelationdescription == null ? null : breakrelationdescription.trim();
    }

    public Integer getPaytimes() {
        return paytimes;
    }

    public void setPaytimes(Integer paytimes) {
        this.paytimes = paytimes;
    }

    public Long getPaytotalmoney() {
        return paytotalmoney;
    }

    public void setPaytotalmoney(Long paytotalmoney) {
        this.paytotalmoney = paytotalmoney;
    }

    public byte[] getUseraccountuuid() {
        return useraccountuuid;
    }

    public void setUseraccountuuid(byte[] useraccountuuid) {
        this.useraccountuuid = useraccountuuid;
    }
}