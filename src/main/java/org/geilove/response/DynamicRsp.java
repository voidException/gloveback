package org.geilove.response;

import java.util.List;

/**
 * Created by mfhj-dz-001-424 on 17/1/23.
 */
public class DynamicRsp {
    private List<Dynamic> lp;
    private  String msg;
    private Integer  retcode;

    public List<Dynamic> getLp() {
        return lp;
    }

    public void setLp(List<Dynamic> lp) {
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
