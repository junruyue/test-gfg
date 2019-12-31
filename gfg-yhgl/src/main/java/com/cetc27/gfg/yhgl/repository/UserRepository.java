package com.cetc27.gfg.yhgl.repository;

import com.cetc27.gfg.yhgl.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

    User findUserByUserNameEquals(String username);

    void deleteUsersByIdIn(List<String> ids);

}
