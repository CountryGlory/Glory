package com.rong.friend.util;

import java.security.MessageDigest;

public class MD5Util {
	public static String generateMD5(String password) throws Exception{
		MessageDigest md5=MessageDigest.getInstance("MD5");
		byte[] result=md5.digest(password.getBytes());
		StringBuffer buffer=new StringBuffer();
		for(byte b:result){
			int number=b & 0xff;
			String str=Integer.toHexString(number);
			if(str.length()==1){
				buffer.append(0);
			}
			buffer.append(str);
		}
		return buffer.toString();
	}
}
