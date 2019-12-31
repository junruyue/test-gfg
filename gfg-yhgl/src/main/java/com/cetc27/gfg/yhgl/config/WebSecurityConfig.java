package com.cetc27.gfg.yhgl.config;


import com.cetc27.gfg.yhgl.config.handler.LoginSuccessHandlerImpl;
import com.cetc27.gfg.yhgl.config.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler loginSuccessHandler;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private SessionRegistry sessionRegistry;

    @Autowired
    @Qualifier("dataSource")//解决注入多个dataSource
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;



    @Bean
    public PasswordEncoder passwordEncoder() {

        return new MessageDigestPasswordEncoder("MD5");
    }

    @Bean
    public UserDetailsService userDetailsService() {
        //获取用户账号密码及权限信息
        return new UserDetailsServiceImpl();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //填写用户信息
        auth.userDetailsService(userDetailsService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //填写过滤认证
        //super.configure(http);
        http
                //权限控制: 重写securitymetadatasource
                /*.authorizeRequests()
                    .antMatchers()
                .and()*/
                .headers()
                .frameOptions()
                .disable()
                .and()
                .formLogin()                    //  定义当需要用户登录时候，转到的登录页面。
                .loginPage("/login.html")           // 设置登录页面
                .loginProcessingUrl("/login")  // 自定义的登录接口
                //.successForwardUrl("/index")
                .successHandler(loginSuccessHandler)
                .and()//登出处理
                .logout().permitAll().logoutSuccessHandler(logoutSuccessHandler).deleteCookies("JSESSIONID").and()
                .authorizeRequests()        // 定义哪些URL需要被保护、哪些不需要被保护
                .antMatchers("/login.html", "/css/*", "/images/*", "/js/*").permitAll()     // 设置所有人都可以访问登录页面
                .antMatchers("/users/**").hasAuthority("系统设置")
                .anyRequest()               // 任何请求,登录后可以访问
                .authenticated()
                .and()
                .csrf().disable();// 关闭csrf防护;

        //注销指定用户
        http.sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry).expiredUrl("/index");


        //记住我
        http.rememberMe()
                .tokenRepository(persistentTokenRepository())//设置操作表的Repository
                .tokenValiditySeconds(60 * 60 * 24 * 7)//设置记住我的时间为1天
                //.tokenValiditySeconds(30)
                .userDetailsService(userDetailsService);//设置userDetailsService

        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);

    }


}
