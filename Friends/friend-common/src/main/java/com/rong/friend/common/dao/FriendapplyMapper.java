package com.rong.friend.dao;

import com.rong.friend.model.Friendapply;

public interface FriendapplyMapper {
    int deleteByPrimaryKey(String id);

    int insert(Friendapply record);

    int insertSelective(Friendapply record);

    Friendapply selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Friendapply record);

    int updateByPrimaryKey(Friendapply record);

    int selectReadCount(String userId);
}