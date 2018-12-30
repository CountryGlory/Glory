package com.rong.friend.oauthserver.common.dao;

import java.util.List;

import com.rong.friend.oauthserver.common.model.ChatRecord;

public interface ChatRecordMapper {

    List<ChatRecord> selectChatRecordsByUserId(String sendUserId, String recUserId, int start, int size)
            throws Exception;

    int insertChatRecord(ChatRecord chatRecord);
}