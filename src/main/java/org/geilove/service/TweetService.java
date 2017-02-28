package org.geilove.service;

import org.geilove.pojo.Tweet;

import java.util.List;
import java.util.Map;

/**
 * Created by mfhj-dz-001-424 on 17/2/28.
 */
public interface TweetService {

    public List<Tweet> getTweetListbyUseriDs(Map<String,Object> maps); //根据一组userID和最晚发布时间，获取一组推文

    public List<Tweet> getTweetByOtherUserID(Map<String,Object> maps);

    public List<Tweet> getMyselfPublishedTweetByMyiD(Map<String,Object> maps);

}
