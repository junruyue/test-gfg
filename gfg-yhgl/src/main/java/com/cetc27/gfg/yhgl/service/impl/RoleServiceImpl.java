package com.cetc27.gfg.yhgl.service.impl;

import com.cetc27.gfg.yhgl.dto.FindRoleResp;
import com.cetc27.gfg.yhgl.dto.PageResult;
import com.cetc27.gfg.yhgl.entity.Privilege;
import com.cetc27.gfg.yhgl.entity.Role;
import com.cetc27.gfg.yhgl.repository.RoleRepository;
import com.cetc27.gfg.yhgl.service.RoleService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;



    @Override
    public Role addRole(Role role) {

        if (role != null) {
            return roleRepository.save(role);
        }else {
            throw new RuntimeException("角色不存在！");
        }

    }

    @Override
    public PageResult<FindRoleResp> findPage(Role role, int pageNum, int pageSize) {
        Specification<Role> querySpec = (Specification<Role>) (root, query, criteriaBuilder) -> {
            ArrayList<Predicate> predicates = Lists.newArrayList();
            if (role != null) {
                if (!StringUtils.isEmpty(role.getRoleName())) {
                    predicates.add(
                            criteriaBuilder.like(root.get("roleName"),
                                    "%"+role.getRoleName()+"%"));
                }
                //比较时间
                if (null != role.getAddTime()) {

                    Date startTime = role.getAddTime();
                    Date endTime = new Date(startTime.getTime() + 86400000);
                    predicates.add(criteriaBuilder
                            .greaterThanOrEqualTo(root.get("addTime"), startTime));
                    predicates.add(criteriaBuilder
                            .lessThan(root.get("addTime"), endTime));

                }
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };

        //组合返回的实体类
        Page<Role> page = roleRepository.findAll(querySpec, PageRequest.of(pageNum - 1, pageSize));
        List<Role> list = page.getContent();
        List<FindRoleResp> pageContent = new ArrayList<>();
        for (Role row : list) {
            FindRoleResp roleResp = new FindRoleResp();
            roleResp.setId(row.getId());
            roleResp.setRoleName(row.getRoleName());
            roleResp.setAddTime(row.getAddTime());
            roleResp.setUpdateTime(row.getUpdateTime());
            List<Privilege> rolePriList = row.getPrivilegeList();
            ArrayList<String> priList = new ArrayList<>();
            for (Privilege privilege : rolePriList) {
                priList.add(privilege.getPriName());
            }
            roleResp.setPriList(priList);
            pageContent.add(roleResp);
        }
        PageResult<FindRoleResp> pageResult = new PageResult<>();

//        Page<Privilege> page = priRepository.findAll(PageRequest.of(pageNum - 1, pageSize));

        long total = page.getTotalElements();
        pageResult.setTotal(total);
        pageResult.setList(pageContent);
        return pageResult;
    }


    @Override
    @Transactional
    public void delete(List<String> selectIds) {

        roleRepository.deleteRolesByIdIn(selectIds);
    }

    @Override
    public void deleteById(String id) {

        roleRepository.deleteById(id);
    }

    @Override
    public void update(Role role) {

        role.setUpdateTime(new Date());
        roleRepository.save(role);
    }

    @Override
    public Role findOne(String id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public List<Role> findByRoleNames(List<String> roleList) {
        return roleRepository.findRolesByRoleNameIn(roleList);
    }
}
