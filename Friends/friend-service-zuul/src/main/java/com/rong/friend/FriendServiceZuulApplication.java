package com.rong.friend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
// import org.springframework.session.data.redis.RedisFlushMode;
// import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

// @EnableRedisHttpSession(maxInactiveIntervalInSeconds = 216000, redisFlushMode
// = RedisFlushMode.IMMEDIATE)
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@EnableFeignClients
@EnableOAuth2Sso
public class FriendServiceZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(FriendServiceZuulApplication.class, args);
	}

	/**
	 * 解决全后端分离的跨域问题
	 */
	// @Bean
	// public CorsFilter corsFilter() {
	// final UrlBasedCorsConfigurationSource source = new
	// UrlBasedCorsConfigurationSource();
	// final CorsConfiguration config = new CorsConfiguration();
	// config.setAllowCredentials(true);
	// config.addAllowedOrigin("*");
	// config.addAllowedHeader("*");
	// // config.addAllowedMethod("OPTIONS");
	// // config.addAllowedMethod("HEAD");
	// // config.addAllowedMethod("GET");
	// // config.addAllowedMethod("PUT");
	// // config.addAllowedMethod("POST");
	// // config.addAllowedMethod("DELETE");
	// // config.addAllowedMethod("PATCH");
	// config.addAllowedMethod("*");
	// source.registerCorsConfiguration("/**", config);
	// return new CorsFilter(source);
	// }
}
