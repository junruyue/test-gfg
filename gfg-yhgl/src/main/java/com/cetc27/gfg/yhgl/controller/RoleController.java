package com.cetc27.gfg.yhgl.controller;

import com.cetc27.gfg.yhgl.dto.FindRoleResp;
import com.cetc27.gfg.yhgl.dto.PageResult;
import com.cetc27.gfg.yhgl.dto.RespResult;
import com.cetc27.gfg.yhgl.dto.SaveRoleRequest;
import com.cetc27.gfg.yhgl.entity.Privilege;
import com.cetc27.gfg.yhgl.entity.Role;
import com.cetc27.gfg.yhgl.service.PrivilegeService;
import com.cetc27.gfg.yhgl.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PrivilegeService privilegeService;

    @RequestMapping("/add")
    public RespResult<Role> addRole(@RequestBody SaveRoleRequest saveRoleRequest) {
        //1.组装role实体
        Role role = new Role();
        role.setRoleName(saveRoleRequest.getRoleName());
        role.setAddTime(new Date());
        role.setUpdateTime(new Date());
        //1.1查询priList对应的主键
        List<Privilege> priList = privilegeService.findByPriNames(saveRoleRequest.getPriList());
        role.setPrivilegeList(priList);

        RespResult<Role> result = new RespResult<>();
        try {
            Role data = roleService.addRole(role);
            result.setCode(200);
            result.setMessage("添加角色成功：" + data.getRoleName());
            result.setData(data);
        } catch (Exception e) {
            result.setCode(500);
            result.setMessage("添加角色失败：" + e.getMessage());
        }
        return result;
    }

    @RequestMapping("/findPage")
    public RespResult<PageResult<FindRoleResp>> findPage(@RequestBody(required = false) Role role, int pageNum, int pageSize) {
        RespResult<PageResult<FindRoleResp>> result = new RespResult<>();
        try {
            PageResult<FindRoleResp> data = roleService.findPage(role, pageNum, pageSize);
            result.setCode(200);
            result.setMessage("查询成功: " + data.getTotal());
            result.setData(data);
        } catch (Exception e) {
            result.setCode(500);
            e.printStackTrace();
            result.setMessage("查询失败: " + e.getLocalizedMessage());
        }
        return result;
    }

    @RequestMapping("/delete")
    public RespResult delete(@RequestBody List<String> selectIds) {
        RespResult result = new RespResult();
        try {
            roleService.delete(selectIds);
            result.setCode(200);
            result.setMessage("删除成功: "+selectIds);
        }catch (Exception e){

            result.setCode(500);
            result.setMessage("删除失败: "+e.getMessage());
        }
        return result;
    }

    @RequestMapping("/deleteOne")
    public RespResult deleteOne(String id) {
        RespResult result = new RespResult();
        try {
            roleService.deleteById(id);
            result.setCode(200);
            result.setMessage("删除成功");
        }catch (Exception e){

            result.setCode(500);
            result.setMessage("删除失败: "+e.getMessage());
        }
        return result;
    }

    @RequestMapping("/update")
    public RespResult update(@RequestBody SaveRoleRequest srr) {
        //1.组装role实体
        Role role = new Role();
        role.setId(srr.getId());
        role.setRoleName(srr.getRoleName());
        role.setAddTime(srr.getAddTime());
        //1.1查询priList对应的主键
        List<Privilege> priList = privilegeService.findByPriNames(srr.getPriList());
        role.setPrivilegeList(priList);
        RespResult result = new RespResult();
        try {
            roleService.update(role);
            result.setCode(200);
            result.setMessage("修改成功");
        }catch (Exception e){

            result.setCode(500);
            result.setMessage("修改失败: "+e.getMessage());
        }
        return result;
    }

    @RequestMapping("/findOne")
    public RespResult<Role> findOne(String id) {
        RespResult<Role> result = new RespResult();
        try {
            Role data = roleService.findOne(id);
            result.setCode(200);
            result.setMessage("查询成功");
            result.setData(data);
        }catch (Exception e){

            result.setCode(500);
            result.setMessage("查询失败: "+e.getMessage());
        }
        return result;
    }

    @RequestMapping("/findAll")
    public RespResult<List<Role>> findAll() {
        RespResult<List<Role>> result = new RespResult<>();
        try {
            List<Role> data = roleService.findAll();
            result.setCode(200);
            result.setMessage("角色记录查询成功");
            result.setData(data);

        }catch (Exception e) {
            result.setCode(500);
            result.setMessage("角色记录查询失败: "+e.getMessage());
        }
        return result;
    }

}
