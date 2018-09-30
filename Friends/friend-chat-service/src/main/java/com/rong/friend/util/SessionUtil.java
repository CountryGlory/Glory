package com.rong.friend.util;

import java.util.Set;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * Session工具类
 * @author 荣
 *
 */
public class SessionUtil {
	
	/**
	 * 设置SessionId
	 * @param key
	 * @param value
	 * @throws Exception
	 */
	public static void setSessionId(String key,String value)throws Exception {
		RedisTemplate< String, String> redisTemplate=new RedisTemplate<>();
		redisTemplate.opsForValue().set(key, value);
	}
	/**
	 * 获取sessionId
	 * @param key
	 * @return
	 */
	public static String getSessionId(String key)throws Exception {
		RedisTemplate< String, String> redisTemplate=new RedisTemplate<>();
		return redisTemplate.opsForValue().get(key);
	}
	/**
	 * 验证SessionId
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static boolean provingSession(String key,String value) throws Exception{
		RedisTemplate<String, String> redisTemplate=new RedisTemplate<>();
		return redisTemplate.opsForValue().get(key).equals(value);
	}
	/**
	 * 清楚key
	 * @param key
	 * @throws Exception
	 */
	public static void removeSessionId(String key)throws Exception{
		RedisTemplate< String, String> redisTemplate=new RedisTemplate<>();
		redisTemplate.delete(key);
	}
	
	/**
	 * 用户唯一登录，后登陆的踢掉前登录的
	 * @param userId
	 */
	public static void userOnly (String userId){
		RedisTemplate< String, String> redisTemplate=new RedisTemplate<>();
		
		Set<Object> sessionIds=redisTemplate.opsForHash().keys("session");
		for (Object object : sessionIds) {
			String sessionId=object.toString();
			String newUserId=redisTemplate.opsForValue().get(sessionId);
			if(userId.equals(newUserId)) {
				redisTemplate.opsForHash().keys("sessionId").remove(sessionId);
				redisTemplate.delete(sessionId);
			}
		}
	}
}
