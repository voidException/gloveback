package org.geilove.controller;

/**
 * 通过这个，获取"支持了"列表，及其对应的评论，对支持了进行评论
 */
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.geilove.pojo.*;
import org.geilove.requestParam.MoneySourceParam;
import org.geilove.response.Dynamic;
import org.geilove.response.DynamicRsp;
import org.geilove.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

     @RequestMapping(value="/backup",method=RequestMethod.POST)
     @ResponseBody
     public Object getMoneySourceList(@RequestBody MoneySourceParam moneySourceParam,HttpServletRequest request ){
         //根据受助人的ID获取这个人的"支持了"列表
        DynamicRsp dynamicRsp=new DynamicRsp(); //最终要返回的
        List<Dynamic> listDynamic=new ArrayList<>(); //DynamicRsp 的一个数据域，"支持了及其评论回复" 的列表

        Map<String,Object>  map=new HashMap<>();
        if (moneySourceParam==null){
            dynamicRsp.setRetcode(2001);
            dynamicRsp.setMsg("参数为空");
            dynamicRsp.setLp(null);
            return  dynamicRsp;
        }
        Integer page= moneySourceParam.getPage();
        Integer pageSize=moneySourceParam.getPageSize();
        String  timeStamp=moneySourceParam.getTimeStamp();
        Long  userBeHelpID=moneySourceParam.getUserIDBehelped();

        map.put("userBeHelpID",userBeHelpID);
        map.put("page",page);
        map.put("pageSize",pageSize);
        map.put("lastTime",timeStamp);
        List<MoneySource>  lms; //单纯的"支持了"列表

        try{
            lms=moneySourceService.getGuyHelpMe(map);
            if (lms==null){
                dynamicRsp.setRetcode(2001);
                dynamicRsp.setMsg("没有支持哦");
                dynamicRsp.setLp(null);
                return  dynamicRsp;
            }
        }catch (Exception e){
            dynamicRsp.setRetcode(2001);
            dynamicRsp.setMsg("查询支持者列表出现问题");
            dynamicRsp.setLp(null);
            return  dynamicRsp;
        }

        //for 循环，将每一个lms列表项，放入到listDynamic中,同时取出来每一个列表项的moneySourceID，组成List<moneySourceID>和List<UserIDgoodguy>
        List<Long> moneySourceIDs=new ArrayList<>();
        List<Dynamic> dynamics=new ArrayList<>();

        for(int i=0;i<lms.size();i++){
            moneySourceIDs.add(lms.get(i).getMoneysourceid());
            Dynamic dynamic=new Dynamic(); //构造DynamicRsp 中的数据域List<Dynamic>中的一个项
            dynamic.setMoneySource(lms.get(i));
            dynamics.add(dynamic);
        }
         dynamicRsp.setLp(dynamics);
        /*可选，用List<UserIDgoodguy>查出捐钱人的头像，因为微信支付的时候可能无法填入用户的头像地址*/

        //for 循环，用List<moneySourceID>，获取每一个"支持了"对应的评论列表list<MoneysrcPinglun>，放入dynamicRsp中的lp中的lmp，与里面的moneySource对应
        for (int i=0;i<moneySourceIDs.size();i++){ //能走到这一步，说明必然有数据
            Long moneySourceID=moneySourceIDs.get(i); //一个"支持了" 项的id
            //用moneySourceID 查询数据表moneySrcPingLun，获得一个列表,假设获得该列表是moneySrcPingLunList
            List<MoneysrcPinglun> moneySrcPingLunList=new ArrayList<>();
            Map<String,Object> map2=new HashMap<>();
            map2.put("moneySourceID",moneySourceID); //"支持了" 的ID
            map2.put("page",0);
            map2.put("pageSize",20);
            try {
                moneySrcPingLunList=moneySourceService.getMoneySourcePingLuns(map2); //会执行moneySourceIDs.size() 次，性能是个问题
            }catch (Exception e){
                moneySrcPingLunList=null;
            }

            /*
            Dynamic dynamic; //构造DynamicRsp 中的数据域List<Dynamic>中的一个项
            dynamic=dynamics.get(i); //这个需要测试是不是能对应好
            dynamic.setLmp(moneySrcPingLunList); //将"支持了"对应的项加入。(要求在增加一条"支持了"数据项时，必须至少有默认的"评论")
            */

            dynamics.get(i).setLmp(moneySrcPingLunList); //如果这个不可以就用上面注释掉的，未测试
        }

        dynamicRsp.setMsg("获取数据成功");
        dynamicRsp.setRetcode(2000); //返回码
        return dynamicRsp;
    }

}
