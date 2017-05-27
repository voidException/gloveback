package org.geilove.thread;

import org.geilove.pojo.MoneySource;

/**
 * Created by aihaitao on 27/5/2017.
 */
public class NotifyThread  extends  Thread {
    private MoneySource  moneySource;

    public NotifyThread(MoneySource  moneySource){  //构造方法
        this.moneySource=moneySource;
    }
    public  void  run(){
        System.out.print(moneySource.getMoneynum());

        for (int i = 0; i < 10; i++) {
            for (long k = 0; k < 3; k++) {
                System.out.println("序号" + ": " + k);
            }

        }
    }
}
