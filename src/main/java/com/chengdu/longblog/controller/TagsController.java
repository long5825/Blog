package com.chengdu.longblog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chengdu.longblog.entity.Blog;
import com.chengdu.longblog.entity.Tag;
import com.chengdu.longblog.service.BlogService;
import com.chengdu.longblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TagsController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TagService tagService;

    /*
    *       将tag表中的信息回显到前端
    *   tagId: tag表的id
    *   currentPage：当前页
    * */
    @GetMapping("/tags/{typeId}/{currentPage}")
    public String getTags(@PathVariable(value = "typeId",required = false) Long tagId,
                          @PathVariable(value = "currentPage",required = false) int currentPage, Model model){

        List<Tag> list = tagService.list();
        model.addAttribute("tag",list);

        // 如果tagId为0，默认传一个值
        if(tagId == 0){
            tagId = 1000L;    //tagId等于0，说明是在一些页面进行访问的，所以让tagId指向第一个type
        }
        model.addAttribute("tagId",tagId);

        // 根据tagId进行查询博客
        Page<Blog> page = new Page<>(currentPage,6);
        QueryWrapper blogQueryWrapper = new QueryWrapper<>();
        blogQueryWrapper.eq("tb.tag_id",tagId);
        Page<Blog> tagByBlog = blogService.getTagByBlog(page,blogQueryWrapper);
        model.addAttribute("blogPage",tagByBlog);

        // 记录tag标签有对少个博客
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tag_id",tagId);
        int blogCount = blogService.count(queryWrapper);
        model.addAttribute("tagCount",blogCount);


        return "/tags";
    }


}
