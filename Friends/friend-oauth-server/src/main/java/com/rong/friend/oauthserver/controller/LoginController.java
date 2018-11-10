package com.rong.friend.oauthserver.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.rong.friend.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(description = "登录接口")
@RestController
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private TokenEndpoint tokenEndpoint;
    @Autowired
    @Qualifier("consumerTokenServices")
    private ConsumerTokenServices consumerTokenServices;

    @ApiOperation(value = "webapp登入接口", notes = "webapp登录接口")
    @PostMapping("/login")
    public Result<OAuth2AccessToken> login(@ApiParam(value = "账号", required = true) @RequestParam String username,
            @ApiParam(value = "密码", required = true) @RequestParam String password) {
        try {
            logger.info("login start ......");
            Map<String, String> parameters = new HashMap<>();
            parameters.put("username", username);
            parameters.put("password", password);
            parameters.put("grant_type", "password");
            Collection<GrantedAuthority> grantedAuthorities = new HashSet<>();
            grantedAuthorities.add(new SimpleGrantedAuthority("findcliend"));
            Authentication authentication = new UsernamePasswordAuthenticationToken("webapp", "webapp",
                    grantedAuthorities);
            ResponseEntity<OAuth2AccessToken> responseEntity = tokenEndpoint.postAccessToken(authentication,
                    parameters);
            logger.info("login end .......");
            return Result.ok().setData(responseEntity.getBody());
        } catch (InvalidGrantException e) {
            logger.error("login error 用户名密码不正确 ......");
            return Result.failure(100, "用户名密码不正确");
        } catch (Exception e) {
            logger.error("login error ......", e);
            return Result.failure(100, e.getMessage());
        }
    }

    @ApiOperation(value = "登出接口", notes = "登出接口")
    @GetMapping("/logouting")
    public Result<String> logouting(String accessToken) {
        try {
            logger.info("logout  start ......");
            consumerTokenServices.revokeToken(accessToken);
            logger.info("logout  end ......");
            return Result.ok("退出登入成功");
        } catch (Exception e) {
            logger.error("logout error ....", e);
            return Result.failure(100, e.getMessage());
        }
    }
}