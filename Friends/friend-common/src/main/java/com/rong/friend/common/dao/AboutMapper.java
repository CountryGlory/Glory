package com.rong.friend.oauthserver.common.dao;

import com.rong.friend.oauthserver.common.model.About;

public interface AboutMapper {
    int insert(About record);

    int insertSelective(About record);
}