package org.geilove.service;
import java.util.List;
import java.util.Map;

import  org.geilove.pojo.MoneySource;
import org.geilove.pojo.MoneysrcPinglun;
import org.geilove.requestParam.BackUpParam;
import org.geilove.response.CommonRsp;
import org.geilove.sqlpojo.PartHelpPojo;
public interface MoneySourceService {
        public   List<PartHelpPojo> getIhelpMen( Map<String,Object> map); //获取我帮助的人
        public   List<PartHelpPojo> getHelpMeMen( Map<String,Object> map);//获取帮助我的人

        //获取完整的，MoneySource 列表
        public  List<MoneySource> getGuyHelpMe(Map<String,Object>  map); //获取帮助我的人

        //根据MoneySourceID 获取其对应的评论
        public  List<MoneysrcPinglun> getMoneySourcePingLuns(Map<String,Object>  map); //获取一条"支持了"的评论列表

        //"支持了" 也就是用户捐钱一次
        public CommonRsp addBackupService(BackUpParam backUpParam); //这个要加入事务支持

        //对"支持了" 进行一次评论
        public  CommonRsp addOneComment(MoneysrcPinglun  moneysrcPinglun) ;

        //删除 "支持了" 的评论
        public  CommonRsp deleteOneComment(Long  moneySrcPingluniD );

}
