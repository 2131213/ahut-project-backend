package org.example.controller;


import org.example.utils.ResultVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/test")
public class HelloController {

    @GetMapping
    public ResultVo getList(){
        List<String> list = new ArrayList<>();
        list.add("新同学！");
        list.add("Java ！");
        list.add("Python！");
        list.add("C语言！");


        return  ResultVo.success("请求成功！",list);
    }
}
