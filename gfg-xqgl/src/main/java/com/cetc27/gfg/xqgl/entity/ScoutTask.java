package com.cetc27.gfg.xqgl.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Data
@Entity
@Table(name = "GFG_PLAN_SCOUTTASK")
public class ScoutTask {
    @Id
    @Column(columnDefinition = "nvarchar(50) COMMENT'任务ID'")
    private String taskId;                                      //任务ID
    @Column(columnDefinition = "varchar(100) COMMENT'任务名称'")
    private String taskName;                                    //任务名称
    @Column(columnDefinition = "varchar(30) COMMENT'生成时间'")
    private String createTime;                               //生成时间
    @Column(columnDefinition = "varchar(50) COMMENT'卫星ID'")
    private String satId;                                       //卫星ID
    @Column(columnDefinition = "varchar(30) COMMENT'传感器ID'")
    private String sensorId;                                    //传感器ID
    @Column(columnDefinition = "varchar(30) COMMENT'传感器模式'")
    private String sensorMode;                                  //传感器模式
    @Column(columnDefinition = "varchar(30) COMMENT'分辨率'")
    private String resolution;                                  //分辨率
    @Column(columnDefinition = "varchar(30) COMMENT'过顶时间'")
    private String topTime;                                  //过顶时间
    @Column(columnDefinition = "varchar(30) COMMENT'侦察开始时间'")
    private String scoutStartTime;                           //侦察开始时间
    @Column(columnDefinition = "varchar(30) COMMENT'侦察结束时间'")
    private String scoutEndTime;                             //侦察结束时间
    @Column(nullable = true)
    private int stabilizTime;                                   //???
    @Column(columnDefinition = "varchar(30) COMMENT'工作模式'")
    private String workMode;                                    //工作模式
    @Column(columnDefinition = "varchar(30) COMMENT'俯仰角'")
    private String pitchAngle;                                  //俯仰角
    @Column(columnDefinition = "varchar(30) COMMENT'側摆角'")
    private String rollAngle;                                   //側摆角
    @Column(columnDefinition = "varchar(20) COMMENT'轨道号'")
    private String orbitNumber;                                 //轨道号
    @Column(nullable = true, columnDefinition = "int COMMENT'优先级'")
    private int priority;                                       //优先级
    @Column(nullable = true, columnDefinition = "int COMMENT'是否实传'")
    private int isRealtimeTrans;                             //是否实传
    @Column(columnDefinition = "BIT(2) COMMENT'是否定标'")
    private boolean isScale;                                    //是否定标
    @Embedded
    private ScoutTarget target;                                 //目标信息
    @Column(columnDefinition = "varchar(50) COMMENT'已筹划需求ID'")
    private String planedReqId;                                  //需求Id
    @Column(columnDefinition = "varchar(50) COMMENT'原始侦察需求ID'")
    private String oirginalReqId;
}
