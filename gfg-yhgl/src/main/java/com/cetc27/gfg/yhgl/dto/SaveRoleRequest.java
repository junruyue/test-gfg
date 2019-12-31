package com.cetc27.gfg.yhgl.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SaveRoleRequest {

    private String id;

    private String roleName;

    private Date addTime;

    private List<String> priList;
}
