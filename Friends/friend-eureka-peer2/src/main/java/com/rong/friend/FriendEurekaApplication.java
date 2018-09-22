package com.rong.friend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableEurekaServer
public class FriendEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FriendEurekaApplication.class, args);
	}
}
