package org.geilove.dao;

import org.geilove.pojo.HelpInfo;

public interface HelpInfoMapper {
    int deleteByPrimaryKey(Long helpeachotherid);

    int insert(HelpInfo record);

    int insertSelective(HelpInfo record);

    HelpInfo selectByPrimaryKey(Long helpeachotherid);

    int updateByPrimaryKeySelective(HelpInfo record);

    int updateByPrimaryKey(HelpInfo record);
}