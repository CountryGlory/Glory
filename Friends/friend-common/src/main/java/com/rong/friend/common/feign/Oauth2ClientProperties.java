// package com.rong.friend.common.feign;

// import org.springframework.boot.context.properties.ConfigurationProperties;

// import lombok.Data;

// /**
//  * OAuth2客户端属性
//  */
// @Data
// @ConfigurationProperties(prefix = "friend.oauth.server")
// public class Oauth2ClientProperties {
//     private String id;
//     private String accessTokenUrl;
//     private String clientId;
//     private String clientSecret;
//     private String clientAuthenticationScheme;

//     public String getId() {
//         return this.id;
//     }

//     public void setId(String id) {
//         this.id = id;
//     }

//     public String getAccessTokenUrl() {
//         return this.accessTokenUrl;
//     }

//     public void setAccessTokenUrl(String accessTokenUrl) {
//         this.accessTokenUrl = accessTokenUrl;
//     }

//     public String getClientId() {
//         return this.clientId;
//     }

//     public void setClientId(String clientId) {
//         this.clientId = clientId;
//     }

//     public String getClientSecret() {
//         return this.clientSecret;
//     }

//     public void setClientSecret(String clientSecret) {
//         this.clientSecret = clientSecret;
//     }

//     public String getClientAuthenticationScheme() {
//         return this.clientAuthenticationScheme;
//     }

//     public void setClientAuthenticationScheme(String clientAuthenticationScheme) {
//         this.clientAuthenticationScheme = clientAuthenticationScheme;
//     }
// }