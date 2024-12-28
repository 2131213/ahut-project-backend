package org.example.web.goods.entity;

import lombok.Data;

//上下架参数
@Data
public class UpDownParam {
    private Long goodsId;
    private String status;
    private String setIndex;
}