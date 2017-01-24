package org.geilove.controller;

/**
 * 通过这个，获取"支持了"列表，及其对应的评论
 */
import javax.annotation.Resource;
import org.geilove.pojo.*;
import org.geilove.response.Dynamic;
import org.geilove.response.DynamicRsp;
import org.geilove.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@Controller
@RequestMapping("/moneysource")
public class MoneySourceController {
     @Resource
     private MoneySourceService moneySourceService;

    public @ResponseBody Object getMoneySourceList(@RequestBody Long userIDBehelped ){
        //根据受助人的ID获取这个人的"支持了"列表
        DynamicRsp dynamicRsp=new DynamicRsp(); //最终要返回的
        List<Dynamic> listDynamic=new ArrayList<>(); //
        Map<String,Object>  map=new HashMap<>();
        map.put("page",1);
        List<MoneySource>  lms=moneySourceService.getGuyHelpMe(map);
        //for 循环，将每一个lms列表项，放入到listDynamic中,同时取出来每一个列表项的moneySourceID，组成List<moneySourceID>和List<UserIDgoodguy>
        List<Long> moneySourceIDs=new ArrayList<>();
        for(int i=0;i<lms.size();i++){
            moneySourceIDs.add(lms.get(i).getMoneysourceid());

            Dynamic dynamic=new Dynamic(); //构造DynamicRsp 中的数据域List<Dynamic>中的一个项
            dynamic.setMoneySource(lms.get(i));
            dynamicRsp.getLp().add(i,dynamic);
        }
        /*可选，用List<UserIDgoodguy>查出捐钱人的头像，因为微信支付的时候可能无法填入用户的头像地址*/
        //for 循环，用List<moneySourceID>，获取每一个"支持了"对应的评论列表list<MoneysrcPinglun>，放入dynamicRsp中的lp中的lmp，与里面的moneySource对应
        for (int i=0;i<moneySourceIDs.size();i++){
            Long moneySourceID=moneySourceIDs.get(i); //一个"支持了" 项的id
            //用moneySourceID 查询数据表moneySrcPingLun，获得一个列表,假设获得该列表是moneySrcPingLunList
            List<MoneysrcPinglun> moneySrcPingLunList=new ArrayList<MoneysrcPinglun>(); //在循环中查找数据库是很慢的，这个怎么优化呢
            
            Dynamic dynamic=new Dynamic(); //构造DynamicRsp 中的数据域List<Dynamic>中的一个项
            dynamic.setLmp(moneySrcPingLunList); //将"支持了"对应的项加入。(要求在增加一条"支持了"数据项时，必须至少有默认的"评论")
            //这个顺序是默认对应的，如果不是，就循环测试
            dynamicRsp.getLp().add(i,dynamic);
        }

        dynamicRsp.setMsg("获取数据成功");
        dynamicRsp.setRetcode(2000); //返回码
        return dynamicRsp;
    }

}
