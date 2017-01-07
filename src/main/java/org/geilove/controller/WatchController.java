package org.geilove.controller;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.apache.commons.collections.map.HashedMap;
import org.geilove.requestParam.QueryWatchIfParam;
import org.geilove.response.QueryIfWatchRsp;
import org.geilove.sqlpojo.PartHelpPojo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.geilove.vo.FollowVo;
import org.geilove.pojo.DoubleFans;
import org.geilove.requestParam.CancelWatchParam;
import org.geilove.requestParam.FollowParam;
import org.geilove.requestParam.WatchListParam;
import org.geilove.response.CommonRsp;
import org.geilove.response.WatchListRsp;
import org.geilove.service.RegisterLoginService;
import org.geilove.service.RelationService;
import org.geilove.service.WatchService;
/*
 * 这个用来提供关注一个人，取消关注一个人的接口,我的粉丝列表，我关注的列表功能
*/
@Controller
@RequestMapping("/watch")
public class WatchController {
	@Resource
	private WatchService watchService;	
	@Resource
	private RegisterLoginService rlService;
	@Resource
	private RelationService relationService;
	
	@RequestMapping(value="/dowatch",method=RequestMethod.POST)
	public @ResponseBody CommonRsp doWatch(@RequestBody FollowParam followParam ){//关注一个人
		CommonRsp commonRsp=new CommonRsp();
		String token=followParam.getToken(); //获取登录凭证
		String userPassword=token.substring(0,32); //token是password和userID拼接成的。
		String useridStr=token.substring(32);		
		Long userid=Long.valueOf(useridStr).longValue();		
		String passwdTrue=rlService.selectMD5Password(Long.valueOf(userid));
		
		if(!userPassword.equals(passwdTrue)){
			commonRsp.setRetcode(2001);
			commonRsp.setMsg("用户验证失败，非法");
			return commonRsp;
		}		
		DoubleFans  dbfans=new DoubleFans();
		dbfans.setUseridfollowe(followParam.getUserIDFollow());
	    dbfans.setUseridbefocus(followParam.getUserIDBeFocus());
	    dbfans.setPaytag(followParam.getPaytag());
	    Date date=new Date();
	    dbfans.setPaydate(date);
	    dbfans.setGroupid((byte)1);
	    dbfans.setSpecialfollow((byte)1);
	    dbfans.setDoublefans((byte)1);
	    try{ //应该先查询下是否有关注

	    	 Integer tag=watchService.doWatch(dbfans);
	    	 if(tag!=1){
	    		 commonRsp.setMsg("关注失败");
	    		 commonRsp.setRetcode(2001);
	    	 }
	    }catch(Exception e){
			commonRsp.setMsg("关注失败");
			commonRsp.setRetcode(2001);
			return commonRsp;
	    }	
	    commonRsp.setMsg("关注成功");
	    commonRsp.setRetcode(2000);
		return commonRsp;
	}
    //关注人列表在peoplelistcontroller中
    //取消关注一个人
	@RequestMapping(value="/cancelwatch",method=RequestMethod.POST)
	public @ResponseBody CommonRsp getWatchList(@RequestBody CancelWatchParam cancelParam ){ 
		CommonRsp commonRsp=new CommonRsp();
		String token=cancelParam.getToken();
		String userPassword=token.substring(0,32); //token是password和userID拼接成的。
		String useridStr=token.substring(32);		
		Long userid=Long.valueOf(useridStr).longValue();		
		String passwdTrue=rlService.selectMD5Password(Long.valueOf(userid));
		//System.out.println("lll");
		if(!userPassword.equals(passwdTrue)){
			commonRsp.setRetcode(2001);
			commonRsp.setMsg("用户验证失败，非法");
			return commonRsp;
		}
		Long canceluserid=cancelParam.getBeCancel();
		//查询关注 和被关注列表
		//根据这组数据，选出id列表
		//用id列表到user表查询数据
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("userIDFollowe", userid);
		map.put("userIDBeFocus",canceluserid); //
		try{

			Integer returnTag=relationService.unWatchManService(map);
			//System.out.println(returnTag);
		}catch(Exception e){
			commonRsp.setMsg("取消关注失败");
			commonRsp.setRetcode(2001);
			return commonRsp;
		}
		commonRsp.setMsg("取消关注成功");
		commonRsp.setRetcode(2000);
		return commonRsp;
	}
	 //这里是查询是否关注一个人，传入两个人的userid，进行查询。
	@RequestMapping(value="/querywatchif",method=RequestMethod.POST)
	public @ResponseBody QueryIfWatchRsp queryWatchIf(@RequestBody QueryWatchIfParam param ){
		QueryIfWatchRsp commonRsp=new QueryIfWatchRsp();
		Long taUserid=param.getTaUserid();
		Long myUserid=param.getMyUserid();
		if (taUserid.equals(null) || myUserid.equals(null)){
			commonRsp.setMsg("参数为空");
			commonRsp.setRetcode(2001);
			commonRsp.setTag(5);
		}
		Map<String,Object> map= new HashMap<String,Object>();;
		map.put("taUserid",taUserid);
		map.put("myUserid",myUserid);

		Integer watchTag=0;
		//写service
		try{
			watchTag=watchService.watchMayNot(map);
//			List<PartHelpPojo> lp=watchService.getWatchList(map);
//			System.out.print(lp);

		}catch (Exception e){
			System.out.print(e);
			System.out.print('\n');

			commonRsp.setMsg("出错了");
			commonRsp.setRetcode(2001);
			commonRsp.setTag(5);
			return  commonRsp;
		}
		if (watchTag==2){
			commonRsp.setMsg("成功");
			commonRsp.setRetcode(2000); //返回这个代表成功
			commonRsp.setTag(2);
			return  commonRsp;
		}else { //watchTag==1  //没有关注，但这次查询是成功的
			commonRsp.setMsg("成功");
			commonRsp.setRetcode(2000); //返回这个代表成功
			commonRsp.setTag(1);  //代表未关注
			return  commonRsp;
		}

	}


}














