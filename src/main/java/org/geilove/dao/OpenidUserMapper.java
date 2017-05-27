package org.geilove.dao;

import org.geilove.pojo.OpenidUser;

public interface OpenidUserMapper {
    int insert(OpenidUser record);

    int insertSelective(OpenidUser record);
}