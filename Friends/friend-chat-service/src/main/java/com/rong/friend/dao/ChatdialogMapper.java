package com.rong.friend.dao;

import java.util.List;

import com.rong.friend.model.Chatdialog;

public interface ChatdialogMapper {
    int deleteByPrimaryKey(String id);

    int insert(Chatdialog record);

    int insertSelective(Chatdialog record);

    Chatdialog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Chatdialog record);

    int updateByPrimaryKey(Chatdialog record);
    
    List<Chatdialog> selectChatdialogByUserId(String userId);
    
    int updateTopChat(String id);
}