package com.cetc27.gfg.yhgl;

import com.cetc27.gfg.yhgl.entity.Privilege;
import com.cetc27.gfg.yhgl.repository.PrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class YHGLApplication {

    public static void main(String[] args) {
        SpringApplication.run(YHGLApplication.class, args);
    }
}
