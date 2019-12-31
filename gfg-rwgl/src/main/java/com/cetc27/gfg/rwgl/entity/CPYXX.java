package com.cetc27.gfg.rwgl.entity;

import ch.qos.logback.classic.db.names.ColumnName;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "GFG_SJCP_CPYXX")
public class CPYXX implements Serializable {
    @Id
    @GenericGenerator(name = "product-uuid",strategy = "uuid")
    @GeneratedValue(generator = "product-uuid")
    @Column(name="productId",columnDefinition = "varchar(100)")
    private String productId;                     //产品标识
    @Column(columnDefinition = "varchar(200)")
    private String productName;                    //产品名称
    @Column(columnDefinition = "int(10)")
    private int productType;                    //产品类型
    @Column(columnDefinition = "nvarchar(10)")
    private String productLevel;                   //产品级别
    @Column(columnDefinition = "nvarchar(100)")
    private String productSource;                            //产品来源
    @Column(columnDefinition = "nvarchar(10)")
    private String secretDegree;                            //产品密级
    @Column(columnDefinition = "timestamp(6)")
    private Timestamp productTime;                              //产品时间
    @Column(columnDefinition = "nvarchar(1000)")
    private String productRepresent;                            //产品描述
    @Column(columnDefinition = "nvarchar(50)")
    private String targetName;                              //目标名称
    @Column(columnDefinition = "nvarchar(50)")
    private String countryRegion;                             //国家地区
    @Column(columnDefinition = "nvarchar(50)")
    private String reqId;                                      //原始侦察需求标识
    @Column(columnDefinition = "nvarchar(50)")
    private String taskId;                                      //任务ID

    @Column(nullable = true)
    private float topLeftLat;          //左上纬度
    @Column(nullable = true)
    private float topLeftLon;          //左上经度

    @Column(nullable = true)
    private float topRightLat;         //右上纬度
    @Column(nullable = true)
    private float topRightLon;          //右上经度

    @Column(nullable = true)
    private float lowerRightLat;          //右下纬度
    @Column(nullable = true)
    private float lowerRightLon;          //右下经度

    @Column(nullable = true)
    private float lowerLeftLat;         //左下纬度
    @Column(nullable = true)
    private float lowerLeftLon;          //左下经度

    @Column(columnDefinition = "varchar(50)")
    private String catalog;                                 //生产编目
    @Column(columnDefinition = "varchar(50)")
    private String satName;                                 //卫星名称
    @Column(columnDefinition = "varchar(50)")
    private String sensorName;                              //传感器名称
    @Column(columnDefinition = "varchar(50)")
    private String sensorMode;                              //传感器模式

    @Column(nullable = true,columnDefinition = "BIT(1)")
    private boolean isImage;                                //是否影像类产品

    @Column(nullable = true,columnDefinition = "BIT(1)")
    private boolean outStatus;                              //输出状态
}
