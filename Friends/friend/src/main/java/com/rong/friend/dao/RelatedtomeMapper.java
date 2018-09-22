package com.rong.friend.dao;

import com.rong.friend.model.Relatedtome;

public interface RelatedtomeMapper {
    int deleteByPrimaryKey(String id);

    int insert(Relatedtome record);

    int insertSelective(Relatedtome record);

    Relatedtome selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Relatedtome record);

    int updateByPrimaryKey(Relatedtome record);
}