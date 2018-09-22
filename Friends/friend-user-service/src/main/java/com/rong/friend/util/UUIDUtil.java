package com.rong.friend.util;

import java.util.UUID;

/**
 * UUID工具类
 * @author 荣
 *
 */
public class UUIDUtil {

	public static String UUID32(){
		String uuid=UUID.randomUUID().toString().replace("-", "");
		return uuid;
	}
	
	public static String UUID64() {
		String uuid=UUID.randomUUID().toString().replace("-", "")+UUID.randomUUID().toString().replace("-", "");
		return uuid;
	}
}
