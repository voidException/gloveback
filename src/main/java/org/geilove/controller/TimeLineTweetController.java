package org.geilove.controller;

/**
 * Created by mfhj-dz-001-424 on 17/1/22.
 *
 */
import javax.annotation.Resource;

import org.geilove.pojo.Cash;
import org.geilove.pojo.Tweet;
import org.geilove.pojo.User;
import org.geilove.service.CashService;
import org.geilove.service.HelpService;
import org.geilove.service.MainService;
import org.geilove.service.WatchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @RequestMapping("/info/{tweetid}")
    public @ResponseBody  Object getInfoByTweetID(@PathVariable("tweetid") Long tweetid){
          //通过推文的id查出这个人的id
        Tweet tweet =mainService.getTweetByID(tweetid); //1.获取推文
        Long  userIDTweet=tweet.getUseridtweet();   //2.获取推文的作者
        Integer cashid=tweet.getTweetbackupfive();    //3.获取推文中的cashid
        //根据推文的作者id获取作者的昵称，头像等
        User user=helpService.getUserPartProfileByID(userIDTweet);
        //根据cashid获取 有关资金的情况和证实人的数量
        Cash  cash=null;
        cash=cash=cashService.getCashRecord(new Long(cashid)); //获取救助推文对应的cash
        //构造一个对象，然后返回
        return 0;
    }


}
