package org.geilove.dao;

import org.geilove.pojo.Public;

public interface PublicMapper {
    int deleteByPrimaryKey(Long publicid);

    int insert(Public record);

    int insertSelective(Public record);

    Public selectByPrimaryKey(Long publicid);

    int updateByPrimaryKeySelective(Public record);

    int updateByPrimaryKey(Public record);
}