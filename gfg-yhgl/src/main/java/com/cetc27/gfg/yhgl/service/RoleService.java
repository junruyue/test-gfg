package com.cetc27.gfg.yhgl.service;

import com.cetc27.gfg.yhgl.dto.FindRoleResp;
import com.cetc27.gfg.yhgl.dto.PageResult;
import com.cetc27.gfg.yhgl.entity.Role;

import java.util.List;

public interface RoleService {

    /**
     * 添加角色
     * @param role
     * @return
     */
    Role addRole(Role role);

    /**
     * 查询角色
     * @param role
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResult<FindRoleResp> findPage(Role role, int pageNum, int pageSize);

    /**
     * 删除选中角色
     * @param selectIds
     */
    void delete(List<String> selectIds);

    /**
     * 删除单个角色
     * @param id
     */
    void deleteById(String id);

    /**
     * 修改角色
     * @param role
     */
    void update(Role role);

    Role findOne(String id);

    List<Role> findAll();

    List<Role> findByRoleNames(List<String> roleList);


}
