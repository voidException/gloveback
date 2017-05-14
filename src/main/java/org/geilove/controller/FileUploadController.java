package org.geilove.controller;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.geilove.pojo.Cash;
import org.geilove.pojo.HelpInfo;
import org.geilove.service.CashService;
import org.geilove.service.impl.HelpInfoServiceImpl;
import org.geilove.util.ServerIP;
import org.geilove.vo.BaseVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.geilove.vo.UploadDemoVo;
import org.geilove.pojo.Tweet;
import org.geilove.response.CommonRsp;
import org.geilove.service.MainService;
import org.geilove.service.RegisterLoginService;
import org.geilove.util.CreateFileUtil;
import org.geilove.util.TimeUtil;
import org.geilove.util.TokenData;
import org.geilove.util.TestFun;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;
@Controller
@RequestMapping("/demo/upload")
public class FileUploadController {	 //发布推文,带图片的
    
	@Resource
	private MainService  mainService;
	@Resource
	private RegisterLoginService rlService;
	@Resource
    private HelpInfoServiceImpl  helpInfoService;

	@Resource
    private CashService cashService;

    @RequestMapping(value="/addman.do",method = RequestMethod.GET)
    public String addHelpMan(){

        String index="front/addHelpMan";
        return index;
    }

	@RequestMapping(value="/multiUpload",method=RequestMethod.POST)
	@ResponseBody
	public CommonRsp multiUpload(HttpServletRequest request)throws IllegalStateException, IOException{
        System.out.println("aaaaa");
		CommonRsp commonRsp=new CommonRsp();

        String ipAndport= ServerIP.getiPPort(); //http://172.16.32.52:8080

		String token=request.getParameter("token");			
		String userPassword=token.substring(0,32); //token是password和userID拼接成的。
		String useridStr=token.substring(32);		
		Long userid=Long.valueOf(useridStr).longValue();
        String passwdTrue=null;
        try{
            passwdTrue=rlService.selectMD5Password(Long.valueOf(userid));
        }catch (Exception e){
            commonRsp.setRetcode(2001);
            commonRsp.setMsg("发布推文出错");
            return commonRsp;
        }


		if(!userPassword.equals(passwdTrue)){
			commonRsp.setRetcode(2001);
			commonRsp.setMsg("用户密码不对，非法");
			return commonRsp;
		}
		List<String> imgPathArray=new ArrayList<String>();  //这个是图片的URL地址。http://wwww.geilove.org/path/weiboPhoto/.../88.png

		//创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求  
        if(multipartResolver.isMultipart(request)){  
            //转换成多部分request    
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            //取得request中的所有文件名  
            //System.out.println(multiRequest.getParameterNames().nextElement()); 
            Iterator<String> iter = multiRequest.getFileNames();
            //在本次上传中，这个部分路径是常量
            String constDirectory="/huzhuguanjia/weiboPhoto"; //tomcat配置的常量路径+weiboPhoto
            String timeDirectory=new TimeUtil().getNyDay(); //每天创建一个文件夹,时间路径
            String directory=constDirectory+timeDirectory+'/'+useridStr+'/';                       
            
            while(iter.hasNext()){  
                //记录上传过程起始时的时间，用来计算上传时间  
                //int pre = (int) System.currentTimeMillis();  
                //取得上传文件  
                MultipartFile file = multiRequest.getFile(iter.next());  
                if(file != null){  
                    //取得当前上传文件的文件名称  
                    String myFileName = file.getOriginalFilename();  
                    //如果名称不为"",说明该文件存在，否则说明该文件不存在  
                    if(myFileName.trim() !=""){  
                        //System.out.println(myFileName);  
                        //重命名上传后的文件名  
                        String originfileName = file.getOriginalFilename(); 
                        String millisFileName=new TimeUtil().getMil().toString();
                        String fileName=millisFileName+originfileName; //文件名，包含时间戳与原始文件名，确保不重复                       
                        String path = directory+fileName+".png";
                        //这里面方法更安全，待测试
                        if(CreateFileUtil.createDirectory(directory)==1){//目录已经存在或创建成功                
                        	  File localFile = new File(path);  
                              file.transferTo(localFile);
                              String needPath=ipAndport+"path/weiboPhoto"+timeDirectory+'/'+useridStr+'/'+fileName+".png"; //测试使用的
                              // String needPath="http://www.geilove.org/path/weiboPhoto"+timeDirectory+'/'+useridStr+'/'+fileName+".png";
                              imgPathArray.add(needPath);                             
                        }else{
                        	commonRsp.setMsg("创建磁盘目录失败");
                        	commonRsp.setRetcode(2005);
                        	return commonRsp;
                        }                              
                    }//if  
                }//if  
                //记录上传该文件后的时间  
                //int finaltime = (int) System.currentTimeMillis();   
            }//while             
        }// if因为是表单，所以一定会执行if里面，while循环发现无图片会跳出if外
		Tweet tweet=new Tweet();
		tweet.setUseridtweet(userid); //发布推文的userid
		tweet.setSourcemsgid(new Long(1));//1代表非转发
        String content=request.getParameter("content"); //获取推文的内容
        tweet.setMsgcontent(content); //放入推文内容到tweet中
        tweet.setTagid((byte)1 );
        tweet.setTopic(new Long(1));
        tweet.setBoxtimes(0);
        tweet.setCommenttimes(0);
        tweet.setOk(0);
        Date date=new Date();
        tweet.setPublishtime(date);
        tweet.setReportedtimes(0);
        tweet.setPublicsee((byte)1); //1代表可见
        tweet.setDeletetag((byte)1); //1代表未删除
        tweet.setVideoaddress(null); //推文只限制3张图
        tweet.setTweetbackupsix(0); //默认承诺0，代表无承诺
        String tweetuuid = UUID.randomUUID().toString();
        tweet.setBackupneight(tweetuuid); //tweet的UUID

        tweet.setBackupnine(request.getParameter("userNickname")); //用户的昵称
        tweet.setBackupten(request.getParameter("selfintroduce")); //用户的自我介绍
        tweet.setBackupeleven(request.getParameter("userphoto")); //用户的头像地址
        tweet.setCityname(request.getParameter("cityName")); //用户所在的城市
        //还有个发表用户的uuid由于表中无，暂时没加入
        //tweet.setuserUUIDTweet(request.getParameter("userUUIDTweet"));
        tweet.setPromise(null); //如果是救助一个人，则必须有文字
        tweet.setTweetbackupseven(null);
        tweet.setTweetbackupfour(1); //备用4等于1代表是一个普通的推文2代表的是救助     
        tweet.setTweetbackupfive(new Long(1)); //2代表党推文是救助时cash表
        for(int i=0;i<imgPathArray.size();i++){
        	if(tweet.getTweetbackupone()==null){
        		tweet.setTweetbackupone(imgPathArray.get(i));
        	}else if(tweet.getTweetbackuptwo()==null){
        		tweet.setTweetbackuptwo(imgPathArray.get(i));
        	}else{
        		tweet.setTweetbackupthree(imgPathArray.get(i));
        	}
        }  
        try{
        	Integer tag=mainService.addTweet(tweet);
        	if(tag!=1){ 
        		commonRsp.setRetcode(2001);
        		commonRsp.setMsg("推文插入数据库失败");
        		return commonRsp;
        	}
        }catch( Exception e){
        	System.out.println(e);
        }
        //插入数据库并判断是否成功
    	commonRsp.setMsg("发布成功");
    	commonRsp.setRetcode(2000);
    	return commonRsp;
	}//multiUpload


