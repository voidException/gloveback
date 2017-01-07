package org.geilove.controller;
//普通用户认证，爱心社青年志愿者认证认证，监督处认证，公益机构认证，都用这个类
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.geilove.service.PictureService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.geilove.pojo.Picture;
import org.geilove.pojo.User;
import org.geilove.response.CommonRsp;
import org.geilove.service.MainService;
import org.geilove.service.RegisterLoginService;
import org.geilove.util.CreateFileUtil;
import org.geilove.util.TimeUtil;
@Controller
@RequestMapping("/renzheng")
public class RenZhengController {	
    
	@Resource
	private MainService  mainService;
	@Resource
	private RegisterLoginService rlService;
	@Resource
	private PictureService  pictureService;
	
	@RequestMapping(value="/author",method=RequestMethod.POST)
	@ResponseBody
	public CommonRsp multiUpload(HttpServletRequest request)throws IllegalStateException, IOException{	
		CommonRsp commonRsp=new CommonRsp();
		String token=request.getParameter("token");		
		String userPassword=token.substring(0,32); //token是password和userID拼接成的。
		String useridStr=token.substring(32); //取得userid部分		
		Long userid=Long.valueOf(useridStr).longValue();  //转换成long类型
		String passwdTrue=rlService.selectMD5Password(Long.valueOf(userid));
		if(!userPassword.equals(passwdTrue)){
			commonRsp.setRetcode(2001);
			commonRsp.setMsg("用户密码不对，非法");
			return commonRsp;
		}
		List<String> imgPathArray=new ArrayList<String>(); //存放图片路径的

		//创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求  
        if(multipartResolver.isMultipart(request)){  
            //转换成多部分request    
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            //取得request中的所有文件名  
            /*System.out.println(multiRequest.getParameterNames().nextElement()); */
            Iterator<String> iter = multiRequest.getFileNames();
            //在本次上传中，这个部分路径是常量,必须到Tomcat目录下启动，才能看到效果
            String constDirectory="/hongqihui/photo/doctorImg/weiboPhoto"; //tomcat配置的常量路径+weiboPhoto
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
                              //System.out.println(directory);
                              //这里的path指的是配置里的名字,注意path前面没有/，客户端拼接url地址时应该加上
                              String needPath="path/weiboPhoto"+timeDirectory+fileName+".png"; 
                              imgPathArray.add(needPath);                             
                        }else{
                        	commonRsp.setMsg("创建磁盘目录失败");
                        	commonRsp.setRetcode(2001);
                        	return commonRsp;
                        }                              
                    }//if  
                }//if  
                //记录上传该文件后的时间  
                //int finaltime = (int) System.currentTimeMillis();   
            }//while             
        }// if因为是表单，所以一定会执行if里面，while循环发现无图片会跳出if外
        else{
			commonRsp.setMsg("没有文件");
			commonRsp.setRetcode(2001);
			return commonRsp;
		}
		//认证的照片地址要放入到picture表中
		Picture picture=new Picture(); //imgPathArray里的url地址放入picture中
		User user=new User(); //认证信息就是更新这个
        String tag=request.getParameter("tag"); //取得标识       
        if(tag.equals("2")){ //爱心社认证，认证不同，更新的字段也是不同的
        	String realName=request.getParameter("realName"); //真实姓名
        	String phoneNo=request.getParameter("phoneNo"); //手机号
        	String identitycard=request.getParameter("idno"); //身份证号
        	String school=request.getParameter("school");//社团所属的大学
        	String post=request.getParameter("post");//社团中的职位
        	String status=request.getParameter("status");//简单描述社团现状
        	user.setUserid(userid); //需要根据主键更新
        	user.setRealname(realName);
        	user.setBackupnine(phoneNo); //手机号
        	user.setIdentitycard(identitycard);//身份证号
        	user.setUniversity(school);
        	user.setBackupseven(post);//社团中的职务
        	user.setSelfintroduce(status);//社团简介
        	
        }else if(tag.equals("3") || tag.equals("1")){ //监督处认证,个人认证
			String realName=request.getParameter("realName"); //真实姓名
			String phoneNo=request.getParameter("phoneNo"); //手机号
			String identitycard=request.getParameter("idno"); //身份证号
			String post=request.getParameter("post");//监督人的社会职务
			String selfIntroduce=request.getParameter("selfIntroduce");//简单的介绍自己
			user.setUserid(userid); //需要根据主键更新
			user.setRealname(realName);
			user.setBackupnine(phoneNo); //手机号
			user.setIdentitycard(identitycard);//身份证号
			user.setBackupseven(post);//工作职务
			user.setSelfintroduce(selfIntroduce);//自我简介

        }else if(tag.equals("5")){ // 社会公益机构认证
        	//第二版本开通
			String realName=request.getParameter("realName"); //机构名称
			String phoneNo=request.getParameter("phoneNo"); //机构联系电话
			String backupTwo=request.getParameter("backupTwo"); //机构性质
			String backupThree=request.getParameter("backupThree"); //机构从事的领域
			String backupFive=request.getParameter("backupFive");//机构的人数规模，转换为数字
			String backupSix=request.getParameter("backupSix");//上一年的募捐金额，转换为数字
			String selfIntroduce=request.getParameter("selfIntroduce");//简介
			commonRsp.setMsg("暂未开通");
			commonRsp.setRetcode(2001);
			return commonRsp;
        }
		/*接下来更新user表*/
		try{
			//返回值是更新成功的条数
			int returnTga=rlService.updateUserSelective(user);//更新user表
			if (returnTga!=1){
				commonRsp.setMsg("认证更新信息失败");
				commonRsp.setRetcode(2001);
				return commonRsp;
			}

		}catch(Exception e){
			commonRsp.setMsg("认证更新信息失败");
			commonRsp.setRetcode(2001);
			return commonRsp;
		}
        //把照片放入相片表
    	for(int i=0;i<imgPathArray.size();i++){
    		if(picture.getPhotopathone()==null){
    			picture.setPhotopathone(imgPathArray.get(i));
    		}else if(picture.getPhotopathtwo()==null){
    			picture.setPhotopathtwo(imgPathArray.get(i));
    		}else if(picture.getPhotopaththree()==null){
    			picture.setPhotopaththree(imgPathArray.get(i));
    		}else if(picture.getPhotopathfour()==null){
    			picture.setPhotopathfour(imgPathArray.get(i));
    		}else if(picture.getPhotopathfive()==null){
    			picture.setPhotopathfive(imgPathArray.get(i));
    		}else if(picture.getPhotopathsix()==null){
    			picture.setPhotopathsix(imgPathArray.get(i));
    		}else if(picture.getPhotopathseven()==null){
    			picture.setPhotopathseven(imgPathArray.get(i));
    		}else{
    			picture.setPhotopatheight(imgPathArray.get(i));
    		}       		
    	}
    	String tagg=request.getParameter("tag");
    	picture.setTag(Integer.parseInt(tagg)); //标志了照片是有什么认证传入的
    	picture.setUserid(userid); //所属的用户id也放入
    	/*接下来新增一条记录到图片表*/
        try{
        	int returnValue=pictureService.insertRecord(picture);
        	if (returnValue!=1){
				commonRsp.setMsg("图片地址入库失败");
				commonRsp.setRetcode(2001);
				return commonRsp;
			}
		}catch(Exception e){
			commonRsp.setMsg("图片地址入库失败");
			commonRsp.setRetcode(2001);
			return commonRsp;

		}
        //插入数据库并判断是否成功
    	commonRsp.setMsg("发布成功");
    	commonRsp.setRetcode(2000);
    	return commonRsp;
	}//multiUpload
}


























