package com.chengdu.longblog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chengdu.longblog.entity.Blog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BlogService extends IService<Blog> {

    List<Blog> getBlogByTypeID(Page<Blog> page);

    List<Blog> getBlogs(Page<Blog> page);

    Page<Blog> searchBlog(Page<Blog> page, QueryWrapper<Blog> queryWrapper);

    Blog getBlog(QueryWrapper queryWrapper);

    List<String> getYears();

    List<Blog> getYearsByBlog(String year);

    Page<Blog> getTagByBlog(Page<Blog> page,QueryWrapper queryWrapper);

}
