package com.rong.friend;

import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import ch.qos.logback.core.db.dialect.DBUtil;

@SpringBootApplication
@MapperScan("com.rong.friend.dao")
public class FriendApplication {
	public static void main(String[] args) {
		SpringApplication.run(FriendApplication.class, args);
		System.out.println(new DBUtil().getStatusManager());
	}
}
