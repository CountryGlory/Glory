package com.rong.friend.dao;

import com.rong.friend.model.About;

public interface AboutMapper {
    int insert(About record);

    int insertSelective(About record);
}