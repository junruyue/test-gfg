package com.cetc27.gfg.yhgl.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SysXmlLog {

    private String id;
    private String xmlName;
    private int isSuccess;
    private String errorReason;
    private String sendSys;//发送方
    private String receiveSys;//接收方
    private Date srTime;//收发时间
}
