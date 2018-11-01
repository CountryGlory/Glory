package com.rong.friend.dao;

import java.util.List;

import com.rong.friend.model.ChatRecord;

import org.springframework.cache.annotation.Cacheable;

public interface ChatRecordMapper {

    List<ChatRecord> selectChatRecordsByUserId(String sendUserId, String recUserId, int start, int size)
            throws Exception;

    int insertChatRecord(ChatRecord chatRecord);
}