package org.geilove.requestParam;

/**
 * Created by mfhj-dz-001-424 on 17/2/7.
 */
public class ItemProgressListParam {
    private  Long   userIDBehelped; //这个被帮助人的id
    private  Long   tweetid;  //这个是与被帮助者关联的tweet 的id
    private  Long    cashid;  //该进度更新对应的cash表的iD，无论进度更新还是moneysource表都应该首先跟cash表关联，而不是推文
    private  Integer  page;  //这个是请求的页数
    private  Integer  pageSize ; //这个是请求的每页的大小
    private  String   timeStamp;

    public Long getCashid() {
        return cashid;
    }

    public void setCashid(Long cashid) {
        this.cashid = cashid;
    }
    public Long getUserIDBehelped() {
        return userIDBehelped;
    }

    public void setUserIDBehelped(Long userIDBehelped) {
        this.userIDBehelped = userIDBehelped;
    }

    public Long getTweetid() {
        return tweetid;
    }

    public void setTweetid(Long tweetid) {
        this.tweetid = tweetid;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
