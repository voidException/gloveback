package org.geilove.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.geilove.dao.MoneySourceMapper;
import org.geilove.dao.MoneysrcPinglunMapper;
import org.geilove.pojo.MoneySource;
import org.geilove.pojo.MoneysrcPinglun;
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
	public  List<MoneySource> getGuyHelpMe(Map<String,Object>  map){
     	List<MoneySource>  lms=moneySourceMapper.getMoneySourceList(map); //
		return  lms;
	}

	//根据MoneySourceID 获取其对应的评论
	public  List<MoneysrcPinglun> getMoneySourcePingLuns(Map<String,Object>  map){
		List<MoneysrcPinglun> lmp=moneysrcPinglunMapper.getMoneySourcePingLunList(map);
		return  lmp;
	}
}
