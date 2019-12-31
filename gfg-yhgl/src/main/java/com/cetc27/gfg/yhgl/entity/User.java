package com.cetc27.gfg.yhgl.entity;

import com.cetc27.gfg.yhgl.enums.UserStatus;
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
@Table(name = "gfg_system_user")
public class User {

    @Id
    @GenericGenerator(name = "entity-uuid", strategy = "uuid")
    @GeneratedValue(generator = "entity-uuid")
    private String id;

    @Column(length = 20, nullable = false, unique = true)
    private String userName;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 20)
    private String department;

    @Column(length = 20)
    private String userGroup;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date addTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginTime;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JsonIgnoreProperties(value = "userList")
    private List<Role> roleList;

}
