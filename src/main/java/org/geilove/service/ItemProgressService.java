package org.geilove.service;

import org.geilove.pojo.ItemPgComment;
import org.geilove.pojo.ItemProgress;

import java.util.List;
import java.util.Map;

/**
 * Created by mfhj-dz-001-424 on 17/2/8.
 */
public interface ItemProgressService {
    //获取完整的，进度更新表
    public  List<ItemProgress> getItemProgressUpdate(Map<String,Object> map);
    //获取一条进度更新的评论
    public List<ItemPgComment> getOneItemProgressUpdateComments(Map<String,Object>  map);
}
