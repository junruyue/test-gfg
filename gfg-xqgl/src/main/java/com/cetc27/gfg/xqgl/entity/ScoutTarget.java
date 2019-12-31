package com.cetc27.gfg.xqgl.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Set;

@Data
@Embeddable
public class ScoutTarget {
    @Column(columnDefinition = "varchar(50) COMMENT'目标ID'")
    private String targetId;                            //目标id
    @Column(columnDefinition = "varchar(100) COMMENT'目标名称'")
    private String targetName;                          //目标名称
    @Column(nullable = true, columnDefinition = "int COMMENT'目标类型'")
    private int targetType;                             //目标类型：0:固定1:移动 2：海洋...
    @Column(columnDefinition = "varchar(50) COMMENT'国家地区'")
    private String countryRegion;                       //国家地区
    @Column(nullable = true, columnDefinition = "double COMMENT'中心纬度'")
    private double centreLat;                           //中心纬度
    @Column(nullable = true, columnDefinition = "double COMMENT'中心经度'")
    private double centreLon;                           //中心经度
    @Column(nullable = true, columnDefinition = "double COMMENT'左上纬度'")
    private double upperLeftLat;                        //左上纬度
    @Column(nullable = true, columnDefinition = "double COMMENT'左上经度'")
    private double upperLeftLon;                        //左上经度
    @Column(nullable = true, columnDefinition = "double COMMENT'右上纬度'")
    private double upperRightLat;                       //右上纬度
    @Column(nullable = true, columnDefinition = "double COMMENT'右上经度'")
    private double upperRightLon;                       //右上经度
    @Column(nullable = true, columnDefinition = "double COMMENT'右下纬度'")
    private double lowerRightLat;                       //右下纬度
    @Column(nullable = true, columnDefinition = "double COMMENT'右下经度'")
    private double lowerRightLon;                       //右下经度
    @Column(nullable = true, columnDefinition = "double COMMENT'左下纬度'")
    private double lowerLeftLat;                        //左下纬度
    @Column(nullable = true, columnDefinition = "double COMMENT'左下经度'")
    private double lowerLeftLon;                        //左下经度
}
