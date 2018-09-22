package com.rong.friend.util;

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
}
