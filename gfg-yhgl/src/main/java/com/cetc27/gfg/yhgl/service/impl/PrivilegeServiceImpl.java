package com.cetc27.gfg.yhgl.service.impl;

import com.cetc27.gfg.yhgl.dto.PageResult;
import com.cetc27.gfg.yhgl.entity.Privilege;
import com.cetc27.gfg.yhgl.repository.PrivilegeRepository;
import com.cetc27.gfg.yhgl.service.PrivilegeService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {

    @Autowired
    private PrivilegeRepository priRepository;

    @Override
    public Privilege addPrivilege(Privilege privilege) {
        if (privilege != null) {
            return priRepository.save(privilege);
        }else {

            throw new RuntimeException("权限不存在");
        }
    }

    @Override
    public PageResult<Privilege> findPage(Privilege privilege ,int pageNum, int pageSize) {
        //构建查询条件
        Specification<Privilege> querySpec = (Specification<Privilege>) (root, query, criteriaBuilder) -> {
            ArrayList<Predicate> predicates = Lists.newArrayList();
            if (privilege != null) {
                if (!StringUtils.isEmpty(privilege.getPriName())) {
                    predicates.add(
                            criteriaBuilder.like(root.get("priName"),
                                    "%"+privilege.getPriName()+"%"));
                }
                //比较时间
                if (null != privilege.getAddTime()) {
                /*predicates.add(
                        criteriaBuilder.like(root.get("priName"),
                                "%"+privilege.getPriName()+"%"));*/

                    Date startTime = privilege.getAddTime();
                    Date endTime = new Date(startTime.getTime() + 86400000);
                    predicates.add(criteriaBuilder
                            .greaterThanOrEqualTo(root.get("addTime"), startTime));
                    predicates.add(criteriaBuilder
                            .lessThan(root.get("addTime"), endTime));

                }
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        PageResult<Privilege> pageResult = new PageResult<>();
//        Page<Privilege> page = priRepository.findAll(PageRequest.of(pageNum - 1, pageSize));
        Page<Privilege> page = priRepository.findAll(querySpec, PageRequest.of(pageNum - 1, pageSize));
        long total = page.getTotalElements();
        List<Privilege> list = page.getContent();
        pageResult.setTotal(total);
        pageResult.setList(list);
        return pageResult;
    }

    @Override
    public List<Privilege> findAll() {
        return priRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteAll(List<String> selectIds) {

        //priRepository.deletePrivilegeByIdIn(selectIds);
        priRepository.deleteBySelectIds(selectIds);
    }

    @Override
    public void deleteById(String id) {
        priRepository.deleteById(id);
    }

    @Override
    public Privilege findOne(String id) {
        Optional<Privilege> byId = priRepository.findById(id);
        return byId.orElse(null);
    }

    @Override
    public void update(Privilege privilege) {

        privilege.setUpdateTime(new Date());
        priRepository.save(privilege);
    }

    @Override
    public List<Privilege> findByPriNames(List<String> priList) {

        return priRepository.findPrivilegeByPriNameIn(priList);
    }
}
