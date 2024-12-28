package org.example.web.wx_user.entity;
import lombok.Data;


//登录成功后返回的数据
@Data
public class LoginVo {
    private Long userId;
    private String phone;
    private String nickName;
    private String token;
}