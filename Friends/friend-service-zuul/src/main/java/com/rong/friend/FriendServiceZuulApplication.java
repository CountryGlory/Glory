package com.rong.friend.oauthserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@EnableFeignClients(basePackages = { "com.rong.friend" })
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
