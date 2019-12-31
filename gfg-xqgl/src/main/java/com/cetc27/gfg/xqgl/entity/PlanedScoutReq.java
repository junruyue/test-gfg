package com.cetc27.gfg.xqgl.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "GFG_PLAN_PLANEDSCOUTREQ")
public class PlanedScoutReq {
    @Id
    @Column(columnDefinition = "varchar(50) COMMENT'筹划需求Id'")
    private String planedReqId;
    @Column(columnDefinition = "varchar(50) COMMENT'原始需求Id'")
    private String originalReqId;
    @Column(columnDefinition = "varchar(100) COMMENT'需求名称'")
    private String reqName;
    @Column(columnDefinition = "varchar(50) COMMENT'卫星ID'")
    private String satId;
    @Column(columnDefinition = "varchar(20) COMMENT'传感器ID'")
    private String sensorId;
    @Column(columnDefinition = "varchar(20) COMMENT'传感器模式'")
    private String sensorMode;
    @Column(columnDefinition = "varchar(1000) COMMENT'传感器频段'")
    private String sensorBand;
    @Column(columnDefinition = "varchar(50) COMMENT'工作模式'")
    private String workMode;
    @Column(columnDefinition = "varchar(30) COMMENT'侦察任务类型'")
    private String scoutType;
    @Column(columnDefinition = "varchar(20) COMMENT'产品类型'")
    private String productType;
    @Column(columnDefinition = "varchar(30) COMMENT'产品格式'")
    private String productFormat;
    @Column(columnDefinition = "varchar(20) COMMENT'产品等级'")
    private String productLevel;
    @Column(columnDefinition = "varchar(50) COMMENT'分辨率'")
    private String resolution;
    @Column(columnDefinition = "varchar(20) COMMENT'侦察轨道号'")
    private String orbitId;
    @Column(columnDefinition = "double COMMENT'俯仰角'")
    private double pitchAngle;
    @Column(columnDefinition = "double COMMENT'側摆角'")
    private double rollAngle;
    @Embedded
    private ScoutTarget target;
    @Column(columnDefinition = "varchar(30) COMMENT'侦察开始时间'")
    private  String scoutStartTime;
    @Column(columnDefinition = "varchar(30) COMMENT'侦察结束时间'")
    private String scoutEndTime;
    @Column(nullable = true, columnDefinition = "BIT(2) COMMENT'是否定标'")
    private int isScale;
    @Column(nullable = true,columnDefinition = "BIT(2) COMMENT'是否裁切'")
    private int isCrop;
    @Column(nullable = true, columnDefinition = "int COMMENT'裁切尺寸'")
    private int cropSize;
    @Column(nullable = true, columnDefinition = "BIT(8) COMMENT'侦察次数'")
    private int scoutTimes;
    @Column(columnDefinition = "varchar(50) COMMENT'需求提交人'")
    private String submitter;
    @Column(columnDefinition = "varchar(50) COMMENT'需求提交单元'")
    private String reqUnit;
    @Column(columnDefinition = "blob COMMENT'描述'")
    private String discription;
}
