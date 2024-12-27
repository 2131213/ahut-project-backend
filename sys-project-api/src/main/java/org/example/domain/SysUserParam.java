package org.example.domain;
import lombok.Data;

@Data
public class SysUserParam {
    private Long curPage = 1L;
    private Long pageSize = 10L;
    private String nickName = "";
}