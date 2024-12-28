package org.example.web.wx_user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 创建时间：2024/12/27 12:32
 * 作者：Jun bu jian
 */
@Data
@TableName("wx_user")
public class WxUser {
    @TableId(type = IdType.AUTO)
    private Long userId;
    private String nickName;
    private String picture;
    private String phone;
    private String username;
    private String password;
    //0：启用 1：停用
    private String status;
}
