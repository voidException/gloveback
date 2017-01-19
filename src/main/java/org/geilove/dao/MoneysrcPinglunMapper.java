package org.geilove.dao;

import org.geilove.pojo.MoneysrcPinglun;

public interface MoneysrcPinglunMapper {
    int deleteByPrimaryKey(Long moneysrcpinglunid);

    int insert(MoneysrcPinglun record);

    int insertSelective(MoneysrcPinglun record);

    MoneysrcPinglun selectByPrimaryKey(Long moneysrcpinglunid);

    int updateByPrimaryKeySelective(MoneysrcPinglun record);

    int updateByPrimaryKey(MoneysrcPinglun record);
}