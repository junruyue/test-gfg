package com.cetc27.gfg.yhgl.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "gfg_system_privilege")
public class Privilege {

    @Id
    @GenericGenerator(name = "entity-uuid",strategy = "uuid")
    @GeneratedValue(generator = "entity-uuid")
    private String id;

    @Column(length = 20, nullable = false)
    private String priName;

    private int grade;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date addTime;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

    @ManyToMany(mappedBy = "privilegeList")
    @JsonIgnoreProperties(value = "privilegeList")
    private List<Role> roleList;
}
