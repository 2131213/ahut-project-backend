package org.example.web.sys_user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang.StringUtils;
import org.example.web.sys_user.entity.SysUser;
import org.example.web.sys_user.entity.SysUserParam;
import org.example.web.sys_user.service.SysUserService;
import org.example.utils.ResultVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/sysUser")
public class SysUserController {
    @Resource
    private SysUserService sysUserService;
    //新增
    @PostMapping
    public ResultVo add(@RequestBody SysUser sysUser){
        if(sysUserService.save(sysUser)){
            return ResultVo.success("新增成功!");
        }
        return ResultVo.error("新增失败!");
    }
    //编辑
    @PutMapping
    public ResultVo edit(@RequestBody SysUser sysUser){
        if(sysUserService.updateById(sysUser)){
            return ResultVo.success("编辑成功!");
        }
        return ResultVo.error("编辑失败!");
    }
    //删除
    @DeleteMapping("/{userId}")
    public ResultVo delete(@PathVariable("userId") Long userId){
        if(sysUserService.removeById(userId)){
            return ResultVo.success("删除成功!");
        }
        return ResultVo.error("删除失败!");
    }
    //列表
    @GetMapping("/getList")
    public ResultVo getList(SysUserParam param){
        //构造查询条件
        QueryWrapper<SysUser> query = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(param.getNickName())){
            query.lambda().like(SysUser::getNickName,param.getNickName());
        }
        //构造分页对象
        IPage<SysUser> page = new Page<>(param.getCurPage(),param.getPageSize());
        //查询
        IPage<SysUser> list = sysUserService.page(page, query);
        return ResultVo.success("查询成功",list);
    }
}