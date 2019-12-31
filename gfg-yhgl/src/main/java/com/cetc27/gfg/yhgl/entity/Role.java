package com.cetc27.gfg.yhgl.entity;

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
@Table(name = "gfg_system_role")
public class Role {

    @Id
    @GenericGenerator(name = "entity-uuid",strategy = "uuid")
    @GeneratedValue(generator = "entity-uuid")
    private String id;

    @Column(length = 20, nullable = false)
    private String roleName;

    @Temporal(TemporalType.TIMESTAMP)
    private Date addTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @ManyToMany(mappedBy = "roleList")
    @JsonIgnoreProperties(value = "roleList")
    private List<User> userList;

    @ManyToMany
    @JoinTable(name = "role_privilege",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id"))
    @JsonIgnoreProperties(value = "roleList")
    private List<Privilege> privilegeList;

}
