package org.example.web.wx_user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.utils.ResultVo;
import org.example.web.wx_user.entity.LoginVo;
import org.example.web.wx_user.entity.WxUser;
import org.example.web.wx_user.service.WxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/wxUser")
public class WxUserController {
    @Resource
    private WxUserService wxUserService;

    //注册
    @PostMapping("/register")
    public ResultVo register(@RequestBody WxUser wxUser) {
        //判断用户是否被占用
        QueryWrapper<WxUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(WxUser::getUsername, wxUser.getUsername());
        //查询用户
        WxUser user = wxUserService.getOne(wrapper);
        if (user != null) {
            return ResultVo.error("用户被占用！重新填写！");
        }
        //密码加密
        String pwd =
                DigestUtils.md5DigestAsHex(wxUser.getPassword().getBytes());
        wxUser.setPassword(pwd);
        //保存
        if (wxUserService.saveOrUpdate(wxUser)) {
            return ResultVo.success("注册成功！");
        }
        return ResultVo.error("注册失败！");
    }

    //登录
    @PostMapping("/login")
    public ResultVo login(@RequestBody WxUser user) {
//构造查询条件
        QueryWrapper<WxUser> query = new QueryWrapper<>();
        query.lambda().eq(WxUser::getUsername, user.getUsername()).eq(WxUser::getPassword,
                DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        WxUser wxUser = wxUserService.getOne(query);
        if (wxUser != null) {
            if (wxUser.getStatus().equals("1")) {
                return ResultVo.error("您的账户被停用，请联系管理员!");
            }
//返回成功的数据
            LoginVo vo = new LoginVo();
            vo.setNickName(wxUser.getNickName());
            vo.setPhone(wxUser.getPhone());
            vo.setUserId(wxUser.getUserId());
            return ResultVo.success("登录成功", vo);
        }
        return ResultVo.error("用户密码或密码错误!");
    }
}