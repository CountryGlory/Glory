package com.rong.friend.dao;

import com.rong.friend.model.Friends;

public interface FriendsMapper {
    int deleteByPrimaryKey(String id);

    int insert(Friends record);

    int insertSelective(Friends record);

    Friends selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Friends record);

    int updateByPrimaryKey(Friends record);
}