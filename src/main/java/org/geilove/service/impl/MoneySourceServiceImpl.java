package org.geilove.service.impl;

import java.util.*;

import javax.annotation.Resource;

import org.apache.commons.collections.map.HashedMap;
import org.geilove.dao.DoubleFansMapper;
import org.geilove.dao.MoneySourceMapper;
import org.geilove.dao.MoneysrcPinglunMapper;
import org.geilove.dao.UserMapper;
import org.geilove.pojo.*;
import org.geilove.requestParam.BackUpParam;
import org.geilove.response.CommonRsp;
import org.geilove.service.CashService;
import org.geilove.service.MainService;
import org.geilove.service.MoneySourceService;
import org.geilove.sqlpojo.PartHelpPojo;
import org.geilove.sqlpojo.PartWatchPojo;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service("moneysourceservice")
public class MoneySourceServiceImpl implements MoneySourceService {
	 @Resource
	 private MoneySourceMapper moneySourceMapper;
	 @Resource
     private UserMapper userMapper;
	 @Resource
	 private MoneysrcPinglunMapper moneysrcPinglunMapper;
	 @Resource
	 private HelpInfoServiceImpl helpInfoService;
	 @Resource
	 private CashService cashService;
	 @Resource
	 private DoubleFansMapper  doubleFansMapper;
	 @Resource
	 private MainService mainService;

//	 //获取我帮助的人的
//	 public   List<PartHelpPojo> getIhelpMen( Map<String,Object> map){
//		 List<PartHelpPojo> php=moneySourceMapper.selectIhelp(map);
//		 return php;
//	 }
//	 //获取帮助我的人
//     public   List<PartHelpPojo> getHelpMeMen( Map<String,Object> map){
//    	 List<PartHelpPojo> php=moneySourceMapper.selectMenHelpMe(map);
//		 return php;
//     }
    //如果微信通知成功了，就执行该服务中的这个方法，如果失败了就执行另一个服务删除moneySource中存在的数据，成功还是失败在Controller里面判定
	public  void  wxNotify(MoneySource  moneySource){//moneySource 中的部分数据由微信通知传递过来，更早的数据来自于预付订单生成时插入

		 String attach=moneySource.getAttach(); //cashUUID+userGoodGuyUUID
		 String cashUUID=attach.substring(0,35);
		 String userGoodGuyUUID=attach.substring(36);
		 String moneySourceUUID=moneySource.getOutTradeNo(); //moneysourceuuid
         String openid=moneySource.getOpenid();
         //捐助人的uuid
		 User user=null;
		 try{
			 //通过moneySouce中的openid查询user表，看用户是否关联了邮箱，
			 user=userMapper.selectByUserOpenid(openid); //待实现，帮助人user
		 }catch (Exception e){
		 	   //这个怎么处理好呢，这里只是查询下是不是存在的，如果抛出了异常？概率很小！
		 }

		 if(user!=null  ){ //关联了邮箱，执行复杂的更新，能获取到用户的uuid
			 //  1.更新 补全moneySource表
			 String  userDonateNickName=user.getUsernickname();// 获取捐钱人的昵称
			 String  userDonatePhotoUrl=user.getUserphoto(); //获取用户的头像地址
			 moneySource.setHelpmanusername(userDonateNickName);
			 moneySource.setHelpmanphotourl(userDonatePhotoUrl);
			 moneySource.setMoneysourceuuid(moneySourceUUID);
			 int updateTag=moneySourceMapper.updateByPrimaryKeySelective(moneySource); //应该是插入


			 // 2.更新cash表，受助人相关信息要更新
			 Cash  cash=cashService.getCashRecordByUUID(cashUUID);
			 Integer  realCash=cash.getRealcash();
			 realCash=realCash+moneySource.getMoneynum();
			 String  userUUIDBehelped=cash.getBeHelpUserUUID(); //受助用户的用户的uuid
			 int cashUpdateTag=cashService.updateCash(cash);

			 // 3.更新受助人接受的总钱数等表helpInfo表
			 HelpInfo helpInfo= helpInfoService.selectRecord(userUUIDBehelped);

			 if (helpInfo!=null){
				 Integer money=0;
				 if (helpInfo.getCashtwouuid()!=null  ){//达到发布2次求助信息
					 money=helpInfo.getAccept2moneymount();
					 money=money+moneySource.getMoneynum();
					 helpInfo.setAccept2moneymount(money);
				 }
				 if (helpInfo.getCashoneuuid()!=null ){
					money=helpInfo.getAcceptfirstmoneymount();
					money=money+moneySource.getMoneynum();
					helpInfo.setAcceptfirstmoneymount(money);
				 }
				 //执行更新helpInfo表
				 int helpInfoInsertTag=helpInfoService.updateRecord(helpInfo);
			 }

			 // 4.更新受助人的User表，因为粉丝增加了。
			 User beHelpUser=userMapper.getUserByUUID(userUUIDBehelped); //这个是受助人user
			 Integer beHelpUserFollow=user.getFollowcount(); //收听人的数量
			 beHelpUserFollow=beHelpUserFollow+1;
			 Integer acceptMoney=user.getAcceptmoney(); //用户获得捐钱的总数
			 acceptMoney=acceptMoney+moneySource.getMoneynum();
			 Integer amountAccept=user.getAmountaccept();
			 amountAccept=amountAccept+1;
			 user.setFollowcount(beHelpUserFollow);
			 user.setAcceptmoney(acceptMoney);
			 user.setAmountaccept(amountAccept);
			 int beHelpUserUpdateTag=userMapper.updateByPrimaryKeySelective(user); //执行更行

			 // 5.更新DoubleFans表，受助人成为帮助人的粉丝，

			 Long goodGuyID=user.getUserid();
			 Long beHelpUserID=beHelpUser.getUserid();
			 DoubleFans doubleFans=new DoubleFans();
			 doubleFans.setUseridbefocus(goodGuyID);
			 doubleFans.setUseridfollowe(beHelpUserID);
			 doubleFans.setPaytag((byte) 2);  //不能取消关注
			 Map<String,Object> map= new HashMap<String,Object>();
			 map.put("taUserid",beHelpUserID);
			 map.put("myUserid",goodGuyID);
			 //查询下有没有已经关注，
			 PartWatchPojo partWatchPojo= doubleFansMapper.watchOrNot(map);
             if (partWatchPojo==null){
             	//执行插入
				 int  doubleFansTag=doubleFansMapper.insert(doubleFans);
			 }else {
             	//执行更行
				 int updateDoubleFansTag=doubleFansMapper.updateByPrimaryKeySelective(doubleFans);
			 }
			 // 6.更新Tweet表，捐款人发布一条推文
			 Tweet tweet=new Tweet();
			 tweet.setBackupneight(UUID.randomUUID().toString()); //tweet 的uuid
			 //tweet.setCashuuid(cashUUID); //cash 的uuid
			 tweet.setUseridtweet(beHelpUser.getUserid()); //发布推文的userid
			 tweet.setSourcemsgid(new Long(1));//1代表非转发
			 //tweet.setBackuptwelve(moneyTitle); //筹款标题
			 tweet.setMsgcontent("感谢先生的捐助，祝福"); //放入推文内容到tweet中
			 tweet.setTagid((byte)1 );
			 tweet.setTopic(new Long(1));
			 tweet.setBoxtimes(0);
			 tweet.setCommenttimes(0);
			 tweet.setOk(0);
			 tweet.setPublishtime(new Date());
			 tweet.setReportedtimes(0);
			 tweet.setPublicsee((byte)1); //1代表可见
			 tweet.setDeletetag((byte)1); //1代表未删除
			 // tweet.setVideoaddress(null); //推文只限制3张图
			 tweet.setTweetbackupsix(0); //默认承诺0，代表承诺A
			 tweet.setBackupnine(beHelpUser.getUsernickname()); //用户的昵称
			 //tweet.setBackupten(selfIntroduce); //用户的自我介绍
			 tweet.setBackupeleven(beHelpUser.getUserphoto()); //用户的头像地址
			 tweet.setCityname("中国"); //用户所在的城市
			 tweet.setTweetbackupfour(1); //备用4等于1代表是一个普通的推文2代表的是救助
			 Integer tag=mainService.addTweet(tweet);

			 // 7.更新HelpInfo表，捐款人信息更新,
			 HelpInfo  helpInfoFgoodGuy=helpInfoService.selectRecord(userGoodGuyUUID); //
			 if (helpInfoFgoodGuy!=null){
				 Integer donateMount=0;
				 Integer helTimes=0;
				 donateMount=moneySource.getMoneynum()+helpInfoFgoodGuy.getDonatemount(); //捐钱总数
				 helTimes=helpInfoFgoodGuy.getHelptimes()+1; //帮助次数+1
				 //执行更新helpInfo表
				 int helpInfoInsertTag=helpInfoService.updateRecord(helpInfoFgoodGuy);
			 }// 按道理是必须存在的，注册的时候就创建了一条记录

			 // 8.更新User表， 捐款人信息更新
			 User helpUser=userMapper.selectByUserEmail(userGoodGuyUUID); //待实现
			 if (helpUser!=null){
			 	 //粉丝，捐钱数等增加
				 int  fansCount=helpUser.getFanscount()+1;  //粉丝总数+1
				 int  moneyDonate=helpUser.getUserdonate()+moneySource.getMoneynum(); //捐钱总数
				 int  helpTimes=helpUser.getUserhelpsman()+1;
				 int  helpUserUpdateTag=userMapper.updateByPrimaryKey(helpUser); //

			 }

		 }else {//没有关联邮箱，执行简单的数据更新  openidUser表和Cash表moneySource表(统一的昵称，头像，userUUID)
		 	try{
		 		// 1.对openidUser表进行更新或插入，为了以后关联，待生成表


				// 2.Cash 表更新，为了让用户看到钱数增加
				Cash  cash=cashService.getCashRecordByUUID(cashUUID);
				Integer  realCash=cash.getRealcash();
				realCash=realCash+moneySource.getMoneynum();
				String  userUUIDBehelped=cash.getBeHelpUserUUID(); //受助用户的用户的uuid
				int cashUpdateTag=cashService.updateCash(cash);

				// 3.moneySource 表更新,为了显示在动态列表上
				String  userNickName="互助管家人";
				String  userPhoto=""; //默认头像地址
				moneySource.setHelpmanusername(userNickName);
				moneySource.setHelpmanphotourl(userPhoto);
				moneySource.setMoneysourceuuid(moneySourceUUID);
				int updateTag=moneySourceMapper.updateByPrimaryKeySelective(moneySource);  //应该是插入

			}catch (Exception e){

			}
		 }
	}


