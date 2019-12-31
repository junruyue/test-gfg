package com.cetc27.gfg.yhgl.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SysErrorLog {

    private String id;
    private String errorMessage;
    private int line;
    private Date errorTime;
}
