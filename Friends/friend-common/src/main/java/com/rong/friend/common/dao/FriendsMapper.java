package com.rong.friend.oauthserver.common.dao;

import com.rong.friend.oauthserver.common.model.Friends;

public interface FriendsMapper {
    int deleteByPrimaryKey(String id);

    int insert(Friends record);

    int insertSelective(Friends record);

    Friends selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Friends record);

    int updateByPrimaryKey(Friends record);

    String selectUserIdByPrimaryKey(String id);
}