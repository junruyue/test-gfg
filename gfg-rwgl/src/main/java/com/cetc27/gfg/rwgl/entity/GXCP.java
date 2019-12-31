package com.cetc27.gfg.rwgl.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "GFG_SJCP_GXCP")
@Data
public class GXCP implements Serializable {
    @Id
    @Column(columnDefinition = "nvarchar(100)")
    private String productId;                   //产品标识
    @Column(columnDefinition = "nvarchar(200)")
    private String productName;                 //产品名称
    @Column(columnDefinition = "int(10)")
    private int imageType;                   //影像类型
    @Column(columnDefinition = "nvarchar(30)")
    private String imageLevel;                  //影像级别

    @Column(nullable = true)
    private int width;                          //影像宽度
    @Column(nullable = true)
    private int height;                         //影像高度
    @Column(nullable = true)
    private float imageResolution;               //影像分辨率
    @Column(nullable = true)
    private float pixelSpacing;                  //水平分辨率
    @Column(nullable = true)
    private float lineSpacing;                    //垂直分辨率
    private String imageBand;                        //成像波段
    @Column(nullable = true)
    private int bandsCount;                        //成像波段数
    @Column(nullable = true)
    private int YXBDS;                              //？？？
    @Column(nullable = true)
    private int BDBTS;                              //???
    @Column(columnDefinition = "nvarchar(100)")
    private String coordinate;                      //坐标系统
    @Column(columnDefinition = "nvarchar(50)")
    private String mapProjection;                   //投影模型
    @Column(nullable = true)
    private float cloudAmount;                      //影像云量
    @Column(nullable = true)
    private float distortion;                       //内部畸变
    @Column(nullable = true)
    private float SNratio;                          //信噪比
    @Column(columnDefinition = "nvarchar(50)")
    private String multiSensorFusion;               //多传感器融合
    @Column(nullable = true)
    private float viewAngle;                        //側摆角
    @Column(nullable = true)
    private float sunAzimuth;                       //太阳高度角
    @Column(nullable = true)
    private float sunElevation;                     //太阳方位角
    @Column(columnDefinition = "nvarchar(10)")
    private String dataFormat;                      //数据格式
    @Column(columnDefinition = "nvarchar(100)")
    private String sceneId;                         //景ID
    @Column(columnDefinition = "nvarchar(10)")
    private String derection;                       //升降轨(DESC或ASCEND)
    @Column(nullable = true)
    private float positioningAccuracy;            //定位精度
    @Column(nullable = true)
    private float centreLat;                        //中心点纬度
    @Column(nullable = true)
    private float centreLon;                        //中心点经度
    @Column(nullable = true)
    private float centreAlt;                        //中心点高程
    @Column(nullable = true)
    private float upperLeftLat;                     //左上纬度
    @Column(nullable = true)
    private float upperLeftLon;                     //左上经度
    @Column(nullable = true)
    private float upperRightLat;                    //右上纬度
    @Column(nullable = true)
    private float upperRightLon;                    //右上经度
    @Column(nullable = true)
    private float lowerLeftLat;                     //左下纬度
    @Column(nullable = true)
    private float lowerLeftLon;                     //左下经度
    @Column(nullable = true)
    private float lowerRightLat;                    //右下纬度
    @Column(nullable = true)
    private float lowerRightLon;                    //右下经度
}
