package org.example.web.goods.entity;

import lombok.Data;

@Data
public class GoodsParam {
    private Long curPage = 1L;
    private Long pageSize = 10L;
    private String goodsName;
}