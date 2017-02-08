package org.geilove.service.impl;

import org.geilove.dao.ItemPgCommentMapper;
import org.geilove.dao.ItemProgressMapper;
import org.geilove.pojo.ItemPgComment;
import org.geilove.pojo.ItemProgress;
import org.geilove.service.ItemProgressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by mfhj-dz-001-424 on 17/2/8.
 */
@Service("itemProgress")
public class ItemProgressServiceImpl implements ItemProgressService {
    @Resource
    private ItemProgressMapper  itemProgressMapper;
    @Resource
    private ItemPgCommentMapper itemPgCommentMapper;

    //获取完整的，进度更新表
    public List<ItemProgress> getItemProgressUpdate(Map<String,Object> map){
        List<ItemProgress> itemProgressList= itemProgressMapper.getItemProgressList(map);
        return  itemProgressList;
    }
    //获取一条进度更新的评论
    public List<ItemPgComment> getOneItemProgressUpdateComments(Map<String,Object>  map){
        List<ItemPgComment>  itemPgCommentList=itemPgCommentMapper.getItemPgComments(map);
        return  itemPgCommentList;
    }
}
