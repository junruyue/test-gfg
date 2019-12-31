package com.cetc27.gfg.yhgl.controller;

import com.cetc27.gfg.yhgl.dto.SaveUserReq;
import com.cetc27.gfg.yhgl.dto.FindUserResp;
import com.cetc27.gfg.yhgl.dto.PageResult;
import com.cetc27.gfg.yhgl.dto.RespResult;
import com.cetc27.gfg.yhgl.entity.Role;
import com.cetc27.gfg.yhgl.entity.User;
import com.cetc27.gfg.yhgl.enums.UserStatus;
import com.cetc27.gfg.yhgl.service.RoleService;
import com.cetc27.gfg.yhgl.service.UserService;
import com.cetc27.gfg.yhgl.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    @RequestMapping("/add")
    public RespResult<User> add(@RequestBody SaveUserReq aur) {
        //1.将aur转换为用户
        User user = new User();
        user.setUserName(aur.getUserName());
        user.setPassword(MD5Util.MD5Encode(aur.getPassword(), null));
        user.setName(aur.getName());
        user.setDepartment(aur.getDepartment());
        user.setUserGroup(aur.getGroup());
        //1.1存储角色信息
        List<Role> roleList = roleService.findByRoleNames(aur.getRoleList());
        user.setRoleList(roleList);
        user.setAddTime(new Date());
        user.setUpdateTime(new Date());
        user.setStatus(UserStatus.LOGOUT);
        //2.将user用户保存
        RespResult<User> result = new RespResult<>();
        try {
            User data = userService.addUser(user);
            result.setCode(200);
            result.setMessage("添加用户成功");
            result.setData(data);
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("添加用户失败: " + e.getMessage());
        }
        return result;
    }

    @RequestMapping("/findPage")
    public RespResult<PageResult<FindUserResp>> findPage(@RequestBody(required = false) User user, int pageNum, int pageSize) {

        //获取所有在线用户
        RespResult<PageResult<FindUserResp>> result = new RespResult<>();
        try {
            PageResult<FindUserResp> data = userService.findPage(user, pageNum, pageSize);
            result.setCode(200);
            result.setMessage("查询用户成功" + data.getTotal());
            result.setData(data);
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("查询用户失败: " + e.getMessage());
        }
        return result;
    }

    @RequestMapping("/findOne")
    public RespResult<User> findOne(String id) {
        RespResult<User> result = new RespResult<>();
        try {
            User data = userService.findOne(id);
            result.setCode(200);
            result.setMessage("查询用户成功: " + data.getName());
            result.setData(data);
        } catch (Exception e) {

            result.setCode(500);
            result.setMessage("查询用户失败: " + e.getMessage());
        }
        return result;
    }

    @RequestMapping("/update")
    public RespResult update(@RequestBody SaveUserReq aur) {
        RespResult result = new RespResult();
        try {
            //转为user
            User user = new User();
            user.setId(aur.getId());
            user.setUserName(aur.getUserName());
            int pwdLen = aur.getPassword().length();
            if (pwdLen >= 6 && pwdLen <= 20) {
                user.setPassword(MD5Util.MD5Encode(aur.getPassword(), null));
            } else {
                user.setPassword(aur.getPassword());
            }
            user.setStatus(UserStatus.valueOf(aur.getStatus()));
            user.setDepartment(aur.getDepartment());
            user.setUserGroup(aur.getGroup());
            user.setName(aur.getName());
            user.setAddTime(aur.getAddTime());
            user.setUpdateTime(new Date());
            user.setLastLoginTime(aur.getLastLoginTime());
            List<Role> roleList = roleService.findByRoleNames(aur.getRoleList());
            user.setRoleList(roleList);
            userService.update(user);
            result.setCode(200);
            result.setMessage("修改用户成功");
            result.setData(aur.getName());
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("修改用户失败: " + e.getMessage());
        }
        return result;
    }

    @RequestMapping("/deleteOne")
    public RespResult deleteOne(String id) {
        RespResult result = new RespResult();
        try {
            userService.deleteOne(id);
            result.setCode(200);
            result.setMessage("删除用户成功: 1");
        }catch (Exception e) {
            result.setCode(500);
            result.setMessage("删除用户失败: " + e.getMessage());
        }
        return result;
    }

    @RequestMapping("/deleteAll")
    public RespResult deleteAll(@RequestBody List<String> ids) {
        RespResult result = new RespResult();
        try {
            userService.deleteAll(ids);
            result.setCode(200);
            result.setMessage("删除用户成功: "+ids.size());
        }catch (Exception e) {
            result.setCode(500);
            result.setMessage("删除用户失败: " + e.getMessage());
        }
        return result;
    }
}
