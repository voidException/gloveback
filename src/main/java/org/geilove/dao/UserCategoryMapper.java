package org.geilove.dao;

import org.geilove.pojo.UserCategory;

public interface UserCategoryMapper {
    int deleteByPrimaryKey(Long usercategoryid);

    int insert(UserCategory record);

    int insertSelective(UserCategory record);

    UserCategory selectByPrimaryKey(Long usercategoryid);

    int updateByPrimaryKeySelective(UserCategory record);

    int updateByPrimaryKey(UserCategory record);
}