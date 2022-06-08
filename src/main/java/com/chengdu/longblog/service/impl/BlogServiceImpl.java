package com.chengdu.longblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chengdu.longblog.entity.Blog;
import com.chengdu.longblog.mapper.BlogMapper;
import com.chengdu.longblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService  {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public List<Blog> getBlogByTypeID(Page<Blog> page) {
        return blogMapper.getBlogByTypeID(page);
    }

    @Override
    public List<Blog> getBlogs(Page<Blog> page){
        return blogMapper.getBlogs(page);
    }

    @Override
    public Page<Blog> searchBlog(Page<Blog> page,QueryWrapper<Blog> queryWrapper){
        return blogMapper.searchBlog(page,queryWrapper);
    }

    @Override
    public Blog getBlog(QueryWrapper queryWrapper){
        return blogMapper.getBlog(queryWrapper);
    }

    @Override
    public List<String> getYears(){
        return blogMapper.getYears();
    }

    @Override
    public List<Blog> getYearsByBlog(String year){
        return blogMapper.getYearsByBlog(year);
    }

    public Page<Blog> getTagByBlog(Page<Blog> page,QueryWrapper queryWrapper){
        return blogMapper.getTagByBlog(page,queryWrapper);
    }
}