    /*******************************************************************************************************************
    ** 1.检查有没有权限  2.是否发表过2次的求助信息 3.tweet表增加。 4cash表增加。5.HelpInfo表增加信息
    *  先获取各种需要的数据，然后紧接着校验，最后再插入或者更新各种表
    * */
    @RequestMapping(value="/wapmultiUpload.do")
    @ResponseBody
    public ModelAndView jspmultiUpload(HttpServletRequest request)throws IllegalStateException, IOException{
       // System.out.println("jspaaaaa");
        String ipAndport= ServerIP.getiPPort(); //http://172.16.32.52:8080
        String token=request.getParameter("token");
        Map<String,String> model =new HashMap();
        //model.put("result", "sucess");
        ModelAndView modelAndView=new ModelAndView("front/addHelpManResult",model);

        String userPassword=token.substring(0,32); //token是password和userID拼接成的。
        String useridStr=token.substring(32);
        Long userid=Long.valueOf(useridStr).longValue();
        String passwdTrue=null;
        try{
            passwdTrue=rlService.selectMD5Password(Long.valueOf(userid));
        }catch (Exception e){
            model.put("result", "权限校验出现异常");
            return modelAndView;
        }
        // 1.检查有没有权限
        if(!userPassword.equals(passwdTrue)){
            model.put("result", "密码不对");
            return modelAndView;
        }

        HelpInfo helpInfo; //求助信息表
        Cash cash= new Cash();
        Tweet tweet=new Tweet();
        int  helpInfoTimes=0; //用户第一次发布求助信息，默认是0
        // 2.是否发表过2次求助信息

        //生成Tweet 表、Cash表、HelpInfo表的UUID
        String tweetUUID=UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        String cashUUID=UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        String helpeachotheruuid= UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        //从前端获取需要的数据
        String shouZhurenName= request.getParameter("shouZhurenName"); //受助人姓名
        String shouZhureniDentityNo= request.getParameter("shouZhureniDentityNo");//受助人身份证号
        String acceptMoneyName= request.getParameter("acceptMoneyName"); //收款人姓名
        String acceptMoneyPhone= request.getParameter("acceptMoneyPhone");//收款人电话
        String cityName=request.getParameter("cityName");//用户所在的城市
        String userName=request.getParameter("userName"); //用户昵称
        String photoUrl=request.getParameter("photoUrl"); //用户的头像地址
        String useruuid=request.getParameter("useruuid"); //根据uuid获取
        String chengnuoContent=request.getParameter("chengnuoContent");//我的承诺
        String targetMoney=request.getParameter("targetMoney");//目标金额
        String moneyTitle=request.getParameter("moneyTitle");//筹款标题
        String content=request.getParameter("content"); //获取推文的内容
        String selfIntroduce=request.getParameter("selfintroduce");
        String dateEndStr=request.getParameter("endDate");//结束日期

        //各种证明
        String[] proves =request.getParameterValues("prove");
        if (proves!=null){
            for (int k=0;k<proves.length;k++){
                System.out.println(proves[k]);
                if (proves[k]=="11"){
                    cash.setProveone(11);    //身份证明
                }
                if (proves[k]=="22"){
                    cash.setProvetwo(22);    //居委会证明
                }
                if (proves[k]=="33"){
                    cash.setProvethree(33);  //医院证明
                }
                if (proves[k]=="44"){
                    cash.setProvefour(44);   //贫困证明
                }
                if (proves[k]=="55"){
                    cash.setBackupfive(55);  //收款人关系证明
                }
            }
        }
        //承诺
        String[] promises =request.getParameterValues("chengnuoType");
        if (promises!=null){
            for(int i=0;i<promises.length;i++){
                System.out.println(promises[i]);
                if (promises[i]=="1"){
                    cash.setPromisetype(1);
                }
                if (promises[i]=="2"){
                    cash.setPromisetype(2);
                }
                if (promises[i]=="3"){
                    cash.setPromisetype(3);
                }
                else {
                    cash.setPromisetype(1); //默认值
                }

            }
        }
        try{
            int cashTag=cashService.cashInsert(cash); //
        }catch (Exception e){
            model.put("result", "新增资金记录记录抛出异常");
            return modelAndView;
        }
        //这里要对字段进行验证
        if (useruuid==null){
            model.put("result", "用户未登录");
            return modelAndView;
        }

        try{
            helpInfo= helpInfoService.selectRecord(useruuid);
        }catch (Exception e){
            model.put("result", "查询helpInfo抛出异常");
            return modelAndView;
        }
        if (helpInfo!=null && helpInfo.getCashtwouuid()!=null){//达到发布2次求助信息
            model.put("result", "超出发布次数");
            return modelAndView;
        }
        if (helpInfo!=null && helpInfo.getCashoneid()!=null){
            helpInfoTimes=1; //有过一次申请帮助
        }
        //校验是否上传了图片
        List<String> imgPathArray=new ArrayList<String>();
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断 request 是否有文件上传,即多部分请求
        if(multipartResolver.isMultipart(request)){
            //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            //取得request中的所有文件名
            //System.out.println(multiRequest.getParameterNames().nextElement());
            Iterator<String> iter = multiRequest.getFileNames();
            //在本次上传中，这个部分路径是常量
            String constDirectory="/huzhuguanjia/weiboPhoto"; //tomcat配置的常量路径+weiboPhoto
            String timeDirectory=new TimeUtil().getNyDay(); //每天创建一个文件夹,时间路径
            String directory=constDirectory+timeDirectory+'/'+useridStr+'/';

            while(iter.hasNext()){
                //记录上传过程起始时的时间，用来计算上传时间
                //int pre = (int) System.currentTimeMillis();
                //取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if(file != null){
                    //取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    //如果名称不为"",说明该文件存在，否则说明该文件不存在
                    if(myFileName.trim() !=""){
                        //System.out.println(myFileName);
                        //重命名上传后的文件名
                        String originfileName = file.getOriginalFilename();
                        String millisFileName=new TimeUtil().getMil().toString();
                        String fileName=millisFileName+originfileName; //文件名，包含时间戳与原始文件名，确保不重复
                        String path = directory+fileName+".png";
                        //这里面方法更安全，待测试
                        if(CreateFileUtil.createDirectory(directory)==1){//目录已经存在或创建成功
                            File localFile = new File(path);
                            file.transferTo(localFile);
                            String needPath=ipAndport+"path/weiboPhoto"+timeDirectory+'/'+useridStr+'/'+fileName+".png"; //测试使用的
                            // String needPath="http://www.geilove.org/path/weiboPhoto"+timeDirectory+'/'+useridStr+'/'+fileName+".png";
                            imgPathArray.add(needPath);
                        }else{
                            return new ModelAndView("front/login");
                        }
                    }//if
                }//if
                //记录上传该文件后的时间
                //int finaltime = (int) System.currentTimeMillis();
            }//while
        }// if因为是表单，所以一定会执行if里面，while循环发现无图片会跳出if外
        else{//必须有附带的图片上传
            model.put("result", "未上传相关证明");
            return modelAndView;
        }
        //3.tweet表增加。
        tweet.setBackupneight(tweetUUID); //tweet 的uuid
        tweet.setCashuuid(cashUUID); //cash 的uuid
        tweet.setUseridtweet(userid); //发布推文的userid
        tweet.setSourcemsgid(new Long(1));//1代表非转发
        tweet.setBackuptwelve(moneyTitle); //筹款标题
        tweet.setMsgcontent(content); //放入推文内容到tweet中
        tweet.setTagid((byte)1 );
        tweet.setTopic(new Long(1));
        tweet.setBoxtimes(0);
        tweet.setCommenttimes(0);
        tweet.setOk(0);
        Date date=new Date();
        tweet.setPublishtime(date);
        tweet.setReportedtimes(0);
        tweet.setPublicsee((byte)1); //1代表可见
        tweet.setDeletetag((byte)1); //1代表未删除
        tweet.setVideoaddress(null); //推文只限制3张图
        tweet.setTweetbackupsix(0); //默认承诺0，代表无承诺
        tweet.setBackupneight(tweetUUID); //tweet的UUID
        tweet.setBackupnine(userName); //用户的昵称
        tweet.setBackupten(selfIntroduce); //用户的自我介绍
        tweet.setBackupeleven(photoUrl); //用户的头像地址
        tweet.setCityname(cityName); //用户所在的城市
        tweet.setPromise(null); //如果是救助一个人，则必须有文字
        tweet.setTweetbackupseven(null);
        tweet.setTweetbackupfour(1); //备用4等于1代表是一个普通的推文2代表的是救助
        tweet.setTweetbackupfive(new Long(1)); //2代表党推文是救助时cash表

