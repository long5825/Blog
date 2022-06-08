package com.chengdu.longblog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chengdu.longblog.entity.Blog;
import com.chengdu.longblog.entity.Tag;
import com.chengdu.longblog.entity.Type;
import com.chengdu.longblog.entity.User;
import com.chengdu.longblog.service.BlogService;
import com.chengdu.longblog.service.TagService;
import com.chengdu.longblog.service.TypeService;
import com.chengdu.longblog.util.MarkdownUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    List<Blog> blogs ;
    /*
    *       首页的显示信息
    * blogCurrent：博客的当前页
    * */
    @GetMapping("/")
    public String index(Model model,@RequestParam(defaultValue = "1") int blogCurrent){

        // 获取到type，显示到index页面
        Page<Type> typePage = new Page<>(1, 6);
        IPage<Type> page = typeService.page(typePage);
        model.addAttribute("typePage",page.getRecords());

        // 获取到tag，显示到index页面
        Page<Tag> tagPage = new Page<>(1, 10);
        IPage<Tag> page1 = tagService.page(tagPage);
        model.addAttribute("tagPage",page1.getRecords());

        // 获取到blog，显示到index页面
        Page<Blog> blogPage = new Page<>(blogCurrent,6);
        List<Blog> blogs = blogService.getBlogs(blogPage);

        // 将blog的文本进行编译
        for(Blog blog: blogPage.getRecords()){
            String content = blog.getContent();
            blog.setContent(MarkdownUtils.markdownToHtml(content));
        }
        model.addAttribute("blogPage",blogs);
        model.addAttribute("blogPages",blogPage);

        // 因为查询时降序的所以前面6个就是最新的blog，所以取出来，返回给前端
        Page<Blog> blogPage1 = new Page<>(1,6);
        IPage<Blog> page2 = blogService.page(blogPage1);
        model.addAttribute("newBlogs",page2.getRecords());

        return "/index";
    }

    //  全局查询功能
    @RequestMapping("/searchResult")
    public String searchResult(Blog blog,Model model,@RequestParam(defaultValue = "1") int current){
        Page<Blog> blogPage = new Page<>(current,6);
        QueryWrapper<Blog> blogQueryWrapper = new QueryWrapper<>();
        blogQueryWrapper.like("title",blog.getTitle())
                        .eq("del_tag",0)
                        .orderByDesc("tb.`id`");
        Page<Blog> blogs = blogService.searchBlog(blogPage, blogQueryWrapper);

        model.addAttribute("blogs",blogs);
        model.addAttribute("title",blog.getTitle());
        return "/searchResult";
    }

    //获取到某个博客，并且回显到页面
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model){

        QueryWrapper blogQueryWrapper = new QueryWrapper<>();
        blogQueryWrapper.eq("tb.id",id);
        Blog blog = blogService.getBlog(blogQueryWrapper);//通过id拿到blog

        Blog b = new Blog();
        BeanUtils.copyProperties(blog,b);   //拷贝一个blog，避免在原blog上做修改

        String content = b.getContent();
        b.setContent(MarkdownUtils.markdownToHtml(content));    //将markdown转为HTML

        // 每执行这个controller，说明这个博客被访问了，那么views（浏览次数）应该加一
        blog.setViews(blog.getViews()+1);
        blog.setUserId();
        blog.setTypeId();       //将一些userid和typeId拿到，避免更新时将原先数据清空
        blogService.updateById(blog);

        model.addAttribute("blog",b);
        return "/blog";
    }



}
