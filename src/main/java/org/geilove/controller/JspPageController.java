package org.geilove.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

//这个主要是为关于我们，常见问题，意见反馈等提供路由
@Controller
@RequestMapping(value="/pages")  
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
		System.out.print(tweetiD);

		String index="front/wechat";
		return index;
	}


}
