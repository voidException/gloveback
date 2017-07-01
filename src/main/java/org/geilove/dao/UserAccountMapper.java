package org.geilove.dao;

import org.geilove.pojo.UserAccount;

public interface UserAccountMapper {
    int deleteByPrimaryKey(Long useraccountid);

    int insert(UserAccount record);

    int insertSelective(UserAccount record);

    UserAccount selectByPrimaryKey(Long useraccountid);

    int updateByPrimaryKeySelective(UserAccount record);

    int updateByPrimaryKeyWithBLOBs(UserAccount record);

    int updateByPrimaryKey(UserAccount record);
}