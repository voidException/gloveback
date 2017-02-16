package org.geilove.pojo;

import java.util.Date;

public class MoneySource {
    private Long moneysourceid;

    private Long useridbehelped;

    private Long useridgoodguy;

    private Integer moneynum;

    private Date helptime;

    private String backupone;  //帮助人昵称

    private String backuptwo; //帮助人头像地址

    private String backupthree; //帮助人的uuid

    private String backupfour; //受助人的uuid

    private Integer backupfive; //1 或者2表明是第几次被帮助

    private Long backupsix;  //cashiD  关联的cashiD

    public Long getMoneysourceid() {
        return moneysourceid;
    }

    public void setMoneysourceid(Long moneysourceid) {
        this.moneysourceid = moneysourceid;
    }

    public Long getUseridbehelped() {
        return useridbehelped;
    }

    public void setUseridbehelped(Long useridbehelped) {
        this.useridbehelped = useridbehelped;
    }

    public Long getUseridgoodguy() {
        return useridgoodguy;
    }

    public void setUseridgoodguy(Long useridgoodguy) {
        this.useridgoodguy = useridgoodguy;
    }

    public Integer getMoneynum() {
        return moneynum;
    }

    public void setMoneynum(Integer moneynum) {
        this.moneynum = moneynum;
    }

    public Date getHelptime() {
        return helptime;
    }

    public void setHelptime(Date helptime) {
        this.helptime = helptime;
    }

    public String getBackupone() {
        return backupone;
    }

    public void setBackupone(String backupone) {
        this.backupone = backupone == null ? null : backupone.trim();
    }

    public String getBackuptwo() {
        return backuptwo;
    }

    public void setBackuptwo(String backuptwo) {
        this.backuptwo = backuptwo == null ? null : backuptwo.trim();
    }

    public String getBackupthree() {
        return backupthree;
    }

    public void setBackupthree(String backupthree) {
        this.backupthree = backupthree == null ? null : backupthree.trim();
    }

    public String getBackupfour() {
        return backupfour;
    }

    public void setBackupfour(String backupfour) {
        this.backupfour = backupfour == null ? null : backupfour.trim();
    }

    public Integer getBackupfive() {
        return backupfive;
    }

    public void setBackupfive(Integer backupfive) {
        this.backupfive = backupfive;
    }

    public Long getBackupsix() {
        return backupsix;
    }

    public void setBackupsix(Long backupsix) {
        this.backupsix = backupsix;
    }

}