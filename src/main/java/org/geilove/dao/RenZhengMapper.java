package org.geilove.dao;

import org.geilove.pojo.RenZheng;

public interface RenZhengMapper {
    int deleteByPrimaryKey(Long renzhengid);

    int insert(RenZheng record);

    int insertSelective(RenZheng record);

    RenZheng selectByPrimaryKey(Long renzhengid);

    int updateByPrimaryKeySelective(RenZheng record);

    int updateByPrimaryKey(RenZheng record);
}