package org.example.web.goods_category.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang.StringUtils;
import org.example.utils.ResultVo;
import org.example.web.goods_category.entity.GoodsCategory;
import org.example.web.goods_category.entity.GoodsCategoryParam;
import org.example.web.goods_category.entity.SelectType;
import org.example.web.goods_category.service.GoodsCategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class GoodsCategoryController {

    @Resource
    private GoodsCategoryService goodsCategoryService;

    //新增
    @PostMapping
    public ResultVo add(@RequestBody GoodsCategory goodsCategory) {
        boolean temp = goodsCategoryService.save(goodsCategory);
        if (temp) {
            return ResultVo.success("新增成功!");
        }
        return ResultVo.error("新增失败!");
    }

    //编辑
    @PutMapping
    public ResultVo edit(@RequestBody GoodsCategory goodsCategory) {
        if (goodsCategoryService.updateById(goodsCategory)) {
            return ResultVo.success("编辑成功!");
        }
        return ResultVo.error("编辑失败!");
    }

    //删除
    @DeleteMapping("/{categoryId}")
    public ResultVo delete(@PathVariable("categoryId") Long categoryId) {
        if (goodsCategoryService.removeById(categoryId)) {
            return ResultVo.success("删除成功!");
        }
        return ResultVo.error("删除失败!");
    }

    //列表
    @GetMapping("/list")
    public ResultVo list(GoodsCategoryParam param) {
        //构造查询条件
        QueryWrapper<GoodsCategory> query = new QueryWrapper<>();
        query.lambda().like(StringUtils.isNotEmpty(param.getCategoryName()),
                        GoodsCategory::getCategoryName, param.getCategoryName())
                .orderByDesc(GoodsCategory::getOrderNum);//desc 降序  asc 升序

        //构造分页对象
        IPage<GoodsCategory> page = new Page<>(param.getCurPage(), param.getPageSize());
        //查询数据
        IPage<GoodsCategory> list = goodsCategoryService.page(page, query);
        return ResultVo.success("查询成功", list);
    }

    //分类列表
    @GetMapping("/getSelectList")
    public ResultVo getSelectList() {
        //查询分类列表
        QueryWrapper<GoodsCategory> query = new QueryWrapper<>();
        query.lambda().orderByAsc(GoodsCategory::getOrderNum);
        List<GoodsCategory> list = goodsCategoryService.list(query);
        //存储小程序需要的类型
        List<SelectType> selectList = new ArrayList<>();
        //构造小程序需要的类型
        Optional.ofNullable(list).orElse(new ArrayList<>())
                .stream()
                .forEach(item -> {
                    SelectType type = new SelectType();
                    type.setLabel(item.getCategoryName());
                    type.setValue(item.getCategoryId());
                    selectList.add(type);
                });
        return ResultVo.success("查询成功", selectList);
    }

}
