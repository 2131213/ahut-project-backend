package org.example.web.goods_category.entity;

import lombok.Data;

@Data
public class GoodsCategoryParam {
    private Integer curPage; //当前页
    private Integer pageSize;//每页查询的条数
    private String categoryName;
}
