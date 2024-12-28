package org.example.web.goods.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.utils.ResultVo;
import org.example.web.goods.entity.Goods;
import org.example.web.goods.entity.GoodsParam;
import org.example.web.goods.entity.UpDownParam;
import org.example.web.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    //发布
    @PostMapping("/release")
    public ResultVo release(@RequestBody Goods goods) {
        //设置时间
        goods.setCreateTime(new Date());
        //设置状态
        goods.setType("0");
        if (goodsService.save(goods)) {
            return ResultVo.success("发布成功!");
        }
        return ResultVo.error("发布失败!");
    }

    @GetMapping("/list")
    public ResultVo getList(GoodsParam param) {
//构造分页对象
        IPage<Goods> page = new Page<>(param.getCurPage(), param.getPageSize());
//构造查询条件
        QueryWrapper<Goods> query = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(param.getGoodsName())) {
            query.lambda().like(Goods::getGoodsName, param.getGoodsName());
        }
        query.lambda().eq(Goods::getDeleteStatus, "0")
                .orderByDesc(Goods::getCreateTime);
        IPage<Goods> list = goodsService.page(page, query);
        return ResultVo.success("查询成功", list);
    }

    //上架下架
    @PostMapping("/upanddown")
    public ResultVo upanddown(@RequestBody UpDownParam param) {
        UpdateWrapper<Goods> query = new UpdateWrapper<>();
        query.lambda().set(Goods::getStatus, param.getStatus())
                .eq(Goods::getGoodsId, param.getGoodsId());
        if (goodsService.update(query)) {
            return ResultVo.success("设置成功!");
        }
        return ResultVo.error("设置失败!");
    }

    //推荐首页
    @PostMapping("/setIndex")
    public ResultVo setIndex(@RequestBody UpDownParam param) {
        UpdateWrapper<Goods> query = new UpdateWrapper<>();
        query.lambda().set(Goods::getSetIndex, param.getSetIndex())
                .eq(Goods::getGoodsId, param.getGoodsId());
        if (goodsService.update(query)) {
            return ResultVo.success("设置成功!");
        }
        return ResultVo.error("设置失败!");
    }

    //删除
    @PostMapping("/delete")
    public ResultVo delete(@RequestBody UpDownParam param) {
        UpdateWrapper<Goods> query = new UpdateWrapper<>();
        query.lambda().set(Goods::getDeleteStatus, "1")
                .eq(Goods::getGoodsId, param.getGoodsId());
        if (goodsService.update(query)) {
            return ResultVo.success("删除成功!");
        }
        return ResultVo.error("删除失败!");
    }
}