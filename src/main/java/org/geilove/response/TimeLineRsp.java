package org.geilove.response;

import org.geilove.vo.TimeLine;

/**
 * Created by mfhj-dz-001-424 on 17/2/14.
 */
public class TimeLineRsp {
    private TimeLine lp;
    private  String   msg;
    private  Integer retcode;

    public TimeLine getLp() {
        return lp;
    }

    public void setLp(TimeLine lp) {
        this.lp = lp;
    }

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
}

