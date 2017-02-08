package org.geilove.controller;

/**
 * 通过这个，获取"支持了"列表，及其对应的评论，对支持了进行评论
 */
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.geilove.pojo.*;
import org.geilove.requestParam.BackUpParam;
import org.geilove.requestParam.ItemProgressListParam;
import org.geilove.requestParam.MoneySourceParam;
import org.geilove.response.CommonRsp;
import org.geilove.response.Dynamic;
import org.geilove.response.DynamicRsp;
import org.geilove.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/moneysource")
public class MoneySourceController {
     @Resource
     private MoneySourceService moneySourceService;

     @RequestMapping(value="/backuplist",method=RequestMethod.POST)
     @ResponseBody
     public Object getMoneySourceList(@RequestBody MoneySourceParam moneySourceParam,HttpServletRequest request ){
         //根据受助人的ID获取这个人的"支持了"列表
        DynamicRsp dynamicRsp=new DynamicRsp(); //最终要返回的
        List<Dynamic> listDynamic=new ArrayList<Dynamic>(); //DynamicRsp 的一个数据域，"支持了及其评论回复" 的列表

        Map<String,Object>  map=new HashMap<String,Object>();
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
        List<Long> moneySourceIDs=new ArrayList<Long>();
        List<Dynamic> dynamics=new ArrayList<Dynamic>();

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
            Map<String,Object> map2=new HashMap<String,Object>();
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
    /*增加一条"支持了"*/
    //这个应该是在微信支付时调用，需要更改moneysource表User表和HelpInfo表,这个较为复杂
    @RequestMapping(value="/backup",method=RequestMethod.POST)
    @ResponseBody
    public Object addBackUp(@RequestBody BackUpParam backUpParam, HttpServletRequest request ){
        //这里需要对微信开发了解后，进行相应的验证
        CommonRsp commonRsp=new CommonRsp();
        if (backUpParam==null){
            commonRsp.setMsg("请求参数为空");
            commonRsp.setRetcode(2001);
            return  commonRsp;
        }
        //确保所有的字段不能为空，应先验证，先略过
        //调用Service，执行插入

        return 0;
    }
    /*对"支持了" 进行评论*/
    @RequestMapping(value="/addmoneysourcecomment",method=RequestMethod.POST)
    @ResponseBody
    public Object addMoneySourceComment(@RequestBody BackUpParam  updateListParam, HttpServletRequest request ){


        return 0;
    }

    /* 对评论进行删除 */
    @RequestMapping(value="/deletemoneysourcecomment",method=RequestMethod.POST)
    @ResponseBody
    public Object deleteMoneySourceComment(@RequestBody BackUpParam  updateListParam, HttpServletRequest request ){


        return 0;
    }
}
