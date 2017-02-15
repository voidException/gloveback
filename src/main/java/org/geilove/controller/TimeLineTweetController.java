package org.geilove.controller;

/**
 * Created by mfhj-dz-001-424 on 17/1/22.
 *  分享到微信圈所需要的主要的数据
 */
import javax.annotation.Resource;

import org.geilove.pojo.Cash;
import org.geilove.pojo.Confirm;
import org.geilove.pojo.Tweet;
import org.geilove.pojo.User;
import org.geilove.response.TimeLineRsp;
import org.geilove.service.*;
import org.geilove.vo.TimeLine;
import org.geilove.vo.UserPartProfile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@Controller
@RequestMapping("/timelinetweet")
public class TimeLineTweetController {
    @Resource
    private MainService mainService;
    @Resource
    private CashService cashService;
    @Resource
    private HelpService helpService;
    @Resource
    private ConfirmService confirmService;

   // @RequestMapping("/info/{useriD}/{tweetiD}/{cashiD}") //用这个也行
    @RequestMapping(value = "/info/{tweetiD}",method = RequestMethod.GET)
    public @ResponseBody  Object getInfoByTweetID(@PathVariable("tweetiD") Long tweetid){
          //通过推文的id查出这个人的id
        TimeLineRsp timeLineRsp=new TimeLineRsp();
        TimeLine timeLine=new TimeLine();
        if (tweetid==null ||tweetid==0){
            timeLineRsp.setLp(null);
            timeLineRsp.setMsg("请求参数为空");
            timeLineRsp.setRetcode(2001);
            return  timeLineRsp;
        }
        Tweet tweet=null;
        try{
            tweet =mainService.getTweetByID(tweetid); //1.获取推文
        }catch (Exception e){
            timeLineRsp.setLp(null);
            timeLineRsp.setMsg("获取相应的推文出错");
            timeLineRsp.setRetcode(2001);
            return  timeLineRsp;
        }
        Long  userIDTweet=tweet.getUseridtweet();   //2.获取推文的作者
        Long cashid=tweet.getTweetbackupfive();    //3.获取推文中的cashid
        //根据推文的作者id获取作者的昵称，头像等
        User user=null;
        try{
            user=helpService.getUserPartProfileByID(userIDTweet);
        }catch (Exception  e){
            timeLineRsp.setLp(null);
            timeLineRsp.setMsg("获取相应用户信息出错");
            timeLineRsp.setRetcode(2001);
            return  timeLineRsp;
        }

        //根据cashid获取 有关资金的情况和证实人的数量
        Cash  cash=null;
        try{
            cash=cashService.getCashRecord(cashid); //获取救助推文对应的cash
        }catch (Exception  e){
            timeLineRsp.setLp(null);
            timeLineRsp.setMsg("获取相应的");
            timeLineRsp.setRetcode(2001);
            return  timeLineRsp;
        }
        //confirmList
        List<Confirm> confirmList=new ArrayList<Confirm>();
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("id",tweetid);
        map.put("tag", 1);
        map.put("page", 0);
        map.put("pageSize", 8);
        try{
            confirmList=confirmService.getConfirmLists(map);
        }catch (Exception e){
            confirmList=null;
        }


        UserPartProfile userPartProfile=new UserPartProfile();
        userPartProfile.setUserid(user.getUserid());
        userPartProfile.setNickName(user.getUsernickname());
        userPartProfile.setUserPhotoUrl(user.getUserphoto());

        timeLine.setUserPartProfile(userPartProfile);
        timeLine.setTweet(tweet);
        timeLine.setCash(cash);
        timeLine.setConfirmList(confirmList);

        timeLineRsp.setLp(timeLine);
        timeLineRsp.setRetcode(2000);
        timeLineRsp.setMsg("朋友圈分享页面数据成功获取");

        return timeLineRsp;
    }


}
