package org.geilove.controller;

import org.geilove.pojo.MoneySource;
import org.geilove.response.CommonRsp;
import org.geilove.thread.NotifyThread;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 这个类用来测试支付回调的
 */
@RequestMapping(value="/wxNotify")
@Controller

public class WxNotifyTest {

    @RequestMapping(value="/test",method= RequestMethod.GET)
    @ResponseBody
    public CommonRsp wxNotify(HttpServletRequest request, HttpServletResponse response){
        MoneySource moneySource=new MoneySource();
        moneySource.setMoneynum(10); //test 这个是测试对象，

        NotifyThread  notifyThread=new NotifyThread(moneySource);
        notifyThread.start();

        System.out.print("执行到这里");
        CommonRsp commonRsp=new CommonRsp();
        commonRsp.setMsg("我要返回了");
        return commonRsp;
    }
}
