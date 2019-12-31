package com.cetc27.gfg.yhgl.config.handler;

import com.cetc27.gfg.yhgl.enums.UserStatus;
import com.cetc27.gfg.yhgl.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
@Component
public class LoginSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        //修改用户信息保存上一次登录时间
        User userDetail = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        com.cetc27.gfg.yhgl.entity.User user = userRepository.findUserByUserNameEquals(userDetail.getUsername());
        user.setLastLoginTime(new Date());
        user.setStatus(UserStatus.LOGIN);
        userRepository.save(user);

        //重定向另一个页面
        response.sendRedirect("/index");
    }
}
