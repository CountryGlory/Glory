package com.rong.friend.dao;

import com.rong.friend.model.Look;

public interface LookMapper {
    int deleteByPrimaryKey(String id);

    int insert(Look record);

    int insertSelective(Look record);

    Look selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Look record);

    int updateByPrimaryKey(Look record);
}