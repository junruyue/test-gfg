package com.cetc27.gfg.yhgl.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class FindRoleResp {
    private String id;

    private String roleName;

    @JsonFormat(pattern = "yyyy'年'MM'月'dd'日'HH'时'mm'分'ss'秒'",timezone = "GMT+8")
    private Date addTime;

    @JsonFormat(pattern = "yyyy'年'MM'月'dd'日'HH'时'mm'分'ss'秒'",timezone = "GMT+8")
    private Date updateTime;

    private List<String> priList;
}
