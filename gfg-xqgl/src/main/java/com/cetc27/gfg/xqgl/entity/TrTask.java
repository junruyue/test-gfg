package com.cetc27.gfg.xqgl.entity;

import lombok.Builder;
import lombok.Data;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "GFG_PLAN_TRTASK")
public class TrTask {
    @Id
    @Column(columnDefinition = "varchar(50) COMMENT'跟踪接收计划ID'")
    private String TrPlanId;
    @Column(columnDefinition = "varchar(30) COMMENT'生成时间'")
    private String createTime;
    @Column(columnDefinition = "varchar(30) COMMENT'天线号'")
    private String antennaId;
    @Column(columnDefinition = "varchar(50) COMMENT'卫星ID'")
    private String satId;
    @Column(columnDefinition = "varchar(20) COMMENT'轨道号'")
    private String orbitId;
    @Column(columnDefinition = "varchar(20) COMMENT'任务优先级'")
    private String taskPriority;
    @Column(columnDefinition = "varchar(30) COMMENT'跟踪开始时间'")
    private String trackStartTime;
    @Column(columnDefinition = "varchar(30) COMMENT'跟踪结束时间'")
    private String trackEndTime;
    @Column(columnDefinition = "varchar(30) COMMENT'接收开始时间'")
    private String receiveStartTime;
    @Column(columnDefinition = "varchar(30) COMMENT'接收结束时间'")
    private String receiveEndTime;
    @Column(columnDefinition = "varchar(30) COMMENT'传输开始时间'")
    private String transmitStartTime;
    @Column(columnDefinition = "varchar(30) COMMENT'传输结束时间'")
    private String transmitEndTime;
    @Column(columnDefinition = "longtext COMMENT'执行结果'")
    private String execResult;
    @Column(columnDefinition = "int COMMENT'关联的侦察任务数'")
    private int taskCount;
    @Column(columnDefinition = "varchar(1000) COMMENT'关联的侦察任务号'")
    private String taskId;
}
