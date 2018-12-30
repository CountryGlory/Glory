package com.rong.friend.oauthserver.common.dao;

import com.rong.friend.oauthserver.common.model.Friendsgrouping;

public interface FriendsgroupingMapper {
    int deleteByPrimaryKey(String id);

    int insert(Friendsgrouping record);

    int insertSelective(Friendsgrouping record);

    Friendsgrouping selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Friendsgrouping record);

    int updateByPrimaryKey(Friendsgrouping record);
}