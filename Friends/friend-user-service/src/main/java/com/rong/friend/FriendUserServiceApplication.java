
package com.rong.friend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

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
	/**
	 * 解决全后端分离的跨域问题
	 */
	@Bean
	public CorsFilter corsFilter() {		
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();		
		final CorsConfiguration config = new CorsConfiguration();		
		config.setAllowCredentials(true);		
		config.addAllowedOrigin("*");		
		config.addAllowedHeader("*");		
		//config.addAllowedMethod("OPTIONS");		
		//config.addAllowedMethod("HEAD");		
		//config.addAllowedMethod("GET");		
		//config.addAllowedMethod("PUT");		
		//config.addAllowedMethod("POST");		
		//config.addAllowedMethod("DELETE");		
		//config.addAllowedMethod("PATCH");		
		config.addAllowedMethod("*");		
		source.registerCorsConfiguration("*//**", config);	 
		return new CorsFilter(source);	 
	}
}
