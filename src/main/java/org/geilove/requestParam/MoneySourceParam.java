package org.geilove.requestParam;

/**
 * Created by mfhj-dz-001-424 on 17/1/24.
 */
//用于MoneySourceController 的请求参数，获得"支持了"列表

public class MoneySourceParam {

    private  Long userIDBehelped; //这个被帮助的id
    private  Long tweetidl;  //这个是与被帮助者关联的tweet 的id
    private  Integer  page;  //这个是请求的页数
    private  Integer  pageSize ; //这个是请求的每页的大小
    private  String   timeStamp; //这个是"支持了" 的时间，要按照时间获取

    public Long getUserIDBehelped() {
        return userIDBehelped;
    }

    public void setUserIDBehelped(Long userIDBehelped) {
        this.userIDBehelped = userIDBehelped;
    }

    public Long getTweetidl() {
        return tweetidl;
    }

    public void setTweetidl(Long tweetidl) {
        this.tweetidl = tweetidl;
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
