package org.geilove.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by mfhj-dz-001-424 on 17/2/18.
 */
@Controller
@RequestMapping(value="")
public class WxController {
    @RequestMapping(value="/wx",method = RequestMethod.GET)
    public void checkProfiles(HttpServletRequest  request,HttpServletResponse response){
        System.out.println("wxToken");
        response.setContentType("type=text/html;charset=UTF-8");
        String s = "一堆字符串";
        String  echoStr=request.getParameter("echostr");
        try{
            //response.getWriter().write(s);
            response.getWriter().write(echoStr);
        }catch (Exception e){

        }
        return ;
    }
}
