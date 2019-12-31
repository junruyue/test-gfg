package com.cetc27.gfg.rwgl.repository;

import com.cetc27.gfg.rwgl.entity.CPYXX;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * 产品Repository
 *  -1.存储顺序: 产品源信息(产品的通用信息)+光学产品/QB产品(产品的特有信息)+产品实体(产品下的多个文件)
 *
 */
public interface CPYXXRepository extends JpaRepository<CPYXX, String> {
    /**
     * 保存产品信息
     */
    @Query(value = "insert into cpyxx values(?,?,?,?)", nativeQuery = true)
    public void saveCPXX(CPYXX cpyxx);
}
