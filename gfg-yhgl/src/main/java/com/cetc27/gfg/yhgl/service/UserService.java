package com.cetc27.gfg.yhgl.service;

import com.cetc27.gfg.yhgl.dto.FindUserResp;
import com.cetc27.gfg.yhgl.dto.PageResult;
import com.cetc27.gfg.yhgl.entity.User;

import java.util.List;

public interface UserService {

    /**
     * 添加用户
     * @param user
     * @return
     */
    User addUser(User user);

    PageResult<FindUserResp> findPage(User user, int pageNum, int pageSize);

    User findOne(String id);

    void update(User user);

    void deleteOne(String id);

    void deleteAll(List<String> ids);

    User findByUserName(String userName);
}
