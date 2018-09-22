package com.rong.friend.dao;

import com.rong.friend.model.Setting;

public interface SettingMapper {
    int deleteByPrimaryKey(String id);

    int insert(Setting record);

    int insertSelective(Setting record);

    Setting selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Setting record);

    int updateByPrimaryKey(Setting record);
}