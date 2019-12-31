package com.cetc27.gfg.xqgl.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "GFG_PLAN_ORIGINALSCOUTREQ")
public class OriginalScoutReq{
    @Id
    @Column(columnDefinition = "varchar(50) COMMENT'原始侦察需求ID'")
    private String originalReqId;
    @Column(columnDefinition = "varchar(100) COMMENT'原始侦察需求名称'")
    private String originalReqName;
    @Column(columnDefinition = "varchar(500) COMMENT'卫星ID，可填多个'")
    private String satId;
    @Column(columnDefinition = "varchar(200) COMMENT'传感器ID，可填多个'")
    private String sensorId;
    @Column(columnDefinition = "varchar(200) COMMENT'传感器模式,可填多个'")
    private String sensorMode;
    @Column(columnDefinition = "varchar(1000) COMMENT'传感器频段，电子星侦察填写'")
    private String sensorBand;
    @Column(columnDefinition = "varchar(500) COMMENT'工作模式，可填多个'")
    private String workMode;
    @Column(columnDefinition = "varchar(50) COMMENT'侦察任务类型'")
    private String scoutType;
    @Column(columnDefinition = "varchar(500)  COMMENT'侦察目标集合'")
    private String targets;
    @Column(columnDefinition = "varchar(30) COMMENT'开始时间'")
    private  String startTime;
    @Column(columnDefinition = "varchar(30) COMMENT'结束时间'")
    private String endTime;
    @Column(nullable = true, columnDefinition = "double COMMENT'最小俯仰角'")
    private double minPitchAngle;
    @Column(nullable = true, columnDefinition = "double COMMENT'最大俯仰角'")
    private double maxPitchAngle;
    @Column(nullable = true, columnDefinition = "double COMMENT'最小側摆角'")
    private double minRollAngle;
    @Column(nullable = true, columnDefinition = "double COMMENT'最大側摆角'")
    private double maxRollAngle;
    @Column(columnDefinition = "varchar(20) COMMENT'产品类型'")
    private String productType;
    @Column(columnDefinition = "varchar(30) COMMENT'产品格式'")
    private String productFormat;
    @Column(columnDefinition = "varchar(20) COMMENT'产品等级'")
    private String productLevel;
    @Column(columnDefinition = "varchar(50) COMMENT'分辨率'")
    private String resolution;
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
