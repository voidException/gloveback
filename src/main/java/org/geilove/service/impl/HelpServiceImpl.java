package org.geilove.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.geilove.dao.MoneySourceMapper;
import org.geilove.dao.UserMapper;
import org.geilove.service.HelpService;
import org.geilove.sqlpojo.OtherPartHelpPojo;
import org.geilove.sqlpojo.PartHelpPojo;
import org.springframework.stereotype.Service;
@Service("helpservice")
public class HelpServiceImpl implements HelpService{
	
	@Resource
	MoneySourceMapper moneySourceMapper;
	@Resource
	private UserMapper userMapper;
	public List<PartHelpPojo> getPartHelpList(Map<String,Object> map){
		List<PartHelpPojo> lp=new ArrayList<PartHelpPojo>();
		lp=moneySourceMapper.selectMenHelpMe(map);   //这个错了，需要改正
		return lp;
	}
	
	public List<PartHelpPojo> getGuyIHelp(Map<String,Object> map){
		List<PartHelpPojo> lp=new ArrayList<PartHelpPojo>();
		//我帮助的人
		Object loadMoreTag=map.get("loadMoreTag");// 1代表刷新 2代表加载更多
		if(loadMoreTag.equals(1)){
			lp=moneySourceMapper.selectIhelp(map); 
		}else{
			lp=moneySourceMapper.selectIhelploadMore(map); 
		}		
		return lp;
	}
	public List<PartHelpPojo> getGodHelpMe(Map<String,Object> map){
		List<PartHelpPojo> lp=new ArrayList<PartHelpPojo>();
		//帮助我的人
		Object loadMoreTag=map.get("loadMoreTag");// 1代表刷新 2代表加载更多
		if(loadMoreTag.equals(1)){
			lp=moneySourceMapper.selectMenHelpMe(map); 
		}else{
			lp=moneySourceMapper.selectMenHelpMeloadMore(map); 
		}
		
		return lp;
	}
	//给定一组联系人id，获取头像，昵称，简介，头像是否上传
	public List<OtherPartHelpPojo> getOtherPartHelpList(List<Long> lst){
		List<OtherPartHelpPojo> lp=new ArrayList<OtherPartHelpPojo>();
		lp=userMapper.selectUserPartProfile(lst);
		return lp;
	}
	
}
