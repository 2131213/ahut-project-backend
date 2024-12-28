package org.example.web.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.web.goods.entity.Goods;
import org.example.web.goods.mapper.GoodsMapper;
import org.example.web.goods.service.GoodsService;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements
        GoodsService {

}