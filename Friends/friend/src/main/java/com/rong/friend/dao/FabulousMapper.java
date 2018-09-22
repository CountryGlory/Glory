package com.rong.friend.dao;

import com.rong.friend.model.Fabulous;

public interface FabulousMapper {
    int deleteByPrimaryKey(String id);

    int insert(Fabulous record);

    int insertSelective(Fabulous record);

    Fabulous selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Fabulous record);

    int updateByPrimaryKey(Fabulous record);
}