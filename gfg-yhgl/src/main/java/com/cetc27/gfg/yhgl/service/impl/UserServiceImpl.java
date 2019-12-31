package com.cetc27.gfg.yhgl.service.impl;

import com.cetc27.gfg.yhgl.dto.FindUserResp;
import com.cetc27.gfg.yhgl.dto.PageResult;
import com.cetc27.gfg.yhgl.entity.Role;
import com.cetc27.gfg.yhgl.entity.User;
import com.cetc27.gfg.yhgl.enums.UserStatus;
import com.cetc27.gfg.yhgl.repository.UserRepository;
import com.cetc27.gfg.yhgl.service.UserService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User addUser(User user) {
        if (user != null) {
            return userRepository.save(user);
        } else {
            throw new RuntimeException("用户不存在");
        }
    }

    @Override
    public PageResult<FindUserResp> findPage(User user, int pageNum, int pageSize) {
        Specification<User> querySpec = (Specification<User>) (root, query, criteriaBuilder) -> {
            ArrayList<Predicate> predicates = Lists.newArrayList();
            if (user != null) {

                //添加用户名查找条件
                if (!StringUtils.isEmpty(user.getUserName())) {
                    predicates.add(
                            criteriaBuilder.like(root.get("userName"),
                                    "%" + user.getUserName() + "%"));
                }
                //添加用户姓名查找条件
                if (!StringUtils.isEmpty(user.getName())) {
                    predicates.add(
                            criteriaBuilder.like(root.get("name"),
                                    "%" + user.getName() + "%"));
                }
                //添加用户是否在线查找条件
                UserStatus status = user.getStatus();
                if (status != null) {
                    if (status == UserStatus.LOGIN) {
                        predicates.add(
                                criteriaBuilder.equal(root.get("status"),
                                        UserStatus.valueOf("LOGIN")
                                ));
                    } else {

                        predicates.add(
                                criteriaBuilder.equal(root.get("status"),
                                        UserStatus.valueOf("LOGOUT")
                                ));
                    }
                }

            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        Page<User> page = userRepository.findAll(querySpec, PageRequest.of(pageNum - 1, pageSize));
        long total = page.getTotalElements();
        List<User> content = page.getContent();
        //开启线程, 更新每个用户状态
        List<FindUserResp> list = new ArrayList<>();
        for (User row : content) {
            FindUserResp userResp = new FindUserResp();
            userResp.setId(row.getId());
            userResp.setUserName(row.getUserName());
            userResp.setName(row.getName());
            userResp.setDepartment(row.getDepartment());
            userResp.setUserGroup(row.getUserGroup());
            userResp.setAddTime(row.getAddTime());
            userResp.setLastLoginTime(row.getLastLoginTime());
            if (row.getStatus() == UserStatus.LOGIN) {
                userResp.setStatus("登录");
            } else if (row.getStatus() == UserStatus.LOGOUT) {
                userResp.setStatus("未登录");
            }
            List<Role> roles = row.getRoleList();
            ArrayList<String> roleList = new ArrayList<>();
            for (Role role : roles) {
                roleList.add(role.getRoleName());
            }
            userResp.setRoleList(roleList);
            list.add(userResp);
        }
        PageResult<FindUserResp> pageResult = new PageResult<>();
        pageResult.setList(list);
        pageResult.setTotal(total);
        return pageResult;
    }

    @Override
    public User findOne(String id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteOne(String id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll(List<String> ids) {
        userRepository.deleteUsersByIdIn(ids);
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findUserByUserNameEquals(userName);
    }

}
