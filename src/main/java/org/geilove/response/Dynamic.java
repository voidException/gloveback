package org.geilove.response;

import org.geilove.pojo.MoneySource;
import org.geilove.pojo.MoneysrcPinglun;

import java.util.List;

/**
 * Created by mfhj-dz-001-424 on 17/1/24.
 */
public class Dynamic {

    private MoneySource  moneySource;
    private List<MoneysrcPinglun> lmp;

    public MoneySource getMoneySource() {
        return moneySource;
    }

    public void setMoneySource(MoneySource moneySource) {
        this.moneySource = moneySource;
    }

    public List<MoneysrcPinglun> getLmp() {
        return lmp;
    }

    public void setLmp(List<MoneysrcPinglun> lmp) {
        this.lmp = lmp;
    }
}
