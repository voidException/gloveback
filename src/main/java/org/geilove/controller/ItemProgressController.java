package org.geilove.controller;

/**
 * Created by mfhj-dz-001-424 on 17/2/7.
 * 实现对救助推文更新进度的功能，进度列表，评论回复进度
 */
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.geilove.pojo.*;
import org.geilove.requestParam.ItemProgressListParam;
import org.geilove.response.ProgressUpdate;
import org.geilove.response.ProgressUpdateRsp;
import org.geilove.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@Controller
@RequestMapping("/itemprogress")
public class ItemProgressController {

    @RequestMapping(value="/backup",method=RequestMethod.POST)
    @ResponseBody
    public Object getProgressUpdateList(@RequestBody ItemProgressListParam updateListParam, HttpServletRequest request ){
        ProgressUpdateRsp progressUpdateRsp=new ProgressUpdateRsp();
        List<ProgressUpdate> listProgress=new ArrayList<>(); //进度更新及其评论列表
        Map<String,Object>  map=new HashMap<>();
        if (updateListParam==null){
            progressUpdateRsp.setRetcode(2001);
            progressUpdateRsp.setMsg("参数为空");
            progressUpdateRsp.setLp(null);
            return  progressUpdateRsp;
        }

        Integer page= updateListParam.getPage();
        Integer pageSize=updateListParam.getPageSize();
        String  timeStamp=updateListParam.getTimeStamp();
        Long  userBeHelpID=updateListParam.getUserIDBehelped();

        map.put("userBeHelpID",userBeHelpID);
        map.put("page",page);
        map.put("pageSize",pageSize);
        map.put("lastTime",timeStamp);

        List<ItemProgress>  lip=new ArrayList<>(); //单纯的更新列表
        try{
            lip=null;  //从数据库获取,待完成
            if (lip==null){
                progressUpdateRsp.setMsg("暂时没有动态更新哦");
                progressUpdateRsp.setRetcode(2001);
                progressUpdateRsp.setLp(null);
            }
        }catch (Exception e){
            progressUpdateRsp.setMsg("暂时没有动态更新哦");
            progressUpdateRsp.setRetcode(2001);
            progressUpdateRsp.setLp(null);
        }
        List<Long> itemProgressIDs=new ArrayList<>(); //存放获取的"进度更新的"iDs
        List<ProgressUpdate> progressUpdates=new ArrayList<>(); // 包含有"评论了"的 进度更新列表

        for(int i=0;i<lip.size();i++){
            itemProgressIDs.add(lip.get(i).getItemprogressid());
            ProgressUpdate  progressUpdate=new ProgressUpdate();
            progressUpdate.setItemProgress(lip.get(i)); //listProgress 中的任意一项的第一次组装，还需要第二次
            listProgress.add(progressUpdate);
        }
        progressUpdateRsp.setLp(listProgress);

        for (int i=0;i<itemProgressIDs.size();i++){
            Long  itemProgressiD=itemProgressIDs.get(i); //获取一个iD
            //用这个iD查找其对应的，评论回复列表
            List<ItemPgComment> pgCommentList=new ArrayList<>();

            Map<String,Object> map2=new HashMap<>();
            map2.put("itemProgressiD",itemProgressiD); //
            map2.put("page",0);
            map2.put("pageSize",20);

            try {
                pgCommentList=null; // 查找"进度更新" 的评论列表，连接数据库，待完成

            }catch (Exception e){
                pgCommentList=null;
            }
            //这样保险
            if(itemProgressiD==  listProgress.get(i).getItemProgress().getItemprogressid()){
                listProgress.get(i).setLmp(pgCommentList); //listProgress 中的任意一项的第二次组装
            }
            //如果不相等，那说明对应不上，这种情况怎么处理呢，怎么处理呢，不处理
            //listProgress.get(i).setLmp(pgCommentList); //listProgress 中的任意一项的第二次组装
        }
        progressUpdateRsp.setMsg("成功了");
        progressUpdateRsp.setRetcode(2000);

        return progressUpdateRsp;
    }

}


















