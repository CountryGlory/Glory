
package com.rong.friend.oauthserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.rong.friend")
@MapperScan("com.rong.friend.common.dao")
@EnableTransactionManagement
public class FriendUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FriendUserServiceApplication.class, args);
	}
}
