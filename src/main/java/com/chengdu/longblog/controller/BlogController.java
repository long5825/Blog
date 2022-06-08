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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    private List<Long> tags;

    private static final int SIZE = 6;

    //分页
    Page<Blog> page ;
    /*
    *  博客页面分页显示博客信息
    *  使用了多表查询
    *  将type查询出来显示到前端页面
    *  */
    @RequestMapping ("/blogs")
    public String blogs(Model model,@RequestParam(defaultValue = "1") int current){

        page = new Page<>(current,SIZE);
        List<Blog> blogByTypeID = blogService.getBlogByTypeID(page);    //存放数据库中所有的blog信息

        model.addAttribute("blogs",blogByTypeID);
        model.addAttribute("page",page);

        // 拿到所有的Type
        List<Type> list = typeService.list();
        model.addAttribute("typeList",list);

        return "/admin/blogs";
    }


    /*
    *   实现翻页功能
    * */
    @GetMapping("/pageBlog")
    public String pageBlog(Model model,@RequestParam(defaultValue = "2") int current,HttpSession session){
        return "/admin/blogs";
    }


    /*
    * 通过逻辑删除进行实现删除blog
    * */
    @GetMapping("/deleteBlog")
    public String deleteBlog(long id,Model model){
        boolean b = blogService.removeById(id);
        model.addAttribute("deleteBlog",b);
        return "redirect:/admin/blogs";
    }


    /* 跳转到blogs-input页面 */
    @GetMapping("blogsInput")
    public String getBlog(Model model){
        List<Type> typeList = typeService.list();
        List<Tag> tagList = tagService.list();

        model.addAttribute("typeList",typeList);
        model.addAttribute("tagList",tagList);

        return "/admin/blogs-input";
    }


    /* 新增Blogs */
    @PostMapping("releaseBlog")
    public String releaseBlog(Blog blog,Model model,HttpSession session){

        //每新增一个Blog,那么Type对应的id也应该加一。
        Long typeId = blog.getType().getId();
        Type byId1 = typeService.getById(typeId); //id查询到type
        byId1.setBlogCount(byId1.getBlogCount()+1); //将type的BlogCount加一。
        typeService.updateById(byId1);  //更新到Type表中

        // 在session域中获取到用户实体类，赋值给Blog
        User user = (User) session.getAttribute("user");
        blog.setUser(user);
        blog.setUserId();   //拿到userid

        // 把创建时间和更新时间进行赋值
        blog.setCreateTime(new java.sql.Date(new java.util.Date().getTime()));
        blog.setUpdateTime(new Date(new java.util.Date().getTime()));

        //通过前端传输的id，查到具体的Type,赋值给Blog
        Type byId = typeService.getById(blog.getType().getId());
        blog.setType(byId);
        blog.setTypeId();

        // 将传输过来的tags字符串，进行截取，并进查询，然后返回tag集合，赋值到blog中
        tags = new ArrayList<>();
        String[] split = blog.getTagIds().split(",");
        for(int i=0;i<split.length;i++){
            tags.add(new Long(split[i]));
        }
        List<Tag> tagList = (List<Tag>) tagService.listByIds(this.tags);
        blog.setTags(tagList);

        // 把博客文本中的内容进行转化为html在持久化到数据库
        String content = blog.getContent();
        blog.setContent(MarkdownUtils.markdownToHtml(content));

        // 把type和Tags都赋值到blog中过后，再把blog进行持久化操作
        boolean save = blogService.save(blog);

        model.addAttribute("blogResult",save);

        return "redirect:/admin/blogs";

    }

    /*
    *   条件查询
    *
    * */
    @RequestMapping("/conditionQuery")
    public String conditionQuery(Blog blog,Model model,@RequestParam(defaultValue = "1") int current){

        QueryWrapper<Blog> blogQueryWrapper = new QueryWrapper<>();

        // 查询条件的追加
        String title = blog.getTitle();     //获取到title，在判断是否为空
        boolean isTitle = title.length() >0 && title != null; //判断是否为空
        boolean b = blog.getType().getId() == null ? false:true;
        System.out.println(b+"========");
        blogQueryWrapper
                .like(isTitle,"title",blog.getTitle())
                .eq(b,"type_id",blog.getType().getId())
                .eq(blog.isRecommend(),"recommend",blog.isRecommend())
                .exists("select * from t_blog tb inner join t_type tt on tb.type_id = tt.id");

        // 分页在t_blog表中查到所有满足条件的数据
        page = new Page<>(current,SIZE);
        IPage<Blog> pages = blogService.page(this.page, blogQueryWrapper);

        // 拿到所有的Type,传输给前端，用作下拉框
        List<Type> list = typeService.list();
        model.addAttribute("typeList",list);

        if(b == false){
            //拿到Type_id集合
            ArrayList<Long> typeIds = new ArrayList<>();
            for(Blog blog1: pages.getRecords()){
                typeIds.add(blog1.getTypeId());
            }
            ArrayList<Type> types1 = (ArrayList<Type>) typeService.listByIds(typeIds);
            for(int i=0;i<pages.getRecords().size();i++){
                for(int j=0;j<types1.size();j++){
                    if(pages.getRecords().get(i).getTypeId() == types1.get(j).getId()){
                        pages.getRecords().get(i).setType(types1.get(j));
                    }
                }
            }

        }
        //将前端传输过来的Type赋值给最终查询出来的结果集，这样就不用再到Type表进行查询获取name。
        if(b == true){
            for(Blog blog1 : pages.getRecords()){
                blog1.setType(blog.getType());
            }
        }

        model.addAttribute("page",pages);
        model.addAttribute("blogs",page.getRecords());
        return "/admin/blogs";
    }


}
