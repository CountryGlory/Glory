// package com.rong.friend;
//
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import
// org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import
// org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import
// org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import
// org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.core.userdetails.UserDetailsService;
//
// @Configuration
// @EnableWebSecurity
// public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
//
// @Override
// protected void configure(AuthenticationManagerBuilder auth) throws Exception
// {
// // TODO Auto-generated method stub
// super.configure(auth);
// }
//
// @Override
// protected AuthenticationManager authenticationManager() throws Exception {
// // TODO Auto-generated method stub
// return super.authenticationManager();
// }
//
// @Override
// protected UserDetailsService userDetailsService() {
// // TODO Auto-generated method stub
// return super.userDetailsService();
// }
//
// @Override
// protected void configure(HttpSecurity http) throws Exception {
// //表示所有的访问都必须进行认证请求处理后才能正常进行
//
// http.httpBasic().and().authorizeRequests().anyRequest().fullyAuthenticated();
//
// //设置session为无状态,提升操作效率
//
// http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
// super.configure(http);
// }
//
// }