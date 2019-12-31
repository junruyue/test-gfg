package com.cetc27.gfg.yhgl.controller;

import com.cetc27.gfg.yhgl.dto.RespResult;
import com.cetc27.gfg.yhgl.enums.UserStatus;
import com.cetc27.gfg.yhgl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private SessionRegistry sessionRegistry;

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index() {

        return "index";
    }

    //注销指定用户
    @RequestMapping("/remove")
    @ResponseBody
    public RespResult remove(String username) {

        RespResult result = new RespResult();
        try {

            List<Object> principals = sessionRegistry.getAllPrincipals();
            for (Object principal : principals) {
                if (principal instanceof User) {
                    UserDetails user = (UserDetails) principal;
                    if (username.equals(user.getUsername())) {
                        //1.删除session
                        List<SessionInformation> sessionInfos = sessionRegistry.getAllSessions(user, false);
                        if (sessionInfos != null && sessionInfos.size() > 0) {
                            for (SessionInformation sessionInfo : sessionInfos) {
                                sessionInfo.expireNow();
                            }
                        }
                        //2.修改用户状态
                        com.cetc27.gfg.yhgl.entity.User userInfo = userService.findByUserName(username);
                        userInfo.setStatus(UserStatus.LOGOUT);
                        userService.update(userInfo);
                        result.setCode(200);
                        result.setMessage(username + "用户注销成功");

                    }
                }
            }


        } catch (Exception e) {
            result.setCode(500);
            result.setMessage(username + "用户注销失败: " + e.getMessage());
        }
        return result;
    }
}
