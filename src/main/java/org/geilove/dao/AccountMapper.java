package org.geilove.dao;

import org.geilove.pojo.Account;

public interface AccountMapper {
    int deleteByPrimaryKey(Long accountid);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Long accountid);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
}