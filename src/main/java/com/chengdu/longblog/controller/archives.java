package com.chengdu.longblog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chengdu.longblog.entity.Blog;
import com.chengdu.longblog.service.BlogService;
import com.fasterxml.jackson.databind.ser.std.ByteBufferSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;

@Controller
public class archives {

    @Autowired
    private BlogService blogService;

    // 在归档页面进行统计
    @GetMapping("/archives")
    public String getYears(Model model){

        // 获取到blog所有表的年
        List<String> years = blogService.getYears();
        HashMap<String, List<Blog>> blogMap = new HashMap<>();

        // 根据年份获取到博客
        for(String s : years){
            blogMap.put(s,blogService.getYearsByBlog(s));
        }

        // 获取一共有多少博客
        int blogCount = blogService.count();

        model.addAttribute("blogCount",blogCount);
        model.addAttribute("blogMap",blogMap);
        return "/archives";
    }

}
