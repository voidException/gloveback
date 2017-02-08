package org.geilove.service.impl;

import java.util.*;

import javax.annotation.Resource;

import org.apache.commons.collections.map.HashedMap;
import org.geilove.dao.MoneySourceMapper;
import org.geilove.dao.MoneysrcPinglunMapper;
import org.geilove.pojo.MoneySource;
import org.geilove.pojo.MoneysrcPinglun;
import org.geilove.pojo.User;
import org.geilove.requestParam.BackUpParam;
import org.geilove.response.CommonRsp;
import org.geilove.service.MoneySourceService;
import org.geilove.sqlpojo.PartHelpPojo;
import org.springframework.stereotype.Service;
@Service("moneysourceservice")
public class MoneySourceServiceImpl implements MoneySourceService {
	 @Resource
	 private MoneySourceMapper moneySourceMapper;

	@Resource
	private MoneysrcPinglunMapper moneysrcPinglunMapper;
	 //获取我帮助的人的
	 public   List<PartHelpPojo> getIhelpMen( Map<String,Object> map){
		 List<PartHelpPojo> php=moneySourceMapper.selectIhelp(map);
		 return php;
	 }
	 //获取帮助我的人
     public   List<PartHelpPojo> getHelpMeMen( Map<String,Object> map){
    	 List<PartHelpPojo> php=moneySourceMapper.selectMenHelpMe(map);
		 return php;
     }
     //获取"支持了"列表
	public  List<MoneySource> getGuyHelpMe(Map<String,Object>  map){
     	List<MoneySource>  lms=moneySourceMapper.getMoneySourceList(map);
		return  lms;
	}

	//根据MoneySourceID 获取其对应的评论
	public  List<MoneysrcPinglun> getMoneySourcePingLuns(Map<String,Object>  map){
		List<MoneysrcPinglun> lmp=moneysrcPinglunMapper.getMoneySourcePingLunList(map);
		return  lmp;
	}

	//要使用事务，还挺复杂的来
	public CommonRsp addBackupService(BackUpParam backUpParam){
		CommonRsp commonRsp=new CommonRsp();

		MoneySource moneySource=new MoneySource();
		moneySource.setUseridbehelped(backUpParam.getUseridbehelped());
		moneySource.setUseridgoodguy(backUpParam.getUseridgoodguy());
		moneySource.setMoneynum(backUpParam.getMoneynum());
		moneySource.setHelptime(new Date());
		moneySource.setBackupone(backUpParam.getGoodGuyNickName());
		moneySource.setBackuptwo(backUpParam.getGoodGuyPhotoUrl());
		moneySource.setBackupthree(backUpParam.getGoodGuyUUID());
		moneySource.setBackupfour(backUpParam.getBeHelpedUUID());

		//1.先增加新记录到moneySource表
		Integer moneySourceTag=moneySourceMapper.insertSelective(moneySource); //执行插入
		//2.查询moneySource表，用UserIDBehelped和UserIDgoodguy还有cashiD，以确定用户是不是对本次救助重复捐款，影响多个表
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("userIDBehelped",backUpParam.getBeHelpedUUID());
		map.put("userIDgoodguy",backUpParam.getUseridgoodguy());
		//map.put("cashiD",backUpParam.getCashiD());  //需要更改表
		List<PartHelpPojo> lpp=moneySourceMapper.selectCheckCount(map);
		//3.对受助人和帮助人的User表执行更新（拆分后的表也要更新），根据useridGoodguy
		if (lpp.size()>1){ //事实上，由于User表的结构，暂时一个人只能受助一次
			//说明重复对一个人的本次救助多次捐助
			//分别查询救助人和帮助人的User表，分别更新受助金额、获得帮助的人的数量和 捐款的钱数、帮助人的数量
			User user=new User();

			//分别更新对应的User表

		}


		//4.最后检查Cash表，如果资金大于给定资金或者日期超出，要设置相关数据域






		return commonRsp;
	}
}
