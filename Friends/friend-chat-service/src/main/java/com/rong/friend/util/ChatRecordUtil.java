// package com.rong.friend.util;

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;

// import com.rong.friend.model.ChatRecord;

// /**
// * 聊天记录工具类
// * @author 荣
// *
// */
// public class ChatRecordUtil {

// @Autowired
// private RedisUtil redisUtil;

// /**
// * 将聊天记录加入到缓存中
// * @param chatRecord
// */
// public void setChatRecord(ChatRecord chatRecord) {
// if(redisUtil.exists(chatRecord.getSendUserId())) {
// HashMap<String, ArrayList<ChatRecord>> sendChatRecord=(HashMap<String,
// ArrayList<ChatRecord>>)redisUtil.get(chatRecord.getSendUserId());
// ArrayList<ChatRecord>
// chatRecordList=sendChatRecord.get(chatRecord.getToUserId());
// chatRecordList.add(chatRecord);
// sendChatRecord.put(chatRecord.getToUserId(), chatRecordList);
// redisUtil.set(chatRecord.getSendUserId(), sendChatRecord);
// }else {
// ArrayList<ChatRecord> chatRecordList=new ArrayList<>();
// chatRecordList.add(chatRecord);
// HashMap<String, ArrayList<ChatRecord>> sendChatRecord=new HashMap<>();
// sendChatRecord.put(chatRecord.getToUserId(), chatRecordList);
// redisUtil.set(chatRecord.getSendUserId(), sendChatRecord);
// }
// if(redisUtil.exists(chatRecord.getToUserId())) {
// HashMap<String, ArrayList<ChatRecord>> sendChatRecord=(HashMap<String,
// ArrayList<ChatRecord>>)redisUtil.get(chatRecord.getToUserId());
// ArrayList<ChatRecord>
// chatRecordList=sendChatRecord.get(chatRecord.getSendUserId());
// chatRecordList.add(chatRecord);
// sendChatRecord.put(chatRecord.getSendUserId(), chatRecordList);
// redisUtil.set(chatRecord.getToUserId(), sendChatRecord);
// }else {
// ArrayList<ChatRecord> chatRecordList=new ArrayList<>();
// chatRecordList.add(chatRecord);
// HashMap<String, ArrayList<ChatRecord>> sendChatRecord=new HashMap<>();
// sendChatRecord.put(chatRecord.getSendUserId(), chatRecordList);
// redisUtil.set(chatRecord.getToUserId(), sendChatRecord);
// }
// }

// /**
// * 获取聊天记录
// * @param sendUserId
// * @param toUserId
// * @param index
// * @return
// */
// public List<ChatRecord> getChatRecords(String sendUserId,String
// toUserId,Integer index){
// HashMap<String, ArrayList<ChatRecord>> sendChatRecord=(HashMap<String,
// ArrayList<ChatRecord>>)redisUtil.get(sendUserId);
// ArrayList<ChatRecord> chatRecordList=sendChatRecord.get(toUserId);
// ArrayList<ChatRecord> chatRecords=new ArrayList<>();
// for(int i=0;i<chatRecordList.size();i++) {
// if(i>=index || i<index+20) {
// chatRecords.add(chatRecordList.get(i));
// }
// }
// return chatRecords;

// }
// }
