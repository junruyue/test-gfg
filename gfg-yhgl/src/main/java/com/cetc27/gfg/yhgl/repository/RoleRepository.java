package com.cetc27.gfg.yhgl.repository;

import com.cetc27.gfg.yhgl.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, String>, JpaSpecificationExecutor<Role> {

    /**
     * 删除选中角色
     * @param selectIds
     */
    void deleteRolesByIdIn(List<String> selectIds);

    /**
     * 根据角色名称查询角色
     * @param roleNames
     * @return
     */
    List<Role> findRolesByRoleNameIn(List<String> roleNames);
}
