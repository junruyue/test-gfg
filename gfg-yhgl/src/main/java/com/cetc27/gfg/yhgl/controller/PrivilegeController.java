package com.cetc27.gfg.yhgl.controller;

import com.cetc27.gfg.yhgl.dto.PageResult;
import com.cetc27.gfg.yhgl.dto.RespResult;
import com.cetc27.gfg.yhgl.entity.Privilege;
import com.cetc27.gfg.yhgl.service.PrivilegeService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/privileges")
@Api(value = "权限业务相关的api", tags = {"权限业务相关的controller"})
public class PrivilegeController {

    @Autowired
    private PrivilegeService privilegeService;

    //@PostMapping("/")
    @RequestMapping("/save")
    @ApiOperation(value = "添加权限", notes = "添加权限的接口")
    @ApiParam(name = "privilege", value = "用户权限", required = true)
    public RespResult<Privilege> save(@RequestBody Privilege privilege) {

        RespResult<Privilege> result = new RespResult<>();
        try {
            privilege.setAddTime(new Date());
            privilege.setUpdateTime(new Date());
            Privilege data = privilegeService.addPrivilege(privilege);
            result.setCode(200);
            result.setMessage("添加权限成功");
            result.setData(data);

        }catch (Exception e) {
            result.setCode(500);
            result.setMessage("添加权限失败: "+e.getMessage());

        }

        return result;
    }

//    @GetMapping("/{pageSize}/{pageNum}")
    @RequestMapping("/findPage")
    public RespResult<PageResult<Privilege>> findPage(@RequestBody(required = false) Privilege privilege, int pageNum, int pageSize) {
        RespResult<PageResult<Privilege>> result = new RespResult<>();
        try {
            PageResult<Privilege> data = privilegeService.findPage(privilege, pageNum, pageSize);
            result.setCode(200);
            result.setMessage("查询权限成功");
            result.setData(data);
        }catch (Exception e) {
            result.setCode(500);
            e.printStackTrace();
            result.setMessage("查询权限失败: "+e.getMessage());
        }

        return result;
    }

    @RequestMapping("/delete")
    public RespResult deleteAll(@RequestBody List<String> selectIds) {
        RespResult result = new RespResult();
        try {
            privilegeService.deleteAll(selectIds);
            result.setCode(200);
            result.setMessage("删除权限记录成功");
        }catch (Exception e) {
            result.setCode(500);
            result.setMessage("删除权限记录失败: "+e.getMessage());
        }
        return result;
    }

    @RequestMapping("/deleteOne")
    public RespResult deleteAll(String id) {
        RespResult result = new RespResult();
        try {
            privilegeService.deleteById(id);
            result.setCode(200);
            result.setMessage("删除权限记录成功");
        }catch (Exception e) {
            result.setCode(500);
            result.setMessage("删除权限记录失败: "+e.getMessage());
        }
        return result;
    }

    @RequestMapping("/findOne")
    public RespResult<Privilege> findOne(String id) {
        RespResult<Privilege> result = new RespResult<>();
        try {
            Privilege data = privilegeService.findOne(id);
            result.setCode(200);
            result.setMessage("查询权限记录成功");
            result.setData(data);
        }catch (Exception e) {
            result.setCode(500);
            result.setMessage("查询权限记录失败: "+e.getMessage());
        }
        return result;
    }

    @RequestMapping("/update")
    public RespResult findOne(@RequestBody Privilege privilege) {
        RespResult result = new RespResult();
        try {
            privilegeService.update(privilege);
            result.setCode(200);
            result.setMessage("权限记录修改成功");

        }catch (Exception e) {
            result.setCode(500);
            result.setMessage("权限记录修改失败: "+e.getMessage());
        }
        return result;
    }

    @RequestMapping("/findAll")
    public RespResult<List<Privilege>> findAll() {
        RespResult<List<Privilege>> result = new RespResult<>();
        try {
            List<Privilege> data = privilegeService.findAll();
            result.setCode(200);
            result.setMessage("权限记录查询成功");
            result.setData(data);

        }catch (Exception e) {
            result.setCode(500);
            result.setMessage("权限记录查询失败: "+e.getMessage());
        }
        return result;
    }
}
