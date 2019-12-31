package com.cetc27.gfg.yhgl.repository;

import com.cetc27.gfg.yhgl.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PrivilegeRepository extends JpaRepository<Privilege, String>, JpaSpecificationExecutor<Privilege> {

    // 注意更新和删除是需要加事务的， 并且要加上 @Modify的注解
    /*@Modifying
    @Transactional
    @Query("delete from Students s where s.stuId in (?1)")
    void deleteBatch(List<Integer> ids);*/

    // 这个是通过spring data拼接关键字进行的操作
    void deletePrivilegeByIdIn(List<String> ids);

    //使用jpql进行批量删除
    @Modifying
    @Query("delete from Privilege p where p.id in (?1)")
    void deleteBySelectIds(List<String> ids);

    List<Privilege> findPrivilegeByPriNameIn(List<String> priNames);
}
