package com.cetc27.gfg.yhgl.config.service;

import com.cetc27.gfg.yhgl.entity.Privilege;
import com.cetc27.gfg.yhgl.entity.Role;
import com.cetc27.gfg.yhgl.enums.UserStatus;
import com.cetc27.gfg.yhgl.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.cetc27.gfg.yhgl.entity.User userInfo = userRepository.findUserByUserNameEquals(username);
        if (userInfo.getStatus() == UserStatus.LOGOUT) {
            Role role = userInfo.getRoleList().get(0);
            List<Privilege> priList = role.getPrivilegeList();
            List<GrantedAuthority> authorityList = new ArrayList<>();
            priList.forEach(privilege -> {
                GrantedAuthority authority = new SimpleGrantedAuthority(privilege.getPriName());
                authorityList.add(authority);
            });
            return new User(username, userInfo.getPassword(), authorityList);
        } else {
            throw new RuntimeException("用户已登录, 请先注销");
        }

    }


}
