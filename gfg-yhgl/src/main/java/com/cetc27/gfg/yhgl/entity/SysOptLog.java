package com.cetc27.gfg.yhgl.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SysOptLog {

    private String id;
    private String operate;
    private Date optTime;
    private String optUser;
}
