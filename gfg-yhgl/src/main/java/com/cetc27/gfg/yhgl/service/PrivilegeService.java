package com.cetc27.gfg.yhgl.service;

import com.cetc27.gfg.yhgl.dto.PageResult;
import com.cetc27.gfg.yhgl.entity.Privilege;

import java.util.List;

public interface PrivilegeService {

    /**
     * 添加
     * @param privilege
     * @return
     */
    Privilege addPrivilege(Privilege privilege);

    /**
     * 分页查询(带查询条件)
     * @param privilege
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResult<Privilege> findPage(Privilege privilege ,int pageNum, int pageSize);

    List<Privilege> findAll();

    void deleteAll(List<String> selectIds);

    void deleteById(String id);

    Privilege findOne(String id);

    void update(Privilege privilege);

    List<Privilege> findByPriNames(List<String> priList);
}
