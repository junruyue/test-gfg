package com.cetc27.gfg.yhgl.config;

import com.cetc27.gfg.yhgl.entity.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;

@Configuration
public class LoginConfig {

    //注销指定用户
    @Bean
    public SessionRegistry sessionRegistry() {

        return new SessionRegistryImpl();
    }
}
