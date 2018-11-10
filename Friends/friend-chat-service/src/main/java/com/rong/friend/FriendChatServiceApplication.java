package com.rong.friend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.rong.friend.dao")
@EnableFeignClients(basePackages = "com.rong.friend")
@EnableTransactionManagement
public class FriendChatServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FriendChatServiceApplication.class, args);
	}
}
