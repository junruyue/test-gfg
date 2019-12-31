package com.cetc27.gfg.yhgl.config.handler;

import com.cetc27.gfg.yhgl.enums.UserStatus;
import com.cetc27.gfg.yhgl.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User userDetail = (User) authentication.getPrincipal();
        com.cetc27.gfg.yhgl.entity.User user = userRepository.findUserByUserNameEquals(userDetail.getUsername());
        user.setStatus(UserStatus.LOGOUT);
        userRepository.save(user);

        //跳转登录页面
        response.sendRedirect("/login.html");
    }
}
