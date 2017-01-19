package org.geilove.dao;

import org.geilove.pojo.ItemProgress;

public interface ItemProgressMapper {
    int deleteByPrimaryKey(Long itemprogressid);

    int insert(ItemProgress record);

    int insertSelective(ItemProgress record);

    ItemProgress selectByPrimaryKey(Long itemprogressid);

    int updateByPrimaryKeySelective(ItemProgress record);

    int updateByPrimaryKey(ItemProgress record);
}