	//获取"支持了"列表
	public  List<MoneySource> getGuyHelpMe(Map<String,Object>  map){
     	List<MoneySource>  lms=moneySourceMapper.getMoneySourceList(map);
		return  lms;
	}

	//获取完整的，"支持了" 列表
	public  List<MoneySource> getGuyHelpMeByCashUUID(Map<String,Object>  map){
		List<MoneySource>  lms=moneySourceMapper.getMoneySourceListByCashUUID(map);
		return  lms;
	} //获取帮助我的人

	//对"支持了" 进行评论
	public  CommonRsp addOneComment(MoneysrcPinglun  moneysrcPinglun){
		   CommonRsp  commonRsp=new CommonRsp();
		   int returnTag=moneysrcPinglunMapper.insertSelective(moneysrcPinglun); //
		   if (returnTag==1){
			   commonRsp.setMsg("插入成功");
			   commonRsp.setRetcode(2000);
		   }else{
		   		commonRsp.setMsg("插入失败");
		   		commonRsp.setRetcode(2001);
		   }
		   return  commonRsp;
	}

   //删除了"支持了"的评论
   public  CommonRsp deleteOneComment(Long  moneySrcPingluniD ){
		CommonRsp  commonRsp=new CommonRsp();
        int deleteTag= moneysrcPinglunMapper.deleteByPrimaryKey(moneySrcPingluniD);
        if (deleteTag==1){
        	commonRsp.setMsg("删除成功");
        	commonRsp.setRetcode(2000);
		}else {
        	commonRsp.setMsg("删除失败");
        	commonRsp.setRetcode(2001);
		}
	   return  commonRsp;
   }

	//根据MoneySourceID 获取其对应的评论
	public  List<MoneysrcPinglun> getMoneySourcePingLuns(Map<String,Object>  map){
		List<MoneysrcPinglun> lmp=moneysrcPinglunMapper.getMoneySourcePingLunList(map);
		return  lmp;
	}

}
