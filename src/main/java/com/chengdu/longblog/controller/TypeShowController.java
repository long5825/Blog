package com.chengdu.longblog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chengdu.longblog.entity.Blog;
import com.chengdu.longblog.entity.Type;
import com.chengdu.longblog.service.BlogService;
import com.chengdu.longblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    /* 分类页的显示 */
    @GetMapping("/types/{typeId}/{currentPage}")
    public String Types(@PathVariable(value = "typeId",required = false) Long typeId,
                        @PathVariable(value = "currentPage",required = false) int currentPage,Model model){

        // 查询所有id
        List<Type> list = typeService.list();
        model.addAttribute("typeList",list);

        // 如果typeId为0，默认传一个值
        if(typeId == 0){
            typeId = 1L;    //typeId等于0，说明是在index页面进行访问的，所以让typeId指向第一个type
        }
        model.addAttribute("typeId",typeId);

        // 根据typeId进行查询博客
        Page<Blog> page = new Page<>(currentPage,6);
        QueryWrapper<Blog> blogQueryWrapper = new QueryWrapper<>();
        blogQueryWrapper.eq("tb.type_id",typeId);
        Page<Blog> blogPage1 = blogService.searchBlog(page, blogQueryWrapper);
        model.addAttribute("blogPage",blogPage1);

        return "types";
    }

}
