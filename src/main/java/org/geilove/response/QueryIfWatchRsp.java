package org.geilove.response;

/**
 * Created by mfhj-dz-001-424 on 17/1/4.
 */
public class QueryIfWatchRsp {

    private  Integer tag;
    private Integer retcode ;
    private String msg;

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }
    public Integer getRetcode() {
        return retcode;
    }
    public void setRetcode(Integer retcode) {
        this.retcode = retcode;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
