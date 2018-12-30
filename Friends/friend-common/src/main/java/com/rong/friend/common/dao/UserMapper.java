package com.rong.friend.oauthserver.common.dao;

import com.rong.friend.oauthserver.common.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User selectBynameNumber(String nameNumber);
    
    User selectBynameNumberLogin(String nameNUmber);
    
    int selectCountBynameNumber(String nameNumber);
    
    int selectCountByEmail(String email);
}