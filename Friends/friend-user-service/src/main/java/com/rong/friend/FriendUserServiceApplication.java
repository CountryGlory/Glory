
package com.rong.friend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.rong.friend.dao")
@EnableTransactionManagement
public class FriendUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FriendUserServiceApplication.class, args);
	}
	/*@Bean  
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {  
	  PropertySourcesPlaceholderConfigurer c = new PropertySourcesPlaceholderConfigurer();  
	  c.setIgnoreUnresolvablePlaceholders(true);  
	  return c;  
	}*/
}
