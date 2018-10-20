package com.rong.friend.dao;

import java.util.List;

import com.rong.friend.model.ChatRecord;

import org.springframework.cache.annotation.Cacheable;

public interface ChatRecordMapper {

    @Cacheable(value = "thisredis", key = "'chatrecords_sendUserId_'+#sendUserId+'_recUserId_'+#recUserId+'_'+#start")
    List<ChatRecord> selectChatRecordsByUserId(String sendUserId, String recUserId, int start, int size)
            throws Exception;

    int insertChatRecord(ChatRecord chatRecord);
}