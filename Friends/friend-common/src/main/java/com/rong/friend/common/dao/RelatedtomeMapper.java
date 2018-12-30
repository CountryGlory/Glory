package com.rong.friend.oauthserver.common.dao;

import com.rong.friend.oauthserver.common.model.Relatedtome;

public interface RelatedtomeMapper {
    int deleteByPrimaryKey(String id);

    int insert(Relatedtome record);

    int insertSelective(Relatedtome record);

    Relatedtome selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Relatedtome record);

    int updateByPrimaryKey(Relatedtome record);

    int selectReadCount(String userId);
}