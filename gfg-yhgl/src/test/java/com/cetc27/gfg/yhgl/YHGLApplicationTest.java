package com.cetc27.gfg.yhgl;

import com.cetc27.gfg.yhgl.entity.Privilege;
import com.cetc27.gfg.yhgl.entity.Role;
import com.cetc27.gfg.yhgl.entity.User;
import com.cetc27.gfg.yhgl.enums.UserStatus;
import com.cetc27.gfg.yhgl.repository.PrivilegeRepository;
import com.cetc27.gfg.yhgl.repository.RoleRepository;
import com.cetc27.gfg.yhgl.repository.UserRepository;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.xmlunit.util.Convert;

import java.time.*;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YHGLApplicationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PrivilegeRepository priRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testSaveUser() {
        /*Role role = roleRepository.findById("4028ab816ed9fca0016ed9fcac4b0000").get();
        User user = new User();
        user.setAddTime(new Date());
        user.setName("张三");
        user.setUserName("root");
        user.setPassword("123456");
        user.setStatus("true");
        user.setUserType("普通");
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoleList(roles);
        userRepository.save(user);*/
    }

    @Test
    public void testSaveEntity() {
        Privilege privilege = new Privilege();
        privilege.setPriName("下载浏览");
        privilege.setGrade(1);
        privilege.setAddTime(new Date());
        privilege.setUpdateTime(new Date());
        priRepository.save(privilege);
    }

    @Test
    public void testSaveRole() {

        Optional<Privilege> byId = priRepository.findById("4028ab816ed914dc016ed914e7520000");
        Privilege privilege = byId.get();
        Privilege privilege1 = priRepository.findById("4028ab816ed9fa8e016ed9fa9b670000").get();
        Role role = new Role();
        role.setAddTime(new Date());
        role.setUpdateTime(new Date());
        role.setRoleName("特殊用户");
        ArrayList<Privilege> list = new ArrayList<>();
        list.add(privilege);
        list.add(privilege1);
        role.setPrivilegeList(list);
        roleRepository.save(role);
    }

    @Test
    public void testDeleteRole() {
        roleRepository.deleteById("4028ab816ed922a2016ed922ad500000");
    }

    @Test
    public void testFindRole() {
        Role role = roleRepository.findById("4028ab816ed91fd0016ed91fdada0000").get();
        List<Privilege> privilegeList = role.getPrivilegeList();
        for (Privilege privilege : privilegeList) {
            System.out.println(privilege.getPriName());
        }
    }

    @Test
    public void testTime() {
        convert("637116821925947500");
    }

    private void convert(String str) {
        final long TICKS_AT_EPOCH = 621355968000000000L;
        final long TICKS_PER_MILLISECOND = 10000;
        TimeZone timeZone = TimeZone.getDefault();

        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.setTimeInMillis((Long.parseLong(str) - TICKS_AT_EPOCH) / TICKS_PER_MILLISECOND);
        calendar.setTimeInMillis(calendar.getTimeInMillis() - calendar.getTimeZone().getRawOffset());
        Date time = calendar.getTime();
        long time1 = time.getTime();
        System.out.println(time1);
    }

    @Test
    public void testFindPrivilege() {
        ArrayList<String> list = new ArrayList<>();
        list.add("系统设置");
        list.add("产品浏览");
        List<Privilege> priList = priRepository.findPrivilegeByPriNameIn(list);
        System.out.println(priList);
    }

    @Test
    public void testUpdateRole() {

        Role role = new Role();
        role.setId("4028ab816f1835b0016f1836dc6a0001");
        role.setRoleName("修改角色");
        roleRepository.save(role);

    }

    @Test
    @Transactional
    public void testUser() {

        User user = userRepository.findUserByUserNameEquals("测试用户1");
        Role role = user.getRoleList().get(0);
        System.out.println(role.getRoleName());
    }

    @Test
    public void testEnums() {
        System.out.println(UserStatus.LOGIN);
        System.out.println(UserStatus.values());
        System.out.println(UserStatus.valueOf("LOGIN"));
        System.out.println(UserStatus.valueOf("LOGOUT"));
        //System.out.println(UserStatus.valueOf("nulltest"));
    }
}
