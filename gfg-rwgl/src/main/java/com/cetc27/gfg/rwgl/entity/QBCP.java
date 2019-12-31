package com.cetc27.gfg.rwgl.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "GFG_SJCP_QBCP")
public class QBCP implements Serializable {
    @Id
    @Column(columnDefinition = "nvarchar(50)")
    private String productId;
    @Column(columnDefinition = "nvarchar(50)")
    private String targetId;
    @Column(columnDefinition = "nvarchar(100)")
    private String targetName;
    @Column(columnDefinition = "nvarchar(50)")
    private String satName;
    @Column(columnDefinition = "nvarchar(50)")
    private String sensor;
    @Column(columnDefinition = "timestamp")
    private Timestamp scoutTime;
    @Column(columnDefinition = "blob")
    private String interpretationResult;
    @Column(columnDefinition = "timestamp")
    private Timestamp interpretationTime;
    @Column(nullable = true)
    private int personId;
    @Column(columnDefinition = "nvarchar(100)")
    private String personName;
    @Column(columnDefinition = "blob")
    private String summary;
    @Column(nullable = true)
    private float centreLon;
    @Column(nullable = true)
    private float centreLat;
}
