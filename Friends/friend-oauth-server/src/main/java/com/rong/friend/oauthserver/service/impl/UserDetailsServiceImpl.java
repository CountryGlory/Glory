package com.rong.friend.oauthserver.service.impl;

import java.util.Collection;
import java.util.HashSet;

import com.rong.friend.model.Result;
import com.rong.friend.model.User;
import com.rong.friend.oauthserver.service.UserService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Result<User> result = userService.findByUsername(username);
        if (result.getCode() == 100) {
            throw new UsernameNotFoundException("用户：" + username + "不存在！");
        }
        User userVo = new User();
        BeanUtils.copyProperties(result.getData(), userVo);
        boolean enabled = true; // 可用性 :true:可用 false:不可用
        boolean accountNonExpired = true; // 过期性 :true:没过期 false:过期
        boolean credentialsNonExpired = true; // 有效性 :true:凭证有效 false:凭证无效
        boolean accountNonLocked = true; // 锁定性 :true:未锁定 false:已锁定
        Collection<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("findcliend"));
        org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(
                userVo.getId(), userVo.getPassword(), enabled, accountNonExpired, credentialsNonExpired,
                accountNonLocked, grantedAuthorities);
        return user;
    }

}