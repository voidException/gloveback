package org.geilove.controller;

/**
 * Created by aihaitao on 14/7/2017.
 */
import org.geilove.pojo.Account;
import org.geilove.pojo.UserAccount;
import org.geilove.response.CommonRsp;
import org.geilove.response.UserAccountRsp;
import org.geilove.service.AshipService;
import org.geilove.service.RegisterLoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/ship")
public class AShipNormalController {
    @Resource
    private AshipService ashipService;
    @Resource
    private RegisterLoginService rlService;

    @RequestMapping(value="/addEmployee.do",method=RequestMethod.POST)
    @ResponseBody
    public CommonRsp addEmployee(HttpServletRequest  addEmployeeParam){
        CommonRsp commonRsp=null;
        if (addEmployeeParam==null){
            commonRsp.setMsg("请求参数为空");
            commonRsp.setRetcode(2001);
            return  commonRsp;
        }

        String token=addEmployeeParam.getParameter("token");
        String accountuuid=addEmployeeParam.getParameter("accountuuid");
        String userUUID=addEmployeeParam.getParameter("userUUID"); //注册用户的uuid
        String buildrelationdescription=addEmployeeParam.getParameter("buildrelationdescription");
        // String userName=addEmployeeParam.getParameter("userName"); //员工姓名
        if(token.length()<33){
            commonRsp.setMsg("凭证不合法");
            commonRsp.setRetcode(2001);
            return commonRsp;
        }

        String userPassword=token.substring(0,32); //token是password和userID拼接成的。
        String useridStr=token.substring(32); //取得userid部分
        Long userid=Long.valueOf(useridStr).longValue();  //转换成long类型

        String passwdTrue=rlService.selectMD5Password(Long.valueOf(userid));
        if(!userPassword.equals(passwdTrue)){
            commonRsp.setRetcode(2001);
            commonRsp.setMsg("用户密码不对，非法");
            return commonRsp;
        }
        //1.查看UserAccount表，确定该用户是否有已经关联的企业
         Map<String,Object> map=new HashMap<String,Object>();
         map.put("userIdentity",accountuuid);
         map.put("buildRelationDescription",buildrelationdescription); //代表与企业关联
         //map.put("breakIf","no"); //代表没有断开关联

        UserAccount userAccount=null;
        try{
            userAccount= ashipService.getUserAccountBreakIf(map);
            if (userAccount==null){
                commonRsp.setMsg("员工尚未加入");
                commonRsp.setRetcode(2001);
                return  commonRsp;
            }
        }catch (Exception e){
             commonRsp.setMsg("增加员工出现异常");
             commonRsp.setRetcode(2001);
             return  commonRsp;
        }
        //2.如果breakIf 为yes，就提示用户取消与先前企业的关联
        if (userAccount.getBreakif().equals("yes")){
            commonRsp.setMsg("未取消与前企业的关联");
            commonRsp.setRetcode(2001);
            return  commonRsp;
        }
        //3.用户取消了与前东家的关联，就更新userAccount表的记录为新东家
        userAccount=new UserAccount();
        userAccount.setUseruuid(userUUID);
        userAccount.setBuildrelationdate(new Date());
        try { //根据accountuuid 进行更新，
            int upDateTag=ashipService.updateByAccountUUIDSelective(userAccount);
            if (upDateTag==0){
                commonRsp.setMsg("添加失败");
                commonRsp.setRetcode(2001);
                return commonRsp;
            }
        }catch (Exception e){
            commonRsp.setMsg("抛出异常");
            commonRsp.setRetcode(2001);
            return commonRsp;
        }
        commonRsp.setMsg("添加成功");
        commonRsp.setRetcode(2000);
        return  commonRsp;
    }

    @RequestMapping(value="/getMyEmployee.do",method=RequestMethod.POST)
    @ResponseBody
    public Object getMyEmployee(HttpServletRequest  getMyEmployeeParam){//这个是获取我的员工列表
       /*   userUUID:用户的uuid
       *    userType:person 或者business
       */
        UserAccountRsp  userAccountRsp=new UserAccountRsp();
        //1.直接从userAccount表根据userUUID 和breakIf 和buildRelationDescription 来查看我的员工

        String  userUUID=getMyEmployeeParam.getParameter("userUUID");
        String buildRelationDate= getMyEmployeeParam.getParameter("getMyEmployeeParam");

        Map<String,Object> map=new HashMap<>();
        map.put("userUUID",userUUID);
        map.put("breakIf","no");
        map.put("buildRelationDate",buildRelationDate); //用户和公司建立关联的时间
        map.put("page",0);
        map.put("pageSize",10);
        //map.put("和buildRelationDescription",buildRelationDescription);
        List<UserAccount> userAccountList=null;
        try{
            userAccountList=ashipService.getUserAccountList(map);
            if (userAccountList==null){
                userAccountRsp.setMsg("没有员工");
                userAccountRsp.setRetcode(2001);
                return  userAccountRsp;
            }
        }catch (Exception e){
            userAccountRsp.setMsg("抛出异常");
            userAccountRsp.setRetcode(2001);
            return  userAccountRsp;
        }
        //userAccountList不为空，有员工或者家人
        userAccountRsp.setLp(userAccountList);
        userAccountRsp.setMsg("成功");
        userAccountRsp.setRetcode(2000);
        return userAccountRsp;
    }

}
















