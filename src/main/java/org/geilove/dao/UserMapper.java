package org.geilove.dao;

import java.util.List;
import  java.util.Map;
import org.geilove.pojo.User;
import org.geilove.sqlpojo.PeopleNeedLovePojo;
import org.geilove.sqlpojo.LoveClubListPojo;
import org.geilove.sqlpojo.DonaterPojo;
import org.geilove.sqlpojo.OtherPartHelpPojo;

public interface UserMapper {
    int deleteByPrimaryKey(Long userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userid);
    
    User selectByUserEmail(String useremail);//自定义，根据用户的邮箱进行验证
    
    User selectByUserNickName(String usernickname); //这个形参要与xml中的参数保持一致

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> selectMenList(Map<String,Object> map); //查询监督处爱心社等列表
    
    List<User>  getPayOrWatchPeople(List<Long> ll); //查询关注我 我关注的人列表,查询我帮助或者帮助我的人列表
    
    List<User> selectDonaterPeople(Map<String,Object> map); // 查询我帮助或者帮助我的人列表,没使用
   
    List<OtherPartHelpPojo> selectUserPartProfile(List<Long> lst); //给定一组id，获取用户的头像昵称简介
    
    String selectMD5Password(Long userid);
    
    String findPasswd(String userEmail);
    
    List<User> selectMenListLoadMore(Map<String,Object> map); //爱心社等列表，刷新
    
    List<User>  gongyiMenList(Map<String,Object> map); //公益排行榜
    List<User>  gongyiMenListloadMore(Map<String,Object> map);//公益排行榜，loadMore
}




