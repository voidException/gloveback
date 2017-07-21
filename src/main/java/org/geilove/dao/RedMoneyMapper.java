package org.geilove.dao;

import org.geilove.pojo.RedMoney;

public interface RedMoneyMapper {
    int deleteByPrimaryKey(Long redmoneyid);

    int insert(RedMoney record);

    int insertSelective(RedMoney record);

    RedMoney selectByPrimaryKey(Long redmoneyid);

    int updateByPrimaryKeySelective(RedMoney record);

    int updateByPrimaryKey(RedMoney record);
}