        for(int i=0;i<imgPathArray.size();i++){
            if(tweet.getTweetbackupone()==null){
                tweet.setTweetbackupone(imgPathArray.get(i));
            }else if(tweet.getTweetbackuptwo()==null){
                tweet.setTweetbackuptwo(imgPathArray.get(i));
            }else{
                tweet.setTweetbackupthree(imgPathArray.get(i));
            }
        }
        try{
            Integer tag=mainService.addTweet(tweet);
            if(tag!=1){
                model.put("result", "发布推文信息失败");
                return modelAndView;
            }
        }catch( Exception e){
            model.put("result", "发布推文信息抛出异常");
            return modelAndView;
        }

        //4.cash表增加一条信息。
        /*证明信息*/
        cash.setCashuuid(cashUUID);
        cash.setCashtweetuuid(tweetUUID);
        cash.setBehelpuserid(userid); //被帮助人的userid
        cash.setBehelpusernickname(userName);//受助人昵称
        cash.setBehelpusername(shouZhurenName); //受助人名字
        cash.setPromisemiaoshu(chengnuoContent); //承诺
        Integer targetMoneyInt=Integer.parseInt(targetMoney);
        cash.setTargetcash(targetMoneyInt); //目标金额
        Date dateStart=new Date(); //开始日期
        cash.setOpentime(dateStart); //账户开启时间
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date closeDate=sdf.parse(dateEndStr);
            cash.setClosetime(closeDate);
        }catch (ParseException e){
            //给个默认的下一个月的今天
        }
        cash.setCountstate( new Byte("1")); //关闭
        cash.setRealcash(0); //当前实际金额是0
        cash.setGetmoneytag(new Byte("1")); //1 不可以提取善款
        cash.setCashok(new Byte("1")); //没有达到
        cash.setSpendmoney(0); //支出金额
        cash.setSumaffirm(0); //证实的人数
        cash.setSumzhuanfa(0); //转发的人数
        cash.setSumattention(0);
        cash.setSumbackup(0); //支持的次数
        cash.setSummanbackup(0);//支持的人数
        cash.setAcceptmoneyman(acceptMoneyName);
        cash.setBackuptwo(acceptMoneyPhone); //资金接收人的电话
        cash.setBackupone(shouZhureniDentityNo); //受助人身份证号


        //5.HelpInfo 表增加，在创建用户的时候就增加用户对应该记录
        // 注意检查是第一次还是第二次
        helpInfo.setHelpeachotheruuid(helpeachotheruuid); //用户的UUID
        helpInfo.setUserid(userid); //被帮助人的id
        //这儿需要判定是第一次还是第二次
        if (helpInfoTimes==0){ //第一次
            helpInfo.setTweetoneuuid(tweetUUID);
            helpInfo.setCashoneuuid(cashUUID);
            helpInfo.setPromiseonetype(cash.getPromisetype());
            helpInfo.setPromiseonedesp(cash.getPromisemiaoshu());
        }
        if (helpInfoTimes==1){ //第二次
            helpInfo.setTweetoneuuid(tweetUUID);
            helpInfo.setCashtwouuid(cashUUID);  //第二次被帮助
            helpInfo.setPromisetwotype(cash.getPromisetype());
            helpInfo.setPromisetwodesp(cash.getPromisemiaoshu());
        }
        try {
            int helpInfoInsertTag=helpInfoService.insertRecord(helpInfo);
        }catch (Exception e){
            model.put("result", "新增救助信息抛出异常");
            return modelAndView;
        }

        model.put("result", "发布成功");
        return modelAndView;
   }
}


























