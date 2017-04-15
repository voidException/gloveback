package org.geilove.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

//这个主要是为关于我们，常见问题，意见反馈等提供路由
@Controller
@RequestMapping(value="/path/pages")
public class JspPageController {

	@RequestMapping(value="/helpApp",method = RequestMethod.GET)
	public String helpApp(){
		String index="front/helpApp";
		return index;
	}
	@RequestMapping(value="/helpAixinshe",method = RequestMethod.GET)
	public String helpAixinshe(){
		String index="front/helpAixinshe";
		return index;
	}
	@RequestMapping(value="/aboutus",method = RequestMethod.GET)
	public String aboutus(){
		String index="front/aboutus";
		return index;
	}
	@RequestMapping(value="/faq",method = RequestMethod.GET)
	public String faq(){
		String index="front/faq";
		return index;
	}
	@RequestMapping(value="/feedback",method = RequestMethod.GET)
	public String feedback(){
		String index="front/feedback";
		return index;
	}
	@RequestMapping(value="/wechat",method = RequestMethod.GET)
	public String wechat(){
		String index="front/wechat";
		return index;
	}
	@RequestMapping(value="/shareTotimeline/{tweetiD}",method=RequestMethod.GET)
	public  String shareTimeline(@PathVariable("tweetiD") Long tweetiD, HttpServletRequest request){
		String index="front/wechat";
		return index;
	}

	@RequestMapping(value="/productDetail/{productuuid}",method=RequestMethod.GET)
	public  String goProductDetail(@PathVariable("productuuid") String productuuid, HttpServletRequest request){
		String index="front/productDetail";
		return index;
	}

	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String login(){
		String index="front/login";
		return index;
	}
	@RequestMapping(value="/loginRegister",method = RequestMethod.GET)
	public String loginPC(){
		String index="front/loginRegister";
		return index;
	}
	@RequestMapping(value="/publishItem",method = RequestMethod.GET)
	public String publishItem(){
		String index="front/publishItemPC";
		return index;
	}
	@RequestMapping(value="/helpCenter",method = RequestMethod.GET)
	public String goHelpCenter(){
		String index="front/helpCenterPC";
		return index;
	}

	@RequestMapping(value="/appDownload",method = RequestMethod.GET)
	public String appDownload(){
		String index="front/appDownload";
		return index;
	}
	@RequestMapping(value="/userProtocol",method = RequestMethod.GET)
	public String userProtocol(){
		String index="front/userProtocolPC";
		return index;
	}

}
