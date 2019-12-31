package com.cetc27.gfg.yhgl.dto;

import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Data
public class SaveUserReq {

    private String id;

    private String userName;

    private String password;

    private String repassword;

    private String name;

    private String department;

    private String status;

    private String group;

    private List<String> roleList;

    private Date addTime;

    private Date updateTime;

    private Date lastLoginTime;

}
